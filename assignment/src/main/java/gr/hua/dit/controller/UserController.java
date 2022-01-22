package gr.hua.dit.controller;

import gr.hua.dit.entity.User;
import gr.hua.dit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/users")
    public List<User> retrieveAllStudents() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveStudent(@PathVariable int id) {
        Optional<User> student = userRepository.findById(id);

        if (!student.isPresent())
            throw new UserNotFoundException("id-" + id);

        return student.get();
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody User student) {
        User savedStudent = userRepository.save(student);
        System.out.println("student id " + savedStudent.getId());


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody User student, @PathVariable int id) {

        Optional<User> studentOptional = userRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        student.setId(id);

        userRepository.save(student);

        return ResponseEntity.noContent().build();
    }
}
