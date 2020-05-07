/*
 * Main_v2.java
 *
 * Created on 2020-05-07
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package main;

import atm.ATM;
import gui_v2.LoginUI;

public class Main_v2 {

    public static void main(String[] args) {

        ATM atm = new ATM();

        LoginUI loginUI = new LoginUI(atm, null);
    }
}
