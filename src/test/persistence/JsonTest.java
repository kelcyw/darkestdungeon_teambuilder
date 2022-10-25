package persistence;

import model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    // EFFECTS: compares expected team to actual team
    protected void checkTeam(Team team1, Team team) {
        assertEquals(team1.getTeamName(), team.getTeamName());
        assertEquals(team1.isFavourite(), team.isFavourite());
        checkMembers(team1.getTeamMembers(), team.getTeamMembers());
    }

    // EFFECTS: compares each expected hero to actual hero
    protected void checkMembers(List<Hero> membersExpected, List<Hero> membersActual) {
        for (int i = 0; i < membersExpected.size(); i++) {
            checkMember(membersExpected.get(i), membersActual.get(i));
        }
    }

    // EFFECTS: compares expected hero to actual hero
    protected void checkMember(Hero hExpected, Hero hActual) {
        assertEquals(hExpected.getHeroGivenName(), hActual.getHeroGivenName());
        assertEquals(hExpected.getHeroLevel(), hActual.getHeroLevel());
        checkHeroType(hExpected.getHeroType(), hActual.getHeroType());
    }

    // EFFECTS: compares expected herotype to actual herotype
    protected void checkHeroType(HeroType htExpected, HeroType htActual) {
        assertEquals(htExpected.getHeroTypeName(), htActual.getHeroTypeName());
        checkSkills(htExpected.getHeroSkills(), htActual.getHeroSkills());
    }

    // EFFECTS: compares expected skills to actual skills
    protected void checkSkills(List<Skill> skillsExpected, List<Skill> skillsActual) {
        for (int i = 0; i < skillsExpected.size(); i++) {
            checkSkill(skillsExpected.get(i), skillsActual.get(i));
        }
    }

    // EFFECTS: compares expected skill to actual skill
    protected void checkSkill(Skill sExpected, Skill sActual) {
        checkStats(sExpected.getSkillStats(), sActual.getSkillStats());
    }

    // EFFECTS: compares expected stats to actual stats
    protected void checkStats(List<Stat> statsExpected, List<Stat> statsActual) {
        for (int i = 0; i < statsExpected.size(); i++) {
            Stat statExpected = statsExpected.get(i);
            Stat statActual = statsActual.get(i);
            assertEquals(statExpected.getStatName(), statActual.getStatName());
            assertEquals(statExpected.getStatChance(), statActual.getStatChance());
        }
    }
}
