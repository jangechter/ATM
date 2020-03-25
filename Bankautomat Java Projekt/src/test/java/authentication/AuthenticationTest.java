/*
 * AuthenticationTest.java
 *
 * Created on 2020-03-25
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package authentication;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import csvWriter.CSVWriter;
import testData.TestData;

class AuthenticationTest extends TestData {

    @Test
    void testCheckbankBalanceNegative() {

        final Authentication auth = new Authentication();

        auth.logIn(TEST_IBAN, TEST_PIN);
        assertTrue(auth.getClient().isActive());

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

        auth.getClient().setActive(true);
        auth.getClient().setNumberAttempts(0);
    }

    @Test
    void testCheckPinPositive() {

        final Authentication auth = new Authentication();

        assertTrue(auth.logIn(TEST_IBAN, TEST_PIN));
        assertTrue(auth.getClient().isActive());
    }

    @Test
    void testBlockingAfter3Attempts() throws IOException {

        final Authentication auth = new Authentication();

        //try to login with wrong credentials 3 times
        auth.logIn(TEST_IBAN, "0000");
        auth.logIn(TEST_IBAN, "0000");
        auth.logIn(TEST_IBAN, "0000");
        auth.logIn(TEST_IBAN, "0000");

        assertFalse(auth.getClient().isActive());

        auth.getClient().setActive(true);
        auth.getClient().setNumberAttempts(0);

        CSVWriter.writeClient(auth.getClient());
    }

    @Test
    void testNoBlockingAfter2Attempts() {

        final Authentication auth = new Authentication();

        //try to login with wrong credentials after 3 times
        auth.logIn(TEST_IBAN, "0000");
        auth.logIn(TEST_IBAN, "0000");
        auth.logIn(TEST_IBAN, TEST_PIN);

        assertTrue(auth.getClient().isActive());
        assertTrue(auth.getClient().getNumberAttempts() < 3);
    }

}
