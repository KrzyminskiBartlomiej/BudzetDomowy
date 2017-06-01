package com.homebudget.MainController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.homebudget.DataBaseHandler.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {

	Stage window;
	Scene logInScene, signUpScene, applicationScene;

	public static void main(String[] args) {

		launch(args);
		DatabaseConnector mysqlConnect = new DatabaseConnector();
		String sqlTestCommand = "SELECT valueCost FROM costs;";

		try {
			PreparedStatement statement = DatabaseConnector.connect().prepareStatement(sqlTestCommand);
			ResultSet rs = statement.executeQuery();
			DatabaseGetData.getIntData(rs);

			System.out.println(DatabaseGetData.testData);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		Label logInLabel = new Label("Welcome into Home Budget 2017");
		logInLabel.setStyle("-fx-font-size: 12pt;");

		Button createUserButton = new Button("Sign Up");
		createUserButton.centerShapeProperty();
		createUserButton.setOnAction(e -> window.setScene(signUpScene));

		Button finalCreateUser = new Button("Create");
		finalCreateUser.setOnAction(e -> window.setScene(logInScene));

		VBox layout2 = new VBox(20);
		layout2.getChildren().add(finalCreateUser);
		layout2.setAlignment(Pos.CENTER);
		signUpScene = new Scene(layout2, 300, 300);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setHgap(12);

		Label userName = new Label("User name:");
		TextField userNameField = new TextField();

		Label userPassword = new Label("User password");
		PasswordField userPasswordField = new PasswordField();

		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10.0);

		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(logInLabel, grid, createUserButton);
		layout1.setAlignment(Pos.CENTER);
		logInScene = new Scene(layout1, 300, 300);

		grid.add(userName, 0, 0);
		grid.add(userNameField, 1, 0);
		grid.add(userPassword, 0, 1);
		grid.add(userPasswordField, 1, 1);
		grid.setAlignment(Pos.CENTER);

		window.setScene(logInScene);
		window.setTitle("Home Budget 2017");
		window.show();
	}
}