/*
 * IncomingCashTransfersView.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import atm.ATM;
import dynamicTable.IncomingCashTransfers;

public class IncomingCashTransfersView extends UI {
    protected IncomingCashTransfersView(final ATM atm, final UI parentUI) {
        super(atm, parentUI);
    }

    @Override
    public String getName() {
        return "Show incoming cash transfers";
    }

    @Override
    public void printContext() {

        IncomingCashTransfers table = new IncomingCashTransfers(getAtm(), "Date", "Amount", "ApplicantIban", "Purpose");
        table.printTable();
    }
}
