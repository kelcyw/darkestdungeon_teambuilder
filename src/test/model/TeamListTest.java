package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamListTest {
    Team testTeam1;
    Team testTeam2;
    TeamList testTeamList;

    @BeforeEach
    public void runBefore() {
        testTeam1 = new Team("Test Team 1");
        testTeam2 = new Team("Test Team 2");
        testTeamList = new TeamList();
        testTeamList.addTeam(testTeam1);
        testTeamList.addTeam(testTeam2);
    }

    @Test
    public void testAddTeam() {
        testTeamList.addTeam(testTeam2);
        List<Team> savedTeamsList = testTeamList.getSavedTeams();

        assertEquals(testTeam1, testTeamList.getTeam(0));
        assertEquals(testTeam2, testTeamList.getTeam(1));
        assertEquals(testTeam2, testTeamList.getTeam(2));
        assertEquals(3, savedTeamsList.size());
    }

    @Test
    public void testRemoveTeam() {
        testTeamList.removeTeam(testTeam2);
        List<Team> savedTeamsList = testTeamList.getSavedTeams();

        assertEquals(testTeam1, testTeamList.getTeam(0));
        assertEquals(1, savedTeamsList.size());
    }

    @Test
    public void testGetFavouriteTeamsNone() {
        List<Team> favouriteTeams = testTeamList.getFavouriteTeams();

        assertEquals(0, favouriteTeams.size());
    }

    @Test
    public void testGetFavouriteTeamsOne() {
        testTeam1.changeFavourite();
        List<Team> favouriteTeams = testTeamList.getFavouriteTeams();

        assertTrue(favouriteTeams.contains(testTeam1));
        assertEquals(1, favouriteTeams.size());
    }

    @Test
    public void testGetFavouriteTeamsMultiple() {
        testTeam1.changeFavourite();
        testTeam2.changeFavourite();
        List<Team> favouriteTeams = testTeamList.getFavouriteTeams();

        assertTrue(favouriteTeams.contains(testTeam1));
        assertTrue(favouriteTeams.contains(testTeam2));
        assertEquals(2, favouriteTeams.size());
    }

}
