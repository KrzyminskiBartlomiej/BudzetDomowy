package com.homebudget.MainController;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainView extends Application {

	public static void main(String[] args) {

		launch(args);
//		DatabaseConnector mysqlConnect = new DatabaseConnector();
//		String sqlTestCommand = "SELECT valueCost FROM costs;";
//
//		try {s
//			PreparedStatement statement = DatabaseConnector.connect().prepareStatement(sqlTestCommand);
//			ResultSet rs = statement.executeQuery();
//			DatabaseGetData.getIntData(rs);
//
//			System.out.println(DatabaseGetData.testData);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			mysqlConnect.disconnect();
//		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		CreateUserView.setSettingsCreateUserWindow();
		LogInView.setSettingsLogInWindow();		
		LogInView.invokeLogInWindow(primaryStage);
	}
}