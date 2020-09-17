/*
 * WithdrawMoneyGUI.java
 *
 * Created on 2020-09-17
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v3;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.*;

import atm.ATM;
import moneynote.Moneynote;

public class WithdrawMoneyGUI extends GUI_v3 {
    private JPanel mainPanel;
    private JComboBox amountCombobox;
    private JButton withdrawButton;
    private JTextField otherAmountTextfield;
    private JLabel withdrawLabel;
    private JButton backButton;
    private JTextPane outputTextpanel;
    private JLabel otherAmountLabel;

    public WithdrawMoneyGUI(ATM atm, JFrame parent) throws HeadlessException {

        super(parent, atm);

        add(mainPanel);

        otherAmountTextfield.setVisible(false);
        otherAmountLabel.setVisible(false);

        fillComboBox();

        backButton.addActionListener(e -> goBack());

        amountCombobox.addItemListener(e -> handleOtherAmountTextboxVisibilty());
        withdrawButton.addActionListener(e -> withdrawMoney());
    }

    private void withdrawMoney() {

        outputTextpanel.setText("");

        Integer amount;

        if (amountCombobox.getSelectedItem().toString().equals("Other Amount")) {

            if (otherAmountTextfield.getText().equals("")) {

                showErrorDialog("Input error", "Please enter an amount");

                return;
            }

            amount = Integer.parseInt(otherAmountTextfield.getText());
        } else {

            amount = Integer.parseInt(amountCombobox.getSelectedItem().toString());
        }

        final Optional<HashMap<Moneynote, Integer>> outputMoneyNotesMap = getAtm().withdrawMoney(amount);

        if (!outputMoneyNotesMap.isPresent()) {

            showErrorDialog("Internal error", "Error while withdraw");

            return;
        }

        StringBuilder output = new StringBuilder(String.format("Moneynote : Amount\n"));

        outputMoneyNotesMap.get().forEach(
                (moneynote, integer) -> output.append(String.format("%4d : %4d\n", moneynote.getValue(), integer)));

        outputTextpanel.setText(output.toString());
    }

    private void handleOtherAmountTextboxVisibilty() {

        if (amountCombobox.getSelectedItem().toString() == "Other amount") {

            otherAmountLabel.setVisible(true);
            otherAmountTextfield.setVisible(true);
        }

        if (amountCombobox.getSelectedItem().toString() != "Other amount") {

            otherAmountLabel.setVisible(false);
            otherAmountTextfield.setVisible(false);

            otherAmountTextfield.setText("");
        }
    }

    private void withdraw() {

        getAtm().withdrawMoney(Integer.parseInt(amountCombobox.getSelectedItem().toString()));
    }

    private void fillComboBox() {

        ArrayList<Moneynote> moneynotesInCashBox = new ArrayList<>(
                getAtm().getCashbox().getNotes().keySet().stream().sorted().collect(
                        Collectors.toList()));

        for (Moneynote m : moneynotesInCashBox) {

            amountCombobox.addItem(m.getValue());
        }

        amountCombobox.addItem("Other amount");
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }
    /**
     * Method generated by IntelliJ IDEA GUI Designer >>> IMPORTANT!! <<< DO NOT edit this method OR call it in your
     * code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 5, new Insets(0, 0, 0, 0), -1, -1));
        withdrawButton = new JButton();
        withdrawButton.setText("Withdraw");
        mainPanel.add(withdrawButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 5,
                                                                                       com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                       com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
                                                                                       com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
                                                                                       | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
                                                                                       com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                       null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("back");
        mainPanel.add(backButton, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
                                                                                   | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                   null, null, null, 0, false));
        withdrawLabel = new JLabel();
        withdrawLabel.setText("Withdraw money");
        mainPanel.add(withdrawLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 3,
                                                                                      com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                      com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
                                                                                      com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                      com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                      null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1,
                                                                                com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
                                                                                com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                1, null, null, null, 0, false));
        amountCombobox = new JComboBox();
        mainPanel.add(amountCombobox, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 5,
                                                                                       com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
                                                                                       com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
                                                                                       com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
                                                                                       com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                       null, null, null, 0, false));
        otherAmountTextfield = new JTextField();
        mainPanel.add(otherAmountTextfield, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 2,
                                                                                             com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
                                                                                             com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
                                                                                             com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                             com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                             null,
                                                                                             new Dimension(150, -1),
                                                                                             null, 0, false));
        otherAmountLabel = new JLabel();
        otherAmountLabel.setText("other Amount");
        mainPanel.add(otherAmountLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1,
                                                                                         com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
                                                                                         com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
                                                                                         com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                         com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                         null, null, null, 0, false));
        outputTextpanel = new JTextPane();
        mainPanel.add(outputTextpanel, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 5,
                                                                                        com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                        com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
                                                                                        com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                        com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                        null, new Dimension(30, 40),
                                                                                        null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
