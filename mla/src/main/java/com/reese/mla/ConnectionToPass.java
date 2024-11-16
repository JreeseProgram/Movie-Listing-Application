package com.reese.mla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This was created in order to allow for a consistent
 * connection to be passed by reference, as just passing
 * the Connection would lead to inconsistencies and crashes
 * due to null values
 */
public class ConnectionToPass {
    Connection connection;

    /**
     * Empty constructor as no startup actions are needed for
     * this simple class.
     */
    public ConnectionToPass(){}

    /**
     * This class creates the connection that the rest of the application
     * will use for statements and other SQL operations. This class follows
     * the singleton design pattern as without it there are problems when
     * passing around the connection.
     *
     * @param url This is the fully qualified MySQL connection
     *            string without the username or password.
     * @param user This is the username to the MySQL connection.
     * @param pwd This is the password to the MySQL connection.
     * @throws SQLException This throws the SQLException as this class
     * is only here to stop inconsistencies withing passing connection around.
     */
    public void connect(String url, String user, String pwd) throws SQLException{
        connection = DriverManager.getConnection(url, user, pwd);
    }

    /**
     *
     * @return This returns a Connection that has been
     * previously created
     * @see java.sql.Connection
     */
    public Connection getConnection(){
        return connection;
    }
}
