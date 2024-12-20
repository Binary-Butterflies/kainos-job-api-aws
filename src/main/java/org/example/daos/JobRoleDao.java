package org.example.daos;

import org.example.models.Band;
import org.example.models.Capability;
import org.example.models.JobRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(JobRoleDao.class);

    public List<JobRole> getAllJobRoles() throws SQLException {
        List<JobRole> jobRoles = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            LOGGER.info("Executing query to get all job roles");
            ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM job_roles INNER JOIN capability ON "
                        + "job_roles.capabilityId = capability.capabilityId "
                        + "INNER JOIN band ON job_roles.bandId = band.bandId");

            while (resultSet.next()) {
                JobRole jobRole = new JobRole(
                        resultSet.getInt("jobRoleId"),
                        resultSet.getString("roleName"),
                        resultSet.getString("location"),
                        resultSet.getDate("closingDate"),
                        new Band(resultSet.getInt("bandID"),
                                resultSet.getString("bandName")),
                        new Capability(resultSet.getInt("capabilityId"),
                                resultSet.getString("capabilityName"))
                );

                jobRoles.add(jobRole);
            }
        }
        return jobRoles;
    }
}
