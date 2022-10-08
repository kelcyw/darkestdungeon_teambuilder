package model;

import java.util.ArrayList;

public class Hero {

    private String givenName;
    private int heroLevel;
    private HeroType heroType;

    public static final int HERO_LEVEL_INCREMENT = 1;

    // EFFECTS: constructs a new Hero
    public Hero(
            String givenName,
            HeroType heroType) {
        this.givenName = givenName;
        this.heroType = heroType;
        heroLevel = 0;
    }

    // EFFECTS: gets the hero's player-determined name
    public String getHeroGivenName() {
        return givenName;
    }

    // EFFECTS: gets given character's level
    public int getHeroLevel() {
        return heroLevel;
    }

    // EFFECTS: gets given character's hero type
    public HeroType getHeroType() {
        return heroType;
    }

    // MODIFIES: this
    // EFFECTS: changes given hero's name
    public void changeHeroGivenName(String newName) {
        givenName = newName;
    }

    // MODIFIES: this
    // EFFECTS: changes hero's current hero type
    public void changeHeroType(HeroType newHeroType) {
        heroType = newHeroType;
    }

    // REQUIRES: current level < 6
    // MODIFIES: this
    // EFFECTS: increases current level by increment
    public void increaseHeroLevel() {
        heroLevel = heroLevel + HERO_LEVEL_INCREMENT;
    }

    // REQUIRES: current level > 0
    // MODIFIES: this
    // EFFECTS: decreases current level by increment
    public void decreaseHeroLevel() {
        heroLevel = heroLevel - HERO_LEVEL_INCREMENT;
    }

    // REQUIRES: given int is more than 0, less than 6
    // MODIFIES: this
    // EFFECTS: sets the hero's current level to given int
    public void setHeroLevel(int newLevel) {
        heroLevel = newLevel;
    }

}
