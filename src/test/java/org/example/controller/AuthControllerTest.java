package org.example.controller;

import org.example.controllers.AuthController;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.models.RegisterRequest;
import org.example.services.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthControllerTest {
    AuthService authService = Mockito.mock(AuthService.class);
    AuthController authController = new AuthController(authService);

    LoginRequest loginRequest = new LoginRequest(
            "my_email@mail.com",
            "SuperCoolPass321"
    );

    RegisterRequest registerRequest = new RegisterRequest(
            "my_email@mail.com",
            "SuperCoolPass321"
    );

    String loginToken = "Token";

    @Test
    void login_shouldReturnToken_whenServiceReturnsToken()
            throws SQLException, InvalidException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        Mockito.when(authService.login(loginRequest)).thenReturn(loginToken);

        Response response = authController.login(loginRequest);

        assertEquals(200, response.getStatus());
        assertEquals(loginToken, response.getEntity());
    }

    @Test
    void login_shouldReturn500_whenServiceThrowsSQLException()
            throws SQLException, InvalidException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        Mockito.when(authService.login(loginRequest)).thenThrow(SQLException.class);

        Response response = authController.login(loginRequest);

        assertEquals(500, response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    void login_shouldReturn400_whenServiceThrowsInvalidException()
            throws SQLException, InvalidException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        Mockito.when(authService.login(loginRequest)).thenThrow(InvalidException.class);

        Response response = authController.login(loginRequest);

        assertEquals(400, response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    void register_shouldReturnNoContent_whenUserIsRegistered() {
        Response response = authController.register(registerRequest);

        assertEquals(201, response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    void register_shouldReturn500_whenServiceThrowsSQLException()
            throws SQLException, InvalidException, NoSuchAlgorithmException,
            InvalidKeySpecException, FailedToCreateException {
        Mockito.doThrow(SQLException.class).when(authService).registerUser(registerRequest);

        Response response = authController.register(registerRequest);

        assertEquals(500, response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    void register_shouldReturn500_whenServiceThrowsFailedToCreateException()
            throws SQLException, InvalidException, NoSuchAlgorithmException,
            InvalidKeySpecException, FailedToCreateException {
        Mockito.doThrow(FailedToCreateException.class).when(authService).registerUser(registerRequest);

        Response response = authController.register(registerRequest);

        assertEquals(500, response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    void register_shouldReturn400_whenServiceThrowsInvalidException()
            throws SQLException, InvalidException, NoSuchAlgorithmException,
            InvalidKeySpecException, FailedToCreateException {
        Mockito.doThrow(InvalidException.class).when(authService).registerUser(registerRequest);

        Response response = authController.register(registerRequest);

        assertEquals(400, response.getStatus());
        assertNull(response.getEntity());
    }
}
