/*
 * WithdrawUI.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Exceptions.WithdrawNotPossibleException;
import atm.ATM;
import consoleInput.ConsoleInput;
import moneynote.Moneynote;

public class WithdrawUI extends UI {

    private List<Moneynote> moneynotesInCashBox = new ArrayList<>(
            getAtm().getCashbox().getNotes().keySet().stream().sorted().collect(
                    Collectors.toList()));

    public WithdrawUI(final ATM atm, final UI parentUI) {
        super(atm, parentUI);
    }

    @Override
    public String getName() {
        return "Withdraw money";
    }

    private void printPossibleNotes() {

        moneynotesInCashBox = new ArrayList<>(
                getAtm().getCashbox().getNotes().keySet().stream().sorted().collect(
                        Collectors.toList()));

        Integer index = 1;

        for (final Moneynote m : moneynotesInCashBox) {

            System.out.println(index + ": " + m.getValue() + " " + getAtm().getCurrency());

            index++;
        }

        System.out.println(index + ": other");
    }

    private void withdraw(Integer menuPoint) {

        Optional<HashMap<Moneynote, Integer>> notes = Optional.empty();

        if (menuPoint <= getAtm().getCashbox().getNotes().size()) {

            final Integer amount = moneynotesInCashBox.get(menuPoint - 1).getValue();

            notes = getAtm().withdrawMoney(amount);
        } else {

            System.out.println("enter an other amount");

            try {

                final Integer amount = ConsoleInput.readIntegerInput();

                notes = getAtm().withdrawMoney(amount);
            } catch (final WithdrawNotPossibleException e) {
                System.out.println("invalid amount for withdraw");
            }
        }

        notes.ifPresent(withdrawNotes -> withdrawNotes
                .forEach((moneynote, amount) -> System.out.printf("%-3s:  %d %n", moneynote.getValue(), amount)));
    }

    @Override
    public void printContext() {
        Integer menuPoint;

        printPossibleNotes();

        System.out.println("0: back");

        menuPoint = readMenuPoint();

        if (menuPoint != 0) {
            withdraw(menuPoint);
        }

        getParentUI().printMenu();
    }

    @Override
    protected Integer readMenuPoint() {

        Integer input;

        HashMap<Moneynote, Integer> notes = getAtm().getCashbox().getNotes();

        do {

            try {
                input = Integer.parseInt(ConsoleInput.readConsoleInput());
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
                input = null;
            }

            if (input > (notes.size() + 1)) {
                input = null;
                System.out.println("inavlid menupoint please enter a number between 0 and " + (notes.size() + 1));
            }
        } while (input == null);

        return input;
    }
}
