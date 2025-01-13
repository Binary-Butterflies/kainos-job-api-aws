package org.example.services;

import org.example.daos.JobRoleDao;
import org.example.models.Band;
import org.example.models.Capability;
import org.example.models.JobRole;
import org.example.models.JobRoleDetails;
import org.example.models.JobRoleResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobRoleServiceTest {
    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    JobRoleService jobRoleService = new JobRoleService(jobRoleDao);

    private final List<JobRole> jobRoles = List.of(new JobRole(
            1,
            "Software Engineer",
            "London",
            null,
            new Band(2, "Senior"),
            new Capability(2, "Project Manager"),
            new JobRoleDetails("test", "test", "test", 1, "test", 1)
    ));

    @Test
    void getAllJobRoles_shouldReturnJobRoleResponse_whenDaoCalled() throws
            SQLException {
        Mockito.when(jobRoleDao.getAllJobRoles()).thenReturn(jobRoles);

        List<JobRoleResponse> response = jobRoleService.getAllJobRoles();

        assertEquals(1, response.size());
        assertEquals("Software Engineer", response.get(0).getRoleName());
    }

    @Test
    void getAllJobRoles_shouldThrowSqlException_whenDaoThrowsSqlException() throws
            SQLException {
        Mockito.when(jobRoleDao.getAllJobRoles()).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.getAllJobRoles());
    }

}
