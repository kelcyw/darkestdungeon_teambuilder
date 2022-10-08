package model;

public class Stat {
    private String name;
    private int chance;
    public static final int CHANCE_INCREMENT = 10;

    // EFFECTS: makes a new stat
    public Stat(String statName, int statChance) {

    }

    // EFFECTS: returns stat name
    public String getStatName() {
        return "sampleStatName"; // stub
    }

    // EFFECTS: returns stat chance
    public int getStatChance() {
        return 0; // stub
    }

    // MODIFIES: this
    // EFFECTS: increases stat chance by CHANCE_INCREMENT w/ level
    public void increaseStatChance() {
        // stub
    }

    // REQUIRES: stat chance > 0
    // MODIFIES: this
    // EFFECTS: decreases stat chance by CHANCE_INCREMENT w/ level
    public void decreaseStatChance() {
        // stub
    }

}
