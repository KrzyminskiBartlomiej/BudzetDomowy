package com.homebudget.MainController;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This public class is created to store static fields used later by specific
 * view class. This kind of solution provides orderly access to class
 * components. Static fields approach is enough solution for SignIn, SignUp
 * purposes where there is no dynamic object creation needed. To make code as
 * much readable as possible it is recommended to keep present order of
 * declaration.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 *
 */

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
