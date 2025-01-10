package org.example.models;

public class Band {
    private final int bandId;
    private final String bandName;

    public Band(final int bandId, final String bandName) {
        this.bandId = bandId;
        this.bandName = bandName;
    }

    public int getBandId() {
        return bandId;
    }

    public String getBandName() {
        return bandName;
    }
}
