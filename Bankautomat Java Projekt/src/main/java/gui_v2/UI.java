/*
 * UI.java
 *
 * Created on 2020-06-16
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import atm.ATM;
import consoleInput.ConsoleInput;

public abstract class UI {

    private final ATM atm;
    private final HashMap<Integer, UI> nextPossibleUIs;
    private final UI parentUI;

    protected ATM getAtm() {
        return atm;
    }

    protected Map<Integer, UI> getNextPossibleUIs() {
        return nextPossibleUIs;
    }

    protected UI(final ATM atm, final UI parentUI) {

        nextPossibleUIs = new HashMap<>();
        this.atm = atm;
        this.parentUI = parentUI;
    }

    public abstract String getName();

    public void printDashLine() {

        System.out.println("----------------------------");
    }

    public final void printMenu() {
        printDashLine();

        if (!(nextPossibleUIs.get(0) instanceof MainUI)) {
            getNextPossibleUIs().forEach((k, v) -> System.out.println(k + ": " + v.getName()));
        }

        printContext();

        menuSelector();
    }

    public void printContext() {
    }

    public final UI getParentUI() {

        return parentUI;
    }

    protected void menuSelector() {

        if (!nextPossibleUIs.isEmpty()) {

            final UI ui = nextPossibleUIs.get(readMenuPoint());

            if (getParentUI() == ui) {
                return;
            }

            ui.printMenu();
        }
    }

    protected Integer readMenuPoint() {

        Integer input = null;

        do {

            try {
                input = ConsoleInput.readIntegerInput();
            } catch (final IOException e) {
                e.printStackTrace();
            }

            if (!nextPossibleUIs.containsKey(input)) {
                System.out.println("inavlid menupoint please enter a number between 1 and " + nextPossibleUIs.size());
                input = null;
            }
        } while (input == null);

        return input;
    }
}
