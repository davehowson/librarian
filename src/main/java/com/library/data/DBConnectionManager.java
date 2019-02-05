package com.library.data;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    private Connection connection;

    public DBConnectionManager(String url, String user, String pass) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(url, user, pass);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeDB() {
        DbUtils.closeQuietly(connection);
    }
}
