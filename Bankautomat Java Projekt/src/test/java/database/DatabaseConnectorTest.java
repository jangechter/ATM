/*
 * DatabaseConnectorTest.java
 *
 * Created on 2020-10-02
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class DatabaseConnectorTest {

    @Test
    void createDatabaseConnectionTest() throws Exception {

        try (Connection connection = DatabaseConnector.connect()) {

            assertThat(connection).isNotNull();

        }

    }

}
