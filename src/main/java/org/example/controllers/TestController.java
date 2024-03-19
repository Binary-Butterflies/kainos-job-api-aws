package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.services.TestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Test API")
@Path("/api/test")
public class TestController {
    TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response testConnection() {
        try {
            return Response.ok().entity(testService.testConnection()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }
}
