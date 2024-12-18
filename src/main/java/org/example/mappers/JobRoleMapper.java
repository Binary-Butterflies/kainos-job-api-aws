package org.example.mappers;

import org.example.models.JobRole;
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
                jobRole.getCapabilityId(),
                jobRole.getBandId(),
                jobRole.getClosingDate()
        );
    }

    public static List<JobRoleResponse> mapJobRoleListToJobRoleResponseList(
            final List<JobRole> jobRoles
    ) {
        return jobRoles.stream()
                .map(JobRoleMapper::mapJobRoleToJobRoleResponse)
                .collect(Collectors.toList());
    }
}
