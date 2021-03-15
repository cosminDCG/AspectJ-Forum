/*
 * Created by JFormDesigner on Wed Mar 10 20:16:40 EET 2021
 */

package com.company.forms.login;

import java.awt.event.*;
import javax.swing.*;

import com.company.forms.register.Register;
import com.company.dto.UserDTO;
import com.company.forms.dashboard.Dashboard;
import com.company.service.UserService;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class Login extends JFrame {
    public Login() {
        initComponents();
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        UserDTO userDTO = UserService.getInstance()
                                     .logIn(emailField.getText(), new String(passwordField.getPassword()));

        if (userDTO != null) {
            JFrame dashboardFrame = new Dashboard(userDTO);
            dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dashboardFrame.setSize(400, 400);
            dashboardFrame.setVisible(true);
            this.dispose();
        }
    }

    private void toRegisterMouseClicked(MouseEvent e) {
        JFrame registerFrame = new Register();
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        loginTitle = new JLabel();
        emailLabel = new JLabel();
        emailField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        loginButton = new JButton();
        toRegister = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- loginTitle ----
        loginTitle.setText("Login");
        loginTitle.setFont(loginTitle.getFont().deriveFont(loginTitle.getFont().getSize() + 9f));
        contentPane.add(loginTitle, "cell 5 0");

        //---- emailLabel ----
        emailLabel.setText("Email");
        contentPane.add(emailLabel, "cell 4 2");
        contentPane.add(emailField, "cell 5 2 3 1");

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        contentPane.add(passwordLabel, "cell 4 3");
        contentPane.add(passwordField, "cell 5 3 3 1");

        //---- loginButton ----
        loginButton.setText("Login");
        loginButton.addActionListener(e -> loginButtonActionPerformed(e));
        contentPane.add(loginButton, "cell 5 5");

        //---- toRegister ----
        toRegister.setText("I don't have an account");
        toRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toRegisterMouseClicked(e);
            }
        });
        contentPane.add(toRegister, "cell 5 6");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel loginTitle;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel toRegister;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
