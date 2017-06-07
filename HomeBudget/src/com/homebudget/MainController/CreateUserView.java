package com.homebudget.MainController;

import com.homebudget.DataBaseHandler.DatabaseSubscribtion;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CreateUserView {
	public static void setSettingsCreateUserWindow() {
		ConfigureView.createUserLabel = new Label("Create new user");
		ConfigureView.createUserLabel.setStyle("-fx-font-size: 12pt;");

		ConfigureView.createNewUser = new Button("Create User");
		ConfigureView.createNewUser.setOnAction(e -> createUser(ConfigureView.setUserField.getText(),
				ConfigureView.createUserPasswordField.getText(), ConfigureView.createUserFailed));

		ConfigureView.backToLogInScene = new Button("Back");
		ConfigureView.backToLogInScene.setOnAction(e -> ConfigureView.window.setScene(ConfigureView.logInScene));

		ConfigureView.createUserGrid = new GridPane();
		ConfigureView.createUserGrid.setHgap(10);
		ConfigureView.createUserGrid.setVgap(12);

		ConfigureView.userButtonsGrid = new GridPane();
		ConfigureView.userButtonsGrid.setAlignment(Pos.CENTER);
		ConfigureView.userButtonsGrid.setHgap(10);

		ConfigureView.createUserFailed = new Label("User already exists");
		ConfigureView.createUserFailed.setStyle("-fx-text-fill: red");
		ConfigureView.createUserFailed.setVisible(false);

		ConfigureView.setUserName = new Label("Your user name:");
		ConfigureView.setUserField = new TextField();

		ConfigureView.setUserPassword = new Label("Your user password:");
		ConfigureView.createUserPasswordField = new PasswordField();

		ConfigureView.rewriteUserPassword = new Label("Rewrite your password:");
		ConfigureView.rewriteUserPasswordField = new PasswordField();

		ConfigureView.layout2 = new VBox(20);
		ConfigureView.layout2.getChildren().addAll(ConfigureView.createUserLabel, ConfigureView.createUserGrid,
				ConfigureView.userButtonsGrid, ConfigureView.createUserFailed);
		ConfigureView.layout2.setAlignment(Pos.CENTER);
		ConfigureView.signUpScene = new Scene(ConfigureView.layout2, 300, 300);

		ConfigureView.createUserGrid.add(ConfigureView.setUserName, 0, 0);
		ConfigureView.createUserGrid.add(ConfigureView.setUserField, 1, 0);
		ConfigureView.createUserGrid.add(ConfigureView.setUserPassword, 0, 1);
		ConfigureView.createUserGrid.add(ConfigureView.createUserPasswordField, 1, 1);
		ConfigureView.createUserGrid.add(ConfigureView.rewriteUserPassword, 0, 2);
		ConfigureView.createUserGrid.add(ConfigureView.rewriteUserPasswordField, 1, 2);
		ConfigureView.createUserGrid.setAlignment(Pos.CENTER);

		ConfigureView.userButtonsGrid.add(ConfigureView.createNewUser, 0, 0);
		ConfigureView.userButtonsGrid.add(ConfigureView.backToLogInScene, 1, 0);
	}

	public static void createUser(String userName, String userPassword, Label showLabel) {
		if (DatabaseSubscribtion.checkUserName(userName) == true) {
			DatabaseSubscribtion.showWarning(showLabel, 1.8, "User already exist", "red");
		} else if (DatabaseSubscribtion.checkCredintialsLength(userName) == false) {
			DatabaseSubscribtion.showWarning(showLabel, 1.8, "Username must contain at least 5 characters", "red");
		} else if (DatabaseSubscribtion.checkCredintialsLength(userPassword) == false) {
			DatabaseSubscribtion.showWarning(showLabel, 1.8, "Your password must contain at least 5 characters", "red");
		} else if(ConfigureView.rewriteUserPasswordField.getText().equals(userPassword) == false){
			DatabaseSubscribtion.showWarning(showLabel, 1.8, "Both passwords must be the same", "red");
		} else{
			DatabaseSubscribtion.insertNewUser(userName, userPassword);		
			DatabaseSubscribtion.showWarning(showLabel, 3.5, "Congratulations ! User Created, please Sign In : )", "green");
		}			
	}
}
