/*
 * CashTransfer.java
 *
 * Created on 2020-09-17
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package cashTransfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
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

    public CashTransfer(final String transactionID, final String recipientIBAN, final String applicantIBAN,
                        final BigDecimal amount, final LocalDateTime date, final String purpose) {

        this.transactionID = transactionID;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        final CashTransfer that = (CashTransfer) o;
        return transactionID.equals(that.getTransactionID()) &&
               recipientIBAN.equals(that.getRecipientIBAN()) &&
               applicantIBAN.equals(that.getApplicantIBAN()) &&
               (amount.compareTo(that.getAmount()) == 0) &&
               date.equals(that.getDate()) &&
               purpose.equals(that.getPurpose());
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionID, recipientIBAN, applicantIBAN, amount, date,
                            purpose);
    }
}
