package ui.gui;

import model.Hero;
import model.HeroType;
import model.Team;
import model.TeamList;
import ui.InitializeHeroes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TeamPanel extends JPanel {

    private TeamList savedTeams;
    private List<HeroType> availableHeroTypes;
    private boolean displayFavourites;

    private ImageIcon iconHighwayman = new ImageIcon("./data/images/icon_highwayman.png");
    private ImageIcon iconCrusader = new ImageIcon("./data/images/icon_crusader.png");
    private ImageIcon iconPlagueDoctor = new ImageIcon("./data/images/icon_plaguedoctor.png");
    private ImageIcon iconVestal = new ImageIcon("./data/images/icon_vestal.png");


    private static final int TEAMVIEW_WIDTH = 500;
    private static final int TEAMVIEW_HEIGHT = 600;

    public TeamPanel(TeamList savedTeams) {
        this.savedTeams = savedTeams;
        availableHeroTypes = new ArrayList<>();
        displayFavourites = false;
        initializeHeroTypes();
        this.setLayout(new FlowLayout());
        this.addTeamButtons();
        this.setVisible(true);
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
    // EFFECTS: sets a new list of saved teams for the panel
    public void setSavedTeams(TeamList newList) {
        this.savedTeams = newList;
    }

    // MODIFIES: this
    // EFFECTS: adds team buttons to panel
    public void addTeamButtons() {
        this.removeAll();
        for (Team team : savedTeams.getSavedTeams()) {
            addTeamButton(team);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds team button to panel
    public void addTeamButton(Team team) {
        JButton button = new JButton(team.getTeamName());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTeamView(e, team);
            }
        });
        this.add(button, BorderLayout.CENTER);
        this.setVisible(false);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: opens up team view for clicked team button
    public void openTeamView(ActionEvent e, Team team) {
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
                    showMessage("Invalid hero name!", dialog, false);
                }
            }
        });
        panel.add(buttonAdd);
    }

    // EFFECTS: returns hero type based on given hero type name
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
                    showMessage("Invalid hero name!", dialog, false);
                }
            }
        });
        panel.add(buttonRem);
    }

    // EFFECTS: removes a hero from the team
    private void removeHeroOption(String heroGivenName, Team team, JDialog dialog) {
        int count = 0;
        int teamSize = team.getTeamMembers().size();
        for (Hero h : team.getTeamMembers()) {
            if (heroGivenName.equals(h.getHeroGivenName())) {
                team.removeHeroFromTeam(h);
                break;
            }
            count++;
        }
        if (count == teamSize) {
            showMessage("No hero with that name!", dialog, false);
        }
        showContent(dialog, team);
        dialog.revalidate();
    }

    public void showMessage(String msg, Component overlay, boolean success) {
        if (!success) {
            JOptionPane failMessage = new JOptionPane();
            failMessage.showMessageDialog(
                    overlay,
                    msg,
                    "Warning",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane successMessage = new JOptionPane();
            successMessage.showMessageDialog(
                    overlay,
                    msg,
                    "Message",
                    JOptionPane.PLAIN_MESSAGE);
        }
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

    // EFFECTS: returns the favourites display status of TeamPanel
    public boolean isDisplayingFavourites() {
        return displayFavourites;
    }

    // MODIFIES: this
    // EFFECTS: changes favourites display status of TeamPanel
    public void changeDisplayingFavourites(boolean status) {
        displayFavourites = status;
    }

}
