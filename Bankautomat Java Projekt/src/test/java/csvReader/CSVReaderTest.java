/*
 * CSVReaderTest.java
 *
 * Created on 2020-03-13
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.Client;
import clientRepository.ClientRepository;

class CSVReaderTest {

    private static final String TEST_NAME = "Mustermann";
    private static final double TEST_BANK_BALANCE = 100.00;
    private static final String TEST_FIRSTNAME = "Max";
    private static final String TEST_IBAN = "DE01 2345 6789 0123 4567 89";
    private static final String TEST_PIN = "1234";
    private static final boolean IS_ACTIVE = true;
    private static final File TEST_FILE = new File(System.getProperty("user.dir") + "/src/main/resources/" + TEST_IBAN + ".csv");
    private static final Client TEST_CLIENT = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST_PIN, BigDecimal
            .valueOf(TEST_BANK_BALANCE), IS_ACTIVE);

    @Test
    void testToReadClientNegative() throws IOException{

        CSVReader reader = new CSVReader();

        TEST_CLIENT.setPin("5555");

        assertFalse(reader.readClient(TEST_FILE).equals(TEST_CLIENT));

        TEST_CLIENT.setPin(TEST_PIN);
    }

    @Test
    void testToReadClientWrongFile() {
        final CSVReader reader = new CSVReader();
        assertThrows(FileNotFoundException.class, ()->reader.readClient(new File("abc")));
    }

    @Test
    void testToReadClientPositive() throws IOException {
        CSVReader reader = new CSVReader();

        assertDoesNotThrow(()->reader.readClient(TEST_FILE));
        assertTrue(reader.readClient(TEST_FILE).equals(TEST_CLIENT));
    }
}
