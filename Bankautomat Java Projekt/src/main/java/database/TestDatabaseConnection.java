/*
 * TestDatabaseConnection.java
 *
 * Created on 2020-10-02
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package database;

import java.sql.SQLException;

public class TestDatabaseConnection {

    public static void main(String[] args) {

        DatabaseConnector connector = new DatabaseConnector();

        try {
            connector.connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
