/*
 * ClientRepositoryTest.java
 *
 * Created on 2020-03-19
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package clientRepository;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.Client;

class ClientRepositoryTest {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("TestData");
    private static final String TEST_NAME = RESOURCE_BUNDLE.getString("name");
    private static final double TEST_BANK_BALANCE = Double.parseDouble(RESOURCE_BUNDLE.getString("bankBalance"));
    private static final String TEST_FIRSTNAME = RESOURCE_BUNDLE.getString("firstname");
    private static final String TEST_IBAN2 = "DE44 5555 6666 7777 8888 99";
    private static final String TEST_IBAN = RESOURCE_BUNDLE.getString("de01.2345.6789.0123.4567.89");
    private static final String TEST_PIN = RESOURCE_BUNDLE.getString("pin");
    private static final boolean IS_ACTIVE = Boolean.parseBoolean(RESOURCE_BUNDLE.getString("status"));
    private static final Client TEST_CLIENT = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST_PIN, BigDecimal
            .valueOf(TEST_BANK_BALANCE), IS_ACTIVE);
    private static final String SRC_TEST_RESOURCES = "/src/test/resources/";
    private static final String SRC_MAIN_RESOURCES = "/src/main/resources/";
    private static final String CSV = ".csv";
    private static final File TEST_FILE1 = new File(System.getProperty("user.dir") + SRC_TEST_RESOURCES
                                                    + TEST_IBAN + CSV);
    private static final File TEST_FILE2 = new File(System.getProperty("user.dir") + SRC_MAIN_RESOURCES
                                                    + TEST_IBAN2 + CSV);

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

    @Test
    void findFileTestPositiveTestResources() throws IOException {

        final ClientRepository cr = new ClientRepository();

        assertTrue(TEST_FILE1.getPath().equals(cr.findFileByIBAN(TEST_IBAN).getPath()));
    }

    @Test
    void findFileTestPositiveMainResources() throws IOException {

        final ClientRepository cr = new ClientRepository();

        assertTrue(TEST_FILE2.getPath().equals(cr.findFileByIBAN(TEST_IBAN2).getPath()));
    }

}
