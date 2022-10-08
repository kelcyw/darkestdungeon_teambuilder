package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.Boss.BOSS_LEVEL_INCREMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BossTest {
    Boss testBoss;
    Stat testStat;
    Stat testStat2;

    @BeforeEach
    void runBefore() {
        testBoss = new Boss();
        testStat = new Stat("Stat", 100);
        testStat2 = new Stat("Stat2", 110);
    }

    @Test
    public void testIncreaseBossLevel() {
        int previousLevel = testBoss.getBossLevel();
        testBoss.increaseBossLevel();
        assertEquals(previousLevel + BOSS_LEVEL_INCREMENT, testBoss.getBossLevel());
    }

    @Test
    public void testDecreaseBossLevel() {
        testBoss.increaseBossLevel();
        testBoss.increaseBossLevel();
        int previousLevel = testBoss.getBossLevel();
        testBoss.decreaseBossLevel();
        assertEquals(previousLevel - BOSS_LEVEL_INCREMENT, testBoss.getBossLevel());
    }

    @Test
    public void testAddBossStat() {
        testBoss.addBossStat(testStat);
        List<Stat> comparisonList = new ArrayList<>();
        comparisonList.add(testStat);
        assertEquals(comparisonList, testBoss.getBossStats());
    }

    @Test
    public void testRemoveBossStat() {
        testBoss.addBossStat(testStat);
        testBoss.addBossStat(testStat2);
        testBoss.removeBossStat(testStat);
        List<Stat> comparisonList = new ArrayList<>();
        comparisonList.add(testStat2);
        assertEquals(comparisonList, testBoss.getBossStats());
    }

    @Test
    public void testDetermineBossWeakness() {
        testBoss.addBossStat(testStat);
        testBoss.addBossStat(testStat2);
        assertEquals(testStat, testBoss.determineBossWeakness());
    }
}
