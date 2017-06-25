package com.homebudget.DataBaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Public class consist necessary methods for server subscription purposes e.g.
 * password checking, user checking, new user insertion. If there is a need to
 * make another function that supports user subscription it is recommended to
 * update this class.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 * 
 */

public class DatabaseSubscription {
	/**
	 * Creates query that is used to check if given user name has corresponding
	 * password in database.
	 * 
	 * @param checkedUser
	 *            contains name of user which already exists in database
	 * @param passwordToBeChecked
	 *            contains password to compare with created one in database
	 * @return true if password is same like given, false if it isn't
	 * 
	 */

	public static boolean checkPassword(String checkedUser, String passwordToBeChecked) {
		Boolean checkResult = new Boolean(false);
		String sqlCheckPasswordQuery = "SELECT userPassword FROM usersBank WHERE userName='" + checkedUser + "';";
		PreparedStatement statement;

		try {
			statement = DatabaseConnector.connect().prepareStatement(sqlCheckPasswordQuery);
			ResultSet rs = statement.executeQuery();
			DatabaseGetData.getStringData(rs, "userPassword");

			if (DatabaseGetData.userName.contains(passwordToBeChecked) == true) {
				checkResult = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkResult;
	}

	/**
	 * Creates query to check if given user name already exist in database.
	 * 
	 * @param userToBeChecked
	 *            contains a name to check if already exist
	 * @return true if user already exists, false if it's not
	 * 
	 */

	public static boolean checkUserName(String userToBeChecked) {
		Boolean checkResult = new Boolean(false);
		DatabaseConnector mysqlConnect = new DatabaseConnector();
		String sqlTestCommand = "SELECT userName FROM usersBank WHERE userName='" + userToBeChecked + "';";
		PreparedStatement statement;
		DatabaseGetData.userName.clear();

		try {
			statement = DatabaseConnector.connect().prepareStatement(sqlTestCommand);
			ResultSet rs = statement.executeQuery();
			DatabaseGetData.getStringData(rs, "userName");

			if (DatabaseGetData.userName.contains(userToBeChecked) == true) {
				checkResult = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}
		return checkResult;
	}

	/**
	 * Creates a query that inserts new user into database. Used only after
	 * validation.
	 * 
	 * @param userName
	 *            user name to be created
	 * @param userPassword
	 *            password corresponding to user name
	 * 
	 */

	public static void insertNewUser(String userName, String userPassword) {
		DatabaseConnector createUserConnect = new DatabaseConnector();
		String createUserQuery = "INSERT INTO usersbank (userName, userPassword) VALUES ('" + userName + "', '"
				+ userPassword + "');";
		PreparedStatement statement;

		try {
			statement = DatabaseConnector.connect().prepareStatement(createUserQuery);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			createUserConnect.disconnect();
		}
	}

	/**
	 * Checks if given String value is longer than four characters.
	 * 
	 * @param stringToBeChecked
	 *            string value to be checked
	 * @param wordLength
	 *            minimal length of given word
	 * 
	 * @return true if string is longer, false if it isn't
	 * 
	 */

	public static boolean checkCredintialsLength(String stringToBeChecked, int wordLength) {
		return stringToBeChecked.length() > wordLength ? true : false;
	}

	/**
	 * Used to show information about current state of work with application.
	 * 
	 * @param showLabel
	 *            Label that is affected with warning
	 * @param showTime
	 *            time in seconds used to set time of Label visibility
	 * @param specificStatement
	 *            information to show
	 * @param e.g
	 *            warningColor red if something wrong, green if everything is
	 *            good
	 * 
	 */

	public static void showWarning(Label showLabel, Double showTime, String specificStatement, String warningColor) {
		PauseTransition visiblePause = new PauseTransition(Duration.seconds(showTime));
		showLabel.setText(specificStatement);
		showLabel.setStyle("-fx-text-fill: " + warningColor);
		showLabel.setVisible(true);
		visiblePause.setOnFinished(e -> showLabel.setVisible(false));
		visiblePause.play();
	}

	/**
	 * Method that executes created query, have to be used after positive
	 * validation for user Sign In or Sign Up when the userName in
	 * DatabaseConnector has been changed from root to current user.
	 * 
	 * @param newQuery
	 *            query executed by executeUpdate() method, so it have to be
	 *            INSERT, UPDATE or DELETE statement.
	 * 
	 */

	public static void executeNewUpdateQuery(String newQuery) {
		DatabaseConnector createUserConnect = new DatabaseConnector();
		PreparedStatement statement;

		try {
			statement = DatabaseConnector.connect().prepareStatement(newQuery);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			createUserConnect.disconnect();
		}
	}

	/**
	 * Method used to delete user from database. First it changes privileges to
	 * root and then it deletes all expensive, user view and user.
	 * 
	 * @param userName
	 *            user to be deleted
	 * 
	 */

	public static void pernamentDeleteUser(String userName) {
		DatabaseConnector.changeToAdmin();
		executeNewUpdateQuery("DELETE FROM costs WHERE userName = '" + userName + "';");
		executeNewUpdateQuery("DROP VIEW " + userName + "View;");
		executeNewUpdateQuery("DELETE FROM usersBank WHERE userName = '" + userName + "';");
	}
}
