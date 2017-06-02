package com.homebudget.MainController;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfigureView {

	// Common fields
	public static Stage window;

	// Log In Window fields
	public static Scene logInScene;
	public static VBox layout1;
	public static Label logInLabel, userName, userPassword;
	public static Button createUserButton;
	public static GridPane logIngrid;
	public static TextField userNameField;
	public static PasswordField userPasswordField;

	// Create User window
	public static Scene signUpScene;
	public static VBox layout2;
	public static Label createUserLabel, setUserName, setUserPassword, rewriteUserPassword;
	public static Button createNewUser;
	public static GridPane createUserGrid;
	public static TextField setUserField, createUserPasswordField, rewriteUserPasswordField;

	// Main Application window
	public static Scene applicationScene;
}
