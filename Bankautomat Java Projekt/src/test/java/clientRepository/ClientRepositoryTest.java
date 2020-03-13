/*
 * ClientRepositoryTest.java
 *
 * Created on 2020-03-13
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package clientRepository;

import java.io.IOException;
import java.math.BigDecimal;

import client.Client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientRepositoryTest {

    private static final String TEST_NAME = "Mustermann";
    private static final double TEST_BANK_BALANCE = 100.00;
    private static final String TEST_FIRSTNAME = "Max";
    private static final String TEST_IBAN = "DE01 2345 6789 0123 4567 89";
    private static final String TEST__PIN = "1234";
    private static final boolean IS_ACTIVE = true;
    private static final Client TEST_CLIENT = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST__PIN,
                                                         BigDecimal.valueOf(TEST_BANK_BALANCE), IS_ACTIVE);

    @Test
    void findClientTestPositive() {

        ClientRepository cr = new ClientRepository();

        assertTrue(cr.findClient(TEST_IBAN).equals(TEST_CLIENT));
    }

    @Test
    void findClientTestNegative() {

        final ClientRepository cr = new ClientRepository();

        TEST_CLIENT.setPin("5555");

        assertFalse(cr.findClient(TEST_IBAN).equals(TEST_CLIENT));

        TEST_CLIENT.setPin(TEST__PIN);
    }

    @Test
    void findClientWrongIBAN() {

        final ClientRepository cr = new ClientRepository();

        assertNull(cr.findClient(" "));

    }
}
