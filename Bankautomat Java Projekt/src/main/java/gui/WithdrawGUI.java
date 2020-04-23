/*
 * WithdrawGUI.java
 *
 * Created on 2020-04-23
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

    public void printWithdrawGui(ATM atm) {

        int index = 1;
        Optional<HashMap<Moneynote, Integer>> notes = Optional.empty();
        System.out.println("----------------------------");
        System.out.println("Choose amount...");

        for (Moneynote m : atm.getCashbox().getNotes().keySet().stream().sorted().collect(Collectors.toList())) {

            System.out.println(index + ": " + m.getValue() + " " + atm.getCurrency());
            index++;
        }

        System.out.println(index + " Other amount");

        try {

            notes = atm.withdrawMoney(amountSelector(index, atm.getCashbox()));

        } catch (IllegalArgumentException | WithdrawNotPossibleException e) {

            System.out.println("Cannot withdraw ");
        }

        System.out.println("Following banknotes will be withdraw...");
        System.out.println("Banknote, amount");

        notes.get().forEach((moneynote, integer) -> System.out.println(moneynote.getValue() + ",      " + integer));

    }

    private Integer amountSelector(Integer numberMenupoints, Cashbox cb) {

        Integer input = readNumericInput();

        if (input < numberMenupoints) {

            return cb.getNotes().keySet().stream().sorted().collect(Collectors.toList()).get(input-1)
                     .getValue();
        } else {

            return otherAmountGUI();
        }
    }

    private Integer otherAmountGUI() {

        System.out.println("Please enter a amount");

        return readNumericInput();
    }
}
