/*
 * ClientDatabaseRepositoryTest.java
 *
 * Created on 2020-10-02
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package clientRepository;

import client.Client;
import database.DatabaseConnector;
import org.junit.jupiter.api.Test;
import testData.TestData;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class ClientDatabaseRepositoryTest {

    private static final String query = String.format("SELECT * FROM CLIENT WHERE IBAN='%s';", TestData.TEST_IBAN);

    @Test
    void findClientTestTestPositive() throws Exception {

        final Statement statement = DatabaseConnector.connect().createStatement();

        final ResultSet rs = statement.executeQuery(query);

        Client testClientFromDB = null;

        if (rs.next()) {

            final String name = rs.getString("name");
            final String firstName = rs.getString("firstName");
            final String iban = rs.getString("iban");
            final String pin = rs.getString("pin");
            final double balance = rs.getDouble("bankbalance");
            final boolean status = rs.getBoolean("status");
            final Integer loginTry = rs.getInt("loginTry");


            testClientFromDB = new Client(name, firstName, iban, pin, BigDecimal.valueOf(balance), status, loginTry);
        } else {
            fail();
        }

        assertThat(testClientFromDB).isEqualTo(TestData.TEST_CLIENT);

    }

}
