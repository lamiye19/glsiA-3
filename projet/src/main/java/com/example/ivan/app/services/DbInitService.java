package com.example.ivan.app.services;

import com.example.ivan.app.models.User;
import com.example.ivan.app.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInitService implements CommandLineRunner {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public DbInitService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) {

        //creation de l'admin
        User admin = new User("admin", passwordEncoder.encode("admin123"),"Doe","John","ADMIN","");

        List<User> users = Arrays.asList(admin);

        //enregistrer l'admin en BD
        if(this.userRepository.count() <= 0) {
            this.userRepository.saveAll(users);
        }

    }
}
