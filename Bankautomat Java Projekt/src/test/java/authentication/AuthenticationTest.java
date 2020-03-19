/*
 * AuthenticationTest.java
 *
 * Created on 2020-03-19
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package authentication;

import java.math.BigDecimal;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.Client;

class AuthenticationTest {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("TestData");
    private static final String TEST_NAME = RESOURCE_BUNDLE.getString("name");
    private static final double TEST_BANK_BALANCE = Double.parseDouble(RESOURCE_BUNDLE.getString("bankBalance"));
    private static final String TEST_FIRSTNAME = RESOURCE_BUNDLE.getString("firstname");
    private static final String TEST_IBAN = RESOURCE_BUNDLE.getString("de01.2345.6789.0123.4567.89");
    private static final String TEST_PIN = RESOURCE_BUNDLE.getString("pin");
    private static final boolean IS_ACTIVE = Boolean.parseBoolean(RESOURCE_BUNDLE.getString("status"));
    private static final Client TEST_CLIENT = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST_PIN, BigDecimal
            .valueOf(TEST_BANK_BALANCE), IS_ACTIVE);
    @Test
    void testCheckbankBalanceNegative() {

        final Authentication auth = new Authentication();

        auth.logIn(TEST_IBAN, TEST_PIN);
        assertTrue(auth.getClient().isActive());
        assertTrue(auth.isClientLoggedIN());

        try {
            assertFalse(auth.getClient().getBankBalance().compareTo(BigDecimal.valueOf(Double.MIN_VALUE)) == 0);
        } catch (final NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCheckBankBalancePositive() {

        final Authentication auth = new Authentication();

        auth.logIn(TEST_IBAN, TEST_PIN);
        assertTrue(auth.getClient().isActive());
        assertTrue(auth.isClientLoggedIN());

        try {
            assertEquals(0, auth.getClient().getBankBalance().compareTo(BigDecimal.valueOf(TEST_BANK_BALANCE)));
        } catch (final NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCheckPinNegative() {

        final Authentication auth = new Authentication();

        assertFalse(auth.logIn(TEST_IBAN, "0000"));
        assertTrue(auth.getClient().isActive());
        assertFalse(auth.isClientLoggedIN());
    }

    @Test
    void testCheckPinPositive() {

        final Authentication auth = new Authentication();

        assertTrue(auth.logIn(TEST_IBAN, TEST_PIN));
        assertTrue(auth.getClient().isActive());
        assertTrue(auth.isClientLoggedIN());
    }

    @Test
    void testBlockingAfter3Attempts() {

        final Authentication auth = new Authentication();

        //try to login with wrong credentials 3 times
        auth.logIn(TEST_IBAN, "0000");
        auth.logIn(TEST_IBAN, "0000");
        auth.logIn(TEST_IBAN, "0000");
        auth.logIn(TEST_IBAN, "0000");

        assertFalse(auth.getClient().isActive());
        assertFalse(auth.isClientLoggedIN());
    }

    @Test
    void testNoBlockingAfter2Attempts() {

        final Authentication auth = new Authentication();

        //try to login with wrong credentials after 3 times
        auth.logIn(TEST_IBAN, "0000");
        auth.logIn(TEST_IBAN, "0000");
        auth.logIn(TEST_IBAN, TEST_PIN);

        assertTrue(auth.getClient().isActive());
        assertTrue(auth.getNumberAttempts() < 3);
        assertTrue(auth.isClientLoggedIN());
    }

}
