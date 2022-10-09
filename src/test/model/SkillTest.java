package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        ArrayList<Stat> resultStatList = new ArrayList<>();
        resultStatList.add(testStat);
        assertEquals(resultStatList, testSkill.getSkillStats());
    }

    @Test
    public void testRemoveSkillStat() {
        testSkill.addSkillStat(testStat);
        testSkill.addSkillStat(testStat2);
        ArrayList<Stat> resultStatList = new ArrayList<>();
        resultStatList.add(testStat);
        resultStatList.add(testStat2);
        testSkill.removeSkillStat(testStat);
        resultStatList.remove(testStat);
        assertEquals(resultStatList, testSkill.getSkillStats());
    }

}
