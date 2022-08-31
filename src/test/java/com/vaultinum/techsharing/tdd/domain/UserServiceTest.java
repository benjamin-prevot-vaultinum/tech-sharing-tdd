package com.vaultinum.techsharing.tdd.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void shouldGetUserById() {
        User expectedUser = new User("24075eb0-a87b-4838-816f-8e810bc6d4c4", "John", "DOE");

        when(userRepository.getById("24075eb0-a87b-4838-816f-8e810bc6d4c4")).thenReturn(expectedUser);

        Optional<User> actualUser = userService.getById("24075eb0-a87b-4838-816f-8e810bc6d4c4");

        actualUser.ifPresentOrElse(
                user -> assertThat(user, is(equalTo(expectedUser))),
                () -> fail("User must exist")
        );
    }

    @Test
    public void shouldReturnEmptyOptional_whenUserDoesNotExist() {
        Optional<User> actualUser = userService.getById("id-for-absent-user");

        assertThat(actualUser.isEmpty(), is(true));
    }

}
