package com.vaultinum.techsharing.tdd.domain;

import java.util.List;

public interface UserRepository {

    User getById(String id);

    List<User> findAll(String orderBy, Sort.Direction direction);

}
