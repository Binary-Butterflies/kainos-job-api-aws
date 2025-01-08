package org.example.services;

import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.DecoderException;
import org.example.daos.AuthDao;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.models.RegisterRequest;
import org.example.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.crypto.SecretKey;
import java.sql.SQLException;
import org.apache.commons.codec.binary.Hex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AuthServiceTest {
    AuthDao authDao = Mockito.mock(AuthDao.class);
    SecretKey jwtKey = Jwts.SIG.HS256.key().build();
    AuthService authService = new AuthService(authDao, jwtKey);

    LoginRequest loginRequest = new LoginRequest(
            "my_email@mail.com",
            "SuperCoolPass321"
    );

    User user = new User(
            "my_email@mail.com",
            Hex.decodeHex(System.getenv("AUTH_TEST_PASS_HASH")),
            Hex.decodeHex(System.getenv("AUTH_TEST_PASS_SALT"))
    );

    String token = System.getenv("AUTH_TEST_JWT_TOKEN");

    public AuthServiceTest() throws DecoderException {
    }

    @Test
    void login_shouldThrowSQLException_whenDaoThrowsSQLException()
            throws SQLException {
        Mockito.when(authDao.getUser(loginRequest)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> authService.login(loginRequest));
    }

    @Test
    void login_shouldThrowInvalidException_whenDaoDoesNotReturnAUser()
            throws SQLException {
        Mockito.when(authDao.getUser(loginRequest)).thenReturn(null);

        InvalidException thrown = assertThrows(InvalidException.class,
                () -> authService.login(loginRequest));

        assertEquals("User is not valid: Invalid Credentials",
                thrown.getMessage());
    }

    @Test
    void login_shouldThrowInvalidException_whenPasswordIsInvalid()
            throws SQLException {
        LoginRequest loginRequestWrongPass = new LoginRequest(
                "my_email@mail.com",
                "ThisIsABadPassword"
        );

        Mockito.when(authDao.getUser(loginRequestWrongPass)).thenReturn(user);

        InvalidException thrown = assertThrows(InvalidException.class,
                () -> authService.login(loginRequest));

        assertEquals("User is not valid: Invalid Credentials",
                thrown.getMessage());
    }

    @Test
    void login_shouldReturnJWTToken_whenSuccessfulLogin()
            throws SQLException {
        Mockito.when(authDao.getUser(loginRequest)).thenReturn(user);
        assertDoesNotThrow(() -> authService.login(loginRequest));
    }
}
