/*
 * DepositGUI.java
 *
 * Created on 2020-04-23
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui;

import java.util.HashMap;

import atm.ATM;
import moneynote.Moneynote;

public class DepositGUI extends GUI {

    public void printDepositGUI(ATM atm) {

        System.out.println("----------------------------");

        atm.depositMoney(readNotesForDeposit());

    }

    private HashMap<Moneynote, Integer> readNotesForDeposit() {

        HashMap<Moneynote, Integer> notes = new HashMap<>();
        Integer moneynoteValue = 0;
        Integer amount = 0;

        System.out.println("How much different notes do you have ?");

        Integer differentNotes = readNumericInput();

        for(int i = 0; i < differentNotes; i++) {

            System.out.println("Banknote: ");

            moneynoteValue = readNumericInput();

            System.out.println("Amount");

            amount = readNumericInput();

            notes.put(new Moneynote(moneynoteValue), amount);
        }

        return notes;
    }

}
