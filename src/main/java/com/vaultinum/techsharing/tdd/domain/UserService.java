package com.vaultinum.techsharing.tdd.domain;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getById(String id) {
        return Optional.ofNullable(userRepository.getById(id));
    }

}
