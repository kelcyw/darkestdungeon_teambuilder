package ui;

import model.Hero;
import model.HeroType;
import model.Team;
import model.TeamList;

import java.util.List;
import java.util.Scanner;

// EditMenu contains information and functionality for the edit menu

public class EditMenu {
    private Scanner input;
    private String teamName;

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
        int wrongMatches = 0;
        for (Team team : savedTeams.getSavedTeams()) {
            String actualTeamNameUpperCase = team.getTeamName().toUpperCase();
            String givenTeamNameUpperCase = teamName.toUpperCase();
            if (actualTeamNameUpperCase.equals(givenTeamNameUpperCase)) {
                printHeroNamesAndTypes(team);
                displayEditMenu();
                command = input.next();
                command = command.toUpperCase();
                processEditMenuCommand(command, team, savedTeams, availableHeroTypes);
                break;
            }
            wrongMatches++;
        }
        if (wrongMatches == savedTeams.getSavedTeams().size()) {
            System.out.println("There is no team with that name!");
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
        System.out.println("- Favourite or unfavourite this team (FAV)");
        System.out.println("- Return to the main menu (RETURN)");
    }

    // MODIFIES: this
    // EFFECTS: processes the user command for edit menu
    private void processEditMenuCommand(String command, Team team,
                                        TeamList savedTeams, List<HeroType> availableHeroTypes) {
        if (command.equals("ADD")) {
            optionAddHero(team, savedTeams, availableHeroTypes);
        } else if (command.equals("REMOVE")) {
            optionRemoveHero(team, savedTeams, availableHeroTypes, "");
        } else if (command.equals("EDITHERO")) {
            optionChangeHero(team, savedTeams, availableHeroTypes);
        } else if (command.equals("ANALYZE")) {
            optionAnalyzeTeam(team, savedTeams, availableHeroTypes);
        } else if (command.equals("RENAME")) {
            optionRenameTeam(team, savedTeams, availableHeroTypes);
        }  else if (command.equals("FAV")) {
            optionFavTeam(team, savedTeams, availableHeroTypes);
        } else if (command.equals("RETURN")) {
            System.out.println("\nReturning to main menu ...");
        } else {
            System.out.println("\nNot a valid command!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a hero to the given team
    public void optionAddHero(Team team, TeamList savedTeams, List<HeroType> availableHeroTypes) {
        HeroType inputHeroType;
        String inputHeroTypeName;
        String inputHeroName;
        System.out.println("Here are the available hero types: ");
        printAvailableHeroTypes(availableHeroTypes);
        System.out.println("Enter the hero type you would like for the new hero: ");
        inputHeroTypeName = input.next();
        inputHeroTypeName = inputHeroTypeName.toUpperCase();
        inputHeroType = findHeroType(inputHeroTypeName, availableHeroTypes);
        if (inputHeroType == null) {
            System.out.println("The selected hero type was not valid!");
        } else {
            System.out.println("Enter the name you would like for the new hero: ");
            inputHeroName = input.next();
            team.addHeroToTeam(new Hero(inputHeroName, inputHeroType));
            System.out.println("New hero added! Name: " + inputHeroName + ", Type: " + inputHeroTypeName);
            editTeam(team.getTeamName(), savedTeams, availableHeroTypes);
        }

    }

    // EFFECTS: gets the hero type based on type name
    public HeroType findHeroType(String heroTypeName, List<HeroType> availableHeroTypes) {
        for (HeroType ht : availableHeroTypes) {
            if (heroTypeName.equals(ht.getHeroTypeName())) {
                if (heroTypeName.equals("HIGHWAYMAN")) {
                    return InitializeHeroes.initializeHighwayMan();
                } else if (heroTypeName.equals("CRUSADER")) {
                    return InitializeHeroes.initializeCrusader();
                } else if (heroTypeName.equals("PLAGUEDOCTOR")) {
                    return InitializeHeroes.initializePlagueDoctor();
                } else if (heroTypeName.equals("VESTAL")) {
                    return InitializeHeroes.initializeVestal();
                }
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: removes a hero with the given name from the team
    //          if a hero w/ that name can't be found, do nothing
    public void removeHero(Team team, TeamList savedTeams, List<HeroType> availableHeroTypes, String heroName) {
        int wrongMatches = 0;
        for (Hero h : team.getTeamMembers()) {
            if (heroName.equals(h.getHeroGivenName())) {
                team.removeHeroFromTeam(h);
                System.out.println("Successfully removed " + heroName + " from the team!");
                editTeam(teamName, savedTeams, availableHeroTypes);
                break;
            }
            wrongMatches++;
        }
        if (wrongMatches == team.getTeamMembers().size()) {
            System.out.println("There is no hero with that given name!");
            editTeam(teamName, savedTeams, availableHeroTypes);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a hero from the given team
    public void optionRemoveHero(Team team, TeamList savedTeams, List<HeroType> availableHeroTypes, String heroName) {
        System.out.println("Enter the name of the hero you would like to remove: ");
        heroName = input.next();
        removeHero(team, savedTeams, availableHeroTypes, heroName);
    }

    // EFFECTS: determines team's strengths
    public void optionAnalyzeTeam(Team team, TeamList savedTeams, List<HeroType> availableHeroTypes) {
        System.out.println("This team has: " + team.determineStrengths());
        editTeam(team.getTeamName(), savedTeams, availableHeroTypes);
    }

    // MODIFIES: this
    // EFFECTS: selects and/or deselects skills for heroes on the team
    public void optionChangeHero(Team team, TeamList savedTeams, List<HeroType> availableHeroTypes) {
        printHeroNamesAndTypes(team);
        System.out.println("Please enter the name of the hero that you would like to edit: ");
        String heroName = input.next();
        changeHero(team, heroName, savedTeams, availableHeroTypes);
    }

    // MODIFIES: this
    // EFFECTS: calls the hero editing menu if the given hero exists
    //          if not, does nothing
    private void changeHero(Team team, String heroName,
                            TeamList savedTeams, List<HeroType> availableHeroesTypes) {
        int wrongMatches = 0;
        for (Hero h : team.getTeamMembers()) {
            if (heroName.equals(h.getHeroGivenName())) {
                new HeroMenu(team, h);
                editTeam(team.getTeamName(), savedTeams, availableHeroesTypes);
                break;
            }
            wrongMatches++;
        }
        if (wrongMatches == team.getTeamMembers().size()) {
            System.out.println("There is no hero with that given name!");
        }
    }

    // MODIFIES: this
    // EFFECTS: renames team
    public void optionRenameTeam(Team team, TeamList savedTeams, List<HeroType> availableHeroTypes) {
        System.out.println("Enter the new team name: ");
        String newTeamName = input.next();
        team.changeTeamName(newTeamName);
        System.out.println("The team's name is now: " + newTeamName + "!");
        editTeam(team.getTeamName(), savedTeams, availableHeroTypes);
    }

    // EFFECTS: prints given names and types of heroes in a team
    private void printHeroNamesAndTypes(Team team) {
        System.out.println("\nThe team '" + team.getTeamName() + "' has the heroes: ");
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

    // MODIFIES: this
    // EFFECTS: changes the team's favourite status
    private void optionFavTeam(Team team, TeamList savedTeams, List<HeroType> availableHeroTypes) {
        team.changeFavourite();
        System.out.println("The team's favourite status is now: " + team.isFavourite() + "!");
        editTeam(team.getTeamName(), savedTeams, availableHeroTypes);
    }
}
