package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// The main application class that contains information for the main menu
// JSON-related code is modelled after JsonSerializationDemo

public class TeamMakerApp {
    private TeamList savedTeams;
    private List<HeroType> availableHeroTypes;
    private Scanner input;
    private static final String JSON_STORE = "./data/teamList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private HeroType highwayman;
    private HeroType crusader;
    private HeroType plaguedoctor;
    private HeroType vestal;

    // EFFECTS: runs team maker application
    public TeamMakerApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTeamMaker();
    }

    // MODIFIES: this
    // EFFECTS: handles user interaction
    private void runTeamMaker() {
        boolean isRunning = true;
        String command = null;
        savedTeams =  new TeamList();
        availableHeroTypes = new ArrayList<>();

        input = new Scanner(System.in);

        initializeHeroTypes();

        while (isRunning) {
            displayMenu();

            command = input.next();
            command = command.toUpperCase();

            if (command.equals("EXIT")) {
                isRunning = false;
            } else {
                processMainMenuCommand(command);
            }
        }

        System.out.println("\nExiting application.");
    }

    // EFFECTS: shows currently saved teams and menu of options for user
    private void displayMenu() {
        System.out.println("\nWelcome to the Darkest Dungeon Team Maker!");
        System.out.println("Your currently saved teams: ");
        printSavedTeams();
        System.out.println("\nWhat would you like to do?");
        System.out.println("- Make a new team (NEW)");
        System.out.println("- Edit one of my saved teams (EDIT)");
        System.out.println("- Delete one of my saved teams (DELETE)");
        System.out.println("- Saved all my current teams (SAVE)");
        System.out.println("- Load another list of teams (LOAD)");
        System.out.println("- Exit the team maker (EXIT)");
    }

    // MODIFIES: this
    // EFFECTS: initializes all hero types
    private void initializeHeroTypes() {
        availableHeroTypes.add(InitializeHeroes.initializeHighwayMan());
        availableHeroTypes.add(InitializeHeroes.initializeCrusader());
        availableHeroTypes.add(InitializeHeroes.initializePlagueDoctor());
        availableHeroTypes.add(InitializeHeroes.initializeVestal());
    }


    // MODIFIES: this
    // EFFECTS: processes the user command for main menu
    private void processMainMenuCommand(String command) {
        if (command.equals("NEW")) {
            optionNew();
        } else if (command.equals("EDIT")) {
            optionEdit();
        } else if (command.equals("DELETE")) {
            optionDelete();
        } else if (command.equals("SAVE")) {
            optionSave();
        } else if (command.equals("LOAD")) {
            optionLoad();
        } else {
            System.out.println("\nNot a valid command!");
        }
    }

    // EFFECTS: prints out all currently saved teams, adding a * to those that are favourited
    private void printSavedTeams() {
        String teamList = " ";
        for (Team team : savedTeams.getSavedTeams()) {
            if (team.isFavourite()) {
                teamList = teamList.concat(team.getTeamName() + "* ");
            } else {
                teamList = teamList.concat(team.getTeamName() + " ");
            }
        }
        System.out.println("\n" + teamList);
    }

    // MODIFIES: this
    // EFFECTS: makes a new team and adds it to saved teams
    private void optionNew() {
        System.out.println("What would you like to name your new team?");
        String command = input.next();
        if (checkForExistingTeam(command)) {
            Team newTeam = new Team(command);
            savedTeams.addTeam(newTeam);
            System.out.println("\nThe new team " + command + " has been created!");
        } else {
            System.out.println("There is already a team with that name!");
        }
    }

    // EFFECTS: helper function for optionNew, checks to see if there is a team with the given name
    private boolean checkForExistingTeam(String newName) {
        for (Team t : savedTeams.getSavedTeams()) {
            String teamNameUpperCase = t.getTeamName().toUpperCase();
            String newNameUpperCase = newName.toUpperCase();
            if (newNameUpperCase.equals(teamNameUpperCase)) {
                return false;
            }
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: allows the user to edit the given team
    private void optionEdit() {
        String command = null;
        System.out.println("\nPlease enter the name of the team you would like to edit: ");
        command = input.next();
        new EditMenu(command, savedTeams, availableHeroTypes);
    }

    // MODIFIES: this
    // EFFECTS: deletes the given team if it exists within the saved teams list
    //          if the team is not present, do nothing
    private void optionDelete() {
        String command = null;
        System.out.println("\nPlease enter the name of the team you would like to remove: ");
        command = input.next();
        deleteTeam(command);
    }

    // MODIFIES: this
    // EFFECTS: finds the first team w/ given name and removes it from the list
    private void deleteTeam(String teamName) {
        String givenTeamNameToCaps = teamName.toUpperCase();
        for (Team team : savedTeams.getSavedTeams()) {
            String currentTeamName = team.getTeamName().toUpperCase();
            if (currentTeamName.equals(givenTeamNameToCaps)) {
                savedTeams.getSavedTeams().remove(team);
                System.out.println("\n" + teamName + " has been removed!");
                break;
            }
        }
    }

    // EFFECTS: saves current list of teams to file
    private void optionSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(savedTeams);
            jsonWriter.close();
            System.out.println("Saved all teams to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads another list of teams from file
    private void optionLoad() {
        try {
            savedTeams = jsonReader.read();
            System.out.println("Loaded all teams from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
