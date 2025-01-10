package org.example.utils;

import io.jsonwebtoken.Jwts;
import org.example.daos.AuthDao;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.services.AuthService;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public final class TestAuthUtils {
    private TestAuthUtils() { }

    public static String generateTestAuthToken()
            throws SQLException, InvalidException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        AuthService authService = new AuthService(new AuthDao(), AuthService.JWT_KEY);

        LoginRequest loginRequest = new LoginRequest(
                System.getenv("TEST_USER_EMAIL"),
                System.getenv("TEST_USER_PASSWORD")
        );

        return "Bearer " + authService.login(loginRequest);
    }
}
