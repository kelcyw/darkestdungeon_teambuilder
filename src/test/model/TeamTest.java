package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamTest {
    Team testTeam;
    Hero testHero;
    Skill testSkill;
    Stat testStat;
    ArrayList<Skill> testSkillset;
    HeroType testHeroType;

    @BeforeEach
    public void runBefore() {
        testTeam = new Team("New Test Team");
        testSkill = new Skill("Skill");
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
        testTeam.addHeroToTeam(testHero, 0);
        assertEquals(testHero, testTeam.getHero(0));
    }

    @Test
    public void testCalculateStrengths() {
        testTeam.addHeroToTeam(testHero, 0);
        testStat.increaseStatChance();
        testSkill.addSkillStat(testStat);
        assertEquals(testStat.getStatName(), testTeam.calculateStrengths());
    }

    @Test
    public void testCompareAgainstBossIdeal() {

    }

    @Test
    public void testCompareAgainstBossNotIdeal() {

    }


}
