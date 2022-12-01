package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// HeroType represents a hero's classification
// It determines their classification name and skillset

public class HeroType implements Writable {
    private String heroTypeName;
    private List<Skill> allHeroSkills;

    // EFFECTS: creates a new HeroType
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

    // EFFECTS: returns the hero's SELECTED skills
    public List<Skill> getHeroSelectedSkills() {
        List<Skill> heroSelectedSkills = new ArrayList<>();
        for (Skill skill: allHeroSkills) {
            if (skill.isSelected()) {
                heroSelectedSkills.add(skill);
            }
        }
        return heroSelectedSkills;
    }

    // MODIFIES: this
    // EFFECTS: changes hero's type name
    public void changeHeroTypeName(String newTypeName) {
        heroTypeName = newTypeName;
    }

    // REQUIRES: size of allHeroSkills < 5, skill is not already in skill list
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("typename", heroTypeName);
        json.put("skills", skillsToJson());
        return json;
    }

    // EFFECTS: returns skills for this HeroType as a JSONArray
    public JSONArray skillsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Skill s : allHeroSkills) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
