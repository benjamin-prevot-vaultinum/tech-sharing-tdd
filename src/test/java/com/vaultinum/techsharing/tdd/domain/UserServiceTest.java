package com.vaultinum.techsharing.tdd.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @ParameterizedTest(name = "Order by {0}, direction {1}")
    @CsvSource(delimiterString = "|", textBlock = """
            id       | ASC
            id       | DESC
            lastName | ASC
            lastName | DESC
            """)
    public void shouldReturnAllUsers(String orderBy, Sort.Direction direction) {
        List<User> expectedUsers = List.of(
                new User("ad358f5c-c978-49f2-9276-10e7f14bb06b", "James", "HETFIELD"),
                new User("93778b59-96af-422d-8a48-71b0d4af0c23", "Lars", "ULRICH"),
                new User("1518e234-a50b-4916-a396-563f22497fb6", "Kirk", "HAMMETT"),
                new User("d10100aa-4eb0-4695-b8a1-ee21a37d5e82", "Robert", "TRUJILLO")
        );

        when(userRepository.findAll(orderBy, direction)).thenReturn(expectedUsers);

        List<User> actualUsers = userService.findAll(orderBy, direction);

        assertThat(actualUsers, is(equalTo(expectedUsers)));
    }

    @Test
    public void shouldThrowIllegalArgumentException_whenOrderByValueIsIncorrect() {
        assertThrows(
                IllegalArgumentException.class,
                () -> userService.findAll("invalid-order-by", Sort.Direction.ASC)
        );
    }

}
