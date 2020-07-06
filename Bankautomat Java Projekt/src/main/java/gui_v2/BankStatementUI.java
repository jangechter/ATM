/*
 * BankStatementUI.java
 *
 * Created on 2020-07-06
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import atm.ATM;
import cashTransfer.CashTransfer;

public class BankStatementUI extends UI {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private Integer maxAmountWidth = 0;
    private Integer maxDateWidth = 0;
    private Integer maxIbanWidth = 0;
    private Integer maxPurposeWidth = 0;

    private List<Integer> columnWidthList = new ArrayList<>();

    BankStatementUI(final ATM atm, final UI parentUI) {
        super(atm, parentUI);
    }

    @Override
    public String getName() {
        return "Print bank statement";
    }

    private void printHeaderForAccountStatement() {

        printDashLine();

        System.out.println(
                String.format("| %-" + maxDateWidth + "s | %-" + maxAmountWidth + "s | %-" + maxIbanWidth + "s | %-"
                              + maxPurposeWidth + "s |",
                              "Date", "Amount", "RecipientIBAN", "Purpose"));
    }

    private void printSingleCashTransfer(CashTransfer ct) {

        System.out.println(
                String.format("| %-" + maxDateWidth + "s | %-" + maxAmountWidth + "s | %-" + maxIbanWidth + "s | %-"
                              + maxPurposeWidth + "s |",
                              ct.getDate().format(formatter), ct.getAmount(), ct.getApplicantIBAN(), ct.getPurpose()));
    }

    @Override
    public void printDashLine() {

        StringBuilder dashLine = new StringBuilder("+");

        for (final Integer width : columnWidthList) {

            for (int i = 0; i < width + 2; i++) {

                dashLine.append("-");
            }

            dashLine.append("+");
        }

        System.out.println(dashLine);
    }

    @Override
    public void printContext() {

        List<CashTransfer> cashTransfers = getAtm().getLoggedInClient().getClient().getCashRepository()
                                                   .getCashTransfers()
                                                   .stream().sorted(
                        Comparator.comparing(CashTransfer::getDate)).collect(Collectors.toList());

        maxDateWidth = cashTransfers.stream().map(c -> c.getDate().toString()).max(String::compareTo).get().length();

        columnWidthList.add(maxDateWidth);

        maxAmountWidth = cashTransfers.stream().map(c -> c.getAmount().toString()).max(String::compareTo)
                                      .get().length();

        columnWidthList.add(maxAmountWidth);

        maxIbanWidth = cashTransfers.stream().map(c -> c.getRecipientIBAN()).max(String::compareTo).get().length();

        columnWidthList.add(maxIbanWidth);

        maxPurposeWidth = cashTransfers.stream().map(c -> c.getPurpose()).max(String::compareTo).get().length();

        columnWidthList.add(maxPurposeWidth);

        printHeaderForAccountStatement();

        for (final CashTransfer ct : cashTransfers) {
            printDashLine();
            printSingleCashTransfer(ct);
        }

        printDashLine();
    }
}
