package org.example.models;

public class JobRoleDetails {
    private final JobRole jobRole;

    public JobRoleDetails(
            final JobRole jobRole
    ) {
        this.jobRole = jobRole;
    }

    public JobRole getJobRole() {
        return jobRole;
    }
}
