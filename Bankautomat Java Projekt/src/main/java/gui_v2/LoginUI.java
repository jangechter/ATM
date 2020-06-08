/*
 * LoginUI.java
 *
 * Created on 2020-06-08
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
    }

    @Override
    public String getName() {
        return "Login";
    }

    @Override
    public void printContext() {
        String iban = null;
        String pin = null;

        do {

            System.out.println("            ATM        ");
            System.out.println("IBAN:");

            try {
                iban = ConsoleInput.readConsoleInput();
            } catch (final IOException e) {
                e.printStackTrace();
            }

            System.out.println("PIN:");

            try {
                pin = ConsoleInput.readConsoleInput();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } while (!getAtm().login(iban, pin));

        do {
            mainUI.printMenu();
        } while (getAtm().getLoggedInClient().getClient() != null);
    }
}
