package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.DoesNotExistException;
import org.example.services.JobRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Job Role API")
@Path("/job-roles")
public class JobRoleController {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(JobRoleController.class);

    final JobRoleService jobRoleService;

    public JobRoleController(
            final JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
        LOGGER.info("Initialised");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles() {
        LOGGER.debug("/job-roles hit");
        try {
            LOGGER.info("Returning job roles");
            return Response.ok()
                    .entity(jobRoleService.getAllJobRoles())
                    .build();
        } catch (SQLException e) {
            LOGGER.error("Error getting job roles {}", e.toString());
            return Response.serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoleById(@PathParam("id") final int id) {
        LOGGER.debug("/job-roles hit");
        try {
            return Response.ok()
                    .entity(jobRoleService.getJobRoleById(id))
                    .build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (SQLException e) {
            return Response.serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }
}
