package model;

import java.util.ArrayList;

public interface Character {

    // EFFECTS: gets given character's name
    public String getCharacterName();

    // EFFECTS: gets given character's level
    public int getCharacterLevel();

    // EFFECTS: gets given character's stat list
    public ArrayList<Stat> getCharacterStats();

    // REQUIRES: current level < 6
    // MODIFIES: this
    // EFFECTS: increases current level by increment
    public void increaseLevel();

    // REQUIRES: current level > 0
    // MODIFIES: this
    // EFFECTS: decreases current level by increment
    public void decreaseLevel();
}
