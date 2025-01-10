package org.example.mappers;

import org.example.models.JobRole;
import org.example.models.JobRoleDetailedResponse;
import org.example.models.JobRoleResponse;

import java.util.List;
import java.util.stream.Collectors;

public final class JobRoleMapper {
    private JobRoleMapper() { }

    public static JobRoleResponse mapJobRoleToJobRoleResponse(
            final JobRole jobRole) {
        return new JobRoleResponse(
                jobRole.getJobRoleId(),
                jobRole.getRoleName(),
                jobRole.getLocation(),
                jobRole.getClosingDate(),
                jobRole.getBand(),
                jobRole.getCapability()
        );
    }

    public static List<JobRoleResponse> mapJobRoleListToJobRoleResponseList(
            final List<JobRole> jobRoles
    ) {
        return jobRoles.stream()
                .map(JobRoleMapper::mapJobRoleToJobRoleResponse)
                .collect(Collectors.toList());
    }

    public static JobRoleDetailedResponse mapJobRoleToJobRoleDetails(
            final JobRole jobRole) {
        return new JobRoleDetailedResponse(
                jobRole.getJobRoleId(),
                jobRole.getRoleName(),
                jobRole.getLocation(),
                jobRole.getClosingDate(),
                jobRole.getBand(),
                jobRole.getCapability(),
                jobRole.getDetails()
        );
    }
}
