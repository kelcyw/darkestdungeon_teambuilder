package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SkillTest {
    Skill testSkill;
    Stat testStat;
    Stat testStat2;

    @BeforeEach
    void runBefore() {
        testSkill = new Skill("Skill");
        testStat = new Stat("Stat", 100);
        testStat2 = new Stat("Stat2", 90);
    }

    @Test
    public void testChangeSelectedStatus() {
        boolean previousStatus = testSkill.isSelected();
        testSkill.changeSelectedStatus();
        assertEquals(testSkill.isSelected(), !previousStatus);
    }

    @Test
    public void testAddSkillStat() {
        testSkill.addSkillStat(testStat);
        List<Stat> statList = testSkill.getSkillStats();

        assertTrue(statList.contains(testStat));
        assertEquals(1, statList.size());
    }

    @Test
    public void testRemoveSkillStat() {
        testSkill.addSkillStat(testStat);
        testSkill.addSkillStat(testStat2);
        testSkill.removeSkillStat(testStat);
        List<Stat> statList = testSkill.getSkillStats();

        assertTrue(statList.contains(testStat2));
        assertFalse(statList.contains(testStat));
        assertEquals(1, statList.size());
    }

}
