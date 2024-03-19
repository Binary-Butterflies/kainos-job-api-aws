package org.example.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.services.TestService;

import java.sql.SQLException;

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
