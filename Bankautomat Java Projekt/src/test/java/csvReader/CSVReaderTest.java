/*
 * CSVReaderTest.java
 *
 * Created on 2020-03-13
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvReader;

import java.math.BigDecimal;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import ClientRepository.ClientRepository;
import client.Client;

public class CSVReaderTest {

    private static final String TEST_NAME = "Mustermann";
    private static final double TEST_BANK_BALANCE = 100.00;
    private static final String TEST_FIRSTNAME = "Max";
    private static final String TEST_IBAN = "DE01 2345 6789 0123 4567 89";
    private static final String TEST_PIN = "1234";
    private static final boolean IS_ACTIVE = true;
    private static final Client CLIENT = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST_PIN, BigDecimal
            .valueOf(TEST_BANK_BALANCE), IS_ACTIVE);

    @Test
    public void testToReadClientNegative() {

        final ClientRepository cr = new ClientRepository();

        CLIENT.setPin("5555");

        assertFalse(CLIENT.equals(cr.findClient(TEST_IBAN)));

        CLIENT.setPin(TEST_PIN);
    }

    @Test
    public void testToReadClientWrongFile() {

        final ClientRepository cr = new ClientRepository();

        assertNull(cr.findClient("wrongFile"));
    }

    @org.junit.Test
    public void testToReadClientPositive() {

        final ClientRepository cr = new ClientRepository();

        assertTrue(cr.findClient(TEST_IBAN).equals(CLIENT));
    }
}
