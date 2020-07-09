/*
 * ClientRepositoryTest.java
 *
 * Created on 2020-07-09
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package clientRepository;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Exceptions.ClientParsingException;
import repositories.ClientRepository;
import testData.TestData;

class ClientRepositoryTest extends TestData {

    @Test
    void findClientTestPositive() throws ClientParsingException {

        final ClientRepository cr = new ClientRepository();

        assertEquals(TEST_CLIENT, cr.findClient(TEST_IBAN));
    }

    @Test
    void findClientTestNegative() throws ClientParsingException {

        final ClientRepository cr = new ClientRepository();

        TEST_CLIENT.setPin("5555");

        assertNotEquals(TEST_CLIENT, cr.findClient(TEST_IBAN));

        TEST_CLIENT.setPin(TEST_PIN);
    }

    @Test
    void findClientTestWrongIBAN() throws ClientParsingException {

        final ClientRepository cr = new ClientRepository();

        assertNull(cr.findClient(" "));
    }

    @Test
    void persistClientTestPositive() throws IOException {

        final ClientRepository cr = new ClientRepository();

        cr.persistClient(TEST_CLIENT);

        assertEquals(TEST_CLIENT,
                     cr.findClient(TEST_IBAN));
    }

    @Test
    void persistClientTestNegativeNPE() {

        final ClientRepository cr = new ClientRepository();

        assertThrows(IllegalArgumentException.class, () -> cr.persistClient(null));
    }

    @Test
    void testToReadClientNegativeClientParsingException() {

        final ClientRepository cr = new ClientRepository();

        assertThrows(ClientParsingException.class, () -> cr.findClient("DE00 0000 0000 0000 0000 00"));
    }

    @Test
    void findClientThrowsClientParsingException() {

        final ClientRepository cr = new ClientRepository();

        assertThrows(ClientParsingException.class, () -> cr.findClient("DE00 0000 0000 0000 0000 00"));
    }
}
