package com.reese.mla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToPass {
    Connection connection;

    public ConnectionToPass(){}

    public void connect(String url, String user, String pwd) throws SQLException{
        connection = DriverManager.getConnection(url, user, pwd);
    }
    public Connection getConnection(){
        return connection;
    }
}
