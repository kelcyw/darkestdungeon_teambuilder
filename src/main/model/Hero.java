package model;

import java.util.ArrayList;

public class Hero implements Character {

    private String givenName;
    private ArrayList<Skill> allSkills;

    private final int HERO_LEVEL_INCREMENT = 1;

    // EFFECTS: constructs a new Hero
    public Hero(
            String givenName,
            ArrayList<Skill> allSkills) {
        // stub
    }

    // EFFECTS: gets given character's name
    @Override
    public String getCharacterName() {
        return "name"; // stub
    }

    // EFFECTS: gets given character's level
    @Override
    public int getCharacterLevel() {
        return 0;
    }

    // EFFECTS: gets given character's stat list
    @Override
    public ArrayList<Stat> getCharacterStats() {
        return null;
    }

    // EFFECTS: gets given hero's skills
    public ArrayList<Skill> getCharacterSkills() {
        return null;
    }

    // EFFECTS: gets the hero's player-determined name
    public String getHeroGivenName() {
        return "name";
    }

    // MODIFIES: this
    // EFFECTS: changes given hero's name
    public void changeHeroGivenName(String newName) {

    }

    // REQUIRES: current level < 6
    // MODIFIES: this
    // EFFECTS: increases current level by increment
    @Override
    public void increaseLevel() {

    }

    // REQUIRES: current level > 0
    // MODIFIES: this
    // EFFECTS: decreases current level by increment
    @Override
    public void decreaseLevel() {

    }
}
