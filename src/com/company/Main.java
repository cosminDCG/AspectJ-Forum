package com.company;

import com.company.forms.register.Register;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame registerFrame = new Register();
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setSize(400, 400);
        registerFrame.setVisible(true);
    }
}
