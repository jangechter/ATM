/*
 * WithdrawGUI.java
 *
 * Created on 2020-05-07
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import Exceptions.WithdrawNotPossibleException;
import atm.ATM;
import cashbox.Cashbox;
import moneynote.Moneynote;

public class WithdrawGUI extends GUI {

    public WithdrawGUI(final ATM atm) {
        super(atm);
    }

    public void printWithdrawGui() {

        clear();
        int index = 1;
        Integer amount;
        Optional<HashMap<Moneynote, Integer>> notes = Optional.empty();
        System.out.println("----------------------------");
        System.out.println("0: back");
        System.out.println("Choose amount...");

        for (Moneynote m : getAtm().getCashbox().getNotes().keySet().stream().sorted().collect(Collectors.toList())) {

            System.out.println(index + ": " + m.getValue() + " " + getAtm().getCurrency());
            index++;
        }

        System.out.println(index + " Other amount");

        amount = amountSelector(index, getAtm().getCashbox());

        if (amount != 0) {

            try {

                notes = getAtm().withdrawMoney(amount);
            } catch (IllegalArgumentException | WithdrawNotPossibleException e) {

                System.out.println("Cannot withdraw, invalid amount");

                return;
            }

            if (notes.isPresent()) {

                System.out.println("Following banknotes will be withdraw...");
                System.out.println("Banknote, amount");
                notes.get().forEach(
                        (moneynote, integer) -> System.out.println(moneynote.getValue() + ",      " + integer));
            }
        }
    }

    private Integer amountSelector(Integer numberMenupoints, Cashbox cb) {

        Integer input = null;

        do {

            input = readNumericInput();

            if (input > numberMenupoints) {
                System.out.println("invalid menupoint");
                input = null;
            }
        } while (input == null);

        if (input != 0) {

            if (input < numberMenupoints) {

                return cb.getNotes().keySet().stream().sorted().collect(Collectors.toList()).get(input - 1)
                         .getValue();
            } else {

                return otherAmountGUI();
            }
        } else {
            return 0;
        }
    }

    private Integer otherAmountGUI() {

        System.out.println("Please enter a amount");

        return readNumericInput();
    }
}
