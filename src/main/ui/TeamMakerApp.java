package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamMakerApp {

    private TeamList savedTeams;
    private List<HeroType> availableHeroTypes;
    private Scanner input;

    private HeroType highwayman;
    private HeroType crusader;
    private HeroType plagueDoctor;
    private HeroType vestal;

    // EFFECTS: runs team maker application
    public TeamMakerApp() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: handles user interaction
    public void runTeamMaker() {
        boolean isRunning = true;
        String command = null;

        initializeHeroTypes();

        while (isRunning) {
            displayMenu();
            command = input.next();
            command = command.toUpperCase();

            if (command.equals("EXIT")) {
                isRunning = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nExiting application.");
    }

    // EFFECTS: shows currently saved teams and menu of options for user
    public void displayMenu() {
        System.out.println("\nWelcome to the Darkest Dungeon Team Maker!");
        System.out.println("\nYour currently saved teams: ");
        printSavedTeams();
        System.out.println("\nWhat would you like to do?");
        System.out.println("\n- Make a new team (NEW)");
        System.out.println("\n- Edit one of my saved teams (EDIT)");
        System.out.println("\n- Delete one of my saved teams (DELETE)");
        System.out.println("\n- Exit the team maker (EXIT)");
    }

    // MODIFIES: this
    // EFFECTS: initializes all hero types
    public void initializeHeroTypes() {
        initializeHighwayMan();
        initializeCrusader();
        initializePlagueDoctor();
        initializeVestal();
    }

    // MODIFIES: this
    // EFFECTS: initializes the highwayman character
    public void initializeHighwayMan() {
        Stat knockBack = new Stat("Knockback", 100);
        Stat moreCrit = new Stat("Increase Crits Received", 100);
        Stat bleed = new Stat("Bleed", 100);

        Skill highwaymanSkill1 = new Skill("Wicked Slice");
        Skill highwaymanSkill2 = new Skill("Pistol Shot");
        Skill highwaymanSkill3 = new Skill("Point Blank Shot");
        Skill highwaymanSkill4 = new Skill("Grapeshot Blast");
        Skill highwaymanSkill5 = new Skill("Open Vein");
        highwaymanSkill3.addSkillStat(knockBack);
        highwaymanSkill4.addSkillStat(moreCrit);
        highwaymanSkill5.addSkillStat(bleed);
        List<Skill> highwaymanSkillset = new ArrayList<>();
        highwaymanSkillset.add(highwaymanSkill1);
        highwaymanSkillset.add(highwaymanSkill2);
        highwaymanSkillset.add(highwaymanSkill3);
        highwaymanSkillset.add(highwaymanSkill4);
        highwaymanSkillset.add(highwaymanSkill5);

        highwayman = new HeroType("Highwayman", highwaymanSkillset);
        availableHeroTypes.add(highwayman);
    }

    // MODIFIES: this
    // EFFECTS: initializes the crusader character
    public void initializeCrusader() {
        Stat stun = new Stat("Stun", 100);

        Skill crusaderSkill1 = new Skill("Smite");
        Skill crusaderSkill2 = new Skill("Zealous Accusation");
        Skill crusaderSkill3 = new Skill("Stunning Blow");
        Skill crusaderSkill4 = new Skill("Holy Lance");
        Skill crusaderSkill5 = new Skill("Battle Heal");
        crusaderSkill3.addSkillStat(stun);

        List<Skill> crusaderSkillset = new ArrayList<>();
        crusaderSkillset.add(crusaderSkill1);
        crusaderSkillset.add(crusaderSkill2);
        crusaderSkillset.add(crusaderSkill3);
        crusaderSkillset.add(crusaderSkill4);
        crusaderSkillset.add(crusaderSkill5);

        crusader = new HeroType("Crusader", crusaderSkillset);
        availableHeroTypes.add(crusader);
    }

    // MODIFIES: this
    // EFFECTS: initializes the plague doctor character
    public void initializePlagueDoctor() {
        Stat blight = new Stat("Blight", 100);
        Stat stun = new Stat("Stun", 100);
        Stat bleed = new Stat("Bleed", 100);
        Stat damageSpeedBuff = new Stat("Buff Damage and Speed", 200);

        Skill plagueDoctorSkill1 = new Skill("Noxious Blast");
        Skill plagueDoctorSkill2 = new Skill("Plague Grenade");
        Skill plagueDoctorSkill3 = new Skill("Blinding Gas");
        Skill plagueDoctorSkill4 = new Skill("Incision");
        Skill plagueDoctorSkill5 = new Skill("Emboldening Vapours");
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

        plagueDoctor = new HeroType("Plague Doctor", plagueDoctorSkillset);
        availableHeroTypes.add(plagueDoctor);
    }

    // MODIFIES: this
    // EFFECTS: initializes the vestal character
    public void initializeVestal() {
        Stat stun = new Stat("Stun", 100);

        Skill vestalSkill1 = new Skill("Judgement");
        Skill vestalSkill2 = new Skill("Dazzling Light");
        Skill vestalSkill3 = new Skill("Divine Grace");
        Skill vestalSkill4 = new Skill("Divine Comfort");
        Skill vestalSkill5 = new Skill("Illumination");
        vestalSkill2.addSkillStat(stun);

        List<Skill> vestalSkillset = new ArrayList<>();
        vestalSkillset.add(vestalSkill1);
        vestalSkillset.add(vestalSkill2);
        vestalSkillset.add(vestalSkill3);
        vestalSkillset.add(vestalSkill4);
        vestalSkillset.add(vestalSkill5);

        vestal = new HeroType("Vestal", vestalSkillset);
        availableHeroTypes.add(vestal);
    }

    // MODIFIES: this
    // EFFECTS: processes the user command
    public void processCommand(String command) {
        if (command.equals("NEW")) {
            optionNewTeam();
        } else if (command.equals("EDIT")) {
            optionEdit();
        } else if (command.equals("DELETE")) {
            optionDelete();
        } else {
            System.out.println("\nNot a valid command!");
        }
    }

    // EFFECTS: prints out all currently saved teams
    public void printSavedTeams() {
        String teamList = " ";
        for (Team team : savedTeams.getSavedTeams()) {
            teamList = teamList.concat(team.getTeamName() + " ");
        }
        System.out.println("\n" + teamList);
    }

    // MODIFIES: this
    // EFFECTS: makes a new team and adds it to saved teams
    public void optionNewTeam() {
        Team newTeam = new Team("New Team");
        savedTeams.addTeam(newTeam);
        System.out.println("\nNew team has been created!");
    }

    // MODIFIES: this
    // EFFECTS: allows the user to edit the given team
    public void optionEdit() {
        String command = null;
        System.out.println("\nPlease enter the name of the team you would like to edit: ");
        command = input.next();
        command = command.toUpperCase();
        editTeam(command);
    }

    // MODIFIES: this
    // EFFECTS: gives the user options to edit the given team
    //          if given team cannot be found, do nothing
    public void editTeam(String teamName) {
        for (Team team : savedTeams.getSavedTeams()) {
            if (team.getTeamName().equals(teamName)) {
                System.out.println("\nThe team '" + teamName + "' has the heroes: ");
                printHeroNamesAndTypes(team);
            }
        }
    }

    // EFFECTS: prints given names and types of heroes in a team
    public void printHeroNamesAndTypes(Team team) {
        String allHeroNamesAndTypes = " ";
        for (Hero h : team.getTeamMembers()) {
            allHeroNamesAndTypes =
                    allHeroNamesAndTypes.concat(
                            h.getHeroGivenName()
                            + ", "
                            + h.getHeroType().getHeroTypeName()
                            + "; ");
        }
        System.out.println("\n" + allHeroNamesAndTypes);
    }

    // MODIFIES: this
    // EFFECTS: deletes the given team if it exists within the saved teams list
    //          if the team is not present, do nothing
    public void optionDelete() {
        String command = null;
        System.out.println("\nPlease enter the name of the team you would like to remove: ");
        command = input.next();
        command = command.toUpperCase();
        deleteTeam(command);
    }

    // MODIFIES: this
    // EFFECTS: finds the first team w/ given name and removes it from the list
    public void deleteTeam(String teamName) {
        for (Team team : savedTeams.getSavedTeams()) {
            if (team.getTeamName().equals(teamName)) {
                savedTeams.getSavedTeams().remove(team);
                System.out.println("\n" + teamName + " has been removed!");
            }
        }
    }
}
