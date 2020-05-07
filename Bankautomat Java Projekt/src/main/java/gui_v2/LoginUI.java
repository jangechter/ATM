/*
 * LoginUI.java
 *
 * Created on 2020-05-07
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import java.io.IOException;

import atm.ATM;
import consoleInput.ConsoleInput;

public class LoginUI extends UI {

    MainUI mainUI = new MainUI(getAtm(), this);

    public LoginUI(final ATM atm, final UI parentUI) {
        super(atm, parentUI);

        getNextPossibleUIs().put(0, mainUI);

        printUI();
    }

    @Override
    public void printUI() {
        String iban = null;
        String pin = null;

        do {
            super.printUI();

            System.out.println("            ATM        ");
            System.out.println("IBAN:");

            try {
                iban = ConsoleInput.readConsoleInput();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("PIN:");

            try {
                pin = ConsoleInput.readConsoleInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!getAtm().login(iban, pin));

        getNextPossibleUIs().get(0).printUI();
    }
}
