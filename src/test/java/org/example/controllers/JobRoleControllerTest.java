package org.example.controllers;

import org.example.models.JobRoleResponse;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class JobRoleControllerTest {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(JobRoleControllerTest.class);

    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    JobRoleController jobRoleController = new JobRoleController(jobRoleService);

    private final List<JobRoleResponse> jobRoleResponse = List.of(new JobRoleResponse(
            1,
            "Software Engineer",
            "London",
            1,
            2,
            null
    ));

    @Test
    void getAllJobRoles_ReturnsJobRoles_ReturnTwoHundred() throws SQLException {
        LOGGER.info("Initialised Unit Test for getAllJobRoles_ReturnsJobRoles_ReturnTwoHundred");
        when(jobRoleService.getAllJobRoles()).thenReturn(jobRoleResponse);
        LOGGER.debug("Mocked jobRoleService to return {}", jobRoleResponse);

        Response response = jobRoleController.getJobRoles();

        assertEquals(200, response.getStatus());
        assertEquals(jobRoleResponse, response.getEntity());
    }

    @Test
    void getAllJobRoles_ThrowSqlException_ReturnFiveHundred() throws SQLException {
        LOGGER.info("Initialised Unit Test for getAllJobRoles_ThrowSqlException_ReturnFiveHundred");
        when(jobRoleService.getAllJobRoles()).thenThrow(new SQLException());
        LOGGER.debug("Mocked jobRoleService to throw SQLException");

        Response response = jobRoleController.getJobRoles();

        assertEquals(500, response.getStatus());
    }
}
