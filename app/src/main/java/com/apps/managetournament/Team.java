package com.apps.managetournament;

import java.util.List;

public class Team {

    String teamName , manager,location,contactNumber;
    List<Player>players;


    public Team(String teamName, String manager, String location, String contactNumber, List<Player> players) {
        this.teamName = teamName;
        this.manager = manager;
        this.location = location;
        this.contactNumber = contactNumber;
        this.players = players;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getManager() {
        return manager;
    }

    public String getLocation() {
        return location;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
