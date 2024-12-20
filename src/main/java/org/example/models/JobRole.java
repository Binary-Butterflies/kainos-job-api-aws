package org.example.models;

import java.sql.Date;

public class JobRole {
    private final int jobRoleId;
    private final String roleName;
    private final String location;
    private final Date closingDate;
    private final Band band;
    private final Capability capability;

    public JobRole(
            final int jobRoleId,
            final String roleName,
            final String location,
            final Date closingDate,
            final Band band,
            final Capability capability
    ) {
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.closingDate = closingDate;
        this.band = band;
        this.capability = capability;
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

    public Date getClosingDate() {
        return closingDate;
    }

    public Capability getCapability() {
        return capability;
    }

    public Band getBand() {
        return band;
    }
}
