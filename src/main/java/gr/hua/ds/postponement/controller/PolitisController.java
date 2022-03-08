package gr.hua.ds.postponement.controller;

import gr.hua.ds.postponement.entity.Postponement;
import gr.hua.ds.postponement.service.PostponementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4100")
@RestController
@RequestMapping("/politis")
public class PolitisController {

    @Autowired
    private PostponementService postponementService;

    @GetMapping("/postponements")
    public List<Postponement> retrievePolitisPostponements() {
        return postponementService.retrievePolitisPostponements();  // status 200, 500=FAILURE
    }

    @GetMapping("/postponements/{id}")
    public Postponement retrievePostponement(@PathVariable int id) {
        // 2nd parameter: true=politis request, false=office request for ypallilos, officer
        // 3nd parameter: true=Admin request, false=Not admin request
        return postponementService.retrievePostponement(id, true, false);  // status 200=OK, 404=NotFound, 500=FAILURE
    }

    @PostMapping("/postponements")
    public ResponseEntity<Object> savePostponement(@RequestBody Postponement postponement) {
         return postponementService.savePostponement(postponement);  // status 201=CREATED, 500=FAILURE
    }

    @PutMapping("/postponements/{id}")
    public ResponseEntity<Object> cancelPostponement(@PathVariable int id, @RequestBody Postponement postponement) {
        return postponementService.updatePostponementStatus(id, 5, postponement.getCommentIn()) ;   // status 204=OK, 404=NotFound, 500=FAILURE
    }


//    Old version without service implementation
//    ==========================================
//
//    @Autowired
//    private PostponementRepository postponementRepository;
//
//    @GetMapping("/postponements")
//    public List<Postponement> retrieveAllPostponements() {
//        return postponementRepository.findAll();
//    }
//
//    @GetMapping("/postponements/{id}")
//    public Postponement retrievePostponement(@PathVariable int id) {
//        Optional<Postponement> postponement = postponementRepository.findById(id);
//
//        if (!postponement.isPresent()) {
//            throw new DBObjectNotFoundException("id-" + id);
//        }
//
//        return postponement.get();
//    }
//
//    @DeleteMapping("/students/{id}")
//    public void deleteStudent(@PathVariable int id) {
//        studentRepository.deleteById(id);
//    }
//
//    @PostMapping("/postponements")
//    public ResponseEntity<Object> createPostponement(@RequestBody Postponement postponement) {
//        Postponement savedPostponement = postponementRepository.save(postponement);
//        System.out.println("Postponement id " + savedPostponement.getId());
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(savedPostponement.getId()).toUri();
//
//        return ResponseEntity.created(location).build();
//    }
//
//    @PutMapping("/postponements/{id}")
//    public ResponseEntity<Object> updatePostponement(@RequestBody Postponement postponement, @PathVariable int id) {
//
//        Optional<Postponement> postponementOptional = postponementRepository.findById(id);
//
//        if (!postponementOptional.isPresent())
//            return ResponseEntity.notFound().build();
//
//        postponement.setId(id);
//        postponementRepository.save(postponement);
//        return ResponseEntity.noContent().build();
//    }


}
