package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamTest {
    Team testTeam;
    Hero testHero;
    Hero testHero2;
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
        testHero2 = new Hero("Barristan", testHeroType);
        EventLog.getInstance().clear();
    }

    @Test
    public void testChangeTeamName() {
        testTeam.changeTeamName("Different Test Name");
        assertEquals("Different Test Name", testTeam.getTeamName());
    }

    @Test
    public void testChangeFavouriteFalseToTrue() {
        boolean previous = testTeam.isFavourite();
        testTeam.changeFavourite();
        assertEquals(testTeam.isFavourite(), !previous);
    }

    @Test
    public void testChangeFavouriteTrueToFalse() {
        boolean previous = testTeam.isFavourite();
        testTeam.changeFavourite();
        testTeam.changeFavourite();
        assertEquals(testTeam.isFavourite(), previous);
    }

    @Test
    public void testAddHeroToTeam() {
        testTeam.addHeroToTeam(testHero);
        assertEquals(testHero, testTeam.getHero(0));
        assertEquals(1, testTeam.getTeamMembers().size());
    }

    @Test
    public void testRemoveHeroFromTeam() {
        testTeam.addHeroToTeam(testHero);
        testTeam.addHeroToTeam(testHero2);
        testTeam.removeHeroFromTeam(testHero);
        assertEquals(testHero2, testTeam.getHero(0));
        assertEquals(1, testTeam.getTeamMembers().size());
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

    @Test
    public void testLogEventOnceAdd() {
        testTeam.addHeroToTeam(testHero);
        List<String> eventDescriptions = new ArrayList<>();
        eventDescriptions.add("Event log cleared.");
        eventDescriptions.add("A " + testHero.getHeroType().getHeroTypeName() + " named " + testHero.getHeroGivenName()
            + " was added to " + testTeam.getTeamName() + "!");
        int counter = 0;
        for (Event e : EventLog.getInstance()) {
            assertEquals(eventDescriptions.get(counter), e.getDescription());
            counter++;
        }
    }

    @Test
    public void testLogEventMultipleAdd() {
        testTeam.addHeroToTeam(testHero);
        testTeam.addHeroToTeam(testHero);
        testTeam.addHeroToTeam(testHero2);
        List<String> eventDescriptions = new ArrayList<>();
        eventDescriptions.add("Event log cleared.");
        eventDescriptions.add("A " + testHero.getHeroType().getHeroTypeName() + " named "
                + testHero.getHeroGivenName()
                + " was added to " + testTeam.getTeamName() + "!");
        eventDescriptions.add("A " + testHero.getHeroType().getHeroTypeName() + " named "
                + testHero.getHeroGivenName()
                + " was added to " + testTeam.getTeamName() + "!");
        eventDescriptions.add("A " + testHero2.getHeroType().getHeroTypeName() + " named "
                + testHero2.getHeroGivenName()
                + " was added to " + testTeam.getTeamName() + "!");
        int counter = 0;
        for (Event e : EventLog.getInstance()) {
            assertEquals(eventDescriptions.get(counter), e.getDescription());
            counter++;
        }

    }

    @Test
    public void testLogEventOnceRemove() {
        testTeam.addHeroToTeam(testHero);
        EventLog.getInstance().clear();
        testTeam.removeHeroFromTeam(testHero);
        List<String> eventDescriptions = new ArrayList<>();
        eventDescriptions.add("Event log cleared.");
        eventDescriptions.add("The " + testHero.getHeroType().getHeroTypeName() + " named " + testHero.getHeroGivenName()
                + " was removed from " + testTeam.getTeamName() + "!");
        int counter = 0;
        for (Event e : EventLog.getInstance()) {
            assertEquals(eventDescriptions.get(counter), e.getDescription());
            counter++;
        }
    }

    @Test
    public void testLogEventMultipleRemove() {
        testTeam.addHeroToTeam(testHero);
        testTeam.addHeroToTeam(testHero);
        testTeam.addHeroToTeam(testHero2);
        EventLog.getInstance().clear();
        testTeam.removeHeroFromTeam(testHero);
        testTeam.removeHeroFromTeam(testHero);
        testTeam.removeHeroFromTeam(testHero2);
        List<String> eventDescriptions = new ArrayList<>();
        eventDescriptions.add("Event log cleared.");
        eventDescriptions.add("The " + testHero.getHeroType().getHeroTypeName() + " named "
                + testHero.getHeroGivenName()
                + " was removed from " + testTeam.getTeamName() + "!");
        eventDescriptions.add("The " + testHero.getHeroType().getHeroTypeName() + " named "
                + testHero.getHeroGivenName()
                + " was removed from " + testTeam.getTeamName() + "!");
        eventDescriptions.add("The " + testHero2.getHeroType().getHeroTypeName() + " named "
                + testHero2.getHeroGivenName()
                + " was removed from " + testTeam.getTeamName() + "!");
        int counter = 0;
        for (Event e : EventLog.getInstance()) {
            assertEquals(eventDescriptions.get(counter), e.getDescription());
            counter++;
        }
    }

    @Test
    public void testLogEventMultipleMixed() {
        testTeam.addHeroToTeam(testHero);
        testTeam.addHeroToTeam(testHero2);
        testTeam.removeHeroFromTeam(testHero);
        List<String> eventDescriptions = new ArrayList<>();
        eventDescriptions.add("Event log cleared.");
        eventDescriptions.add("A " + testHero.getHeroType().getHeroTypeName() + " named "
                + testHero.getHeroGivenName()
                + " was added to " + testTeam.getTeamName() + "!");
        eventDescriptions.add("A " + testHero2.getHeroType().getHeroTypeName() + " named "
                + testHero2.getHeroGivenName()
                + " was added to " + testTeam.getTeamName() + "!");
        eventDescriptions.add("The " + testHero.getHeroType().getHeroTypeName() + " named "
                + testHero.getHeroGivenName()
                + " was removed from " + testTeam.getTeamName() + "!");
        int counter = 0;
        for (Event e : EventLog.getInstance()) {
            assertEquals(eventDescriptions.get(counter), e.getDescription());
            counter++;
        }
    }


}
