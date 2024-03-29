package com.apps.managetournament;


import java.util.List;

public class Match {

     private List<Team>teams;
     private String team1;
     private String team2;
     private int scoreTeam1, scoreTeam2;
     private String matchStatus;  //Give options to choose from Upcoming, ongoing and complete;
                                  // Complete only under a conditions when score is entered


    public Match(String team1, String team2) {
        this.team1 = team1;
        this.team2 = team2;
        //this.scoreTeam1 = scoreTeam1;
        //this.scoreTeam2 = scoreTeam2;

        // Create match and update the score in the match activity class
        // choose which score to update
        // Only organisers have access to update score
        // Other users are only able to view
        // Match activity is only available for organisers
    }

    //Here create a method to arrange matches
    // In the match activity user will have an option to
    // Select matches by himself or the system randomly picks for them



    @Override
    public String toString() {     // Test this by creating a match and put two teams and
        return "Match{" +          // have a look to it by displaying as a text view and try to update score.
                "team1=" + team1 +
                ", scoreTeam1=" + scoreTeam1 +
                ", team2=" + team2 +
                ", scoreTeam2=" + scoreTeam2 +
                ", matchStatus='" + matchStatus + '\'' +
                '}';
    }

}
