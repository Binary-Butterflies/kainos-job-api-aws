package org.example.models;

import java.sql.Date;

public class JobRole {
    private final int jobRoleId;
    private final String roleName;
    private final String location;
    private final int capabilityId;
    private final int bandId;
    private final Date closingDate;

    public JobRole(
            final int jobRoleId,
            final String roleName,
            final String location,
            final int capabilityId,
            final int bandId,
            final Date closingDate
    ) {
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.capabilityId = capabilityId;
        this.bandId = bandId;
        this.closingDate = closingDate;
    }

    public int getJobRoleId() {
        return jobRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getLocation() {
        return location;
    }

    public int getCapabilityId() {
        return capabilityId;
    }

    public int getBandId() {
        return bandId;
    }

    public Date getClosingDate() {
        return closingDate;
    }
}

