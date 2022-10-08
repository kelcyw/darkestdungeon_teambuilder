package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.Hero.HERO_LEVEL_INCREMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HeroTest {
    Hero testHero;
    HeroType testHeroType;
    Skill testSkill;
    Skill testSkill2;
    List<Skill> testSkillSet;

    @BeforeEach
    void runBefore() {
        testSkill = new Skill("Skill");
        testSkill2 = new Skill("Skill2");
        testSkillSet = new ArrayList<>();
        testSkillSet.add(testSkill);
        testSkillSet.add(testSkill2);
        testHeroType = new HeroType("Test Hero Type", testSkillSet);
        testHero = new Hero("testName", testHeroType);
    }

    @Test
    public void testChangeHeroGivenName() {
        testHero.changeHeroGivenName("Dismas");
        assertEquals("Dismas", testHero.getHeroGivenName());
    }

    @Test
    public void testChangeHeroType() {
        HeroType testHeroType2 = new HeroType("Test Hero Type 2", testSkillSet);
        testHero.changeHeroType(testHeroType2);
        assertEquals(testHeroType2, testHero.getHeroType());
    }

    @Test
    public void testIncreaseHeroLevel() {
        int previousLevel = testHero.getHeroLevel();
        testHero.increaseHeroLevel();
        assertEquals(previousLevel + HERO_LEVEL_INCREMENT, testHero.getHeroLevel());
    }

    @Test
    public void testDecreaseHeroLevel() {
        testHero.setHeroLevel(2);
        int previousLevel = testHero.getHeroLevel();
        testHero.decreaseHeroLevel();
        assertEquals(previousLevel - HERO_LEVEL_INCREMENT, testHero.getHeroLevel());
    }

}