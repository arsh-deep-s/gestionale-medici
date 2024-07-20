package it.jac.spring.secondaprova.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.jac.spring.secondaprova.entity.User;
import it.jac.spring.secondaprova.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}