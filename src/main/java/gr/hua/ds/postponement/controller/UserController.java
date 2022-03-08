package gr.hua.ds.postponement.controller;

import gr.hua.ds.postponement.entity.Office;
import gr.hua.ds.postponement.entity.User;
import gr.hua.ds.postponement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4100")
@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/userinfo")
    public User retrieveUser() {
        return userService.retrieveUserinfo();  // status 200=OK, 404=NotFound, 500=FAILURE
    }


    @GetMapping("/useroffice")
    public Office retrieveCurrentUserOffice() {
        return userService.retrieveCurrentUserOffice();  // status 200=OK, 404=NotFound, 500=FAILURE
    }


//    @GetMapping("/signup")
//    public String showSignUpForm(User user) {
//        return "add-user";
//    }
//
//    @PostMapping("/adduser")
//    public String addUser(@Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }
//
//        userService.registerUser(user);
//        return "redirect:/index";
//    }

}
