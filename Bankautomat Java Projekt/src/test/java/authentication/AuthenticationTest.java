/*
 * AuthenticationTest.java
 *
 * Created on 2020-03-13
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package authentication;

import java.math.BigDecimal;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import client.Client;

public class AuthenticationTest {


    private static final String TEST_NAME = "Mustermann";
    private static final double TEST_BANK_BALANCE = 100.00;
    private static final String TEST_FIRSTNAME = "Max";
    private static final String TEST_IBAN = "DE01 2345 6789 0123 4567 89";
    private static final String TEST__PIN = "1234";
    private static final boolean IS_ACTIVE = true;
    private static final Client client = new Client(TEST_NAME, TEST_FIRSTNAME, TEST_IBAN, TEST__PIN,
                                                    BigDecimal.valueOf(TEST_BANK_BALANCE), IS_ACTIVE);


    @Test
    public void test_Check_bankBalance_Negative(){

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

            assertEquals(auth.getClient().getBankBalance().compareTo(BigDecimal.valueOf(TEST_BANK_BALANCE)) == 0,
                         true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
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

}
