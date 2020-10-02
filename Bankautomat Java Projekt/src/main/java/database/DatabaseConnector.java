/*
 * DatabaseConnector.java
 *
 * Created on 2020-10-02
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public static Connection connect() throws SQLException {

        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:h2:~/Bankautomat", "root", "root");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
}
