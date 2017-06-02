package com.homebudget.MainController;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogInView {
	public static void setSettingsLogInWindow() {
		ConfigureView.logInLabel = new Label("Welcome into Home Budget 2017");
		ConfigureView.logInLabel.setStyle("-fx-font-size: 12pt;");

		ConfigureView.createUserButton = new Button("Sign Up");
		ConfigureView.createUserButton.centerShapeProperty();
		ConfigureView.createUserButton.setOnAction(e -> ConfigureView.window.setScene(ConfigureView.signUpScene));

		ConfigureView.logIngrid = new GridPane();
		ConfigureView.logIngrid.setHgap(10);
		ConfigureView.logIngrid.setHgap(12);
		
		ConfigureView.layout1 = new VBox(20);
		ConfigureView.layout1.getChildren().addAll(ConfigureView.logInLabel, ConfigureView.logIngrid, ConfigureView.createUserButton);
		ConfigureView.layout1.setAlignment(Pos.CENTER);
		ConfigureView.logInScene = new Scene(ConfigureView.layout1, 300, 300);
		
		ConfigureView.userName = new Label("User name:");
		ConfigureView.userNameField = new TextField();

		ConfigureView.userPassword = new Label("User password:");
		ConfigureView.userPasswordField = new PasswordField();

		ConfigureView.logIngrid.add(ConfigureView.userName, 0, 0);
		ConfigureView.logIngrid.add(ConfigureView.userNameField, 1, 0);
		ConfigureView.logIngrid.add(ConfigureView.userPassword, 0, 1);
		ConfigureView.logIngrid.add(ConfigureView.userPasswordField, 1, 1);
		ConfigureView.logIngrid.setAlignment(Pos.CENTER);
	}
	
	public static void invokeLogInWindow(Stage primaryStage){
		ConfigureView.window = primaryStage;
		ConfigureView.window.setScene(ConfigureView.logInScene);
		ConfigureView.window.setTitle("Home Budget 2017");
		ConfigureView.window.show();
	}
}
