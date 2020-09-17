/*
 * ViewUserInformationGUI.java
 *
 * Created on 2020-09-17
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package gui_v3;

import java.awt.*;

import javax.swing.*;

import atm.ATM;
import client.Client;

public class ViewUserInformationGUI extends GUI_v3 {
    private JButton backButton;
    private JTextArea userInformationTextArea;
    private JPanel mainPanel;

    ViewUserInformationGUI(final JFrame parentFrame, final ATM atm) throws HeadlessException {
        super(parentFrame, atm);

        add(mainPanel);

        backButton.addActionListener(e -> goBack());

        Client user = getAtm().getLoggedInClient().getClient();

        String userInformation = String.format("Name: %s %nFirstname: %s %nIBAN: %s %nBankbalance %.2f",
                                               user.getName(),
                                               user.getFirstName(),
                                               user.getIban(),
                                               user.getBankBalance().doubleValue());

        userInformationTextArea.append(userInformation);
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
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        backButton = new JButton();
        backButton.setText("back");
        mainPanel.add(backButton, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
                                                                                   | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
                                                                                   com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                                                                                   null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1,
                                                                                com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
                                                                                com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                1, null, null, null, 0, false));
        userInformationTextArea = new JTextArea();
        mainPanel.add(userInformationTextArea, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1,
                                                                                                com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                                com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
                                                                                                com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                                com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                                null,
                                                                                                new Dimension(150, 50),
                                                                                                null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1,
                                                                                com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL,
                                                                                1,
                                                                                com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1,
                                                                                com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
                                                                                com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1,
                                                                                com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                                                                                com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL,
                                                                                1,
                                                                                com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                                                                                null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}