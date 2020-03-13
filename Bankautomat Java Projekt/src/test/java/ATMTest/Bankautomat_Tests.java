/*
 * Bankautomat_Tests.java
 *
 * Created on 2020-03-13
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package ATMTest;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import clientRepository.ClientRepository;
import authentication.Authentication;
import client.Client;

public class Bankautomat_Tests {

    private static final String TEST_NAME = "Mustermann";
    private static final double TEST_BANK_BALANCE = 100.00;
    private static final String TEST_FIRSTNAME = "Max";
    private static final String TEST_IBAN = "DE01 2345 6789 0123 4567 89";
    private static final String TEST__PIN = "1234";
    private static final boolean IS_ACTIVE = true;
    private static final Client client = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST__PIN,
                                                    BigDecimal.valueOf(TEST_BANK_BALANCE), IS_ACTIVE);

    @Test
    public void test_Client_Equals_Positive() {

        assertTrue(client.equals(client));
        assertTrue(client.equals(
                new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST__PIN, BigDecimal.valueOf(TEST_BANK_BALANCE),
                           IS_ACTIVE)));
    }

    @Test
    public void test_Client_Equals_Negative() {
        assertFalse(client.equals(null));
        assertFalse(client.equals(
                new Client(" ", " ", TEST_IBAN, TEST__PIN, BigDecimal.valueOf(TEST_BANK_BALANCE), IS_ACTIVE)));
    }

    @Test
    public void test_toReadClient_Negative() {

        final ClientRepository cr = new ClientRepository();

        client.setPin("5555");

        assertFalse(client.equals(cr.findClient(TEST_IBAN)));

        client.setPin(TEST__PIN);
    }

    @Test
    public void test_toReadClient_WrongFile() {

        final ClientRepository cr = new ClientRepository();

        assertEquals(null, cr.findClient("wrongFile"));
    }

    @Test
    public void test_ToRead_Client_Positive() {

        final ClientRepository cr = new ClientRepository();

        assertTrue(client.equals(cr.findClient(TEST_IBAN)));
    }

    @Test
    public void test_Check_Pin_Negative() {

        final Authentication auth = new Authentication();

        assertFalse(auth.logIn(TEST_IBAN, "0000"));
    }

    @Test
    public void test_Check_Pin_Positive() {

        final Authentication auth = new Authentication();

        assertTrue(auth.logIn(TEST_IBAN, TEST__PIN));
    }

    @Test
    public void test_Check_bankBalance_Negative() {

        final Authentication auth = new Authentication();

        auth.logIn(TEST_IBAN, TEST__PIN);

        try {

            assertFalse(auth.getClient().getBankBalance().compareTo(BigDecimal.valueOf(Double.MIN_VALUE)) == 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test__Check_bankBalance_Positive() {

        final Authentication auth = new Authentication();

        auth.logIn(TEST_IBAN, TEST__PIN);

        try {

            assertTrue(auth.getClient().getBankBalance().compareTo(BigDecimal.valueOf(TEST_BANK_BALANCE)) == 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
