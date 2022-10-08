package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Stat.CHANCE_INCREMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatTest {
    Stat testStat;

    @BeforeEach
    public void runBefore() {
        testStat = new Stat("Stat", 100);
    }

    @Test
    public void testIncreaseStatPercentage() {
        int previous = testStat.getStatChance();
        testStat.increaseStatChance();
        assertEquals(previous + CHANCE_INCREMENT, testStat.getStatChance());
    }

    @Test
    public void testDecreaseStatPercentage() {
        int previous = testStat.getStatChance();
        testStat.decreaseStatChance();
        assertEquals(previous - CHANCE_INCREMENT, testStat.getStatChance());
    }
}
