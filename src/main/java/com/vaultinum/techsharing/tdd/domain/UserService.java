package com.vaultinum.techsharing.tdd.domain;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getById(String id) {
        return Optional.ofNullable(userRepository.getById(id));
    }

    public List<User> findAll(String orderBy, Sort.Direction direction) {
        return userRepository.findAll(orderBy, direction);
    }

}
