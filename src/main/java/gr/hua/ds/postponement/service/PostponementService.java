package gr.hua.ds.postponement.service;

import gr.hua.ds.postponement.controller.SecurityController;
import gr.hua.ds.postponement.entity.Office;
import gr.hua.ds.postponement.entity.Postponement;
import gr.hua.ds.postponement.entity.Reason;
import gr.hua.ds.postponement.entity.User;
import gr.hua.ds.postponement.repository.OfficeRepository;
import gr.hua.ds.postponement.repository.PostponementRepository;
import gr.hua.ds.postponement.repository.ReasonRepository;
import gr.hua.ds.postponement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PostponementService {

    @Autowired
    private PostponementRepository postponementRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReasonRepository reasonRepository;

    @Autowired
    private SecurityController securityController;


    // For  ROLE_ADMIN  only. No Checks
    public List<Postponement> retrieveAllPostponements() {
        System.out.println("GET:Retrieving ALL postponements for Admin user");
        return postponementRepository.findAll();   // status 200
    }


    // For ROLE_ADMIN  only. No Checks
    public ResponseEntity<Object> deletePostponement(int id) {
        Optional<Postponement> postponement = postponementRepository.findById(id);

        if (!postponement.isPresent()) {
            System.out.println("DELETE:Postponement with id  <" + id + ">  not found");
            throw new PostponementNotFoundException("Postponement with id  <" + id + ">  not found");  // status 404
        }

        postponementRepository.deleteById(id);
        System.out.println("DELETE:Postponement with id  <" + id + ">  deleted");
        return ResponseEntity.noContent().build();   // status 204
        //return ResponseEntity.ok().build();   // status 200
    }



    public List<Postponement> retrievePolitisPostponements() {

        // Get the currentUserName from authentication object and retrieve his postponements
        String currentUserName = securityController.getCurrentUserName();

        // if currentUserName is NULL throw exception
        if (currentUserName == null || currentUserName.length() == 0) {
            System.out.println("ERROR:GET:Retrieving postponements for politis with username  <" + currentUserName + ">. The username can not be NULL.");
            throw new InternalApplicationErrorException("Something went wrong....  Call to method retrievePolitisPostponements with currentUserName NULL.");  // status 500
        }

        // Fetch the postponements for the politi
        System.out.println("GET:Retrieving postponements for politis with username  <" + currentUserName +">");
        User userIn = new User();
        //userIn.setUsername("stratos");
        userIn.setUsername(currentUserName);
        return postponementRepository.findAllByUserIn(userIn);  // status 200   [] empty list if zero (0) records
    }



    public List<Postponement> retrieveOfficePostponements() {

        // Get the currentUserName from authentication object and retrieve his the postponements
        String currentUserName = securityController.getCurrentUserName();

        // if currentUserName is NULL throw exception
        if (currentUserName == null || currentUserName.length() == 0) {
            System.out.println("ERROR:GET:Retrieving postponements for office of username  <" + currentUserName + ">. The username can not be NULL.");
            throw new InternalApplicationErrorException("Something went wrong....  Call to method retrieveOfficePostponements with currentUserName NULL.");  // status 500
        }
        // Get the optional entity for the current user
        Optional<User> optionalUser = userRepository.findById(currentUserName);
        if (!optionalUser.isPresent()) {
            System.out.println("ERROR:PUT:retrieveOfficePostponements. User details with id  <" + currentUserName + ">  not found");
            throw new UserNotFoundException("Retrieving postponements for office... User details with id  <" + currentUserName + ">  not found");  // status 404
        }
        // create object for currentUser
        User currentUser = optionalUser.get();

        System.out.println("GET:Retrieving postponements for office  <" + currentUser.getOffice() +">");
        Office office = new Office();
        office.setId(currentUser.getOffice());
        return postponementRepository.findAllByOffice(office);  // status 200  [] empty list if not found
    }



    public Postponement retrievePostponement(int id, boolean bPolitisRequest, boolean bAdminRequest) {
        Optional<Postponement> postponement = postponementRepository.findById(id);

        if (!postponement.isPresent()) {
            System.out.println("GET:Postponement with id  <" + id + ">  not found");
            throw new PostponementNotFoundException("Postponement with id  <" + id + ">  not found");  // status 404
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postponement with id  <" + id + ">  not found");
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postponement with id  <" + id + ">  not found", ex);
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postponement with id  <" + id + ">  not found", new ResponseStatusException());
            //return ResponseEntity.notFound().build();
        }

        // checks if currentUserOffice = postponementOffice. One office can access only his postponements
        // ==============================================================================================

        // Get the currentUserName from authentication object
        String currentUserName = securityController.getCurrentUserName();

        // if currentUserName is NULL throw exception
        if (currentUserName == null || currentUserName.length() == 0) {  // status 500
            System.out.println("GET:RetrievePostponement:Request from entity with username  <" + currentUserName + ">. The username can not be NULL.");
            throw new InternalApplicationErrorException("Something went wrong....  Call to method RetrievePostponement with currentUserName NULL.");  // status 500
        }
        // Get the optional entity for the current user
        Optional<User> optionalUser = userRepository.findById(currentUserName);
        if (!optionalUser.isPresent()) {  // status 404
            System.out.println("ERROR:GET:RetrievePostponement. User details with id  <" + currentUserName + ">  not found");
            throw new UserNotFoundException("Retrieving postponement... User details with id  <" + currentUserName + ">  not found");  // status 404
        }
        // check if currentUserOffice is same with postponementOffice. One office cannot see others office postponements
        if (!bAdminRequest) {   // Admin can access postponements of all offices
            if (postponement.get().getOffice().getId() != optionalUser.get().getOffice()) {    // status 500
                System.out.println("ERROR:GET:RetrievePostponement. User's office not same with Postponement's office");
                throw new InternalApplicationErrorException("Something went wrong.... User's office not same with Postponement's office");
            }
        }

        if (bPolitisRequest) {
            // check if currentUser is same with postponementUser. Politis can access only his postponement
            if ( postponement.get().getUserIn().getUsername() != optionalUser.get().getUsername() ) {   // status 500
                System.out.println("ERROR:GET:RetrievePostponement. currentUser not same with Postponement's userIn");
                throw new InternalApplicationErrorException("Something went wrong.... currentUser not same with Postponement's user");
            }
        }

        System.out.println("GET:Postponement with id  <" + id + ">  retrieved");
        return postponement.get();   // status 200
    }



    public ResponseEntity<Object> savePostponement(Postponement postponement) {

        // Get the currentUserName from authentication object and retrieve his the postponements
        String currentUserName = securityController.getCurrentUserName();

        // if currentUserName is NULL throw exception
        if (currentUserName == null || currentUserName.length() == 0) {
            System.out.println("ERROR:POST:Saving postponement for politis with username  <" + currentUserName + ">. The username can not be NULL.");
            throw new InternalApplicationErrorException("Something went wrong....  Call to method savePostponement with currentUserName NULL.");  // status 500
        }

        // create object for userIn
        User userIn = new User();
        userIn.setUsername(currentUserName);  //userIn.setUsername("stratos");
        postponement.setUserIn(userIn);


        // Getting the Office id for the UserIn attribute  from the currentUser
        // First Get the entity for the current user
        Optional<User> currentUser = userRepository.findById(currentUserName);
        if (!currentUser.isPresent()) {
            System.out.println("ERROR:POST:Saving postponement. User details with id  <" + currentUserName + ">  not found");
            throw new UserNotFoundException("Saving postponement... User details with id  <" + currentUserName + ">  not found");  // status 404
        }

        // set the office attribute in the postponement entity
        Office officePostponement = new Office();
        officePostponement.setId(currentUser.get().getOffice());
        postponement.setOffice(officePostponement);

        // Set the Office Description in case FRONTEND could not get it from JSON
        Office officeDetails = officeRepository.getById(currentUser.get().getOffice());
        postponement.setOfficeDescr(officeDetails.getDescription());

        //Frontend Sends the ReasonDescription
        //postponement.setReasonDescr();

        // Set the Reason id. Frontend Sends the ReasonDescription
        Reason reasonId = reasonRepository.findByDescription(postponement.getReasonDescr());
        if (reasonId==null) {
            System.out.println("ERROR:POST:Saving postponement. The reason id with description  <" + postponement.getReasonDescr() + ">  not found");
            throw new InternalApplicationErrorException("Saving postponement... The reason id with description  <" + postponement.getReasonDescr() + ">  not found"); // status 404
        }
        postponement.setReason(reasonId.getId());


        // set status=0  Pending
        postponement.setStatus(0);

        postponement.setUserValid(null);
        postponement.setCommentValid(null);
        postponement.setUserApproved(null);
        postponement.setCommentApproved(null);

        // CommentIn   and  File  are already entered by the user
        //postponement.setCommentIn();
        //postponement.setFile();


        // Set time with Timestamp time /  set date with date value only only FOR Searches by date
        // καλυτερα να μπει μια ημερομηνία. Δεν αξιζει τον κοπο. Δεν θα γινουν αναζητήσεις με ημερομηνία.
        Instant instant = Instant.now();
        postponement.setTime(Timestamp.from(instant));
        postponement.setDate(Timestamp.from(instant));

        // Save only the date for Searching purposes.  IT'S NOT WORTH IMPLEMENTING
        // =======================================================================
        // Get Current time as Timestamp
//        Date dateDateTimeNow = new Date();
//        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy");
//        String stringDateTimeNow  = dateTimeFormat.format(dateDateTimeNow);
//
//        Date dateDateTimeFromString = null;
//        try {
//            dateDateTimeFromString = dateTimeFormat.parse(stringDateTimeNow);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        postponement.setDate(new Timestamp(dateDateTimeFromString.getTime()));


        // Save postponement to database
        Postponement savedPostponement = postponementRepository.save(postponement);   // status 500 if fails

        System.out.println("POST:Postponement with id  <" + savedPostponement.getId() + ">  created");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPostponement.getId()).toUri();

        return ResponseEntity.created(location).build();  // status 201

//        try catch may be better, but this does not work
//        try {
//            Postponement savedPostponement = postponementRepository.save(postponement);
//            System.out.println("POST:Postponement with id  <" + savedPostponement.getId() + ">  created");
//            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                    .buildAndExpand(savedPostponement.getId()).toUri();
//            return ResponseEntity.created(location).build();  // status 201
//        } catch (SQLExecuteErrorException ex) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "POST:Postponement not saved. Error in executing the statement", ex);
//        }
    }



    public ResponseEntity<Object> updatePostponementStatus(int id, int newStatus, String newComment) {

        // Get the postponement from database and the current status
        Optional<Postponement> postponementOptional = postponementRepository.findById(id);
        if (!postponementOptional.isPresent()) {
            System.out.println("updatePostponementStatus:Postponement with id  <" + id + ">  not found");
            throw new PostponementNotFoundException("Postponement with id  <" + id + ">  not found");  // status 404
        }

        Postponement postponement = postponementOptional.get();
        int currentStatus = postponement.getStatus();


        // Get the currentUserName from authentication object and retrieve his the postponements
        String currentUserName = securityController.getCurrentUserName();

        // if currentUserName is NULL throw exception
        if (currentUserName == null || currentUserName.length() == 0) {
            System.out.println("PUT:updatePostponementStatus:Request from entity with username  <" + currentUserName + ">. The username can not be NULL.");
            throw new InternalApplicationErrorException("Something went wrong....  Call to method updatePostponementStatus with currentUserName NULL.");  // status 500
        }
        // Get the optional entity for the current user
        Optional<User> optionalUser = userRepository.findById(currentUserName);
        if (!optionalUser.isPresent()) {
            System.out.println("ERROR:PUT:updatePostponementStatus. User details with id  <" + currentUserName + ">  not found");
            throw new UserNotFoundException("Saving postponement... User details with id  <" + currentUserName + ">  not found");  // status 404
        }
        // create object for currentUser
        User currentUser = optionalUser.get();

        // check if currentUserOffice is same with postponementOffice. One office cannot see others office postponements
        if ( postponement.getOffice().getId() != currentUser.getOffice() ){    // status 500
            System.out.println("ERROR:PUT:updatePostponementStatus. User's office not same with Postponement's office");
            throw new InternalApplicationErrorException("Something went wrong.... User's office not same with Postponement's office");
        }

        // Get Current time as Timestamp
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String stringDateTimeNow  = dateTimeFormat.format(new Date());


        // Cancel postponement
        if (newStatus==5) {

            // check if currentUser is same with postponementUser. Only the same user can Cancel the postponement
            if ( postponement.getUserIn().getUsername() != currentUser.getUsername() ) {   // status 500
                System.out.println("ERROR:PUT:updatePostponementStatus. currentUser not same with Postponement's userIn");
                throw new InternalApplicationErrorException("Something went wrong.... currentUser not same with Postponement's user");
            }

            if (currentStatus==3) {  // status 500
                System.out.println("ERROR:Cancel postponement... Postponement already Approved.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Cancel postponement... Postponement already Approved.  id <" + postponement.getId() + ">");
            }
            if (currentStatus==4) {  // status 500
                System.out.println("ERROR:Cancel postponement... Postponement already Rejected.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Cancel postponement... Postponement already Approved.  id <" + postponement.getId() + ">");
            }
            if (currentStatus==5) {  // status 500
                System.out.println("ERROR:Cancel postponement... Postponement already Canceled.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Cancel postponement... Postponement already Canceled.  id <" + postponement.getId() + ">");
            }
            postponement.setCommentIn(postponement.getCommentIn()
                    + "   Postponement Canceled by user " + currentUserName
                    + " at " + stringDateTimeNow
                    + ". Comments: " + newComment);
        }


        // Validation or Not Validation of postponement
        if ( (newStatus==1) || (newStatus==2) ) {

            if (currentStatus==1) {  // status 500
                System.out.println("ERROR:Validation postponement... Postponement already Validated.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Validation postponement... Postponement already Validated.  id <" + postponement.getId() + ">");
            }
            if (currentStatus==2) {  // status 500
                System.out.println("ERROR:Validation postponement... Postponement already Not Validated.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Validation postponement... Postponement already Not Validated.  id <" + postponement.getId() + ">");
            }
            if (currentStatus==3) {  // status 500
                System.out.println("ERROR:Validation postponement... Postponement already Approved.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Validation postponement... Postponement already Approved.  id <" + postponement.getId() + ">");
            }
            if (currentStatus==4) {  // status 500
                System.out.println("ERROR:Validation postponement... Postponement already Rejected.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Validation postponement... Postponement already Rejected.  id <" + postponement.getId() + ">");
            }
            if (currentStatus==5) {  // status 500
                System.out.println("ERROR:Validation postponement... Postponement already Canceled.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Validation postponement... Postponement already Canceled.  id <" + postponement.getId() + ">");
            }

            String msgComment = null;
            if (newStatus==1) { msgComment = "Validated"; } else { msgComment = "Not Validated"; }

            postponement.setUserValid(currentUser);

            postponement.setCommentValid( postponement.getCommentValid()
                    + "   Postponement " + msgComment + " by user " + currentUserName
                    + " at " + stringDateTimeNow
                    + ". Comments: " + newComment);
        }


        // Approve or Reject postponement
        if ( (newStatus==3) || (newStatus==4) ) {

            if (currentStatus==0) {  // status 500
                System.out.println("ERROR:Approve/Reject postponement... Postponement is Pending. First must be Validated.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Approve/Reject postponement... Postponement is Pending. First must be Validated.  id <" + postponement.getId() + ">");
            }
            if (currentStatus==1) {}
            if (currentStatus==2) {  // status 500
                System.out.println("ERROR:Approve/Reject postponement... Postponement already Not Validated.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Approve/Reject postponement... Postponement already Not Validated.  id <" + postponement.getId() + ">");
            }
            if (currentStatus==3) {  // status 500
                System.out.println("ERROR:Approve/Reject postponement... Postponement already Approved.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Approve/Reject postponement... Postponement already Approved.  id <" + postponement.getId() + ">");
            }
            if (currentStatus==4) {  // status 500
                System.out.println("ERROR:Approve/Reject postponement... Postponement already Rejected.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Approve/Reject postponement... Postponement already Rejected.  id <" + postponement.getId() + ">");
            }
            if (currentStatus==5) {  // status 500
                System.out.println("ERROR:Validation postponement... Postponement already Canceled.  id <" + postponement.getId() + ">");
                throw new InternalApplicationErrorException("ERROR:Validation postponement... Postponement already Canceled.  id <" + postponement.getId() + ">");
            }

            String msgComment = null;
            if (newStatus==3) { msgComment = "Approved"; } else { msgComment = "Rejected"; }
            postponement.setUserApproved(currentUser);
            postponement.setCommentApproved( postponement.getCommentApproved()
                    + "   Postponement " + msgComment + " by user " + currentUserName
                    + " at " + stringDateTimeNow
                    + ". Comments: " + newComment);
        }


        // Save to database
        postponement.setStatus(newStatus);
        postponementRepository.save(postponement);

        return ResponseEntity.noContent().build();  // status 204
        //return ResponseEntity.ok().build();   // status 200
    }


}
