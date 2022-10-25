package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import ui.InitializeHeroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Modelled after JsonWriterTest in JsonSerializationDemo

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            TeamList tl = new TeamList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // passes
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            TeamList tl = new TeamList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTeamList.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTeamList.json");
            tl = reader.read();
            assertEquals(0, tl.getSavedTeams().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            TeamList tl = new TeamList();
            Team team1 = new Team("Team1");
            Team team2 = new Team("Team2");
            team1.addHeroToTeam(new Hero("Dismas", InitializeHeroes.initializeHighwayMan()));
            team1.addHeroToTeam(new Hero("Reynauld", InitializeHeroes.initializeCrusader()));
            team2.addHeroToTeam(new Hero("Paracelsus", InitializeHeroes.initializePlagueDoctor()));
            team2.addHeroToTeam(new Hero("Junia", InitializeHeroes.initializeVestal()));
            tl.addTeam(team1);
            tl.addTeam(team2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTeamList.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTeamList.json");
            tl = reader.read();
            assertEquals(2, tl.getSavedTeams().size());
            checkTeam(team1, tl.getTeam(0));
            checkTeam(team2, tl.getTeam(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
