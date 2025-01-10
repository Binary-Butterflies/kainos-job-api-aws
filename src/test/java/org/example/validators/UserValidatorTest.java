package org.example.validators;

import org.example.exceptions.InvalidException;
import org.example.models.RegisterRequest;
import org.example.services.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UserValidatorTest {
    AuthService authService = Mockito.mock(AuthService.class);

    @Test
    void validateRegistrationRequest_shouldThrowInvalidException_whenInvalidEmail() {
        InvalidException thrown = assertThrows(InvalidException.class,
                () -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                        "thisisnotanemail",
                        "MyPassword123"
                ), authService));

        assertEquals("User is not valid: Invalid email \"thisisnotanemail\"",
                thrown.getMessage());
    }

    @Test
    void validateRegistrationRequest_shouldPass_whenValidEmail() {
        assertDoesNotThrow(() -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                "thisisanemail@mail.com",
                "MyPassword123"
        ), authService));
    }

    @Test
    void validateRegistrationRequest_shouldPass_whenValidEmailWithDots() {
        assertDoesNotThrow(() -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                "this.is.an.email@mail.com",
                "MyPassword123"
        ), authService));
    }

    @Test
    void validateRegistrationRequest_shouldPass_whenValidEmailWithPluses() {
        assertDoesNotThrow(() -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                "this+is+an+email@mail.com",
                "MyPassword123"
        ), authService));
    }

    @Test
    void validateRegistrationRequest_shouldPass_whenValidEmailWithDotsAndPluses() {
        assertDoesNotThrow(() -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                "this.is+an.email@mail.com",
                "MyPassword123"
        ), authService));
    }

    @Test
    void validateRegistrationRequest_shouldThrowInvalidException_whenPasswordIsBlank() {
        InvalidException thrown = assertThrows(InvalidException.class,
                () -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                        "this.is+an+email@mail.com",
                        ""
                ), authService));

        assertEquals("User is not valid: Password cannot be blank",
                thrown.getMessage());
    }

    @Test
    void validateRegistrationRequest_shouldThrowInvalidException_whenUserExists()
            throws SQLException {
        Mockito.when(authService.doesUserExists("this.user.already.exists@mail.com"))
                .thenReturn(true);

        InvalidException thrown = assertThrows(InvalidException.class,
                () -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                        "this.user.already.exists@mail.com",
                        "MyPassword123"
                ), authService));

        assertEquals("User is not valid: User already exists",
                thrown.getMessage());
    }
}
