package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HeroTypeTest {
    HeroType testHeroType;
    Skill testSkill1;
    Skill testSkill2;
    Skill testSkill3;
    List<Skill> testSkillList;

    @BeforeEach
    public void runBefore() {
        testSkill1 = new Skill("Skill 1");
        testSkill2 = new Skill("Skill 2");
        testSkill3 = new Skill("Skill 3");
        testSkillList = new ArrayList<>();
        testSkillList.add(testSkill1);
        testSkillList.add(testSkill2);

        testHeroType = new HeroType("Test Hero Type", testSkillList);
    }

    @Test
    public void testChangeHeroTypeName() {
        testHeroType.changeHeroTypeName("New Type Name");

        assertEquals("New Type Name", testHeroType.getHeroTypeName());
    }

    @Test
    public void testAddHeroSkill() {
        testHeroType.addHeroSkill(testSkill3);
        List<Skill> skillList = testHeroType.getHeroSkills();

        assertTrue(skillList.contains(testSkill3));
        assertEquals(3, skillList.size());
    }

    @Test
    public void testRemoveHeroSkill() {
        testHeroType.removeHeroSkill(testSkill2);
        List<Skill> skillList = testHeroType.getHeroSkills();

        assertFalse(skillList.contains(testSkill2));
        assertEquals(1, skillList.size());
    }

    @Test
    public void testGetHeroSelectedSkillsNone() {
        List<Skill> selectedSkills = new ArrayList<>();

        assertEquals(selectedSkills, testHeroType.getHeroSelectedSkills());
        assertEquals(0, testHeroType.getHeroSelectedSkills().size());
    }

    @Test
    public void testGetHeroSelectedSkills() {
        testSkill1.changeSelectedStatus();
        List<Skill> selectedSkills = new ArrayList<>();

        selectedSkills.add(testSkill1);

        assertEquals(selectedSkills, testHeroType.getHeroSelectedSkills());
        assertEquals(1, testHeroType.getHeroSelectedSkills().size());
    }

    @Test
    public void testGetHeroSelectedSkillsMultiple() {
        testSkill1.changeSelectedStatus();
        testSkill2.changeSelectedStatus();

        List<Skill> selectedSkills = new ArrayList<>();

        selectedSkills.add(testSkill1);
        selectedSkills.add(testSkill2);
        assertEquals(selectedSkills, testHeroType.getHeroSelectedSkills());
        assertEquals(2, testHeroType.getHeroSelectedSkills().size());
    }

}
