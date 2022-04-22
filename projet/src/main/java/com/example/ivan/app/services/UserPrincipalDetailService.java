package com.example.ivan.app.services;

import com.example.ivan.app.models.User;
import com.example.ivan.app.models.UserPrincipal;
import com.example.ivan.app.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPrincipalDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public UserPrincipalDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }


    public List<User> getList() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
