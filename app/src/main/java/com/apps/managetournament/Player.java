package com.apps.managetournament;

import java.util.Date;

public class Player {

    private String firstName, lastName, position; //for position a drop down list will be
    private int shirtNumber;  // used in the player activity class to choose
    private Date dateOfBirth;// from all the playing positions of football


    public Player(String firstName, String lastName, String position, int shirtNumber, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.shirtNumber = shirtNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
