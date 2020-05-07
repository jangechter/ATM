/*
 * DepositGUI.java
 *
 * Created on 2020-05-07
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui;

import java.util.HashMap;

import atm.ATM;
import moneynote.Moneynote;

public class DepositGUI extends GUI {

    public DepositGUI(final ATM atm) {
        super(atm);
    }

    public void printDepositGUI() {

        clear();
        System.out.println("----------------------------");

        getAtm().depositMoney(readNotesForDeposit());
    }

    private HashMap<Moneynote, Integer> readNotesForDeposit() {

        HashMap<Moneynote, Integer> notes = new HashMap<>();
        Integer moneynoteValue = 0;
        Integer amount = 0;

        System.out.println("0: back");
        System.out.println("How much different notes do you have ?");
        System.out.println("");
        Integer differentNotes = readNumericInput();

        if (differentNotes != 0) {
            for (int i = 0; i < differentNotes; i++) {

                System.out.println("Banknote: ");

                moneynoteValue = readNumericInput();

                System.out.println("Amount");

                amount = readNumericInput();

                notes.put(new Moneynote(moneynoteValue), amount);
            }
        } else {

            showGUI();
        }

        return notes;
    }

}
