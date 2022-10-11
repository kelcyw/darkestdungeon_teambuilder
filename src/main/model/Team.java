package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private String teamName;
    private List<Hero> teamMembers;
    private static final int COMMON_THRESHOLD = 3;

    // EFFECTS: makes a new team
    public Team(String teamName) {
        this.teamName = teamName;
        teamMembers = new ArrayList<>();
    }

    // EFFECTS: returns team name
    public String getTeamName() {
        return teamName;
    }

    // REQUIRES: index must be between 0-3 (limited team size)
    // EFFECTS: returns the hero at the given index
    public Hero getHero(int index) {
        return teamMembers.get(index);
    }

    // EFFECTS: gets all heroes contained in team
    // TODO: create a test for this?
    public List<Hero> getTeamMembers() {
        return teamMembers;
    }

    // MODIFIES: this
    // EFFECTS: changes team name to given string
    public void changeTeamName(String newName) {
        teamName = newName;
    }

    // MODIFIES: this
    // EFFECTS: adds the given Hero to the team at index 0
    public void addHeroToTeam(Hero newHero) {
        teamMembers.add(newHero);
    }

    // REQUIRES: index must be between 0 and 3
    // MODIFIES: this
    // EFFECTS: removes the given Hero from the team at given index
    public void removeHeroFromTeam(Hero currentHero) {
        teamMembers.remove(currentHero);
    }

    // EFFECTS: combines all heroes' selected skills into one list
    public List<Skill> getAllHeroesSkills(List<Hero> heroList) {
        List<Skill> skillList = new ArrayList<>();
        for (Hero h: heroList) {
            List<Skill> currentHeroSkills = h.getHeroType().getHeroSelectedSkills();
            skillList.addAll(currentHeroSkills);
        }
        return skillList;
    }

    // EFFECTS: gets all stat names from given skill
    public List<String> getSkillStatNames(List<Stat> statList) {
        List<String> statNames = new ArrayList<>();
        for (Stat stat: statList) {
            String statName = stat.getStatName();
            statNames.add(statName);
        }
        return statNames;
    }

    // EFFECTS: gets a list of stat names from each skill
    public List<String> getAllSkillStatNames(List<Skill> skillList) {
        List<String> statTypeList = new ArrayList<>();
        for (Skill skill: skillList) {
            List<Stat> statList = skill.getSkillStats();
            List<String> skillStatNames = getSkillStatNames(statList);
            statTypeList.addAll(skillStatNames);
        }
        return statTypeList;
    }

    // EFFECTS: returns common stats (i.e. those that appear 3 or more times)
    //          if no stats are common, return "none"
    public String determineCommonStats(List<String> statNames) {
        String commonStats = "";
        for (String statName: statNames) {
            int occurrences = Collections.frequency(statNames, statName);
            if (occurrences >= COMMON_THRESHOLD) {
                if (!commonStats.contains(statName)) {
                    commonStats = commonStats.concat(" " + statName);
                }
            }
        }
        if (commonStats.equals("")) {
            return "none";
        } else {
            return commonStats;
        }
    }

    // EFFECTS: determines strongest stats (those that are most common in team)
    //          if no stats are common (i.e. <= 3 skills have the stat)
    //          return "none"
    public String determineStrengths() {
        // need a method to combine all hero skills into 1 list
        List<Skill> allHeroesSkills = getAllHeroesSkills(teamMembers);
        // then need to go through all the skills, and grab their stat types
        List<String> allStatNames = getAllSkillStatNames(allHeroesSkills);
        // count all the stat types: if any appear 3 or more times, then return stat type as string
        // if none do, then return "none"
        return determineCommonStats(allStatNames);
    }
}