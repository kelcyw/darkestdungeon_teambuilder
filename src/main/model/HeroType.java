package model;

import java.util.ArrayList;
import java.util.List;

public class HeroType {
    private String heroTypeName;
    private List<Skill> allHeroSkills;

    public HeroType(String heroTypeName, List<Skill> allHeroSkills) {
        this.heroTypeName = heroTypeName;
        this.allHeroSkills = allHeroSkills;
    }

    // EFFECTS: returns the hero's type name
    public String getHeroTypeName() {
        return heroTypeName;
    }

    // EFFECTS: returns the hero's skill list
    public List<Skill> getHeroSkills() {
        return allHeroSkills;
    }

    // MODIFIES: this
    // EFFECTS: changes hero's type name
    public void changeHeroTypeName(String newTypeName) {
        heroTypeName = newTypeName;
    }

    // REQUIRES: size of allHeroSkills < 7
    // MODIFIES: this
    // EFFECTS: adds a skill to the hero's skill list
    public void addHeroSkill(Skill newSkill) {
        allHeroSkills.add(newSkill);
    }

    // REQUIRES: size of allHeroSkills > 0
    // MODIFIES: this
    // EFFECTS: removes a skill from the hero's skill list
    public void removeHeroSkill(Skill currentSkill) {
        allHeroSkills.remove(currentSkill);
    }

}
