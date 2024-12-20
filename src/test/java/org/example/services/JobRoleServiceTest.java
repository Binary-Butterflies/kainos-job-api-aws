package org.example.services;

import org.example.daos.JobRoleDao;
import org.example.models.Band;
import org.example.models.Capability;
import org.example.models.JobRole;
import org.example.models.JobRoleResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobRoleServiceTest {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(JobRoleServiceTest.class);

    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    JobRoleService jobRoleService = new JobRoleService(jobRoleDao);

    private final List<JobRole> jobRoles = List.of(new JobRole(
            1,
            "Software Engineer",
            "London",
            null,
            new Band(2, "Senior"),
            new Capability(2, "Project Manager")
    ));

    @Test
    void getAllJobRoles_shouldReturnJobRoleResponse_whenDaoCalled() throws
            SQLException {
        LOGGER.info("Initialised Unit Test for getAllJobRoles_shouldReturnJobRoleResponse_whenDaoCalled");
        Mockito.when(jobRoleDao.getAllJobRoles()).thenReturn(jobRoles);
        LOGGER.debug("Mocked JobRoleDao to return: {}", jobRoles);

        List<JobRoleResponse> response = jobRoleService.getAllJobRoles();
        LOGGER.debug("Response received: {}", response);

        assertEquals(1, response.size());
        assertEquals("Software Engineer", response.get(0).getRoleName());
    }

    @Test
    void getAllJobRoles_shouldThrowSqlException_whenDaoThrowsSqlException() throws
            SQLException {
        LOGGER.info("Initialised Unit Test for getAllJobRoles_shouldThrowSqlException_whenDaoThrowsSqlException");
        Mockito.when(jobRoleDao.getAllJobRoles()).thenThrow(SQLException.class);
        LOGGER.debug("Mocked JobRoleDao to throw SQLException");

        assertThrows(SQLException.class,
                () -> jobRoleService.getAllJobRoles());
    }
}
