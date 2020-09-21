/*
 * ViewCashTransferGUI.java
 *
 * Created on 2020-09-21
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v3;

import java.awt.*;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import atm.ATM;
import cashTransfer.CashTransfer;

public class ViewCashTransferGUI extends GUI_v3 {
    private JTable cashTransferTable;
    private JPanel mainPanel;
    private JButton backButton;
    private JTable outgoingCashtransfers;
    private JTable incomingCashTransfers;

    public ViewCashTransferGUI(final JFrame parentFrame, final ATM atm) throws HeadlessException {
        super(parentFrame, atm);

        add(mainPanel);

        setSize(600, 600);

        fillOutgoingCashTransferTable();
        fillIncomingcashTransferTable();

        backButton.addActionListener(e -> goBack());
    }

    private void fillOutgoingCashTransferTable() {

        String[] columnHeaders = {"Date", "Amount", "Recipient Iban", "Purpose"};

        DefaultTableModel outgoingCashtransfersModel = new DefaultTableModel(0, 0);

        outgoingCashtransfersModel.setColumnIdentifiers(columnHeaders);

        outgoingCashtransfersModel.addRow(columnHeaders);

        List<CashTransfer> cashTransfers = getAtm().getLoggedInClient().getClient().getCashRepository()
                                                   .getCashTransfers()
                                                   .stream()
                                                   .filter(ct -> ct.getAmount().doubleValue() < 0)
                                                   .sorted(Comparator.comparing(CashTransfer::getDate))
                                                   .collect(Collectors.toList());

        for (final CashTransfer c : cashTransfers) {

            final Object[] content = {c.getDate().truncatedTo(ChronoUnit.MINUTES).toString().replace("T", " "),
                                      c.getAmount(), c.getRecipientIBAN(), c.getPurpose()};

            outgoingCashtransfersModel.addRow(content);
        }

        outgoingCashtransfers.setModel(outgoingCashtransfersModel);
    }

    private void fillIncomingcashTransferTable() {

        String[] columnHeaders = {"Date", "Amount", "Applicant Iban", "Purpose"};

        List<CashTransfer> cashTransfers = getAtm().getLoggedInClient().getClient().getCashRepository()
                                                   .getCashTransfers()
                                                   .stream()
                                                   .filter(ct -> ct.getAmount().doubleValue() > 0)
                                                   .sorted(Comparator.comparing(CashTransfer::getDate))
                                                   .collect(Collectors.toList());

        DefaultTableModel incomingCashtransfersModel = new DefaultTableModel(0, 0);

        incomingCashtransfersModel.setColumnIdentifiers(columnHeaders);
        incomingCashtransfersModel.addRow(columnHeaders);

        for (final CashTransfer c : cashTransfers) {

            final Object[] content = {c.getDate().truncatedTo(ChronoUnit.MINUTES).toString().replace("T", " "),
                                      c.getAmount(), c.getRecipientIBAN(), c.getPurpose()};

            incomingCashtransfersModel.addRow(content);
        }

        incomingCashTransfers.setModel(incomingCashtransfersModel);
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
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        backButton = new JButton();
        backButton.setText("back");
        mainPanel.add(backButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
                                                                                   | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                   null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1,
                                                                                com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
                                                                                com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                1, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setHorizontalTextPosition(0);
        label1.setText("Outgoing cash transfers:");
        mainPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2,
                                                                               com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
                                                                               com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
                                                                               com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                               com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                               null, null, null, 0, false));
        outgoingCashtransfers = new JTable();
        mainPanel.add(outgoingCashtransfers, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2,
                                                                                              com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                              com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
                                                                                              com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                              com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                              null,
                                                                                              new Dimension(150, 50),
                                                                                              null, 0, false));
        incomingCashTransfers = new JTable();
        mainPanel.add(incomingCashTransfers, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2,
                                                                                              com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                              com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
                                                                                              com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                              com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                              null,
                                                                                              new Dimension(150, 50),
                                                                                              null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Incoming cash transfers:");
        mainPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1,
                                                                               com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
                                                                               com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
                                                                               com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                               com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                               null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
