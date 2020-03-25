/*
 * ClientRepositoryTest.java
 *
 * Created on 2020-03-25
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package clientRepository;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import testData.TestData;

class ClientRepositoryTest extends TestData {

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

        TEST_CLIENT.setPin(TEST_PIN);
    }

    @Test
    void findClientTestWrongIBAN() {

        final ClientRepository cr = new ClientRepository();

        assertNull(cr.findClient(" "));
    }

    void updtaeClientTestNullPointer() {

    }
}
