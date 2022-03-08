package gr.hua.ds.postponement.repository;

import gr.hua.ds.postponement.entity.Authorities;
import gr.hua.ds.postponement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RepositoryEventHandler
public class UserEventHandler {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authRepository;

    @HandleBeforeCreate
    public void handleUserCreate(User user) {
        System.out.println("Enter handleUserCreate");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Authorities auth = new Authorities("ROLE_USER", user);
        userRepository.save(user);
        authRepository.save(auth);

    }

    @HandleBeforeSave
    public void handleUserUpdate(User user) {
        if (user.getPassword() == null || user.getPassword().equals("")) {
            //keeps the last password
            Optional<User> storedUser = userRepository.findByUsername(user.getUsername());
            storedUser.ifPresent(value -> user.setPassword(value.getPassword()));
        }
        else {
            //password change request
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }
}
