package com.vaultinum.techsharing.tdd.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Test
    public void shouldGetUserById() {
        User expectedUser = new User("24075eb0-a87b-4838-816f-8e810bc6d4c4", "John", "DOE");

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.getById("24075eb0-a87b-4838-816f-8e810bc6d4c4")).thenReturn(expectedUser);

        Optional<User> actualUser = new UserService(userRepository).getById("24075eb0-a87b-4838-816f-8e810bc6d4c4");

        assertThat(actualUser.get(), is(equalTo(expectedUser)));
    }

    @Test
    public void shouldReturnEmptyOptional_whenUserDoesNotExist() {
        UserRepository userRepository = mock(UserRepository.class);

        Optional<User> actualUser = new UserService(userRepository).getById("id-for-absent-user");

        assertThat(actualUser.isEmpty(), is(true));
    }

}
