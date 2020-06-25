/*
 * CashTransfer.java
 *
 * Created on 2020-06-25
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package cashTransfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CashTransfer {

    private final String transactionID;
    private final String recipientIBAN;
    private final String applicantIBAN;
    private final BigDecimal amount;
    private final LocalDateTime date;
    private final String purpose;

    public CashTransfer(final String recipientIBAN, final String applicantIBAN, final BigDecimal amount,
                        final LocalDateTime date,
                        final java.lang.String purpose) {
        transactionID = UUID.randomUUID().toString();
        this.recipientIBAN = recipientIBAN;
        this.applicantIBAN = applicantIBAN;
        this.amount = amount;
        this.date = date;
        this.purpose = purpose;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getRecipientIBAN() {
        return recipientIBAN;
    }

    public String getApplicantIBAN() {
        return applicantIBAN;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public java.lang.String getPurpose() {
        return purpose;
    }

}
