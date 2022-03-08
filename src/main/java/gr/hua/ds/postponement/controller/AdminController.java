package gr.hua.ds.postponement.controller;

import gr.hua.ds.postponement.entity.Postponement;
import gr.hua.ds.postponement.entity.User;
import gr.hua.ds.postponement.service.PostponementService;
import gr.hua.ds.postponement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4100")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PostponementService postponementService;

    @Autowired
    private UserService userService;


    @GetMapping("/postponements")
    public List<Postponement> retrieveAllPostponements() {
        return postponementService.retrieveAllPostponements();  // status 200, 500=FAILURE
    }

    @GetMapping("/postponements/{id}")
    public Postponement retrievePostponement(@PathVariable int id) {
        // 2nd parameter: true=politis request, false=office request for ypallilos, officer
        // 3nd parameter: true=Admin request, false=Not admin request
        return postponementService.retrievePostponement(id, false, true);  // status 200=OK, 404=NotFound, 500=FAILURE
    }

    @DeleteMapping("/postponements/{id}")
    public ResponseEntity<Object> deletePostponement(@PathVariable int id) {
        return postponementService.deletePostponement(id);  // status 204=OK, 404=NotFound, 500=FAILURE
    }


    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userService.retrieveAllUsers();  // status 200, 500=FAILURE
    }

    @GetMapping("/users/{username}")
    public User retrieveUser(@PathVariable String username) {
        return userService.retrieveUser(username);  // status 200=OK, 404=NotFound, 500=FAILURE
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        return userService.addUser(user);  // status 201=CREATED, 500=FAILURE
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);  // status 204=OK, 404=NotFound, 500=FAILURE
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        return userService.updateUser(user);  // status 201=CREATED, 500=FAILURE
    }


}