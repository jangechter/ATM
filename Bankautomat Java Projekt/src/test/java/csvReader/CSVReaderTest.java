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
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cashbox.Cashbox;
import moneynote.Moneynote;
import testData.TestData;

class CSVReaderTest extends TestData {

    @Test
    void testToReadClientNegative() throws IOException{

        CSVReader reader = new CSVReader();

        TEST_CLIENT.setPin("5555");

        assertFalse(CSVReader.readClient(TEST_FILE_CLIENT).equals(TEST_CLIENT));

        TEST_CLIENT.setPin(TestData.TEST_PIN);
    }

    @Test
    void testToReadClientWrongFile() {
        assertThrows(FileNotFoundException.class, () -> CSVReader.readClient(new File("abc")));
    }

    @Test
    void testToReadClientPositive() throws IOException {
        final CSVReader reader = new CSVReader();

        assertDoesNotThrow(() -> CSVReader.readClient(TEST_FILE_CLIENT));
        assertTrue(CSVReader.readClient(TEST_FILE_CLIENT).equals(TEST_CLIENT));
    }

    @Test
    void testToReadCashboxPositiveEuro() throws IOException {
        HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);
        final Cashbox cashbox = new Cashbox(notes);

        assertDoesNotThrow(() -> CSVReader.readCashbox(TEST_FILE_CASHBOX));
        assertTrue(cashbox.equals(CSVReader.readCashbox(TEST_FILE_CASHBOX)));
    }

    @Test
    void testToReadCashboxPositiveUSDollar() throws IOException {
        HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);
        final Cashbox cashbox = new Cashbox(notes);

        assertDoesNotThrow(() -> CSVReader.readCashbox(TEST_FILE_CASHBOX));
        assertTrue(cashbox.equals(CSVReader.readCashbox(TEST_FILE_CASHBOX)));
    }

    @Test
    void testToReadCashboxNegativeEuro() throws IOException {
        HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 90);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);

        final Cashbox cashbox = new Cashbox(notes);

        assertDoesNotThrow(() -> CSVReader.readCashbox(TEST_FILE_CASHBOX));
        assertFalse(cashbox.equals(CSVReader.readCashbox(TEST_FILE_CASHBOX)));
    }

    @Test
    void testToReadCashboxWrongFile(){

        assertThrows(FileNotFoundException.class, () -> CSVReader.readCashbox(new File("abc")));

    }
}
