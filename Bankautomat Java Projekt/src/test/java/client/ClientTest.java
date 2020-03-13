/*
 * ClientTest.java
 *
 * Created on 2020-03-13
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package client;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientTest {

    private static final String TEST_NAME = "Mustermann";
    private static final double TEST_BANK_BALANCE = 100.00;
    private static final String TEST_FIRSTNAME = "Max";
    private static final String TEST_IBAN = "DE01 2345 6789 0123 4567 89";
    private static final String TEST__PIN = "1234";
    private static final boolean IS_ACTIVE = true;
    private static final Client TEST_CLIENT = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST__PIN, BigDecimal
            .valueOf(TEST_BANK_BALANCE), IS_ACTIVE);

    @Test
    void testEqualsPositive() {

        assertTrue(TEST_CLIENT.equals(TEST_CLIENT));
        assertTrue(TEST_CLIENT.equals(
                new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST__PIN, BigDecimal.valueOf(TEST_BANK_BALANCE),
                           IS_ACTIVE)));
    }

    @Test
    void testClientEqualsNegative() {
        assertFalse(TEST_CLIENT.equals(null));
        assertFalse(TEST_CLIENT.equals(
                new Client(" ", " ", TEST_IBAN, TEST__PIN, BigDecimal.valueOf(TEST_BANK_BALANCE), IS_ACTIVE)));
    }
}
