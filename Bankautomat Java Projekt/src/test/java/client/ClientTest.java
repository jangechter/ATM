/*
 * ClientTest.java
 *
 * Created on 2020-03-16
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package client;

import java.math.BigDecimal;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientTest {

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
