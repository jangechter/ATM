/*
 * DepositUI.java
 *
 * Created on 2020-09-17
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import atm.ATM;
import consoleInput.ConsoleInput;
import moneynote.Moneynote;

public class DepositUI extends UI {
    public DepositUI(final ATM atm, final UI parentUI) {
        super(atm, parentUI);
    }

    @Override
    public String getName() {
        return "Deposit money";
    }

    @Override
    public void printContext() {

        System.out.println("0: back");
        System.out.println("How many different notes do you have ?");
        System.out.println("");

        try {
            readNotesForDeposit().ifPresent(notes -> getAtm().depositMoney(notes));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private Optional<HashMap<Moneynote, Integer>> readNotesForDeposit() throws IOException {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();
        Integer moneynoteValue = 0;
        Integer amount = 0;
        Integer differentNotes = 0;

        while (differentNotes == 0) {

            differentNotes = ConsoleInput.readIntegerInput();

            if (differentNotes == 0) {
                return Optional.empty();
            }

            if (differentNotes < 0) {
                System.out.println("Please enter a number greater 0");
                differentNotes = 0;
            }
        }

        for (int i = 0; i < differentNotes; i++) {

            while ((moneynoteValue == 0) || (amount == 0)) {
                System.out.println("Banknote: ");

                moneynoteValue = ConsoleInput.readIntegerInput();

                if (moneynoteValue == 0) {
                    return Optional.empty();
                }

                if (moneynoteValue < 0) {
                    System.out.println("Please enter a number greater 0");
                    differentNotes = 0;
                }

                if (amount == 0) {
                    return Optional.empty();
                }

                if (amount < 0) {
                    System.out.println("Please enter a number greater 0");
                    differentNotes = 0;
                }

                System.out.println("Amount");

                amount = ConsoleInput.readIntegerInput();
            }

            notes.put(new Moneynote(moneynoteValue), amount);
        }

        return Optional.of(notes);
    }
}
