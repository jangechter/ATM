/*
 * Main.java
 *
 * Created on 2020-05-07
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package main;

import atm.ATM;
import gui.GUI;

public class Main {

    public static void main(String[] args) {

        ATM atm = new ATM();
        GUI gui = new GUI(atm);

        gui.showGUI();
    }

}
