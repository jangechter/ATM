/*
 * LoginGUI.java
 *
 * Created on 2020-05-07
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui;

import atm.ATM;

public class LoginGUI extends GUI {

    public LoginGUI(final ATM atm) {
        super(atm);
    }

    static public void printLoginScreenIban() {

        System.out.println("----------------------------");
        System.out.println("            ATM        ");
        System.out.println("IBAN: ");
    }

    static public void printLoginScreenPin() {

        System.out.println("Pin: ");

    }

}
