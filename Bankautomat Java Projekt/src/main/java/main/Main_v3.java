/*
 * Main_v3.java
 *
 * Created on 2020-09-17
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package main;

import javax.swing.*;

import atm.ATM;
import gui_v3.LoginGUI;

public class Main_v3 {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LoginGUI loginGUI = new LoginGUI(new ATM(), null);

            loginGUI.setVisible(true);
        });
    }
}
