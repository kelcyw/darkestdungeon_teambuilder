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

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel teamPanel;

    private ImageIcon iconHighwayman = new ImageIcon("./data/images/icon_highwayman.png");
    private ImageIcon iconCrusader = new ImageIcon("./data/images/icon_crusader.png");
    private ImageIcon iconPlagueDoctor = new ImageIcon("./data/images/icon_plaguedoctor.png");
    private ImageIcon iconVestal = new ImageIcon("./data/images/icon_vestal.png");


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

    // MODIFIES: this
    // EFFECTS: adds team buttons to panel
    private void addTeamButtons(JPanel panel) {
        panel.removeAll();
        for (Team team : savedTeams.getSavedTeams()) {
            JButton button = new JButton(team.getTeamName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openTeamView(e, team);
                }
            });
            panel.add(button, BorderLayout.CENTER);
            panel.setVisible(false);
            panel.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds tool buttons for toolbar
    private void addToolButtons(JToolBar tb) {
        initializeNewButton(tb);
        initializeDelButton(tb);
        initializeLoadButton(tb);
        initializeSaveButton(tb);
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

        showContent(teamView, team);
        addTeamOptions(teamView, team);

        teamView.setVisible(false);
        teamView.setVisible(true);
    }

    // MODIFIES: teamView
    // EFFECTS: adds option buttons for teamView dialog
    private void addTeamOptions(JDialog dialog, Team team) {
        JPanel teamOptions = new JPanel();
        initializeAddButton(dialog, teamOptions, team);
        initializeRemButton(dialog, teamOptions, team);
        initializeFavButton(dialog, teamOptions, team);

        dialog.add(teamOptions, BorderLayout.PAGE_END);

    }

    // MODIFIES: teamView
    // EFFECTS: adds Add Hero button for teamView dialog
    private void initializeAddButton(JDialog dialog, JPanel panel, Team team) {
        JButton buttonAdd = new JButton("Add Hero");
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane newHeroPrompt = new JOptionPane();
                Object[] possibleHeroTypeNames = {"HIGHWAYMAN", "CRUSADER", "PLAGUEDOCTOR", "VESTAL"};
                String heroGivenName = (String)newHeroPrompt.showInputDialog(
                        dialog, "Please enter a name for your new hero: ", "Add New Hero",
                        newHeroPrompt.PLAIN_MESSAGE);

                String heroTypeName = (String)newHeroPrompt.showInputDialog(
                        dialog, "Please select the type of the new hero: ", "Add New Hero",
                        newHeroPrompt.PLAIN_MESSAGE,
                        null, possibleHeroTypeNames, "HIGHWAYMAN");

                // runs if string is valid
                if ((heroGivenName != null) && (heroGivenName.length() > 0)) {
                    team.addHeroToTeam(new Hero(heroGivenName, getHeroType(heroTypeName)));
                    showContent(dialog, team);
                    dialog.revalidate();
                } else {
                    // runs if string was null
                    showFailMessage("Invalid hero name!", dialog);
                }
            }
        });
        panel.add(buttonAdd);
    }

    // EFFECTS: returns hero type based on given hero type name
    // TODO: throw exception?
    private HeroType getHeroType(String typeName) {
        for (HeroType ht : availableHeroTypes) {
            if (typeName.equals(ht.getHeroTypeName())) {
                return ht;
            }
        }
        return availableHeroTypes.get(0);
    }

    // MODIFIES: teamView
    // EFFECTS: adds Remove Hero button for teamView dialog
    private void initializeRemButton(JDialog dialog, JPanel panel, Team team) {
        JButton buttonRem = new JButton("Remove Hero");
        buttonRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane remHeroPrompt = new JOptionPane();
                String heroGivenName = (String)remHeroPrompt.showInputDialog(
                        dialog, "Please enter the name of the hero you would like to remove: ",
                        "Remove a Hero",
                        remHeroPrompt.PLAIN_MESSAGE);

                // runs if string is valid
                if ((heroGivenName != null) && (heroGivenName.length() > 0)) {
                    removeHeroOption(heroGivenName, team, dialog);
                } else {
                    // runs if string was null
                    showFailMessage("Invalid hero name!", dialog);
                }
            }
        });
        panel.add(buttonRem);
    }

    // EFFECTS: removes a hero from the team
    private void removeHeroOption(String heroGivenName, Team team, JDialog dialog) {
        int count = 0;
        for (Hero h : team.getTeamMembers()) {
            if (heroGivenName.equals(h.getHeroGivenName())) {
                team.removeHeroFromTeam(h);
                break;
            }
            count++;
        }
        if (count == team.getTeamMembers().size()) {
            showFailMessage("No hero with that name!", dialog);
        }
        showContent(dialog, team);
        dialog.revalidate();
    }

    // MODIFIES: teamView
    // EFFECTS: adds Favourite button for teamView dialog
    private void initializeFavButton(JDialog dialog, JPanel panel, Team team) {
        JButton buttonFav = new JButton("Favourite");
        buttonFav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                team.changeFavourite();
                showContent(dialog, team);
                dialog.revalidate();
            }
        });
        panel.add(buttonFav);
    }

    // MODIFIES: this
    // EFFECTS: displays members and favourite status of current team in teamview dialog
    private void showContent(JDialog dialog, Team team) {
        JPanel teamContent = new JPanel();
        teamContent.setLayout(new BoxLayout(teamContent, BoxLayout.PAGE_AXIS));
        for (Hero h : team.getTeamMembers()) {
            String heroTypeName = h.getHeroType().getHeroTypeName();
            JLabel heroLabel = new JLabel("Name: " + h.getHeroGivenName()
                    + ", Type: " + heroTypeName);
            JLabel heroIcon = new JLabel(getHeroIcon(heroTypeName));
            teamContent.add(heroIcon);
            teamContent.add(heroLabel);
        }
        JLabel favLabel = new JLabel("Favourite?: " + team.isFavourite());
        teamContent.add(favLabel);
        dialog.add(teamContent);
    }

    // EFFECTS: returns the icon for the given hero type name
    private ImageIcon getHeroIcon(String heroTypeName) {
        if (heroTypeName.equals(availableHeroTypes.get(0).getHeroTypeName())) {
            return iconHighwayman;
        } else if (heroTypeName.equals(availableHeroTypes.get(1).getHeroTypeName())) {
            return iconCrusader;
        } else if (heroTypeName.equals(availableHeroTypes.get(2).getHeroTypeName())) {
            return iconPlagueDoctor;
        } else if (heroTypeName.equals(availableHeroTypes.get(3).getHeroTypeName())) {
            return iconVestal;
        } else {
            return new ImageIcon("./data/tobs.jpg");
        }
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
            addTeamButtons(teamPanel);
            teamPanel.revalidate();
        } else {
            // runs if string was null
            showFailMessage("Invalid team name!", teamPanel);
        }


    }

    // MODIFIES: this
    // EFFECTS: removes team button matching user input
    // TODO: bidirectional relationship between teams and their buttons?
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
            showFailMessage("Invalid team name!", frame);
        }

        teamNamePrompt.setVisible(true);

        teamPanel.setVisible(false);
        teamPanel.setVisible(true);
    }

    // EFFECTS: removes a team with given name
    private void removeTeamOption(String s) {
        boolean success = false;
        for (Team team : savedTeams.getSavedTeams()) {
            if (s.equals(team.getTeamName())) {
                savedTeams.removeTeam(team);
                addTeamButtons(teamPanel);
                teamPanel.revalidate();
                success = true;
                break;
            }
        }
        if (!success) {
            showFailMessage("Couldn't find a team with name: " + s, frame);
        }
    }

    private void showFailMessage(String msg, Component overlay) {
        JOptionPane failMessage = new JOptionPane();
        failMessage.showMessageDialog(
                overlay,
                msg,
                "Warning",
                JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: loads all teams from JSON_STORE
    private void loadTeams(ActionEvent e) {
        try {
            savedTeams = jsonReader.read();
            System.out.println("Loaded all teams from " + JSON_STORE);
            addTeamButtons(teamPanel);
        } catch (IOException exc) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves all teams to JSON_STORE
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

// TODO: clean this code up ,,, it's a mess


