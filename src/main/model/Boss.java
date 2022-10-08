package model;

import java.util.ArrayList;

public class Boss {

    private String charName;
    private int bossLevel;
    private ArrayList<Stat> charStats;

    public static final int BOSS_LEVEL_INCREMENT = 2;

    // EFFECTS: constructs a new boss
    public Boss() {

    }

    // EFFECTS: gets given Boss's name
    public String getBossName() {
        return "name";
    }

    // EFFECTS: gets given character's level
    public int getBossLevel() {
        return 1;
    }

    // EFFECTS: gets given boss's resistance stat list
    public ArrayList<Stat> getBossStats() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: adds a new resistance stat for the boss
    public void addBossStat(Stat newStat) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes a resistance stat from the boss
    public void removeBossStat(Stat currentStat) {
        // stub
    }


    // REQUIRES: current level < 5
    // MODIFIES: this
    // EFFECTS: increases current level by increment
    public void increaseBossLevel() {

    }

    // REQUIRES: current level > 1
    // MODIFIES: this
    // EFFECTS: increases current level by increment
    public void decreaseBossLevel() {

    }

    // EFFECTS: determines boss's weakness (lowest resistance stat)
    public Stat determineBossWeakness() {
        return null;
    }
}
