package model;

import java.util.ArrayList;

public class Boss implements Character {

    private ArrayList<Integer> possiblePositions;

    private final int BOSS_LEVEL_INCREMENT = 2;

    // EFFECTS: constructs a new boss
    public Boss(ArrayList<Integer> possiblePositions) {

    }

    // EFFECTS: gets given character's name
    @Override
    public String getCharacterName() {
        return "name";
    }

    // EFFECTS: gets given character's level
    @Override
    public int getCharacterLevel() {
        return 1;
    }

    // EFFECTS: gets given character's stat list
    @Override
    public ArrayList<Stat> getCharacterStats() {
        return null;
    }

    // REQUIRES: current level < 5
    // MODIFIES: this
    // EFFECTS: increases current level by increment
    @Override
    public void increaseLevel() {

    }

    // REQUIRES: current level > 1
    // MODIFIES: this
    // EFFECTS: increases current level by increment
    @Override
    public void decreaseLevel() {

    }
}
