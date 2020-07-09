/*
 * CashTransferUI.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import java.io.IOException;
import java.math.BigDecimal;

import atm.ATM;
import consoleInput.ConsoleInput;

public class CashTransferUI extends UI {

    protected CashTransferUI(final ATM atm, final UI parentUI) {
        super(atm, parentUI);
    }

    @Override
    public String getName() {
        return "Transfer Money";
    }

    @Override
    public void printContext() {

        String recipientIban = "";
        BigDecimal amount = BigDecimal.ZERO;
        String purpose = "";

        System.out.println("0: Back");
        System.out.println("Please enter the recipient IBAN: ");

        try {
            recipientIban = ConsoleInput.readConsoleInput();
            if (recipientIban.equals("0")) {
                return;
            }
        } catch (final IOException | NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Please enter the amount to transfer");

        amount = BigDecimal.valueOf(ConsoleInput.readDoubleInput());

        if (amount.doubleValue() == 0.0) {
            return;
        }

        System.out.println("Please enter the purpose of the transfer");

        try {
            purpose = ConsoleInput.readConsoleInput();
            if (purpose.equals("0")) {
                return;
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }

        if ((!purpose.isEmpty()) && (!(amount.compareTo(BigDecimal.ZERO) == 0)) && (!recipientIban.isEmpty())) {

            getAtm().transferMoney(recipientIban, amount, purpose);
        } else {
            System.out.println("Invalid cash transfer data");
        }
    }
}
