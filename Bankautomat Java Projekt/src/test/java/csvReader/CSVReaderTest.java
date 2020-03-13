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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ClientRepository.ClientRepository;
import client.Client;

public class CSVReaderTest {

    private static final String TEST_NAME = "Mustermann";
    private static final double TEST_BANK_BALANCE = 100.00;
    private static final String TEST_FIRSTNAME = "Max";
    private static final String TEST_IBAN = "DE01 2345 6789 0123 4567 89";
    private static final String TEST__PIN = "1234";
    private static final boolean IS_ACTIVE = true;
    private static final Client client = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST__PIN, BigDecimal
            .valueOf(TEST_BANK_BALANCE), IS_ACTIVE);

    @Test
    public void test_toReadClient_Negative() {

        final ClientRepository cr = new ClientRepository();

        client.setPin("5555");

        assertFalse(client.equals(cr.findClient(TEST_IBAN)));

        client.setPin(TEST__PIN);
    }

    @Test
    public void test_toReadClient_WrongFile() {

        final ClientRepository cr = new ClientRepository();

        assertEquals(null, cr.findClient("wrongFile"));
    }

    @org.junit.Test
    public void test_ToRead_Client_Positive() {

        final ClientRepository cr = new ClientRepository();

        assertTrue("Clients Equal", client.equals(cr.findClient(TEST_IBAN)));
    }
}
