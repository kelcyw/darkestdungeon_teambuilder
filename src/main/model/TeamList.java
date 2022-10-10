package model;

import java.util.ArrayList;
import java.util.List;

public class TeamList {
    private List<Team> savedTeams;

    // EFFECTS: makes a new team list
    public TeamList() {
        savedTeams = new ArrayList<Team>();
    }

    // MODIFIES: this
    // EFFECTS: adds given team to the list of saved teams
    public void addTeam(Team newTeam) {
        savedTeams.add(newTeam);
    }

    // MODIFIES: this
    // EFFECTS: removes given team from the list of saved teams
    public void removeTeam(Team currentTeam) {
        savedTeams.remove(currentTeam);
    }

    // REQUIRES: given index must exist within current teams list
    // EFFECTS: returns team at given index
    public Team getTeam(int index) {
        return savedTeams.get(index);
    }

    // EFFECTS: returns all saved teams
    public List<Team> getSavedTeams() {
        return savedTeams;
    }

}
