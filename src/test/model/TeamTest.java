package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamTest {
    Team testTeam;
    Hero testHero;
    Skill testSkill;
    Stat testStat;
    List<Skill> testSkillset;
    HeroType testHeroType;

    @BeforeEach
    public void runBefore() {
        testTeam = new Team("New Test Team");
        testSkill = new Skill("Skill");
        testSkillset = new ArrayList<>();
        testSkillset.add(testSkill);
        testStat = new Stat("Stat", 100);
        testHeroType = new HeroType("Test Hero Type", testSkillset);
        testHero = new Hero("Boudica", testHeroType);
    }

    @Test
    public void testChangeTeamName() {
        testTeam.changeTeamName("Different Test Name");
        assertEquals("Different Test Name", testTeam.getTeamName());
    }

    @Test
    public void testAddHeroToTeam() {
        testTeam.addHeroToTeam(testHero);
        assertEquals(testHero, testTeam.getHero(0));
    }

    @Test
    public void testDetermineStrengthsSuccess() {
        testTeam.addHeroToTeam(testHero);
        testTeam.addHeroToTeam(testHero);
        testTeam.addHeroToTeam(testHero);
        testStat.increaseStatChance();
        testSkill.addSkillStat(testStat);
        testSkill.changeSelectedStatus();
        assertEquals(" " + testStat.getStatName(), testTeam.determineStrengths());
    }

    @Test
    public void testDetermineStrengthsFail() {
        testTeam.addHeroToTeam(testHero);
        testTeam.addHeroToTeam(testHero);
        testStat.increaseStatChance();
        testSkill.addSkillStat(testStat);
        testSkill.changeSelectedStatus();
        assertEquals(" none", testTeam.determineStrengths());
    }


}
