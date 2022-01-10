package gr.hua.dit.controller;

import gr.hua.dit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {
    @Autowired
    private UserRepository userRepository;
}
