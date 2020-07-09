/*
 * IncomingCashTransfers.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package dynamicTable;

import java.util.Comparator;
import java.util.stream.Collectors;

import atm.ATM;
import cashTransfer.CashTransfer;

public class IncomingCashTransfers extends CashTrasferTable {

    public IncomingCashTransfers(final ATM atm, final String... columnNames) {
        super(atm, columnNames);

        cashTransfers = atm.getLoggedInClient().getClient().getCashRepository()
                           .getCashTransfers()
                           .stream()
                           .filter(ct -> ct.getAmount().doubleValue() > 0)
                           .sorted(Comparator.comparing(CashTransfer::getDate))
                           .collect(Collectors.toList());
    }

    @Override
    protected void printSingleRow(final CashTransfer ct) {

        System.out.println(
                String.format(
                        "| %-" + columnWidthList.get(0) + "s "
                        + "| %-" + columnWidthList.get(1) + "s "
                        + "| %-" + columnWidthList.get(2) + "s "
                        + "| %-" + columnWidthList.get(3) + "s |",
                        ct.getDate().format(formatter), ct.getAmount(), ct.getApplicantIBAN(), ct.getPurpose()));
    }

    @Override
    public void printTable() {
        if (!buildTable()) {
            System.out.println("No incoming cash transfers present");
        }
    }
}
