package persistence;

import model.*;
import org.junit.jupiter.api.Test;

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

    // TODO: add test file
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

    // TODO: add test file
    @Test
    void testWriterGeneralWorkroom() {
        try {
            TeamList tl = new TeamList();
            Team team1 = new Team("Team1");
            Team team2 = new Team("Team2");
            team1.addHeroToTeam(initializeHighwayman());
            team1.addHeroToTeam(initializeCrusader());
            team2.addHeroToTeam(initializePlagueDoctor());
            team2.addHeroToTeam(initializeVestal());
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

    // EFFECTS: helper function for initializing a highwayman to add to test team
    private Hero initializeHighwayman() {
        Stat knockBack = new Stat("Knockback", 100);
        Stat moreCrit = new Stat("Increase Crits Received", 100);
        Stat bleed = new Stat("Bleed", 100);

        Skill highwaymanSkill1 = new Skill("Wicked_Slice");
        Skill highwaymanSkill2 = new Skill("Pistol_Shot");
        Skill highwaymanSkill3 = new Skill("Point_Blank_Shot");
        Skill highwaymanSkill4 = new Skill("Grapeshot_Blast");
        Skill highwaymanSkill5 = new Skill("Open_Vein");
        highwaymanSkill3.addSkillStat(knockBack);
        highwaymanSkill4.addSkillStat(moreCrit);
        highwaymanSkill5.addSkillStat(bleed);
        List<Skill> highwaymanSkillset = new ArrayList<>();
        highwaymanSkillset.add(highwaymanSkill1);
        highwaymanSkillset.add(highwaymanSkill2);
        highwaymanSkillset.add(highwaymanSkill3);
        highwaymanSkillset.add(highwaymanSkill4);
        highwaymanSkillset.add(highwaymanSkill5);

        HeroType highwayman = new HeroType("HIGHWAYMAN", highwaymanSkillset);
        return new Hero("Dismas", highwayman);
    }

    // EFFECTS: helper function for initializing a crusader to add to test team
    private Hero initializeCrusader() {
        Stat stun = new Stat("Stun", 100);

        Skill crusaderSkill1 = new Skill("Smite");
        Skill crusaderSkill2 = new Skill("Zealous_Accusation");
        Skill crusaderSkill3 = new Skill("Stunning_Blow");
        Skill crusaderSkill4 = new Skill("Holy_Lance");
        Skill crusaderSkill5 = new Skill("Battle_Heal");
        crusaderSkill3.addSkillStat(stun);

        List<Skill> crusaderSkillset = new ArrayList<>();
        crusaderSkillset.add(crusaderSkill1);
        crusaderSkillset.add(crusaderSkill2);
        crusaderSkillset.add(crusaderSkill3);
        crusaderSkillset.add(crusaderSkill4);
        crusaderSkillset.add(crusaderSkill5);

        HeroType crusader = new HeroType("CRUSADER", crusaderSkillset);
        return new Hero("Reynauld", crusader);
    }

    // EFFECTS: helper function for initializing a plague doctor to add to test team
    private Hero initializePlagueDoctor() {
        Stat blight = new Stat("Blight", 100);
        Stat stun = new Stat("Stun", 100);
        Stat bleed = new Stat("Bleed", 100);
        Stat damageSpeedBuff = new Stat("Buff Damage and Speed", 200);

        Skill plagueDoctorSkill1 = new Skill("Noxious_Blast");
        Skill plagueDoctorSkill2 = new Skill("Plague_Grenade");
        Skill plagueDoctorSkill3 = new Skill("Blinding_Gas");
        Skill plagueDoctorSkill4 = new Skill("Incision");
        Skill plagueDoctorSkill5 = new Skill("Emboldening_Vapours");
        plagueDoctorSkill1.addSkillStat(blight);
        plagueDoctorSkill2.addSkillStat(blight);
        plagueDoctorSkill3.addSkillStat(stun);
        plagueDoctorSkill4.addSkillStat(bleed);
        plagueDoctorSkill5.addSkillStat(damageSpeedBuff);

        List<Skill> plagueDoctorSkillset = new ArrayList<>();
        plagueDoctorSkillset.add(plagueDoctorSkill1);
        plagueDoctorSkillset.add(plagueDoctorSkill2);
        plagueDoctorSkillset.add(plagueDoctorSkill3);
        plagueDoctorSkillset.add(plagueDoctorSkill4);
        plagueDoctorSkillset.add(plagueDoctorSkill5);

        HeroType plaguedoctor = new HeroType("PLAGUEDOCTOR", plagueDoctorSkillset);
        return new Hero("Paracelsus", plaguedoctor);
    }

    // EFFECTS: helper function for initializing a plague doctor to add to test team
    private Hero initializeVestal() {
        Stat stun = new Stat("Stun", 100);

        Skill vestalSkill1 = new Skill("Judgement");
        Skill vestalSkill2 = new Skill("Dazzling_Light");
        Skill vestalSkill3 = new Skill("Divine_Grace");
        Skill vestalSkill4 = new Skill("Divine_Comfort");
        Skill vestalSkill5 = new Skill("Illumination");
        vestalSkill2.addSkillStat(stun);

        List<Skill> vestalSkillset = new ArrayList<>();
        vestalSkillset.add(vestalSkill1);
        vestalSkillset.add(vestalSkill2);
        vestalSkillset.add(vestalSkill3);
        vestalSkillset.add(vestalSkill4);
        vestalSkillset.add(vestalSkill5);

        HeroType vestal = new HeroType("VESTAL", vestalSkillset);
        return new Hero("Junia", vestal);
    }
}
