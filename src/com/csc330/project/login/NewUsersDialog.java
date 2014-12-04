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
    private Writer writer = null;

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnCreate;
    private JButton btnCancel;

    public NewUsersDialog(Frame parent, Login login) {
        super(parent, "User Creation");
        this.login = login;

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
                if(Tournament.newUserInstances % 2 == 0)
                    writer.append(String.valueOf(Tournament.numberOfGames)+"\n");
                writer.append(tfUsername.getText()+"\n");
                writer.append(pfPassword.getText()+"\n");
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                tfUsername.setText("");
                pfPassword.setText("");
            }

            JOptionPane.showMessageDialog(NewUsersDialog.this,
                    "You have successfully created user!",
                    "Created",
                    JOptionPane.INFORMATION_MESSAGE);
            Tournament.newUserInstances++;
            dispose();
        }
    }
}