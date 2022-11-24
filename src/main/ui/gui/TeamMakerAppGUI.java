package ui.gui;

import model.Event;
import model.EventLog;
import model.Team;
import model.TeamList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// The main application class that contains information for the main menu (GUI version)
// JSON-related code is modelled after JsonSerializationDemo
// GUI-related code is partially based on code from SimpleDrawingPlayer and from Java Swing Tutorials

public class TeamMakerAppGUI {

    private TeamList savedTeams;

    private static final String JSON_STORE = "./data/teamList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame frame;
    private JPanel mainPanel;
    private TeamPanel teamPanel;

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    // EFFECTS: runs the team maker application (with GUI)
    public TeamMakerAppGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTeamMaker();
    }

    // EFFECTS: instantiates team list, initializes graphics
    private void runTeamMaker() {
        savedTeams =  new TeamList();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window where the TeamMaker application will operate
    //          adds mainPanel and mainPanel's associated elements
    //          adds teamPanel
    private void initializeGraphics() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        this.teamPanel = new TeamPanel(savedTeams);

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        addToolButtons(toolbar);
        mainPanel.add(toolbar, BorderLayout.PAGE_START);
        mainPanel.add(teamPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        mainPanel.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                printLog();
                System.exit(0);
            }
        });
    }

    // EFFECTS: prints out event descriptions for each event in EventLog
    public void printLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.getDescription());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds tool buttons for toolbar
    private void addToolButtons(JToolBar tb) {
        initializeNewButton(tb);
        initializeDelButton(tb);
        initializeLoadButton(tb);
        initializeSaveButton(tb);
        initializeShowFavButton(tb);
    }

    // MODIFIES: toolbar
    // EFFECTS: adds New Team button to toolbar
    private void initializeNewButton(JToolBar tb) {
        JButton buttonNew = new JButton("New Team");
        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeam(e);
            }
        });
        tb.add(buttonNew);
    }

    // MODIFIES: toolbar
    // EFFECTS: adds Delete Team button to toolbar
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

    // MODIFIES: toolbar
    // EFFECTS: adds Load Teams button to toolbar
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

    // MODIFIES: toolbar
    // EFFECTS: adds Save Teams button to toolbar
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

    // MODIFIES: toolbar
    // EFFECTS: sets team panel view so that:
    //          if all teams are shown, show favourite teams only
    //          if only favourite teams are shown, show all teams
    public void initializeShowFavButton(JToolBar tb) {
        JButton buttonShowFav = new JButton("Toggle Favourites");
        buttonShowFav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFavouriteTeams(e);
            }
        });
        tb.add(buttonShowFav);
    }

    // MODIFIES: this
    // EFFECTS: adds new team button with user-inputted name
    private void addTeam(ActionEvent e) {
        JOptionPane teamNamePrompt = new JOptionPane();

        String s = (String)teamNamePrompt.showInputDialog(
                frame,
                "Please enter a name for your new team: ",
                "Create New Team",
                teamNamePrompt.PLAIN_MESSAGE);

        // runs if string is valid
        if ((s != null) && (s.length() > 0)) {
            savedTeams.addTeam(new Team(s));
            teamPanel.addTeamButtons();
            teamPanel.revalidate();
        } else {
            // runs if string was null
            teamPanel.showMessage("Invalid team name!", frame, false);
        }

    }

    // MODIFIES: this
    // EFFECTS: removes team button matching user input
    private void removeTeam(ActionEvent e) {
        JOptionPane teamNamePrompt = new JOptionPane();

        String s = (String)teamNamePrompt.showInputDialog(
                frame,
                "Please enter the name of the team you would like to remove: ",
                "Delete a team",
                teamNamePrompt.PLAIN_MESSAGE);

        // runs if string is valid
        if ((s != null) && (s.length() > 0)) {
            removeTeamOption(s);
        } else {
            // runs if string was null
            teamPanel.showMessage("Invalid team name!", frame, false);
        }

        teamNamePrompt.setVisible(true);

        teamPanel.setVisible(false);
        teamPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: removes a team with given name
    private void removeTeamOption(String s) {
        boolean success = false;
        for (Team team : savedTeams.getSavedTeams()) {
            if (s.equals(team.getTeamName())) {
                savedTeams.removeTeam(team);
                teamPanel.addTeamButtons();
                teamPanel.revalidate();
                success = true;
                break;
            }
        }
        if (!success) {
            teamPanel.showMessage("Couldn't find a team with name: " + s, frame, false);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads all teams from JSON_STORE
    private void loadTeams(ActionEvent e) {
        try {
            savedTeams = jsonReader.read();
            teamPanel.setSavedTeams(savedTeams);
            teamPanel.addTeamButtons();
            teamPanel.showMessage("Loaded all teams from " + JSON_STORE, frame, true);
        } catch (IOException exc) {
            teamPanel.showMessage("Unable to read from file: " + JSON_STORE, frame, false);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves all teams to JSON_STORE
    private void saveTeams(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(savedTeams);
            jsonWriter.close();
            teamPanel.showMessage("Saved all teams to " + JSON_STORE, frame, true);
        } catch (FileNotFoundException exc) {
            teamPanel.showMessage("Unable to write to file: " + JSON_STORE, frame, false);
        }
    }

    // MODIFIES: this
    // EFFECTS: toggles between showing all teams and favourites only
    private void showFavouriteTeams(ActionEvent e) {
        teamPanel.removeAll();
        if (!teamPanel.displayFavourites) {
            for (Team team : savedTeams.getSavedTeams()) {
                if (team.isFavourite()) {
                    teamPanel.addTeamButton(team);
                }
            }
            teamPanel.displayFavourites = true;
        } else {
            teamPanel.addTeamButtons();
            teamPanel.displayFavourites = false;
        }
        teamPanel.setVisible(false);
        teamPanel.setVisible(true);
    }

}


