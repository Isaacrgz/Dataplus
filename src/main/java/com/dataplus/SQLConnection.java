package com.dataplus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/accounts?useSSL=false&useTimezone=true=&serverTimezone=UTC&allowKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "admin";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    public static void close(Statement smtm) throws SQLException{
        smtm.close();
    }
    public static void close(PreparedStatement smtm) throws SQLException{
        smtm.close();
    }
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
        
}
