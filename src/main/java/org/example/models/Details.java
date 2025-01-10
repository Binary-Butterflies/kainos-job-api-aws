package org.example.models;

public class Details {
    private final String description;
    private final String responsibilities;
    private final String sharepointURL;
    private final int statusId;
    private final String statusName;
    private final int numberOfOpenPositions;

    public Details(
            final String description,
            final String responsibilities,
            final String sharepointURL,
            final int statusId,
            final String statusName,
            final int numberOfOpenPositions
    ) {
        this.description = description;
        this.responsibilities = responsibilities;
        this.sharepointURL = sharepointURL;
        this.statusId = statusId;
        this.statusName = statusName;
        this.numberOfOpenPositions = numberOfOpenPositions;
    }

    public String getDescription() {
        return description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public String getSharepointURL() {
        return sharepointURL;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public int getNumberOfOpenPositions() {
        return numberOfOpenPositions;
    }
}
