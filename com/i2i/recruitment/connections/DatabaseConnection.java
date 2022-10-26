/*
 * Subash rajinikanth M
 *
 * Copyright (c) 2022 Ideas2it, Inc. All Rights Reserved.
 *
 * This is the simple Recruitment System console application,
 * add the employee and candidate details to the mysql Database
 * using JDBC
 * 
 */
package com.i2i.recruitment.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;    

/**
 * Database conection is established in singleton class 
 * object creation is only once for whole application
 *
 * @version 1.0
 * @author subashrajinikanth
 */
public class DatabaseConnection {

    private static Connection connection;
    private static DatabaseConnection databaseConnection;   
    private static final String URL = "jdbc:mysql://localhost:3306/recruitment?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";

    private DatabaseConnection() throws SQLException {
	connection = DriverManager.getConnection(URL, USER, PASS);
    }

    /*
     * Get the the connection if no connection is already created or null,
     * if the connection is already open,  it will return same object reference to the caller.
     *
     * @return Connection Object 
     *
     */
    public static Connection getConnection() throws SQLException {
	if(connection == null || connection.isClosed()) {
	    databaseConnection = new DatabaseConnection();
	}
	return connection;
    }
}