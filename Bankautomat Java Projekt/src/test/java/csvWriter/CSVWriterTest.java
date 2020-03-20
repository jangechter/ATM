/*
 * CSVWriterTest.java
 *
 * Created on 2020-03-20
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvWriter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.Client;
import testData.TestData;

class CSVWriterTest extends TestData {

    private static final String TEST_IBAN2 = "DE11 2222 3333 4444 5555 66";
    private static final Client TEST_CLIENT2 = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN2,
                                                          TEST_PIN,
                                                          BigDecimal.valueOf(TEST_BANK_BALANCE), IS_ACTIVE,
                                                          TEST_NUMBER_ATTEMPTS);

    @Test
    void writeClientTestPositive() throws IOException {

        csvWriter.CSVWriter.writeClient(TEST_CLIENT2);

        File file = new File(System.getProperty("user.dir") + "/Clients/" + TEST_IBAN2 + ".csv");

        assertTrue(file.exists());

        assertTrue(file.delete());
    }

    @Test
    void writeClientTestNegativeNullCliente() throws IOException {

        assertThrows(NullPointerException.class, () -> csvWriter.CSVWriter.writeClient(null));
    }
}
