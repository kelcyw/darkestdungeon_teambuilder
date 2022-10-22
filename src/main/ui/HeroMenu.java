package ui;

import model.Hero;
import model.Skill;
import model.Team;

import java.util.Scanner;

// HeroMenu contains information and functionality for the hero edit menu

public class HeroMenu {
    Scanner input;
    Team team;
    Hero hero;

    // EFFECTS: runs the hero editor menu
    public HeroMenu(Team team, Hero hero) {
        input = new Scanner(System.in);
        this.team = team;
        this.hero = hero;
        runHeroMenu(team, hero);
    }

    // MODIFIES: this
    // EFFECTS: handles user interaction for the hero edit menu
    private void runHeroMenu(Team team, Hero hero) {
        editHero(team, hero);
    }

    // MODIFIES: this
    // EFFECTS: gives the user options to edit the given hero
    private void editHero(Team team, Hero hero) {
        displayEditHeroMenu();
        String command = input.next();
        command = command.toUpperCase();
        processEditHeroMenuCommand(command, team, hero);
    }

    // EFFECTS: displays the edit menu for a hero
    private void displayEditHeroMenu() {
        System.out.println("\nWhat would you like to do? ");
        System.out.println("- Change the hero's name (RENAME)");
        System.out.println("- Increase the hero's level (LEVELUP)");
        System.out.println("- Decrease the hero's level (LEVELDOWN)");
        System.out.println("- Select and deselect skills for the hero (SELECT)");
        System.out.println("- Return to the team edit menu (RETURN)");
    }

    // MODIFIES: this
    // EFFECTS: processes the user command for hero edit menu
    private void processEditHeroMenuCommand(String command, Team team, Hero hero) {
        if (command.equals("RENAME")) {
            optionRenameHero(hero);
        } else if (command.equals("LEVELUP")) {
            optionLevelUpHero(hero);
        } else if (command.equals("LEVELDOWN")) {
            optionLevelDownHero(hero);
        } else if (command.equals("SELECT")) {
            optionSelectHeroSkills(hero);
        } else if (command.equals("RETURN")) {
            System.out.println("\nReturning to team edit menu ...");
        } else {
            System.out.println("\nNot a valid command!");
        }
    }

    // MODIFIES: this
    // EFFECTS: changes hero's name
    private void optionRenameHero(Hero hero) {
        System.out.println("Please enter the hero's new name: ");
        String heroNewName = input.next();
        hero.changeHeroGivenName(heroNewName);
        System.out.println("The hero's name is now " + heroNewName + "!");
        editHero(team, hero);
    }

    // MODIFIES: this
    // EFFECTS: levels the hero up by HERO_LEVEL_INCREMENT
    private void optionLevelUpHero(Hero hero) {
        hero.increaseHeroLevel();
        System.out.println("The hero's level is now " + hero.getHeroLevel() + "!");
        editHero(team, hero);
    }

    // MODIFIES: this
    // EFFECTS: levels the hero down by HERO_LEVEL_INCREMENT
    private void optionLevelDownHero(Hero hero) {
        hero.decreaseHeroLevel();
        System.out.println("The hero's level is now " + hero.getHeroLevel() + "!");
        editHero(team, hero);
    }

    // MODIFIES: this
    // EFFECTS: changes selected status of hero's skills
    private void optionSelectHeroSkills(Hero hero) {
        System.out.println("The hero has the following skills: ");
        printHeroSkills(hero);
        System.out.println("Which skill would you like to change the selected status of?");
        String skillName = input.next();
        changeSelectionStatus(skillName);
    }

    // EFFECTS: prints all hero skill descriptions out
    private void printHeroSkills(Hero hero) {
        String allHeroSkills = " ";
        for (Skill s : hero.getHeroType().getHeroSkills()) {
            allHeroSkills = allHeroSkills.concat(s.getSkillDescription() + ", "
                    + s.isSelected() + " ; ");
        }
        System.out.println("\n" + allHeroSkills);
    }

    // MODIFIES: this
    // EFFECTS: changes the selected status of the given skill
    //          if the hero does not have that skill, do nothing
    private void changeSelectionStatus(String skillDesc) {
        String givenSkillDescUpperCase = skillDesc.toUpperCase();
        for (Skill s : hero.getHeroType().getHeroSkills()) {
            String actualSkillDescUpperCase = s.getSkillDescription().toUpperCase();
            if (givenSkillDescUpperCase.equals(actualSkillDescUpperCase)) {
                s.changeSelectedStatus();
                System.out.println("The skill " + skillDesc
                        + "'s selected status is: " + s.isSelected());
                editHero(team, hero);
                break;
            }
        }
        System.out.println("The hero does not have a skill with the description "
                + skillDesc + "!");
    }

}
