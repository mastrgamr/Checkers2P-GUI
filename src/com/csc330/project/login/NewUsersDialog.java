package com.csc330.project.login;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Collection;
import java.util.Collections;

/**
 * Project: Checkers2P
 * Author: Jose Martinez, Stuart Smith
 * Date: 12/1/2014
 *
 * This dialog pops up to create a new user, information persists through a generated text file.
 */
public class NewUsersDialog extends JDialog implements ActionListener {

    private Login login;
    private Tournament tournament;
    private Writer writer = null;

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnCreate;
    private JButton btnCancel;

    public NewUsersDialog(Frame parent, Login login, Tournament tournament) {
        super(parent, "User Creation");
        this.login = login;
        this.tournament = tournament;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
 
        tfUsername = new JTextField(10);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
 
        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
 
        pfPassword = new JPasswordField(10);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
 
        btnCreate = new JButton("Create");
 
        btnCreate.addActionListener(this);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnCreate);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Create"))
        {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out.txt", true)));
                writer.append(tfUsername.getText()+"\n");
                writer.append(pfPassword.getText()+"\n");

                writer.close();

                Tournament.numberOfNewUsers++;
                System.out.println(Tournament.numberOfNewUsers + ">> number of new users");

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            JOptionPane.showMessageDialog(NewUsersDialog.this,
                    "You have successfully created user!",
                    "Created",
                    JOptionPane.INFORMATION_MESSAGE);
            Tournament.newUserInstances++;
            dispose();
        }
        tournament.gatherPlayerInfo(tfUsername.getText(), pfPassword.getText());
        if(Tournament.numberOfNewUsers == Integer.parseInt(Tournament.numberOfPlayers)) {
            tournament.generateMatches();
        }
    }
}