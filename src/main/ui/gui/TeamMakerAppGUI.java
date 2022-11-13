package ui.gui;

import model.Hero;
import model.HeroType;
import model.Team;
import model.TeamList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.InitializeHeroes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// The main application class that contains information for the main menu (GUI version)
// JSON-related code is modelled after JsonSerializationDemo
// GUI-related code is partially based on code from SimpleDrawingPlayer

public class TeamMakerAppGUI {

    private TeamList savedTeams;
    private List<HeroType> availableHeroTypes;

    private static final String JSON_STORE = "./data/teamList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int TEAMVIEW_WIDTH = 500;
    private static final int TEAMVIEW_HEIGHT = 600;
    private static final int TEAMVIEW_BUTTON_X = 37;

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel teamPanel;

    // EFFECTS: runs the team maker application (with GUI)
    public TeamMakerAppGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTeamMaker();
    }

    // EFFECTS: initializes graphics, heroTypes
    private void runTeamMaker() {
        availableHeroTypes = new ArrayList<>();
        initializeHeroTypes();

        savedTeams =  new TeamList();
        Team team1 = new Team("Team 1");
        team1.addHeroToTeam(new Hero("Dismas", availableHeroTypes.get(0)));
        savedTeams.addTeam(team1);

        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where the TeamMaker application will operate
    //          adds panel and panel's associated elements
    private void initializeGraphics() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        teamPanel = new JPanel();
        teamPanel.setLayout(new FlowLayout());
        addTeamButtons(teamPanel);
        teamPanel.setVisible(true);

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        addToolButtons(toolbar);
        mainPanel.add(toolbar, BorderLayout.PAGE_START);
        mainPanel.add(teamPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        mainPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes all hero types
    private void initializeHeroTypes() {
        availableHeroTypes.add(InitializeHeroes.initializeHighwayMan());
        availableHeroTypes.add(InitializeHeroes.initializeCrusader());
        availableHeroTypes.add(InitializeHeroes.initializePlagueDoctor());
        availableHeroTypes.add(InitializeHeroes.initializeVestal());
    }

    // MODIFIES: panel
    // EFFECTS: adds team buttons to panel
    private void addTeamButtons(JPanel panel) {
        for (Team team : savedTeams.getSavedTeams()) {
            JButton button = new JButton(team.getTeamName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openTeamView(e, team);
                }
            });
            panel.add(button, BorderLayout.CENTER);
        }
    }

    // MODIFIES: toolbar
    // EFFECTS: adds tool buttons for toolbar
    private void addToolButtons(JToolBar tb) {
        initializeAddButton(tb);
        initializeDelButton(tb);
        initializeLoadButton(tb);
        initializeSaveButton(tb);
    }

    private void initializeAddButton(JToolBar tb) {
        JButton buttonNew = new JButton("New Team");
        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeam(e);
            }
        });
        tb.add(buttonNew);
    }

    private void initializeDelButton(JToolBar tb) {
        JButton buttonDel = new JButton("Delete Team");
        buttonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTeam(e);
            }
        });
        tb.add(buttonDel);
    }

    public void initializeLoadButton(JToolBar tb) {
        JButton buttonLoad = new JButton("Load Teams");
        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTeams(e);
            }
        });
        tb.add(buttonLoad);

    }

    public void initializeSaveButton(JToolBar tb) {
        JButton buttonSave = new JButton("Save Teams");
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTeams(e);
            }
        });
        tb.add(buttonSave);
    }

    // MODIFIES: this
    // EFFECTS: opens up team view for clicked team button
    private void openTeamView(ActionEvent e, Team team) {
        JDialog teamView = new JDialog();
        teamView.setSize(new Dimension(TEAMVIEW_WIDTH,TEAMVIEW_HEIGHT));
        teamView.setTitle(team.getTeamName());
        teamView.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        // ^ prevent user from doing something else
        teamView.setLocationRelativeTo(null);
        teamView.setLayout(new BorderLayout());

        showMembers(teamView, team);
        addTeamOptions(teamView);

        teamView.setVisible(false);
        teamView.setVisible(true);
    }

    // MODIFIES: teamView
    // EFFECTS: adds option buttons for teamView dialog
    private void addTeamOptions(JDialog dialog) {
        JPanel teamOptions = new JPanel();
        JButton buttonAdd = new JButton("Add Hero");
        JButton buttonRem = new JButton("Remove Hero");
        JButton buttonFav = new JButton("Favourite");
        teamOptions.add(buttonAdd);
        teamOptions.add(buttonRem);
        teamOptions.add(buttonFav);
        dialog.add(teamOptions, BorderLayout.PAGE_END);

    }

    // MODIFIES: this
    // EFFECTS: displays members of current team in teamview dialog
    private void showMembers(JDialog dialog, Team team) {
        JPanel teamMembers = new JPanel();
        teamMembers.setLayout(new BoxLayout(teamMembers, BoxLayout.PAGE_AXIS));
        for (Hero h : team.getTeamMembers()) {
            JLabel heroLabel = new JLabel("Name: " + h.getHeroGivenName()
                    + ", Class: " + h.getHeroType().getHeroTypeName());
            teamMembers.add(heroLabel);
        }
        dialog.add(teamMembers);
    }

    // MODIFIES: this
    // EFFECTS: adds new team button
    // TODO: placeholder method; add ability to name new team immediately
    private void addTeam(ActionEvent e) {
        JButton newTeamButton = new JButton("New Team");
        teamPanel.add(newTeamButton);
        teamPanel.setVisible(false);
        teamPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: removes last team button
    // TODO: placeholder method; add ability to click on team button that should be removed
    private void removeTeam(ActionEvent e) {
        if (teamPanel.getComponentCount() > 0) {
            teamPanel.remove(teamPanel.getComponentCount() - 1);
        }
        teamPanel.setVisible(false);
        teamPanel.setVisible(true);
    }

    private void loadTeams(ActionEvent e) {
        try {
            savedTeams = jsonReader.read();
            System.out.println("Loaded all teams from " + JSON_STORE);
        } catch (IOException exc) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void saveTeams(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(savedTeams);
            jsonWriter.close();
            System.out.println("Saved all teams to " + JSON_STORE);
        } catch (FileNotFoundException exc) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

}


