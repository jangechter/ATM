/*
 * CashTransfer.java
 *
 * Created on 2020-06-16
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package cashTransfer;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

import client.Client;

public class CashTransfer {

    private static final String CLIENTS = "/Clients/";
    private final Client recipient;
    private final Client applicant;
    private final BigDecimal amount;
    private final Date date;
    private final String purpose;

    public CashTransfer(final Client recipient, final Client applicant, final BigDecimal amount, final Date date,
                        final String purpose) {
        this.recipient = recipient;
        this.applicant = applicant;
        this.amount = amount;
        this.date = date;
        this.purpose = purpose;
    }

    public Client getRecipient() {
        return recipient;
    }

    public Client getApplicant() {
        return applicant;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getPurpose() {
        return purpose;
    }

    void persistCashTransfer() {

        File cashTransferFile = new File(
                System.getProperty("user.dir") + CLIENTS + recipient.getIban() + "/" + recipient.getIban()
                + "_Cash_Transfers.csv");
    }
}
