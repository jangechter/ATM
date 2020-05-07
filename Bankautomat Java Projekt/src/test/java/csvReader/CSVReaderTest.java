/*
 * CSVReaderTest.java
 *
 * Created on 2020-05-07
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import testData.TestData;

class CSVReaderTest extends TestData {

    @Test
    void testToReadClientNegative() throws IOException {

        final String clientValues = TEST_NAME + "," + TEST_FIRSTNAME + "," + TEST_IBAN + "," + TEST_PIN + ","
                                    + TEST_BANK_BALANCE + "," + true + "," + 0;

        TEST_CLIENT.setPin("5555");

        assertEquals(CSVReader.readCSVFile(TEST_FILE_CLIENT).get(0), clientValues);

        TEST_CLIENT.setPin(TestData.TEST_PIN);
    }

    @Test
    void testToReadClientWrongFile() {
        assertThrows(FileNotFoundException.class, () -> CSVReader.readCSVFile(new File("abc")));
    }

    @Test
    void testToReadClientPositive() throws IOException {

        final String clientValues = TEST_NAME + "," + TEST_FIRSTNAME + "," + TEST_IBAN + "," + TEST_PIN + ","
                                    + TEST_BANK_BALANCE + "," + true + "," + 0;

        assertDoesNotThrow(() -> CSVReader.readCSVFile(TEST_FILE_CLIENT));
        assertEquals(clientValues, CSVReader.readCSVFile(TEST_FILE_CLIENT).get(0));
    }

    @Test
    void testToReadCashboxPositiveEuro() throws IOException {

        List<String> cashboxValues = new ArrayList<>();

        cashboxValues.add("5,100");
        cashboxValues.add("10,100");
        cashboxValues.add("20,100");
        cashboxValues.add("50,100");
        cashboxValues.add("100,100");
        cashboxValues.add("200,100");

        assertDoesNotThrow(() -> CSVReader.readCSVFile(TEST_FILE_CASHBOX));
        assertEquals(cashboxValues, CSVReader.readCSVFile(TEST_FILE_CASHBOX));
    }

    @Test
    void testToReadCashboxNegativeEuro() throws IOException {
        List<String> cashboxValues = new ArrayList<>();

        cashboxValues.add("5,90");
        cashboxValues.add("10,100");
        cashboxValues.add("20,100");
        cashboxValues.add("50,100");
        cashboxValues.add("100,100");
        cashboxValues.add("200,100");

        assertDoesNotThrow(() -> CSVReader.readCSVFile(TEST_FILE_CASHBOX));
        assertNotEquals(cashboxValues, CSVReader.readCSVFile(TEST_FILE_CASHBOX));
    }

    @Test
    void testToReadCashboxWrongFile() {

        assertThrows(FileNotFoundException.class, () -> CSVReader.readCSVFile(new File("abc")));
    }




}
