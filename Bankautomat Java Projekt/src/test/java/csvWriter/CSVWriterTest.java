/*
 * CSVWriterTest.java
 *
 * Created on 2020-04-20
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package csvWriter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cashbox.Cashbox;
import client.Client;
import csvReader.CSVReader;
import moneynote.Moneynote;
import testData.TestData;

class CSVWriterTest extends TestData {

    private static final String TEST_IBAN2 = "DE11 2222 3333 4444 5555 66";
    private static final Client TEST_CLIENT2 = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN2,
                                                          TEST_PIN,
                                                          BigDecimal.valueOf(TEST_BANK_BALANCE), IS_ACTIVE,
                                                          TEST_NUMBER_ATTEMPTS);

    @Test
    void writeClientTestPositiveFileExists() throws IOException {

        csvWriter.CSVWriter.writeClient(TEST_CLIENT2);

        File file = new File(System.getProperty("user.dir") + "/Clients/" + TEST_IBAN2 + ".csv");

        assertTrue(file.exists());

        assertTrue(file.delete());
    }

    @Test
    void writeClientTestPositiveContentValid() throws IOException {

        csvWriter.CSVWriter.writeClient(TEST_CLIENT2);

        File file = new File(System.getProperty("user.dir") + "/Clients/" + TEST_IBAN2 + ".csv");

        assertTrue(TEST_CLIENT2.equals(CSVReader.readClient(file)));

        assertTrue(file.delete());
    }

    @Test
    void writeClientTestNegativeIllegalArgument() {

        assertThrows(IllegalArgumentException.class, () -> csvWriter.CSVWriter.writeClient(null));
    }

    @Test
    void writeClientTestNegativeContentInvalid() throws IOException {

        CSVWriter.writeClient(TEST_CLIENT2);

        File file = new File(System.getProperty("user.dir") + "/Clients/" + TEST_IBAN2 + ".csv");

        TEST_CLIENT2.setPin("0000");

        assertFalse(TEST_CLIENT2.equals(CSVReader.readClient(file)));

        TEST_CLIENT2.setPin(TEST_PIN);
    }

    @Test
    void writeClientTestNegativeFileDoesNotExists() throws IOException {

        CSVWriter.writeClient(TEST_CLIENT2);

        File file = new File(System.getProperty("user.dir") + "/Clients/" + TEST_IBAN2 + "Wrong Iban" + ".csv");

        assertFalse(file.exists());
    }

    @Test
    void writeCashboxTestPositive() throws IOException {

        HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);
        final Cashbox cashbox = new Cashbox(notes);

        CSVWriter.writeCashbox(cashbox);

        assertTrue(cashbox.equals(
                CSVReader.readCashbox(new File(System.getProperty("user.dir") + CASHBOX + "CashboxNotes" + CSV))));
    }

    @Test
    void writeCashboxTestNegative() throws IOException {

        HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);
        final Cashbox cashbox = new Cashbox(notes);

        CSVWriter.writeCashbox(cashbox);

        notes.put(new Moneynote(10), 900);

        assertFalse(cashbox.equals(
                CSVReader.readCashbox(new File(System.getProperty("user.dir") + CASHBOX + "CashboxNotes" + CSV))));
    }

    @Test
    void writeCashboxTestFileExist() throws IOException {
        HashMap<Moneynote, Integer> notes = new HashMap<>();

        final Cashbox cashbox = new Cashbox(notes);

        CSVWriter.writeCashbox(cashbox);

        File file = new File(System.getProperty("user.dir") + CASHBOX + "CashboxNotes" + CSV);

        assertTrue(cashbox.equals(
                CSVReader.readCashbox(file)));

        assertTrue(file.exists());
    }

    @Test
    void writeCashboxTestNegativeIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> CSVWriter.writeCashbox(null));
    }
}
