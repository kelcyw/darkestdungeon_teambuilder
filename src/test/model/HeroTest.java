package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeroTest {

    Hero testHero;

    @BeforeEach
    void runBefore() {
        testHero = new Hero("testName", null);
    }

    @Test
    public void testChangeHeroGivenName() {
        testHero.changeHeroGivenName("Dismas");
        assertEquals("Dismas", testHero.getHeroGivenName());
    }

}