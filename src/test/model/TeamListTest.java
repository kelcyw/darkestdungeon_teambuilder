package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamListTest {
    Team testTeam1;
    Team testTeam2;
    TeamList testTeamList;

    @BeforeEach
    public void runBefore() {
        testTeam1 = new Team("Test Team 1");
        testTeam2 = new Team("Test Team 2");
        testTeamList = new TeamList();
    }

    @Test
    public void testAddTeam() {
        testTeamList.addTeam(testTeam1);
        assertEquals(testTeam1, testTeamList.getTeam(0));
    }

    @Test
    public void testRemoveTeam() {
        testTeamList.addTeam(testTeam1);
        testTeamList.addTeam(testTeam2);
        List<Team> currentList = new ArrayList<>();
        currentList.add(testTeam1);
        currentList.add(testTeam2);
        testTeamList.removeTeam(testTeam2);
        currentList.remove(testTeam2);
        assertEquals(currentList, testTeamList.getSavedTeams());
    }

}
