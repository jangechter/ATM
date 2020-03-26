/*
 * CashboxTest.java
 *
 * Created on 2020-03-25
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package cashbox;

import java.io.File;
import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import csvReader.CSVReader;
import moneynote.Moneynote;
import testData.TestData;
import static currency.Currency.*;

class CashboxTest extends TestData {

    @Test
    void withdrawTestPositiveEuro() throws IOException {

        HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);

        HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(5), 100);
        notes2.put(new Moneynote(10), 99);
        notes2.put(new Moneynote(20), 98);
        notes2.put(new Moneynote(50), 99);
        notes2.put(new Moneynote(100), 100);
        notes2.put(new Moneynote(200), 100);

        Cashbox cb = new Cashbox(notes);

        cb.withdraw(100);

        assertTrue(new Cashbox(notes).equals(cb));
    }

    @Test
    void withdrawTestPositiveEuro2() throws IOException {
        HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);

        HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(5), 98);
        notes2.put(new Moneynote(20), 98);
        notes2.put(new Moneynote(50), 99);
        notes2.put(new Moneynote(100), 100);
        notes2.put(new Moneynote(200), 100);

        Cashbox cb = new Cashbox(notes);

        cb.withdraw(100);

        assertTrue(new Cashbox(notes).equals(cb));
    }
}
