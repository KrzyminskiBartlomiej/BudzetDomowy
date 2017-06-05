package com.homebudget.DataBaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.homebudget.MainController.ConfigureView;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class DatabaseSubscribtion {
	public static void checkCredintials(String userName, String password) {
		if (checkPassword(userName, password) == true && checkUserName(userName) == true) {
			ConfigureView.window.setScene(ConfigureView.applicationScene);
		} else
			showWarrning(ConfigureView.logInFailedInformation, 1.8);
	}

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
				System.out.println("fjuty");
				checkResult = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}
		return checkResult;
	}

	public static void showWarrning(Label showLabel, Double showTime) {
		PauseTransition visiblePause = new PauseTransition(Duration.seconds(showTime));
		showLabel.setVisible(true);
		visiblePause.setOnFinished(e -> showLabel.setVisible(false));
		visiblePause.play();
	}

	public void createUser() {

	}
}
