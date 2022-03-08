package gr.hua.ds.postponement.service;

import gr.hua.ds.postponement.controller.SecurityController;
import gr.hua.ds.postponement.entity.*;
import gr.hua.ds.postponement.repository.AuthorityRepository;
import gr.hua.ds.postponement.repository.OfficeRepository;
import gr.hua.ds.postponement.repository.PostponementRepository;
import gr.hua.ds.postponement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authRepository;

    @Autowired
    private PostponementRepository postponementRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private SecurityController securityController;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),  user.get().getAuthorities());
        }else{
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
    }



    public List<User> retrieveAllUsers() {
        System.out.println("GET:Retrieving ALL users");
        return userRepository.findAll();   // status 200
    }



    public User retrieveUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent()) {
            System.out.println("GET:retrieveUser:User with username  <" + username + ">  not found");
            throw new UserNotFoundException("User with id  <" + username + ">  not found");  // status 404
        }

        System.out.println("GET:User with username  <" + username + ">  retrieved");
        user.get().setPassword(passwordEncoder.encode(user.get().getPassword()));
        return user.get();   // status 200
    }


    public User retrieveUserinfo() {

        // Get the currentUserName from authentication object
        String currentUserName = securityController.getCurrentUserName();

        // if currentUserName is NULL Unauthenticated User
        if (currentUserName == null || currentUserName.length() == 0) {
            User tmpUser = new User();
            tmpUser.setFirstname("Unauthenticated");
            tmpUser.setLastname("User");
            return tmpUser;   // status 200
        }

        // Get the entity for the current user from database
        Optional<User> currentUser = userRepository.findById(currentUserName);
        if (!currentUser.isPresent()) {
            System.out.println("GET:Userinfo. User details with id  <" + currentUserName + ">  not found");
            throw new UserNotFoundException("GET:Userinfo... User details with id  <" + currentUserName + ">  not found");  // status 404
        }

        return currentUser.get();
    }


    public Office retrieveCurrentUserOffice() {

        // Get the currentUserName from authentication object
        String currentUserName = securityController.getCurrentUserName();

        // if currentUserName is NULL Unauthenticated User
        if (currentUserName == null || currentUserName.length() == 0) {
            Office tmpOffice = new Office();
            tmpOffice.setDescription("None office. Unauthenticated User!");
            return tmpOffice;   // status 200
        }

        // Get the entity for the current user from database
        Optional<User> currentUser = userRepository.findById(currentUserName);
        if (!currentUser.isPresent()) {
            System.out.println("GET:RetrieveCurrentUserOffice. User details with id  <" + currentUserName + ">  not found");
            throw new UserNotFoundException("GET:RetrieveCurrentUserOffice... User details with id  <" + currentUserName + ">  not found");  // status 404
        }

        Integer officeId = currentUser.get().getOffice();
        Optional<Office> userOffice = officeRepository.findById(officeId);  // status 200  [] empty list if not found
        return userOffice.get();
    }


    @Transactional
    public ResponseEntity<Object>  addUser(User user) {

        // check if username is null
        if ( (user.getUsername()==null) || (user.getUsername().length()==0) ) {
            throw new InternalApplicationErrorException("Username can not be null or empty");  // status 500
        }

        // check if the username already exists
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) { // status 500
            System.out.println("POST:addUser:User with username  <" + user.getUsername() + ">  already exists");
            throw new InternalApplicationErrorException("User with username  <" + user.getUsername() + ">  already exists");
        }

        // check if password is null
        if ( (user.getPassword()==null) || (user.getPassword().length()==0) ) {
            throw new InternalApplicationErrorException("Password can not be null or empty");  // status 500
        }

        // check if First name is null
        if ( (user.getFirstname()==null) || (user.getFirstname().length()==0) ) {
            throw new InternalApplicationErrorException("First name can not be null or empty");  // status 500
        }

        // check if Last name is null
        if ( (user.getLastname()==null) || (user.getLastname().isEmpty()) ) {
            throw new InternalApplicationErrorException("Last name can not be null or empty");  // status 500
        }

        // check for valid user status  (0,1)
        if ( (user.getEnabled()<0) || (user.getEnabled()>1) ){
            throw new InternalApplicationErrorException("Enabled value not valid");  // status 500
        }

        // check if office exists
        Optional<Office> optionalOffice = officeRepository.findById(user.getOffice());
        if (!optionalOffice.isPresent()) { // status 500
            System.out.println("POST:addUser:the office  <" + user.getOffice() + ">  does not exist");
            throw new InternalApplicationErrorException("The office  <" + user.getOffice() + ">  does not exist");
        }

        // check if entered Authorities are Valid
        // TODO CODE HERE

        // if null Authorities add default role ROLE_POLITIS
        if (user.getAuthorities().size()==0) {
            Authorities auth = new Authorities("ROLE_POLITIS", user);
            user.addAuthority(auth);
        }

        //user.setAsm("999999999");

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // save user to database
        userRepository.save(user);
        // save authority to database  -> no code. It's automated with the entities Persistence
        //authRepository.save(auth);

        System.out.println("POST:User with username  <" + user.getUsername() + ">  created");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(user.getUsername()).toUri();
        return ResponseEntity.created(location).build();  // status 201
    }



    public ResponseEntity<Object> deleteUser(String username) {

        // check if user to be deleted exists
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            System.out.println("DELETE:User with username  <" + username + ">  not found");
            throw new UserNotFoundException("User with username  <" + username + ">  not found");  // status 404
        }

        // check if user to be deleted has postponements
        User user = optionalUser.get();
        //Optional<Postponement> postponement = postponementRepository.findById(id);
        Optional<Postponement> postponement = postponementRepository.findDistinctByUserIn(user);
        if (postponement.isPresent()) {  // status 500
            System.out.println("DELETE:User can not be deleted. Postponements with username  <" + username + ">  found");
            throw new InternalApplicationErrorException("User can not be deleted. Postponements with username  <" + username + ">  found");  // status 404
        }

        // OK to delete user.  Authorities is automatically deleted via entities persistence
        userRepository.deleteById(username);
        System.out.println("DELETE:User with username  <" + username + ">  deleted");
        return ResponseEntity.noContent().build();   // status 204
        //return ResponseEntity.ok().build();   // status 200
    }



    @Transactional
    public ResponseEntity<Object>  updateUser(User user) {

        // check if username is null
        if (  (user.getUsername()==null) || (user.getUsername().length()==0) ){
            throw new InternalApplicationErrorException("Username can not be null or empty");  // status 500
        }

        // check if the user to be updated exists
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (!optionalUser.isPresent()) { // status 500
            System.out.println("POST:updateUser:User with username  <" + user.getUsername() + ">  not found");
            throw new UserNotFoundException("User with username  <" + user.getUsername() + ">  not found");
        }

        // check if user changed his username. It not correct  !!!!!!!!!!!!!!!!
        // TODO CODE HERE

        // check if First name is null
        if ( (user.getFirstname()==null) || (user.getFirstname().length()==0) ) {
            throw new InternalApplicationErrorException("First name can not be null or empty");  // status 500
        }

        // check if Last name is null
        if ( (user.getLastname()==null) || (user.getLastname().isEmpty()) ) {
            throw new InternalApplicationErrorException("Last name can not be null or empty");  // status 500
        }

        // check if office exists
        Optional<Office> optionalOffice = officeRepository.findById(user.getOffice());
        if (!optionalOffice.isPresent()) { // status 500
            System.out.println("POST:updateUser:the office  <" + user.getOffice() + ">  does not exist");
            throw new InternalApplicationErrorException("The office  <" + user.getOffice() + ">  does not exist");
        }

        // check for valid user status  (0,1)
        if ( (user.getEnabled()<0) || (user.getEnabled()>1) ){
            throw new InternalApplicationErrorException("Enabled value not valid");  // status 500
        }

        //user.setAsm("999999999");

        // check if entered Authorities are Valid
        // TODO CODE HERE

        // checks for password
        //System.out.println("User:" + user.getPassword());
        //System.out.println("optionalUser:" + optionalUser.get().getPassword());
        //if (user.getPassword() == null || user.getPassword().equals("") || user.getPassword().equals(optionalUser.get().getPassword()) ) {
        if (user.getPassword() == null || user.getPassword().equals("") ) {
            //keeps the last password
            user.setPassword(optionalUser.get().getPassword());
        }
        else {
            //password change request
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            System.out.println("Password changed for user:" + optionalUser.get().getUsername());
        }

        // delete old Authorities that not included in new authorities
        // !!! SOS !!!  if delete Authority that is included in new authorities then Internal Server Error
        List<String> newRoles = new ArrayList<>();
        Collection<Authorities> newAuthorities = user.getAuthorities();
        for (Authorities newAuthority : newAuthorities) {
            newRoles.add(newAuthority.getAuthority());
        }

        Collection<Authorities> oldAuthorities = optionalUser.get().getAuthorities();
        oldAuthorities.forEach((authorities -> {
            if ( !newRoles.contains(authorities.getAuthority()) ) {
                AuthPK authPK = new AuthPK(authorities.getUser().getUsername(), authorities.getAuthority());
                authRepository.deleteById(authPK);
            }
        }));

        // save user to database
        userRepository.save(user);
        // save new authorities to database  -> no code. It's automated via entities Persistence !!!!!!
        //authRepository.save(auth);

        System.out.println("POST:User with username  <" + user.getUsername() + ">  updated");
        return ResponseEntity.noContent().build();  // status 204
    }



//    public void registerUser(User user) {
//
//        User newUser = new User();
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//        Authorities auth = new Authorities("ROLE_USER", newUser);
//        userRepository.save(newUser);
//        authRepository.save(auth);
//    }


}