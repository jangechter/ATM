/*
 * CashboxTest.java
 *
 * Created on 2020-03-25
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package cashbox;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import moneynote.Moneynote;
import testData.TestData;

class CashboxTest extends TestData {

    @Test
    void withdrawTestPositiveEuro() {

        HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 20);
        notes.put(new Moneynote(50), 20);

        HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(5), 100);
        notes2.put(new Moneynote(10), 99);
        notes2.put(new Moneynote(20), 18);
        notes2.put(new Moneynote(50), 19);

        Cashbox cb = new Cashbox(notes);

        cb.withdraw(100);

        assertTrue(new Cashbox(notes2).equals(cb));
    }

    @Test
    void withdrawTestPositive() {

        HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 20);
        notes.put(new Moneynote(50), 20);

        HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(5), 100);
        notes2.put(new Moneynote(10), 99);
        notes2.put(new Moneynote(20), 18);
        notes2.put(new Moneynote(50), 19);

        Cashbox cb = new Cashbox(notes);

        cb.withdraw(100);

        assertTrue(new Cashbox(notes2).equals(cb));
    }
}
