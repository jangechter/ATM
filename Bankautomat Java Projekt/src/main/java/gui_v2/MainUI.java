/*
 * MainUI.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import atm.ATM;

public class MainUI extends UI {

    public MainUI(final ATM atm, final UI parentUI) {
        super(atm, parentUI);

        final WithdrawUI withdrawUI = new WithdrawUI(atm, this);
        final DepositUI depositUI = new DepositUI(atm, this);
        final BankBalanceUI bankBalanceUI = new BankBalanceUI(atm, this);
        final BankStatementUI bankStatementUI = new BankStatementUI(atm, this);
        final CashTransferUI cashTransferUI = new CashTransferUI(atm, this);
        final LogoutUI logoutUI = new LogoutUI(atm, this);

        getNextPossibleUIs().put(1, withdrawUI);
        getNextPossibleUIs().put(2, bankBalanceUI);
        getNextPossibleUIs().put(3, bankStatementUI);
        getNextPossibleUIs().put(4, depositUI);
        getNextPossibleUIs().put(5, cashTransferUI);
        getNextPossibleUIs().put(6, logoutUI);
    }

    @Override
    public String getName() {
        return "Main UI";
    }
}
