package org.example.validators;

import org.apache.commons.validator.routines.EmailValidator;
import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;
import org.example.models.RegisterRequest;

public final class UserValidator {
    private UserValidator() { }

    public static void validateRegistrationRequest(
            final RegisterRequest registerRequest) throws InvalidException {
        if (registerRequest.getPassword().isEmpty()) {
            throw new InvalidException(Entity.USER,
                    "Password cannot be blank");
        }

        if (!isValidEmail(registerRequest.getEmail())) {
            throw new InvalidException(Entity.USER,
                    "Invalid email \""
            + registerRequest.getEmail() + "\"");
        }
    }

    public static boolean isValidEmail(final String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
