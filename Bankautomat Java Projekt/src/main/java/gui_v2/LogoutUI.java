/*
 * LogoutUI.java
 *
 * Created on 2020-06-08
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v2;

import atm.ATM;

public class LogoutUI extends UI {

    public LogoutUI(final ATM atm, final UI parentUI) {
        super(atm, parentUI);
    }

    @Override
    public String getName() {
        return "Logout";
    }

    @Override
    public void printContext() {
        getAtm().logout();
    }
}
