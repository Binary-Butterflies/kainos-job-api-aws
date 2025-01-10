package org.example.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.example.models.JobRoleResponse;
import org.example.models.UserRole;
import org.example.services.JobRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
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
    @RolesAllowed({UserRole.ADMIN, UserRole.USER})
    @ApiOperation(
            value = "Returns all job roles",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = JobRoleResponse[].class
    )
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
}
