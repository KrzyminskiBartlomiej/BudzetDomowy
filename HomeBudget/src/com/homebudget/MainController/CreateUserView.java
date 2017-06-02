package com.homebudget.MainController;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateUserView {
	public static void setSettingsCreateUserWindow() {	
		ConfigureView.createUserLabel = new Label("Create new user");
		ConfigureView.createUserLabel.setStyle("-fx-font-size: 12pt;");
		
		ConfigureView.createNewUser = new Button("Create User");
		ConfigureView.createNewUser.setOnAction(e -> ConfigureView.window.setScene(ConfigureView.logInScene));
		
		ConfigureView.createUserGrid = new GridPane();
		ConfigureView.createUserGrid.setHgap(10);
		ConfigureView.createUserGrid.setHgap(12);
		
		ConfigureView.setUserName = new Label("Your user name:");
		ConfigureView.setUserField = new TextField();
		
		ConfigureView.setUserPassword = new Label("Your user password:");
		ConfigureView.createUserPasswordField = new TextField();
		
		ConfigureView.rewriteUserPassword = new Label("Rewrite your password:");
		ConfigureView.rewriteUserPasswordField = new TextField();
		
		ConfigureView.layout2 = new VBox(20);
		ConfigureView.layout2.getChildren().addAll(ConfigureView.createUserLabel, ConfigureView.createUserGrid, ConfigureView.createNewUser);
		ConfigureView.layout2.setAlignment(Pos.CENTER);
		ConfigureView.signUpScene = new Scene(ConfigureView.layout2, 300, 300);
		
		ConfigureView.createUserGrid.add(ConfigureView.setUserName, 0, 0);
		ConfigureView.createUserGrid.add(ConfigureView.setUserField, 1, 0);
		ConfigureView.createUserGrid.add(ConfigureView.setUserPassword, 0, 1);
		ConfigureView.createUserGrid.add(ConfigureView.createUserPasswordField , 1, 1);
		ConfigureView.createUserGrid.add(ConfigureView.rewriteUserPassword, 0, 2);
		ConfigureView.createUserGrid.add(ConfigureView.rewriteUserPasswordField , 1, 2);
	}

	public static void invokeCreateUserWindow(Stage primaryStage) {
		//TODO
	}
}
