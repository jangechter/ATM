/*
 * DatabaseQueryExecutor.java
 *
 * Created on 2020-10-02
 *
 * Copyright (C) 2020 Volkswagen AG, All rights reserved.
 */

package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseQueryExecutor {

    private final Connection connection;

    public DatabaseQueryExecutor() throws SQLException {

        connection = DatabaseConnector.connect();

    }

    public List<String> getClientDataByIBAN(final String iban) throws SQLException {

        final Statement statement = connection.createStatement();

        final ResultSet rs = statement.executeQuery("select * from client c where c.iban=" + iban);

        ArrayList<String> resultSetList = new ArrayList<>();

        if (!rs.next()) {
            return Collections.emptyList();
        }

        resultSetList.add(rs.getString("name"));
        resultSetList.add(rs.getString("firstName"));
        resultSetList.add(rs.getString("iban"));
        resultSetList.add(rs.getString("pin"));
        resultSetList.add(rs.getString("bankbalance"));
        resultSetList.add(rs.getString("status"));
        resultSetList.add(rs.getString("loginTry"));

        return resultSetList;

    }

}
