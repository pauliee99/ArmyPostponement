package gr.hua.ds.postponement.controller;


import gr.hua.ds.postponement.entity.Postponement;
import gr.hua.ds.postponement.service.PostponementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4100")
@RestController
@RequestMapping("/ypallilos")
public class YpallilosController {

    @Autowired
    private PostponementService postponementService;

    @GetMapping("/postponements")
    public List<Postponement> retrieveOfficePostponements() {
        return postponementService.retrieveOfficePostponements();  // status 200, 500=FAILURE
    }

    @GetMapping("/postponements/{id}")
    public Postponement retrievePostponement(@PathVariable int id) {
        // 2nd parameter: true=politis request, false=office request for ypallilos, officer
        // 3nd parameter: true=Admin request, false=Not admin request
        return postponementService.retrievePostponement(id, false, false);  // status 200=OK, 404=NotFound, 500=FAILURE
    }

    @PutMapping("/postponements/valid/{id}")
    public ResponseEntity<Object> ValidPostponement(@PathVariable int id, @RequestBody Postponement postponement) {
        return postponementService.updatePostponementStatus(id, 1, postponement.getCommentValid());   // status 204=OK, 404=NotFound, 500=FAILURE
    }

    @PutMapping("/postponements/notvalid/{id}")
    public ResponseEntity<Object> NotValidPostponement(@PathVariable int id, @RequestBody Postponement postponement) {
        return postponementService.updatePostponementStatus(id, 2, postponement.getCommentValid());   // status 204=OK, 404=NotFound, 500=FAILURE
    }

}