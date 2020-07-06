/*
 * TestData.java
 *
 * Created on 2020-07-06
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package testData;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import cashTransfer.CashTransfer;
import client.Client;

public class TestData {
    public static final java.lang.String TEST_NAME = "Mustermann";
    public static final Double TEST_BANK_BALANCE = 100.00;
    public static final java.lang.String TEST_FIRSTNAME = "Max";
    public static final java.lang.String TEST_IBAN = "DE01 2345 6789 0123 4567 89";
    public static final java.lang.String TEST_PIN = "1234";
    public static final Integer TEST_NUMBER_ATTEMPTS = 0;
    public static final boolean IS_ACTIVE = true;
    public static final Client TEST_CLIENT = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST_PIN, BigDecimal
            .valueOf(TEST_BANK_BALANCE), IS_ACTIVE, TEST_NUMBER_ATTEMPTS);
    public static final java.lang.String CLIENTS = "/Clients/";
    public static final java.lang.String CSV = ".csv";
    public static final java.lang.String CASHBOX = "/Cashbox/";
    public static final java.lang.String CURRENCY = "Euro";
    public static final File TEST_FILE_CLIENT = new File(
            System.getProperty("user.dir") + CLIENTS + TEST_IBAN + "/" + TEST_IBAN + CSV);
    public static final File TEST_FILE_CASHBOX = new File(System.getProperty("user.dir") + CASHBOX + CURRENCY + CSV);
    public static final String TEST_TRANSACTION_ID = "1";
    public static final String TEST_RECIPIENT_IBAN = "DE01 2345 5555 7777 0909 44";
    public static final String TEST_APPLICANT_IBAN = "DE01 2345 6789 0123 4567 89";
    public static final Double TEST_TRANSFER_AMOUNT = 50.00;
    public static final LocalDateTime TEST_DATE = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
    public static final String TEST_PURPOSE = "Unit-Test CashTransfer";
    public static final CashTransfer TEST_CASH_TRANSFER = new CashTransfer(TEST_TRANSACTION_ID, TEST_RECIPIENT_IBAN,
                                                                           TEST_APPLICANT_IBAN,
                                                                           BigDecimal.valueOf(TEST_TRANSFER_AMOUNT),
                                                                           TEST_DATE, TEST_PURPOSE);
    public static final Client TEST_CLIENT2 = new Client("Mustermann", "Max", "DE11 2222 3333 4444 5555 66", "1234",
                                                         BigDecimal.valueOf(2000), true, 0);

    public static final String TEST_IBAN3 = "DE11 2222 3333 4444 5555 66";
}
