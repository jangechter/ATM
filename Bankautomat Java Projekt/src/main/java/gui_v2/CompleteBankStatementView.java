/*
 * CompleteBankStatementView.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import atm.ATM;
import dynamicTable.IncomingCashTransfers;
import dynamicTable.OutgoingCashTransfers;

public class CompleteBankStatementView extends UI {
    protected CompleteBankStatementView(final ATM atm, final UI parentUI) {
        super(atm, parentUI);
    }

    @Override
    public String getName() {
        return "Show complete bank statement";
    }

    @Override
    public void printContext() {

        printDashLine();
        System.out.println("Incoming cash transfers");
        printDashLine();

        IncomingCashTransfers table = new IncomingCashTransfers(getAtm(), "Date", "Amount", "ApplicantIban", "Purpose");
        table.printTable();

        printDashLine();
        System.out.println("Outgoing cash transfers");
        printDashLine();

        OutgoingCashTransfers table2 = new OutgoingCashTransfers(getAtm(), "Date", "Amount", "RecipientIban",
                                                                 "Purpose");
        table2.printTable();
    }
}
