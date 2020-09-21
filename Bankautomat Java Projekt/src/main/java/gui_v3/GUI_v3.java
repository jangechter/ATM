/*
 * GUI_v3.java
 *
 * Created on 2020-09-21
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v3;

import java.awt.*;

import javax.swing.*;

import atm.ATM;

public abstract class GUI_v3 extends JFrame {

    private JFrame parentFrame;
    private ATM atm;

    protected GUI_v3(final JFrame parentFrame, final ATM atm) throws HeadlessException {
        this.parentFrame = parentFrame;
        this.atm = atm;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 400);
        setVisible(true);
    }

    protected JFrame getParentFrame() {
        return parentFrame;
    }

    protected ATM getAtm() {
        return atm;
    }

    protected void showErrorDialog(final String title, final String message) {

        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    protected void showInfoDialog(final String title, final String message) {

        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    protected void goBack() {

        setVisible(false);

        getParentFrame().setVisible(true);
    }
}
