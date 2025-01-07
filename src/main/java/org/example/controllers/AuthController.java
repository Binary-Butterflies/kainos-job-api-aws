package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.models.RegisterRequest;
import org.example.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

@Api("Auth API")
@Path("/api/auth")
public class AuthController {
    static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    final AuthService authService;

    public AuthController(final AuthService service) {
        this.authService = service;
        LOGGER.info("Initialised");
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(final LoginRequest loginRequest) {
        LOGGER.debug("/login");
        try {
            return Response.ok()
                    .entity(authService.login(loginRequest))
                    .build();
        } catch (SQLException
                 | NoSuchAlgorithmException
                 | InvalidKeySpecException e) {
            LOGGER.error(e.toString());
            return Response.serverError()
                    .entity(e.getMessage())
                    .build();
        } catch (InvalidException e) {
            LOGGER.error(e.toString());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(final RegisterRequest registerRequest) {
        LOGGER.debug("/register");
        try {
            authService.registerUser(registerRequest);

            return Response
                    .status(Response.Status.CREATED)
                    .build();
        } catch (FailedToCreateException
                 | SQLException | NoSuchAlgorithmException
                 | InvalidKeySpecException e) {
            LOGGER.error(e.toString());
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        } catch (InvalidException e) {
            LOGGER.error(e.toString());
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
