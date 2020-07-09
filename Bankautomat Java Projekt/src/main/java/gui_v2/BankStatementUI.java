/*
 * BankStatementUI.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import atm.ATM;
import cashTransfer.CashTransfer;

public class BankStatementUI extends UI {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private Integer maxAmountWidth = 0;
    private Integer maxDateWidth = 0;
    private Integer maxIbanWidth = 0;
    private Integer maxPurposeWidth = 0;

    private List<Integer> columnWidthList = new ArrayList<>();
    private List<CashTransfer> cashTransfers;

    BankStatementUI(final ATM atm, final UI parentUI) {
        super(atm, parentUI);

        getNextPossibleUIs().put(1, new OutGoingCashTransfersView(atm, this));
        getNextPossibleUIs().put(2, new IncomingCashTransfersView(atm, this));
        getNextPossibleUIs().put(3, new CompleteBankStatementView(atm, this));
        getNextPossibleUIs().put(4, getParentUI());
    }

    @Override
    public String getName() {
        return "Print bank statement";
    }

    @Override
    public void printContext() {

    }
}
