/*
 * TestData.java
 *
 * Created on 2020-05-07
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package testData;

import java.io.File;
import java.math.BigDecimal;

import client.Client;

public class TestData {
    public static final String TEST_NAME = "Mustermann";
    public static final Double TEST_BANK_BALANCE = 100.00;
    public static final String TEST_FIRSTNAME = "Max";
    public static final String TEST_IBAN = "DE01 2345 6789 0123 4567 89";
    public static final String TEST_PIN = "1234";
    public static final Integer TEST_NUMBER_ATTEMPTS = 0;
    public static final boolean IS_ACTIVE = true;
    public static final Client TEST_CLIENT = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST_PIN, BigDecimal
            .valueOf(TEST_BANK_BALANCE), IS_ACTIVE, TEST_NUMBER_ATTEMPTS);
    public static final String CLIENTS = "/Clients/";
    public static final String CSV = ".csv";
    public static final String CASHBOX = "/Cashbox/";
    public static final String CURRENCY = "Euro";
    public static final File TEST_FILE_CLIENT = new File(System.getProperty("user.dir") + CLIENTS + TEST_IBAN + CSV);
    public static final File TEST_FILE_CASHBOX = new File(System.getProperty("user.dir") + CASHBOX + CURRENCY + CSV);
}
