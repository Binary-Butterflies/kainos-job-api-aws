package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.models.RegisterRequest;
import org.example.services.AuthService;

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
    AuthService authService;

    public AuthController(final AuthService service) {
        this.authService = service;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(final LoginRequest loginRequest) {
        try {
            return Response.ok()
                    .entity(authService.login(loginRequest))
                    .build();
        } catch (SQLException
                 | NoSuchAlgorithmException
                 | InvalidKeySpecException e) {
            return Response.serverError()
                    .entity(e.getMessage())
                    .build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(final RegisterRequest registerRequest) {
        try {
            authService.registerUser(registerRequest);

            return Response
                    .noContent()
                    .build();
        } catch (FailedToCreateException
                 | SQLException | NoSuchAlgorithmException
                 | InvalidKeySpecException e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        } catch (InvalidException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
