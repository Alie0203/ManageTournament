package com.apps.managetournament;

import java.util.Date;

public class Tournament {

    String tournamentName;
    String tournamentLocation;
    String startDate;
    String endDate;
    String tournamentType;
    String id;

    public Tournament() {
        // Public constructor without arguments is needed when
        // retrieving data
    }

    public Tournament(String tournamentName, String tournamentLocation, String startDate, String endDate, String tournamentType, String id) {
        this.tournamentName = tournamentName;
        this.tournamentLocation = tournamentLocation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tournamentType = tournamentType;
        this.id = id;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public String getTournamentLocation() {
        return tournamentLocation;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTournamentType() {
        return tournamentType;
    }

    public String getId() {
        return id;
    }
}
