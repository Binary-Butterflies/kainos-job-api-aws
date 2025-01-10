package org.example.models;

public class Capability {
    private final int capabilityId;
    private final String capabilityName;

    public Capability(
            final int capabilityId,
            final String capabilityName
    ) {
        this.capabilityId = capabilityId;
        this.capabilityName = capabilityName;
    }

    public int getCapabilityId() {
        return capabilityId;
    }

    public String getCapabilityName() {
        return capabilityName;
    }
}
