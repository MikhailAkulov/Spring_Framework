package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        userRepository.save(new User(null, "Evgeny", "sagad@df.ru"));
        userRepository.save(new User(null, "John", "aetjj@df.ru"));
        userRepository.save(new User(null, "Rate", "jkhik@df.ru"));
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
