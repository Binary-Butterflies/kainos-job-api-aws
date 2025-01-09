package org.example.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.example.models.UserRole;
import org.example.services.TestService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Test API")
@Path("/api/test")
public class TestController {
    TestService testService;
    public TestController(final TestService testService) {
        this.testService = testService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Test endpoint that returns a list of all databases",
            response = String[].class
    )
    public Response testConnection() {
        try {
            return Response.ok().entity(testService.testConnection()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.ADMIN, UserRole.USER})
    @ApiOperation(
            value = "Test endpoint, which requires authentication, "
            + "that returns a list of all databases",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = String[].class
    )
    public Response testConnectionUser() {
        try {
            return Response.ok().entity(testService.testConnection()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.ADMIN})
    @ApiOperation(
            value = "Test endpoint, which requires admin privileges, "
                    + "that returns a list of all databases",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = String[].class
    )
    public Response testConnectionAdmin() {
        try {
            return Response.ok().entity(testService.testConnection()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }
}
