/*
 * ClientTest.java
 *
 * Created on 2020-03-19
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package client;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import testData.TestData;

public class ClientTest extends TestData {

    @Test
    void testEqualsPositive() {

        assertTrue(TEST_CLIENT.equals(TEST_CLIENT));
        assertTrue(TEST_CLIENT.equals(
                new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST_PIN, BigDecimal.valueOf(TEST_BANK_BALANCE),
                           IS_ACTIVE)));
    }

    @Test
    void testClientEqualsNegative() {
        assertFalse(TEST_CLIENT.equals(null));
        assertFalse(TEST_CLIENT.equals(
                new Client(" ", " ", TEST_IBAN, TEST_PIN, BigDecimal.valueOf(TEST_BANK_BALANCE), IS_ACTIVE)));
    }
}
