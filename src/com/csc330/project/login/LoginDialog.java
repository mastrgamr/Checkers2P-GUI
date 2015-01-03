package com.csc330.project.login;

import com.csc330.project.checkers2p.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Project: Checkers2P
 * Author: Jose Martinez
 * Date: 12/1/2014
 */

/**
 * This dialog pops up to allow users to login and match successful logins to the generated text file when the tournament started.
 */
public class LoginDialog extends JDialog {
 
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;

    private Login login;

    private BufferedReader br;
 
    public LoginDialog(Frame parent, Login login) {
        super(parent, "User Login");

        this.login = login;

        try {
            br = new BufferedReader(new FileReader("out.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found!");
        }

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
 
        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
 
        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
 
        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Login");
 
        btnLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                try {
                    if (LoginDialog.this.login.authenticate(getUsername(), getPassword(), br)) {
                        JOptionPane.showMessageDialog(LoginDialog.this,
                                "Hi " + getUsername() + "! You have successfully logged in.",
                                "Login",
                                JOptionPane.INFORMATION_MESSAGE);
                        Tournament.numberOfLogins++;
                        succeeded = true;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(LoginDialog.this,
                                "Invalid username or password",
                                "Login",
                                JOptionPane.ERROR_MESSAGE);
                        // reset username and password
                        tfUsername.setText("");
                        pfPassword.setText("");
                        succeeded = false;
                    }
                    new Main();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    public String getUsername() {
        return tfUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
}