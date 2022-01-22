package gr.hua.ds.springboot1.service;

import gr.hua.ds.springboot1.entity.Authorities;
import gr.hua.ds.springboot1.entity.User;
import gr.hua.ds.springboot1.repository.AuthorityRepository;
import gr.hua.ds.springboot1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authRepository;


    public void registerUser(User user) {

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Authorities auth = new Authorities("ROLE_USER", newUser);
        userRepository.save(newUser);
        authRepository.save(auth);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),  user.get().getAuthorities());
        }else{
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }

    }



}