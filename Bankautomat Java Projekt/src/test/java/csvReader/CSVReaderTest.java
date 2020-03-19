/*
 * CSVReaderTest.java
 *
 * Created on 2020-03-19
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import testData.TestData;

class CSVReaderTest extends TestData {

    @Test
    void testToReadClientNegative() throws IOException{

        CSVReader reader = new CSVReader();

        TEST_CLIENT.setPin("5555");

        assertFalse(CSVReader.readClient(TEST_FILE).equals(TEST_CLIENT));

        TEST_CLIENT.setPin(TestData.TEST_PIN);
    }

    @Test
    void testToReadClientWrongFile() {
        final CSVReader reader = new CSVReader();
        assertThrows(FileNotFoundException.class, () -> CSVReader.readClient(new File("abc")));
    }

    @Test
    void testToReadClientPositive() throws IOException {
        final CSVReader reader = new CSVReader();

        assertDoesNotThrow(() -> CSVReader.readClient(TEST_FILE));
        assertTrue(CSVReader.readClient(TEST_FILE).equals(TEST_CLIENT));
    }
}
