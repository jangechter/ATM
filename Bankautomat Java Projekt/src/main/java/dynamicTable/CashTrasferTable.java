/*
 * CashTrasferTable.java
 *
 * Created on 2020-09-17
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package dynamicTable;

import java.time.format.DateTimeFormatter;
import java.util.List;

import atm.ATM;
import cashTransfer.CashTransfer;

public abstract class CashTrasferTable extends DynamicTable {

    protected ATM atm;

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    List<CashTransfer> cashTransfers;

    public CashTrasferTable(final ATM atm, final String... columnNames) {
        super(columnNames);
        this.atm = atm;

        cashTransfers = atm.getLoggedInClient().getClient().getCashRepository().getCashTransfers();
    }

    @Override
    protected boolean computeColumnWidthList() {
        Integer temp;

        if (cashTransfers.size() > 0) {

            temp = cashTransfers.stream()
                                .map(ct -> ct.getDate().toString())
                                .max(stringLengthComp)
                                .get()
                                .length();

            if (columnWidthList.get(0) < temp) {
                columnWidthList.set(0, temp);
            }

            temp = cashTransfers.stream()
                                .map(ct -> ct.getAmount().toString())
                                .max(stringLengthComp)
                                .get()
                                .length();

            if (columnWidthList.get(1) < temp) {
                columnWidthList.set(1, temp);
            }

            temp = cashTransfers.stream()
                                .map(ct -> ct.getRecipientIBAN().toString())
                                .max(stringLengthComp)
                                .get()
                                .length();

            if (columnWidthList.get(2) < temp) {
                columnWidthList.set(2, temp);
            }

            temp = cashTransfers.stream()
                                .map(ct -> ct.getPurpose().toString())
                                .max(stringLengthComp)
                                .get()
                                .length();

            if (columnWidthList.get(3) < temp) {
                columnWidthList.set(3, temp);
            }

            return true;
        }

        return false;
    }

    @Override
    public void printTable() {
        if (!buildTable()) {
            System.out.println("No cash transfers present");
        }
    }

    protected void printSingleRow(final CashTransfer ct) {

        System.out.println(
                String.format(
                        "| %-" + columnWidthList.get(0) + "s "
                        + "| %-" + columnWidthList.get(1) + "s "
                        + "| %-" + columnWidthList.get(2) + "s "
                        + "| %-" + columnWidthList.get(3) + "s |",
                        ct.getDate().format(formatter), ct.getAmount(), ct.getApplicantIBAN(), ct.getPurpose()));
    }

    final boolean buildTable() {

        if (computeColumnWidthList()) {

            printHeaderForAccountStatement();
            printDashLine();
            for (final CashTransfer cashTransfer : cashTransfers) {
                printSingleRow(cashTransfer);
            }
            printDashLine();

            return true;
        }

        return false;
    }
}
