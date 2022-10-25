package ui;

// contains methods for initializing hero types

import model.HeroType;
import model.Skill;
import model.Stat;

import java.util.ArrayList;
import java.util.List;

public class InitializeHeroes {

    // MODIFIES: this
    // EFFECTS: initializes the highwayman character
    public static HeroType initializeHighwayMan() {
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
        return highwayman;
    }

    // MODIFIES: this
    // EFFECTS: initializes the crusader character
    public static HeroType initializeCrusader() {
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
        return crusader;
    }

    // MODIFIES: this
    // EFFECTS: initializes the plague doctor character
    public static HeroType initializePlagueDoctor() {
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
        return plaguedoctor;
    }

    // MODIFIES: this
    // EFFECTS: initializes the vestal character
    public static HeroType initializeVestal() {
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
        return vestal;
    }
}
