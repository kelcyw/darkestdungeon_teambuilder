package ui;

import model.Hero;
import model.HeroType;
import model.Team;
import model.TeamList;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Scanner;

public class EditMenu {
    Scanner input;
    String teamName;

    // EFFECTS: runs the edit menu
    public EditMenu(String teamName, TeamList savedTeams, List<HeroType> availableHeroTypes) {
        input = new Scanner(System.in);
        this.teamName = teamName;
        runEditMenu(teamName, savedTeams, availableHeroTypes);
    }

    // MODIFIES: this
    // EFFECTS: handles user interaction for edit menu
    private void runEditMenu(String teamName, TeamList savedTeams, List<HeroType> availableHeroTypes) {
        editTeam(teamName, savedTeams, availableHeroTypes);
    }

    // MODIFIES: this
    // EFFECTS: gives the user options to edit the given team
    //          if given team cannot be found, do nothing
    private void editTeam(String teamName, TeamList savedTeams, List<HeroType> availableHeroTypes) {
        String command = null;
        for (Team team : savedTeams.getSavedTeams()) {
            if (team.getTeamName().equals(teamName)) {
                printHeroNamesAndTypes(team);
                displayEditMenu();
                command = input.next();
                command = command.toUpperCase();
                processEditMenuCommand(command, team, availableHeroTypes);
            }
        }
    }

    // EFFECTS: shows options for editing the given team
    private void displayEditMenu() {
        System.out.println("\nWhat would you like to do? ");
        System.out.println("- Add a new hero (ADD)");
        System.out.println("- Remove a hero (REMOVE)");
        System.out.println("- Edit a hero (EDITHERO)");
        System.out.println("- Determine the team's strengths (ANALYZE)");
        System.out.println("- Change the team's name (RENAME)");
        System.out.println("- Return to the main menu (RETURN)");
    }

    // MODIFIES: this
    // EFFECTS: processes the user command for edit menu
    private void processEditMenuCommand(String command, Team team, List<HeroType> availableHeroTypes) {
        if (command.equals("ADD")) {
            optionAddHero(team, availableHeroTypes);
        } else if (command.equals("REMOVE")) {
            optionRemoveHero(team, "");
        } else if (command.equals("EDITHERO")) {
            optionChangeHero(team);
        } else if (command.equals("ANALYZE")) {
            optionAnalyzeTeam(team);
        } else if (command.equals("RENAME")) {
            optionRenameTeam(team);
        }  else if (command.equals("RETURN")) {
            System.out.println("\nReturning to main menu ...");
        } else {
            System.out.println("\nNot a valid command!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a hero to the given team
    public void optionAddHero(Team team, List<HeroType> availableHeroTypes) {
        HeroType inputHeroType = new HeroType("Placeholder", null);
        String inputHeroTypeName = "";
        String inputHeroName = "";
        System.out.println("Here are the available hero types: ");
        printAvailableHeroTypes(availableHeroTypes);
        System.out.println("Enter the hero type you would like for the new hero: ");
        inputHeroTypeName = input.next();
        inputHeroTypeName = inputHeroTypeName.toUpperCase();
        inputHeroType = findHeroType(inputHeroTypeName, availableHeroTypes);
        System.out.println("Enter the name you would like for the new hero: ");
        inputHeroName = input.next();
        if (inputHeroType == null) {
            System.out.println("The selected hero type was not valid!");
        } else {
            team.addHeroToTeam(new Hero(inputHeroName, inputHeroType));
            System.out.println("New hero added! Name: " + inputHeroName + ", Type: " + inputHeroTypeName);
        }

    }

    // EFFECTS: gets the hero type based on type name
    public HeroType findHeroType(String heroTypeName, List<HeroType> availableHeroTypes) {
        for (HeroType ht : availableHeroTypes) {
            if (heroTypeName.equals(ht.getHeroTypeName())) {
                return ht;
            }
        }
        return null;
    }

    // EFFECTS: removes a hero with the given name from the team
    //          if a hero w/ that name can't be found, do nothing
    public void removeHero(Team team, String heroName) {
        for (Hero h : team.getTeamMembers()) {
            if (heroName.equals(h.getHeroGivenName())) {
                team.removeHeroFromTeam(h);
                System.out.println("Successfully removed " + heroName + " from the team!");
                break;
            }
            System.out.println("There is no hero with that given name!");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a hero from the given team
    public void optionRemoveHero(Team team, String heroName) {
        System.out.println("Enter the name of the hero you would like to remove: ");
        heroName = input.next();
        removeHero(team, heroName);
    }

    // MODIFIES: this
    // EFFECTS: determines team's strengths
    public void optionAnalyzeTeam(Team team) {
        System.out.println("This team has: " + team.determineStrengths());
    }

    // MODIFIES: this
    // EFFECTS: selects and/or deselects skills for heroes on the team
    public void optionChangeHero(Team team) {
        printHeroNamesAndTypes(team);
        System.out.println("Please enter the name of the hero that you would like to edit: ");
        String heroName = input.next();
        changeHero(team, heroName);
    }

    // MODIFIES: this
    // EFFECTS: calls the hero editing menu if the given hero exists
    //          if not, does nothing
    private void changeHero(Team team, String heroName) {
        for (Hero h : team.getTeamMembers()) {
            if (heroName.equals(h.getHeroGivenName())) {
                new HeroMenu(team, h);
                break;
            }
            System.out.println("There is no hero with that given name!");
        }
    }

    // MODIFIES: this
    // EFFECTS: renames team
    public void optionRenameTeam(Team team) {
        System.out.println("Enter the new team name: ");
        String newTeamName = input.next();
        team.changeTeamName(newTeamName);
    }

    // EFFECTS: prints given names and types of heroes in a team
    private void printHeroNamesAndTypes(Team team) {
        System.out.println("\nThe team '" + teamName + "' has the heroes: ");
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

    // EFFECTS: prints available Hero types
    private void printAvailableHeroTypes(List<HeroType> availableHeroTypes) {
        String allHeroTypes = " ";
        for (HeroType ht : availableHeroTypes) {
            allHeroTypes = allHeroTypes.concat(ht.getHeroTypeName() + " ; ");
        }
        System.out.println(allHeroTypes);
    }
}
