package model;

import java.util.ArrayList;
import java.util.List;

public class Skill {
    private String skillDescription;
    private List<Stat> skillStats;
    private boolean selected;

    // EFFECTS: constructs a new skill
    public Skill(String skillDescription) {
        this.skillDescription = skillDescription;
        skillStats = new ArrayList<>();
        selected = false;
    }

    // EFFECTS: returns skill description
    public String getSkillDescription() {
        return skillDescription;
    }

    // EFFECTS: returns a list of skill stats
    public List<Stat> getSkillStats() {
        return skillStats;
    }

    // EFFECTS: returns the skill's selected status
    public boolean isSelected() {
        return selected;  // stub
    }

    // MODIFIES: this
    // EFFECTS: selects the skill if it is deselected, and vice versa
    public void changeSelectedStatus() {
        selected = !selected;
    }

    // REQUIRES: stat is not already part of the skill
    // MODIFIES: this
    // EFFECTS: adds a stat to a skill
    public void addSkillStat(Stat newStat) {
        skillStats.add(newStat);
    }

    // MODIFIES: this
    // EFFECTS: removes a stat from a skill
    public void removeSkillStat(Stat currentStat) {
        skillStats.remove(currentStat);
    }

}
