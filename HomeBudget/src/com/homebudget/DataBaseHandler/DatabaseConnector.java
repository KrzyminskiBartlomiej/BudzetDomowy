package com.homebudget.DataBaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Public class used to configure connection with MySQl database.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 *
 */

public class DatabaseConnector {
	private static String databaseDriver = "com.mysql.jdbc.Driver";
	private static String databaseURL = "jdbc:mysql://localhost:3306/basiccosts?autoReconnect=true&useSSL=false";
	private static String username = "root";
	private static String password = "Spiderman143";
	private static String maxPool = "250";

	private static Connection connection;
	private static Properties properties;

	/**
	 * The Properties class represents a persistent set of properties. The
	 * Properties can be saved to a stream or loaded from a stream. Each key and
	 * its corresponding value in the property list is a string.
	 * 
	 * @return configured properties object which is now ready to set up the
	 *         connection
	 * 
	 */

	private static Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			properties.setProperty("user", username);
			properties.setProperty("password", password);
			properties.setProperty("MaxPooledStatements", maxPool);
		}
		return properties;
	}

	/**
	 * A connection (session) with a specific database. SQL statements are
	 * executed and results are returned within the context of a connection.
	 * 
	 * A Connection object's database is able to provide information describing
	 * its tables, its supported SQL grammar, its stored procedures, the
	 * capabilities of this connection, and so on.
	 * 
	 * @return prepared session with database
	 * 
	 */

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

	/**
	 * Basically closes specific session
	 * 
	 */

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
	
	/**
	 * Setting up credentials to root.
	 * 
	 */
	
	public static void changeToAdmin(){
		username = "root";
		password = "Spiderman143";
	}

	public static void setUsername(String un) {
		username = un;
	}
	
	public static String getUserName(){
		return username;
	}

	public void setPassword(String pw) {
		password = pw;
	}

	public void setMaxPool(String mp) {
		maxPool = mp;
	}
}
