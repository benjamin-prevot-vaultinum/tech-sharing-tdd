package com.vaultinum.techsharing.tdd.domain;

import java.util.List;
import java.util.Optional;

public class UserService {

    private static final List<String> ALLOWED_ORDER_BY = List.of("id", "lastName");

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getById(String id) {
        return Optional.ofNullable(userRepository.getById(id));
    }

    public List<User> findAll(String orderBy, Sort.Direction direction) {
        if (!ALLOWED_ORDER_BY.contains(orderBy)) {
            throw new IllegalArgumentException("Incorrect order by value: " + orderBy);
        }

        return userRepository.findAll(orderBy, direction);
    }

}
