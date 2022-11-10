package ui.gui;

import model.HeroType;
import model.TeamList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.InitializeHeroes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// The main application class that contains information for the main menu (GUI version)
// JSON-related code is modelled after JsonSerializationDemo
// GUI-related code is based on code from SimpleDrawingPlayer

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
    private JDialog teamView;
    private JPanel panel;

    // EFFECTS: runs the team maker application (with GUI)
    public TeamMakerAppGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTeamMaker();
    }

    // EFFECTS: initializes graphics, interaction, heroTypes
    private void runTeamMaker() {
        initializeGraphics();
        // initializeInteraction();
        availableHeroTypes = new ArrayList<>();
        initializeHeroTypes();
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

        panel = new JPanel();

        JToolBar toolbar = new JToolBar();
        panel.add(toolbar);

        addToolButtons(toolbar);
        addMenuButtons(panel);

        frame.getContentPane().add(panel);
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
    // EFFECTS: adds menu buttons to panel
    private void addMenuButtons(JPanel panel) {
        JButton t1Button = new JButton("Team 1");
        t1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTeamView(e, "Team 1");
            }
        });
        JButton t2Button = new JButton("Team 2");
        t2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTeamView(e, "Team 2");
            }
        });
        panel.add(t1Button);
        panel.add(t2Button);
    }

    // MODIFIES: toolbar
    // EFFECTS: adds tool buttons for toolbar
    private void addToolButtons(JToolBar tb) {
        JButton buttonNew = new JButton("New Team");
        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeam(e);
            }
        });

        JButton buttonDel = new JButton("Delete Team");
        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTeam(e);
            }
        });
        JButton buttonLoad = new JButton("Load Teams");
        JButton buttonSave = new JButton("Save Teams");
        tb.add(buttonNew);
        tb.add(buttonDel);
        tb.add(buttonLoad);
        tb.add(buttonSave);
    }

    // MODIFIES: teamView
    // EFFECTS: adds option buttons for teamView dialog
    private void addTeamOptions(JDialog dialog) {
        dialog.setLayout(null);
        JButton buttonAdd = new JButton("Add Hero");
        JButton buttonRem = new JButton("Remove Hero");
        JButton buttonFav = new JButton("Favourite");
        buttonAdd.setBounds(TEAMVIEW_BUTTON_X,TEAMVIEW_HEIGHT - 100,130,40);
        buttonRem.setBounds(TEAMVIEW_BUTTON_X + 140,TEAMVIEW_HEIGHT - 100,130,40);
        buttonFav.setBounds(TEAMVIEW_BUTTON_X + (2 * 140),TEAMVIEW_HEIGHT - 100,130,40);
        dialog.add(buttonAdd);
        dialog.add(buttonRem);
        dialog.add(buttonFav);

    }

    // MODIFIES: this
    // EFFECTS: opens up team view for clicked team button
    private void openTeamView(ActionEvent e, String teamName) {
        teamView = new JDialog();
        teamView.setSize(new Dimension(TEAMVIEW_WIDTH,TEAMVIEW_HEIGHT));
        teamView.setTitle(teamName);
        teamView.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        // ^ prevent user from doing something else
        teamView.setLocationRelativeTo(null);
        addTeamOptions(teamView);
        teamView.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds new team button
    // TODO: placeholder method
    private void addTeam(ActionEvent e) {
        JButton newTeamButton = new JButton("New Team");
        panel.add(newTeamButton);
        panel.setVisible(false);
        panel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: removes last team button
    // TODO: placeholder method
    private void removeTeam(ActionEvent e) {
        JButton newTeamButton = new JButton("New Team");
        panel.remove(newTeamButton);
        panel.setVisible(false);
        panel.setVisible(true);
    }

}


