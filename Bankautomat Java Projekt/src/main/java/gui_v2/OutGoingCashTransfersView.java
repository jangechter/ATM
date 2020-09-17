/*
 * OutGoingCashTransfersView.java
 *
 * Created on 2020-09-17
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import atm.ATM;
import dynamicTable.OutgoingCashTransfers;

public class OutGoingCashTransfersView extends UI {

    protected OutGoingCashTransfersView(final ATM atm, final UI parentUI) {
        super(atm, parentUI);
    }

    @Override
    public String getName() {
        return "Show outgoing cash transfers";
    }

    @Override
    public void printContext() {
        System.out.println("Outgoing cash transfers");
        OutgoingCashTransfers table = new OutgoingCashTransfers(getAtm(), "Date", "Amount", "RecipientIban",
                                                                "Purpose");
        table.printTable();
    }
}
