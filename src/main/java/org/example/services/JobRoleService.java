package org.example.services;

import org.example.daos.JobRoleDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.mappers.JobRoleMapper;
import org.example.models.JobRole;
import org.example.models.JobRoleDetails;
import org.example.models.JobRoleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(JobRoleService.class);

    final JobRoleDao jobRoleDao;

    public JobRoleService(final JobRoleDao jobRoleDao) {
        this.jobRoleDao = jobRoleDao;
        LOGGER.info("Initialised");
    }

    public List<JobRoleResponse> getAllJobRoles() throws SQLException {
        LOGGER.info("Getting all job roles from JobRoleDao");
        List<JobRole> jobRoles = jobRoleDao.getAllJobRoles();
        LOGGER.debug("Total job roles: {}", jobRoles.size());

        LOGGER.debug("Mapping JobRole list to JobRoleResponse list");
        return JobRoleMapper.mapJobRoleListToJobRoleResponseList(jobRoles);
    }

    public JobRoleDetails getJobRoleById(final int id)
            throws SQLException, DoesNotExistException {
        LOGGER.info("Getting job role with matching inputted id");
        JobRole jobRole = jobRoleDao.getJobRoleById(id);

        if (jobRole == null) {
            throw new DoesNotExistException(Entity.JOBROLE);
        }
        LOGGER.debug("Mapping JobRole to JobRoleDetails");
        return JobRoleMapper.mapJobRoleToJobRoleDetails(jobRole);
    }
}
