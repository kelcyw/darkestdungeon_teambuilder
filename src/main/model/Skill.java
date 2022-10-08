package model;

import java.util.ArrayList;

public class Skill {
    private String skillDescription;
    private ArrayList<Stat> listOfStats;
    private boolean selected;

    // EFFECTS: constructs a new skill
    public Skill(String skillDescription) {
        // stub
    }

    // EFFECTS: returns skill description
    public String getSkillDescription() {
        return "sample description";
    }

    // EFFECTS: returns a list of skill stats
    public ArrayList<Stat> getListOfStats() {
        ArrayList<Stat> stubListOfStats = new ArrayList<Stat>();
        return stubListOfStats;  // stub
    }

    // EFFECTS: returns the skill's selected status
    public boolean isSelected() {
        return false;  // stub
    }

    // MODIFIES: this
    // EFFECTS: selects the skill if it is deselected, and vice versa
    public void changeSelectedStatus() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds a stat to a skill
    public void addSkillStat(Stat newStat) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds a stat to a skill
    public void removeSkillStat(Stat currentStat) {
        // stub
    }

}
