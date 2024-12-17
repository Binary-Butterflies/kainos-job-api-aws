package org.example.validators;

import org.example.exceptions.InvalidException;
import org.example.models.RegisterRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UserValidatorTest {
    @Test
    void validateRegistrationRequest_shouldThrowInvalidException_whenInvalidEmail() {
        InvalidException thrown = assertThrows(InvalidException.class,
                () -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                        "thisisnotanemail",
                        "MyPassword123"
                )));

        assertEquals(thrown.getMessage(),
                "User is not valid: Invalid email \"thisisnotanemail\"");
    }

    @Test
    void validateRegistrationRequest_shouldPass_whenValidEmail() {
        assertDoesNotThrow(() -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                "thisisanemail@mail.com",
                "MyPassword123"
        )));
    }

    @Test
    void validateRegistrationRequest_shouldPass_whenValidEmailWithDots() {
        assertDoesNotThrow(() -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                "this.is.an.email@mail.com",
                "MyPassword123"
        )));
    }

    @Test
    void validateRegistrationRequest_shouldPass_whenValidEmailWithPluses() {
        assertDoesNotThrow(() -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                "this+is+an+email@mail.com",
                "MyPassword123"
        )));
    }

    @Test
    void validateRegistrationRequest_shouldPass_whenValidEmailWithDotsAndPluses() {
        assertDoesNotThrow(() -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                "this.is+an.email@mail.com",
                "MyPassword123"
        )));
    }

    @Test
    void validateRegistrationRequest_shouldThrowInvalidException_whenPasswordIsBlank() {
        InvalidException thrown = assertThrows(InvalidException.class,
                () -> UserValidator.validateRegistrationRequest(new RegisterRequest(
                        "this.is+an+email@mail.com",
                        ""
                )));

        assertEquals(thrown.getMessage(),
                "User is not valid: Password cannot be blank");
    }
}
