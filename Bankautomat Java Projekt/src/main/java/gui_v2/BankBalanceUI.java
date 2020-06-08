/*
 * BankBalanceUI.java
 *
 * Created on 2020-06-08
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import atm.ATM;

public class BankBalanceUI extends UI {

    public BankBalanceUI(final ATM atm, final UI parentUI) {
        super(atm, parentUI);
    }

    @Override
    public String getName() {
        return "Show bankbalance";
    }

    @Override
    public void printContext() {
        System.out.println(
                "Your current bank balance represents " + getAtm().getLoggedInClient().getClient().getBankBalance()
                + " of your main currency");
    }
}
