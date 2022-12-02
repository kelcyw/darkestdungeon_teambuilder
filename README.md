# Personal Project - Darkest Dungeon Team Assembler

## What will the application do?

This application will allow users to create 
team setups for the game Darkest Dungeon. The 
user will be able to: 
- save and name their team setups
- add and remove characters from a team
- select skills for characters

Once a team is created, the user should 
also be able to: 
- see the team's strongest stats
- favourite or unfavourite any team


## Why is this project of interest to you? Who will use it?

I find that I'm a little forgetful when it comes to remembering 
good strategies and team compositions for certain areas 
and bosses within the game. I tend to spend a lot of time searching 
online for compositions that other people like to 
use as well as tips and tricks, and team setups are a pretty 
heavily-discussed topic within the game's community as 
well. A tool that could help me put characters together in a team and
tell me the team's strengths will help me with determining what and where
I should be fighting with that team, which could be very helpful not just for me
but for players who are newer to the game as well!

## User Stories

- as a user, I want to be able to add and remove characters to my team
- as a user, I want to be able to select and deselect skills from characters
- as a user, I want to be able to name and save my team
- as a user, I want to be able to favourite or unfavourite my team
- as a user, I want to be able to have a list of my teams
  - as a user, I want to be able to get a team from this list to view 
    and/or edit it
- as a user, I want to be able to see which stats the team is strong in
- as a user, I want to be able to save all of my saved teams to a file
- as a user, I want to be able to reload that file and resume editing the saved teams list


## Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by clicking on the "New Team" button,
    and entering a team name. 
- You can generate the second required event related to adding Xs to a Y by clicking on the "Delete Team" button,
    and entering the name of the team you would like to delete.
- You can locate my visual component by clicking on a team button, clicking on "Add Hero", and entering a valid name and 
    hero type. The hero's icon should appear. 
- You can save the state of my application by clicking on the "Save Teams" button.
- You can reload the state of my application by clicking on the "Load Teams" button. 

## Phase 4: Task 2

A team named Team 1 was added!
A team named Team 2 was added!
A HIGHWAYMAN named Dismas was added to Team 1!
A CRUSADER named Reynauld was added to Team 1!
The CRUSADER named Reynauld was removed from Team 1!
The team named Team 2 was removed!

## Phase 4: Task 3
- Pull code related to the toolbar in "TeamMakerAppGUI" into a new class (maybe named "Toolbar") to
  make the project's structure easier to understand and to increase cohesion
- Have any methods with a REQUIRES clause throw an exception, (ex. addHeroToTeam in the Team class should
  throw an exception like "TeamSizeException") to increase code robustness
  - This should be done for the following methods: getTeam (TeamList), addHeroToTeam (Team), 
    removeHeroFromTeam (Team), increaseHeroLevel (Hero), decreaseHeroLevel (Hero), setHeroLevel (Hero), 
    addHeroSkill (HeroType), removeHeroSkill (HeroType), addSkillStat (Skill), decreaseStatChance (Stat)
- Some methods share a lot of similarity in their code bodies
  - Some examples are methods like teamsToJson, membersToJson, skillsToJson, statsToJson
  - These methods could be refactored to take a list of objects, then applying the put(object.toJson())
    on each one.
