/*
 * DepositUI.java
 *
 * Created on 2020-06-08
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

        Integer differentNotes = ConsoleInput.readIntegerInput();

        if (differentNotes != 0) {
            for (int i = 0; i < differentNotes; i++) {

                System.out.println("Banknote: ");

                moneynoteValue = ConsoleInput.readIntegerInput();

                System.out.println("Amount");

                amount = ConsoleInput.readIntegerInput();

                notes.put(new Moneynote(moneynoteValue), amount);
            }
        } else {

            return Optional.empty();
        }

        return Optional.of(notes);
    }
}
