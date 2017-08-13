package com.homebudget.MainController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	static double xOffset = 0;
	static double yOffset = 0;
	public static ObservableList<String> costTypeItems;
	public static ObservableList<String> tableColumnsNames = FXCollections.observableArrayList("Id", "Type", "Name", "Value", "Date");
	public static String currentMonth = "All";

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
	public static BorderPane mainBorderPane;

	public static Button addNew, showExpense, showJuxtaposition;
	public static Image logoImage;
	public static ImageView picture;

	/**
	 * It basically adds border to given button
	 * 
	 * @param toBorder
	 *            button to be bordered
	 * 
	 */

	public static void addBorderToButton(Button toBorder) {
		toBorder.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	/**
	 * Creates specific button with given parameters, extension of simple button
	 * constructor.
	 * 
	 * @param buttonName
	 *            button to be customized
	 * @param fontType
	 *            button text font type
	 * @param buttonStyle
	 *            string value with CSS information
	 * @return customized button
	 * 
	 */

	public static Button customButtonCreator(String buttonName, Font fontType, String buttonStyle) {
		Button buttonToBeCustomized = new Button(buttonName);
		buttonToBeCustomized.setFont(fontType);
		buttonToBeCustomized.setStyle(buttonStyle);
		return buttonToBeCustomized;
	}

	/**
	 * Sets x-axis value and y-axis value while button pressed to pass current
	 * mouse location.
	 * 
	 * @param event
	 *            passed event
	 * 
	 */

	public static void mousePressHandler(MouseEvent event) {
		ConfigureView.xOffset = event.getSceneX();
		ConfigureView.yOffset = event.getSceneY();
	}

	/**
	 * Computes where primaryStage have to be moved. Calculation are based on
	 * xOffset and yOffset variables.
	 * 
	 * @param event
	 *            passed event
	 * @param primaryStage
	 *            stage to be moved
	 *            
	 */

	public static void mouseDragHandler(MouseEvent event, Stage primaryStage) {
		primaryStage.setX(event.getScreenX() - ConfigureView.xOffset);
		primaryStage.setY(event.getScreenY() - ConfigureView.yOffset);
	}
}
