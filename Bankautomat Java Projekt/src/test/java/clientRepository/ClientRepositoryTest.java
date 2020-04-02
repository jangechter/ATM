/*
 * ClientRepositoryTest.java
 *
 * Created on 2020-04-02
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package clientRepository;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import csvReader.CSVReader;
import testData.TestData;

class ClientRepositoryTest extends TestData {

    @Test
    void findClientTestPositive() {

        final ClientRepository cr = new ClientRepository();

        assertEquals(TEST_CLIENT, cr.findClient(TEST_IBAN));
    }

    @Test
    void findClientTestNegative() {

        final ClientRepository cr = new ClientRepository();

        TEST_CLIENT.setPin("5555");

        assertNotEquals(TEST_CLIENT, cr.findClient(TEST_IBAN));

        TEST_CLIENT.setPin(TEST_PIN);
    }

    @Test
    void findClientTestWrongIBAN() {

        final ClientRepository cr = new ClientRepository();

        assertNull(cr.findClient(" "));
    }

    @Test
    void persistClientTestPositive() throws IOException {

        final ClientRepository cr = new ClientRepository();

        cr.persistClient(TEST_CLIENT);

        assertEquals(TEST_CLIENT,
                     CSVReader.readClient(new File(System.getProperty("user.dir") + CLIENTS + TEST_IBAN + CSV)));
    }

    @Test
    void persistClientTestNegativeNPE() {

        final ClientRepository cr = new ClientRepository();

        assertThrows(IllegalArgumentException.class, () -> cr.persistClient(null));
    }
}
