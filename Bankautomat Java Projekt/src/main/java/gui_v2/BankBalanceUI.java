/*
 * BankBalanceUI.java
 *
 * Created on 2020-05-07
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
    public void printUI() {
        super.printUI();

        System.out.println(
                "Your current bank balance represents " + getAtm().getLoggedInClient().getClient().getBankBalance()
                + " of your main currency");

        getParentUI().printUI();
    }
}
