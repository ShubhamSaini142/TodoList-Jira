package com.jira.todolist.todlistbackend.Services;

import com.jira.todolist.todlistbackend.Repositories.userRepository;
import com.jira.todolist.todlistbackend.beans.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private userRepository userRep;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public ResponseEntity<?> registerUser(Users user) {
        if(userRep.findByUsername(user.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Username Already Exist");
        }

        if(userRep.findByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("Email Already Exist");
        }

        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRep.save(user);

        return ResponseEntity.ok("User Is Created");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRep.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.withUsername(user.getUsername())
                .password(user.getPassword()).build();
    }


}
