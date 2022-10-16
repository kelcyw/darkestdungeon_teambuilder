package model;

// Stat is an effect that a skill inflicts on others

public class Stat {
    private String statName;
    private int statChance;
    public static final int CHANCE_INCREMENT = 10;

    // EFFECTS: makes a new stat
    public Stat(String statName, int statChance) {
        this.statName = statName;
        this.statChance = statChance;

    }

    // EFFECTS: returns stat name
    public String getStatName() {
        return statName;
    }

    // EFFECTS: returns stat chance
    public int getStatChance() {
        return statChance;
    }

    // MODIFIES: this
    // EFFECTS: increases stat chance by CHANCE_INCREMENT w/ level
    public void increaseStatChance() {
        statChance = statChance + CHANCE_INCREMENT;
    }

    // REQUIRES: stat chance > 0
    // MODIFIES: this
    // EFFECTS: decreases stat chance by CHANCE_INCREMENT w/ level
    public void decreaseStatChance() {
        statChance = statChance - CHANCE_INCREMENT;
    }

}
