package ui.gui;

import javax.swing.*;
import java.awt.*;

public class TeamPanel {
    public static JPanel newPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(40,80,100,150);
        panel.setBackground(Color.gray);
        panel.add(new JButton("Add Hero"));
        panel.add(new JButton("Remove Hero"));
        panel.add(new JButton("Favourite"));
        return panel;
    }
}
