/*
 * UI.java
 *
 * Created on 2020-05-07
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
    private UI parentUI;

    protected void setParentUI(final UI parentUI) {
        this.parentUI = parentUI;
    }

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

    public void printUI() {

        System.out.println("----------------------------");
    }

    public final UI getParentUI() {

        return parentUI;
    }

    protected void menuSelector(final Integer menuPoint) {

        nextPossibleUIs.get(menuPoint).printUI();
    }

    protected Integer readMenuPoint() {

        Integer input = null;

        do {

            try {
                input = ConsoleInput.readNumericInput();
            } catch (final IOException e) {
                e.printStackTrace();
            }

            if (!nextPossibleUIs.containsKey(input)) {
                System.out.println("inavlid menupoint please enter a number between 0 and " + nextPossibleUIs.size());
                input = null;
            }
        } while (input == null);

        return input;
    }
}
