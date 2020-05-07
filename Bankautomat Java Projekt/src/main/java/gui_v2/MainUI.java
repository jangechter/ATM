/*
 * MainUI.java
 *
 * Created on 2020-05-07
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import atm.ATM;

public class MainUI extends UI {

    private static final Integer WITHDRAW_MONEY_MENU_INDEX = 1;
    private static final Integer SHOW_BANKBALANCE_MENU_INDEX = 2;
    private static final Integer DEPOSITE_MONEY_MENU_INDEX = 3;
    private static final Integer LOGOUT_MENU_INDEX = 4;

    public MainUI(final ATM atm, final UI parentUI) {
        super(atm, parentUI);

        final WithdrawUI withdrawUI = new WithdrawUI(atm, this);
        final DepositUI depositUI = new DepositUI(atm, this);
        final BankBalanceUI bankBalanceUI = new BankBalanceUI(atm, this);

        getNextPossibleUIs().put(1, withdrawUI);
        getNextPossibleUIs().put(2, bankBalanceUI);
        getNextPossibleUIs().put(3, depositUI);
        getNextPossibleUIs().put(4, getParentUI());
    }

    @Override
    public void printUI() {
        super.printUI();

        System.out.println("Choose...");
        System.out.println(WITHDRAW_MONEY_MENU_INDEX + ": Withdraw money");
        System.out.println(SHOW_BANKBALANCE_MENU_INDEX + ": Show bankbalance");
        System.out.println(DEPOSITE_MONEY_MENU_INDEX + ": Deposit money");
        System.out.println(LOGOUT_MENU_INDEX + ": logout");

        menuSelector(readMenuPoint());
    }
}
