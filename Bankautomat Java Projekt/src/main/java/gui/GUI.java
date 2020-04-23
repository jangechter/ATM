/*
 * MainGui.java
 *
 * Created on 2020-04-23
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui;

import java.io.IOException;

import atm.ATM;
import consoleInput.ConsoleInput;

public class GUI {

    private static final Integer WITHDRAW_MONEY_MENU_INDEX = 1;
    private static final Integer SHOW_BANKBALANCE_MENU_INDEX = 2;
    private static final Integer DEPOSITE_MONEY_MENU_INDEX = 3;
    private static final Integer LOGOUT_MENU_INDEX = 4;

    private ATM atm;

    private void clear() {

        for (int i = 0; i < 200; i++) {

            System.out.println("");
        }
    }

    public GUI() {

        atm = new ATM();
    }

    public void showGUI() {

        while (true) {

            if (atm.getLoggedInClient().getClient() == null) {

                diplayLogin();
            }

            diplayMainGui();

            menuSelector();
        }
    }

    protected Integer readNumericInput() {

        Integer menuPoint = null;

        while (menuPoint == null) {

            try {
                menuPoint = Integer.valueOf(ConsoleInput.readConsoleInput());
            } catch (IOException  | NumberFormatException e) {
                System.out.println("Invalid input, please enter a numeric Number");
            }
        }

        return menuPoint;
    }

    private void diplayMainGui() {

        System.out.println("----------------------------");
        System.out.println("Choose...");
        System.out.println(WITHDRAW_MONEY_MENU_INDEX + ": Withdraw money");
        System.out.println(SHOW_BANKBALANCE_MENU_INDEX + ": Show bankbalance");
        System.out.println(DEPOSITE_MONEY_MENU_INDEX + ": Deposit money");
        System.out.println(LOGOUT_MENU_INDEX + ": logout");
    }

    private void menuSelector() {

        switch (readNumericInput()) {

            case 1:

                new WithdrawGUI().printWithdrawGui(atm);
                break;
            case 2:
                System.out.println(
                        "Your current bank balance represents " + atm.getLoggedInClient().getClient().getBankBalance()
                        + " of your main currency");
                break;
            case 3:
                new DepositGUI().printDepositGUI(atm);
                break;
            case 4:
                atm.logout();

                System.out.println("Successful logout");

                clear();

                diplayLogin();
            default:
                System.out.println("Please select a valid menupoint");
        }
    }

    private void diplayLogin() {

        String iban = null;
        String pin = null;

        do {

            LoginGUI.printLoginScreenIban();

            try {
                iban = ConsoleInput.readConsoleInput();
            } catch (IOException e) {
                e.printStackTrace();
            }

            LoginGUI.printLoginScreenPin();

            try {
                pin = ConsoleInput.readConsoleInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!atm.login(iban, pin));
    }
}
