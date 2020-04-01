/*
 * CashboxTest.java
 *
 * Created on 2020-04-01
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package cashbox;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Exceptions.WithdrawNotPossibleException;
import moneynote.Moneynote;
import testData.TestData;

class CashboxTest extends TestData {

    @Test
    void withdrawTestPositiveEuro() throws IOException {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(5), 100);
        notes2.put(new Moneynote(10), 99);
        notes2.put(new Moneynote(20), 98);
        notes2.put(new Moneynote(50), 99);
        notes2.put(new Moneynote(100), 100);
        notes2.put(new Moneynote(200), 100);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(100);

        assertEquals(new Cashbox(notes), cb);
    }

    @Test
    void withdrawTestPositiveEuro2() throws IOException {
        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(5), 98);
        notes2.put(new Moneynote(20), 98);
        notes2.put(new Moneynote(50), 99);
        notes2.put(new Moneynote(100), 100);
        notes2.put(new Moneynote(200), 100);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(100);

        assertEquals(new Cashbox(notes), cb);
    }

    @Test
    void withdrawTestPositiveEuro3() throws IOException {
        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(5), 100);
        notes2.put(new Moneynote(20), 100);
        notes2.put(new Moneynote(50), 100);
        notes2.put(new Moneynote(100), 100);
        notes2.put(new Moneynote(200), 95);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(1000);

        assertEquals(new Cashbox(notes), cb);
    }

    @Test
    void withdrawTestNegativeEuroWithdrawNotPossible() {
        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        final Cashbox cb = new Cashbox(notes);

        assertThrows(IllegalArgumentException.class, () -> cb.withdraw(222));
    }

    @Test
    void withdrawTestPositiveDollar() {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(1), 100);
        notes.put(new Moneynote(2), 100);
        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);
        notes.put(new Moneynote(500), 100);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(100);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(1), 100);
        notes2.put(new Moneynote(2), 100);
        notes2.put(new Moneynote(5), 100);
        notes2.put(new Moneynote(10), 100);
        notes2.put(new Moneynote(20), 100);
        notes2.put(new Moneynote(50), 98);
        notes2.put(new Moneynote(100), 100);
        notes2.put(new Moneynote(200), 100);
        notes2.put(new Moneynote(500), 100);

        final Cashbox cb2 = new Cashbox(notes2);

        assertEquals(cb, cb2);
    }

    @Test
    void withdrawTestPositiveDollar2() {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(1), 100);
        notes.put(new Moneynote(2), 100);
        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 1);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);
        notes.put(new Moneynote(500), 100);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(100);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(1), 100);
        notes2.put(new Moneynote(2), 100);
        notes2.put(new Moneynote(5), 100);
        notes2.put(new Moneynote(10), 99);
        notes2.put(new Moneynote(20), 98);
        notes2.put(new Moneynote(50), 0);
        notes2.put(new Moneynote(100), 100);
        notes2.put(new Moneynote(200), 100);
        notes2.put(new Moneynote(500), 100);

        final Cashbox cb2 = new Cashbox(notes2);

        assertEquals(cb, cb2);
    }

    @Test
    void withdrawTestPositiveDollar3() {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(1), 100);
        notes.put(new Moneynote(2), 100);
        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(100);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(1), 100);
        notes2.put(new Moneynote(2), 100);
        notes2.put(new Moneynote(5), 100);
        notes2.put(new Moneynote(10), 100);
        notes2.put(new Moneynote(20), 95);

        final Cashbox cb2 = new Cashbox(notes2);

        assertEquals(cb, cb2);
    }

    @Test
    void withdrawTestPositiveDollar4() {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(1), 100);
        notes.put(new Moneynote(2), 100);
        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(104);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(1), 100);
        notes2.put(new Moneynote(2), 98);
        notes2.put(new Moneynote(5), 100);
        notes2.put(new Moneynote(10), 100);
        notes2.put(new Moneynote(20), 95);

        final Cashbox cb2 = new Cashbox(notes2);

        assertEquals(cb, cb2);
    }

    @Test
    void withdrawTestPositiveDollar5() {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(1), 100);
        notes.put(new Moneynote(2), 100);
        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);
        notes.put(new Moneynote(500), 100);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(2000);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(1), 100);
        notes2.put(new Moneynote(2), 100);
        notes2.put(new Moneynote(5), 100);
        notes2.put(new Moneynote(10), 100);
        notes2.put(new Moneynote(20), 100);
        notes2.put(new Moneynote(50), 98);
        notes2.put(new Moneynote(100), 100);
        notes2.put(new Moneynote(200), 98);
        notes2.put(new Moneynote(500), 97);

        final Cashbox cb2 = new Cashbox(notes2);

        assertEquals(cb, cb2);
    }

    @Test
    void withdrawTestPositiveDollar6() {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(50), 2);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(100);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(50), 0);

        final Cashbox cb2 = new Cashbox(notes2);

        assertEquals(cb, cb2);
    }

    @Test
    void withdrawTestPositiveDollar7() {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(100), 2);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(200);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(100), 0);

        final Cashbox cb2 = new Cashbox(notes2);

        assertEquals(cb, cb2);
    }

    @Test
    void withdrawTestPositiveDollar8() {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(1), 10);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(10);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(1), 0);

        final Cashbox cb2 = new Cashbox(notes2);

        assertEquals(cb, cb2);
    }

    @Test
    void withdrawTestPositiveDollar9() {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(1), 10);

        final Cashbox cb = new Cashbox(notes);

        cb.withdraw(6);

        final HashMap<Moneynote, Integer> notes2 = new HashMap<>();

        notes2.put(new Moneynote(1), 4);

        final Cashbox cb2 = new Cashbox(notes2);

        assertEquals(cb, cb2);
    }

    @Test
    void withdrawTestNegativeNotEnoughMoneynotes() {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(10), 3);
        notes.put(new Moneynote(200), 3);

        final Cashbox cb = new Cashbox(notes);

        assertThrows(WithdrawNotPossibleException.class, () -> cb.withdraw(100));
    }

    @Test
    void withdrawTestNegativeIllegalArgumentException() {

        final HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(1), 100);
        notes.put(new Moneynote(2), 100);
        notes.put(new Moneynote(5), 100);
        notes.put(new Moneynote(10), 100);
        notes.put(new Moneynote(20), 100);
        notes.put(new Moneynote(50), 100);
        notes.put(new Moneynote(100), 100);
        notes.put(new Moneynote(200), 100);
        notes.put(new Moneynote(500), 100);

        final Cashbox cb = new Cashbox(notes);

        assertThrows(IllegalArgumentException.class, () -> cb.withdraw(0));
    }
}
