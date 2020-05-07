/*
 * ATMTest.java
 *
 * Created on 2020-05-07
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package atm;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cashbox.Cashbox;
import csvWriter.CSVWriter;
import moneynote.Moneynote;
import testData.TestData;

class ATMTest extends TestData {

    private Cashbox cb = null;

    @BeforeEach
    void persistData() throws IOException {

        cb = new Cashbox();

    }

    @AfterEach
    void resetData() throws IOException {

        CSVWriter.writeCashbox(cb);

        CSVWriter.writeClient(TEST_CLIENT);
    }

    //@Test
    void testWithdrawMoneyPositive() throws IOException {

        final ATM atm = new ATM();

        HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(10), 1);
        notes.put(new Moneynote(20), 2);
        notes.put(new Moneynote(50), 1);

        atm.login(TEST_IBAN, TEST_PIN);

        assertEquals(notes, atm.withdrawMoney(100).get());

        assertEquals(BigDecimal.valueOf(0.0), atm.getLoggedInClient().getClient().getBankBalance());

    }

    @Test
    void testWithdrawMoneyInvalidAmount() {

        final ATM atm = new ATM();

        atm.login(TEST_IBAN, TEST_PIN);

        assertThat(atm.withdrawMoney(111)).isEmpty();

    }

    @Test
    void testDepositMoneyPositive() throws IOException {

        final ATM atm = new ATM();

        HashMap<Moneynote, Integer> notes = new HashMap<>();

        notes.put(new Moneynote(10), 1);
        notes.put(new Moneynote(20), 2);
        notes.put(new Moneynote(50), 1);

        atm.login(TEST_IBAN, TEST_PIN);

        atm.depositMoney(notes);

        assertEquals(BigDecimal.valueOf(TEST_BANK_BALANCE + 100),
                     atm.getLoggedInClient().getClient().getBankBalance());

    }

    @Test
    void testLoginPositive() {

        ATM atm = new ATM();

        assertThat(atm.login(TEST_IBAN, TEST_PIN)).isTrue();
    }

    @Test
    void testLogoutPositive() {

        ATM atm = new ATM();

        atm.login(TEST_IBAN, TEST_PIN);

        assertThat(atm.logout()).isTrue();
    }

    @Test
    void testLogoutNegative() {

        ATM atm = new ATM();

        assertThat(atm.logout()).isFalse();
    }
}
