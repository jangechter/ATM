/*
 * AuthenticationTest.java
 *
 * Created on 2020-03-13
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package authentication;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.Client;

public class AuthenticationTest {


    private static final String TEST_NAME = "Mustermann";
    private static final double TEST_BANK_BALANCE = 100.00;
    private static final String TEST_FIRSTNAME = "Max";
    private static final String TEST_IBAN = "DE01 2345 6789 0123 4567 89";
    private static final String TEST__PIN = "1234";
    private static final boolean IS_ACTIVE = true;
    private static final Client TEST_CLIENT = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST__PIN,
                                                         BigDecimal.valueOf(TEST_BANK_BALANCE), IS_ACTIVE);

    @Test
    public void testCheckbankBalanceNegative() {

        final Authentication auth = new Authentication();

        auth.logIn(TEST_IBAN, TEST__PIN);

        try {

            assertFalse(auth.getClient().getBankBalance().compareTo(BigDecimal.valueOf(Double.MIN_VALUE)) == 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCheckBankBalancePositive() {

        final Authentication auth = new Authentication();

        auth.logIn(TEST_IBAN, TEST__PIN);

        try {

            assertTrue(auth.getClient().getBankBalance().compareTo(BigDecimal.valueOf(TEST_BANK_BALANCE)) == 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckPinNegative() {

        final Authentication auth = new Authentication();

        assertFalse(auth.logIn(TEST_IBAN, "0000"));
    }

    @Test
    public void testCheckPinPositive() {

        final Authentication auth = new Authentication();

        assertTrue(auth.logIn(TEST_IBAN, TEST__PIN));
    }

}
