package org.example.controllers;

import org.example.models.Band;
import org.example.models.Capability;
import org.example.models.JobRoleResponse;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class JobRoleControllerTest {
    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    JobRoleController jobRoleController = new JobRoleController(jobRoleService);

    private final List<JobRoleResponse> jobRoleResponse = List.of(new JobRoleResponse(
            1,
            "Software Engineer",
            "London",
            null,
            new Band(2, "Senior"),
            new Capability(2, "Project Manager")
    ));

    @Test
    void getAllJobRoles_ReturnsJobRoles_ReturnTwoHundred() throws SQLException {
        when(jobRoleService.getAllJobRoles()).thenReturn(jobRoleResponse);

        Response response = jobRoleController.getJobRoles();

        assertEquals(200, response.getStatus());
        assertEquals(jobRoleResponse, response.getEntity());
    }

    @Test
    void getAllJobRoles_ThrowSqlException_ReturnFiveHundred() throws SQLException {
        when(jobRoleService.getAllJobRoles()).thenThrow(new SQLException());

        Response response = jobRoleController.getJobRoles();

        assertEquals(500, response.getStatus());
    }
}
