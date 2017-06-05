package com.homebudget.DataBaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
	private static String databaseDriver = "com.mysql.jdbc.Driver";
	private static String databaseURL = "jdbc:mysql://localhost:3306/basiccosts?autoReconnect=true&useSSL=false";
	private static String username = "root";
	private static String password = "Spiderman143";
	private static String maxPool = "250";

	private static Connection connection;
	private static Properties properties;

	private static Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			properties.setProperty("user", username);
			properties.setProperty("password", password);
			properties.setProperty("MaxPooledStatements", maxPool);
		}
		return properties;
	}

	public static Connection connect() {
		if (connection == null) {
			try {
				Class.forName(databaseDriver);
				connection = DriverManager.getConnection(databaseURL, getProperties());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void setUsername(String un) {
		username = un;
	}

	public void setPassword(String pw) {
		password = pw;
	}
	
	public void setMaxPool(String mp){
		maxPool = mp;
	}
}
