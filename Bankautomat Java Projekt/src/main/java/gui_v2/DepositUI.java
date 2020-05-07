/*
 * DepositUI.java
 *
 * Created on 2020-05-07
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
    public void printUI() {
        super.printUI();

        System.out.println("0: back");
        System.out.println("How much different notes do you have ?");
        System.out.println("");

        try {
            readNotesForDeposit().ifPresentOrElse(depositNotes -> getAtm().depositMoney(depositNotes),
                                                  () -> getParentUI().printUI());
        } catch (final IOException e) {
            e.printStackTrace();
        }

        getParentUI().printUI();
    }

    private Optional<HashMap<Moneynote, Integer>> readNotesForDeposit() throws IOException {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();
        Integer moneynoteValue = 0;
        Integer amount = 0;

        Integer differentNotes = ConsoleInput.readNumericInput();

        if (differentNotes != 0) {
            for (int i = 0; i < differentNotes; i++) {

                System.out.println("Banknote: ");

                moneynoteValue = ConsoleInput.readNumericInput();

                System.out.println("Amount");

                amount = ConsoleInput.readNumericInput();

                notes.put(new Moneynote(moneynoteValue), amount);
            }
        } else {

            return Optional.empty();
        }

        return Optional.of(notes);
    }
}
