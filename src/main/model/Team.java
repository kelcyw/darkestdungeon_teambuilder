package model;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<Hero> teamMembers;

    // EFFECTS: makes a new team
    public Team(String teamName) {
        // stub
    }

    // do i put the method for adding a hero to a team in here? or in the hero class?
    // same thing for teams and teamlist


    // EFFECTS: returns team name
    public String getTeamName() {
        return "sample team name"; // stub
    }

    // REQUIRES: index must be between 0-3 (limited team size)
    // EFFECTS: returns the hero at the given index
    public Hero getHero(int index) {
        return null; // stub
    }

    // MODIFIES: this
    // EFFECTS: changes team name to given string
    public void changeTeamName(String newName) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds the given Hero to the team at the given index
    //          if the given index already has a hero, do nothing
    public void addHeroToTeam(Hero h, int position) {
        // stub
    }

    // EFFECTS: determines strongest stats (those with highest chance)
    //          if no stats are associated with current skills,
    //          return "none"
    public String calculateStrengths() {
        return "none";  // stub
    }

    // EFFECTS: compares strong stats against given boss
    //          if strong stats are good enough against boss's stats
    //          (i.e. team is strong in stat that boss is weak in)
    //          tell the user that the team can work
    //          if strong stats are not good enough
    //          tell the user that the team is not ideal

    public String compareAgainstBoss(Boss b) {
        return "not ideal against boss";
    }
}