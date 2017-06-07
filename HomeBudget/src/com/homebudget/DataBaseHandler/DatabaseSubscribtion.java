package com.homebudget.DataBaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class DatabaseSubscribtion {
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

	public static boolean checkUserName(String userToBeChecked) {
		Boolean checkResult = new Boolean(false);
		DatabaseConnector mysqlConnect = new DatabaseConnector();
		String sqlTestCommand = "SELECT userName FROM usersBank WHERE userName='" + userToBeChecked + "';";
		PreparedStatement statement;

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

	public static void insertNewUser(String userName, String userPassword) {
		DatabaseConnector createUserConnect = new DatabaseConnector();
		String createUserQuery = "INSERT INTO usersbank (userName, userPassword) VALUES ('" + userName + "', '" + userPassword + "');";
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

	public static boolean checkCredintialsLength(String stringToBeChecked) {
		return stringToBeChecked.length() > 4 ? true : false;
	}

	public static void showWarning(Label showLabel, Double showTime, String specificStatement, String warningColor) {
		PauseTransition visiblePause = new PauseTransition(Duration.seconds(showTime));
		showLabel.setText(specificStatement);
		showLabel.setStyle("-fx-text-fill: " + warningColor);
		showLabel.setVisible(true);
		visiblePause.setOnFinished(e -> showLabel.setVisible(false));
		visiblePause.play();
	}
}
