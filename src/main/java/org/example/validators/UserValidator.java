package org.example.validators;

import org.apache.commons.validator.routines.EmailValidator;
import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;
import org.example.models.RegisterRequest;
import org.example.services.AuthService;

import java.sql.SQLException;

public final class UserValidator {
    private UserValidator() { }

    public static void validateRegistrationRequest(
            final RegisterRequest registerRequest,
            final AuthService authService)
            throws InvalidException, SQLException {
        if (registerRequest.getPassword().isEmpty()) {
            throw new InvalidException(Entity.USER,
                    "Password cannot be blank");
        }

        if (!isValidEmail(registerRequest.getEmail())) {
            throw new InvalidException(Entity.USER,
                    "Invalid email \""
            + registerRequest.getEmail() + "\"");
        }

        if (authService.doesUserExists(registerRequest.getEmail())) {
            throw new InvalidException(Entity.USER,
                    "User already exists");
        }
    }

    public static boolean isValidEmail(final String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
