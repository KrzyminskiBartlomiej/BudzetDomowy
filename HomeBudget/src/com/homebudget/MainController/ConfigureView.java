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
	public static Label logInFailedInformation;
	public static Button createUserButton, logInButton, exitButton;
	public static GridPane logIngrid, buttonsGrid;
	public static TextField userNameField;
	public static PasswordField userPasswordField;

	// Create User window fields
	public static Scene signUpScene;
	public static VBox layout2;
	public static Label createUserLabel, setUserName, setUserPassword, rewriteUserPassword, createUserFailed;
	public static Button createNewUser, backToLogInScene;
	public static GridPane createUserGrid, userButtonsGrid;
	public static TextField setUserField;
	public static PasswordField createUserPasswordField, rewriteUserPasswordField;

	// Main Application window fields
	public static Scene applicationScene;
	public static VBox layout3;
}
