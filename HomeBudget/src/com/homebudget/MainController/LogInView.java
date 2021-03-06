package com.homebudget.MainController;

import com.homebudget.DataBaseHandler.DatabaseConnector;
import com.homebudget.DataBaseHandler.DatabaseSubscription;
import com.homebudget.Utils.TableViewHandler;
import com.homebudget.Utils.TextEdit;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
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
 * This public class is used to create specific login window view and
 * transitions to other views. It is done by initialization declared fields from
 * ConfigureView class. It also consists a methods that are responsible for
 * transitions to other views, secured by specific logic.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 * 
 */

public class LogInView {

	/**
	 * Core method responsible for ConfigureView fields initialization. Any
	 * additional element of window must be first declared into ConfihureView
	 * class. This manipulation provides easy access to specific fields from
	 * outer classes.
	 * 
	 */

	public void setLogInWindow(Stage primaryStage) {
		ConfigureView.logInLabel = new Label("Welcome into Home Budget 2017");
		ConfigureView.logInLabel.setFont(new Font(TextEdit.fontType, TextEdit.titleFontSize));

		ConfigureView.createUserButton = ConfigureView.customButtonCreator("Sign Up",
				new Font(TextEdit.fontType, TextEdit.plainFontSize), "-fx-background-color: #ECF99C");
		ConfigureView.createUserButton.setOnAction(e -> ConfigureView.window.setScene(ConfigureView.signUpScene));

		ConfigureView.userNameField = new TextField();
		ConfigureView.userNameField.setStyle("-fx-background-color: #DCDCDC");

		ConfigureView.logInButton = ConfigureView.customButtonCreator("Sign In",
				new Font(TextEdit.fontType, TextEdit.plainFontSize), "-fx-background-color: #83D18D");
		ConfigureView.logInButton.setOnAction(e -> checkCredentials(ConfigureView.userNameField.getText(),
				ConfigureView.userPasswordField.getText()));
		ConfigureView.logInButton.setDefaultButton(true);

		ConfigureView.exitButton = ConfigureView.customButtonCreator("Exit",
				new Font(TextEdit.fontType, TextEdit.plainFontSize), "-fx-background-color: #FFA7A0");
		ConfigureView.exitButton.setOnAction(e -> Platform.exit());

		ConfigureView.logInFailedInformation = new Label();
		ConfigureView.logInFailedInformation.setVisible(false);

		ConfigureView.logIngrid = new GridPane();
		ConfigureView.logIngrid.setHgap(10);
		ConfigureView.logIngrid.setVgap(12);

		ConfigureView.buttonsGrid = new GridPane();
		ConfigureView.buttonsGrid.setAlignment(Pos.CENTER);
		ConfigureView.buttonsGrid.setHgap(10);

		ConfigureView.layout1 = new VBox(20);
		ConfigureView.layout1.setStyle("-fx-border-color: black;");
		ConfigureView.layout1.setOnMousePressed(e -> ConfigureView.mousePressHandler(e));
		ConfigureView.layout1.setOnMouseDragged(e -> ConfigureView.mouseDragHandler(e, primaryStage));

		ConfigureView.layout1.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		ConfigureView.layout1.getChildren().addAll(ConfigureView.logInLabel, ConfigureView.logIngrid,
				ConfigureView.buttonsGrid, ConfigureView.logInFailedInformation);
		ConfigureView.layout1.setStyle("-fx-background-color: #FFFFFF");

		ConfigureView.layout1.setAlignment(Pos.CENTER);
		ConfigureView.logInScene = new Scene(ConfigureView.layout1, 400, 250);

		primaryStage.setScene(ConfigureView.logInScene);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();

		ConfigureView.userName = new Label("User name:");
		ConfigureView.userName.setFont(new Font(TextEdit.fontType, TextEdit.credFontSize));

		ConfigureView.userPassword = new Label("User password:");
		ConfigureView.userPassword.setFont(new Font(TextEdit.fontType, TextEdit.credFontSize));
		ConfigureView.userPasswordField = new PasswordField();
		ConfigureView.userPasswordField.setStyle("-fx-background-color: #DCDCDC");

		ConfigureView.logIngrid.add(ConfigureView.userName, 0, 0);
		ConfigureView.logIngrid.add(ConfigureView.userNameField, 1, 0);
		ConfigureView.logIngrid.add(ConfigureView.userPassword, 0, 1);
		ConfigureView.logIngrid.add(ConfigureView.userPasswordField, 1, 1);
		ConfigureView.logIngrid.setAlignment(Pos.CENTER);

		ConfigureView.buttonsGrid.add(ConfigureView.logInButton, 0, 0);
		ConfigureView.buttonsGrid.add(ConfigureView.createUserButton, 1, 0);
		ConfigureView.buttonsGrid.add(ConfigureView.exitButton, 2, 0);
	}

	/**
	 * This method is responsible for validation user credentials. Secure
	 * mechanism that allow to login into main application checks if user
	 * identified by given password exists in database. Wrong credentials case
	 * are indicated by warning. Positive validation creates also
	 * mainApplicationView.
	 * 
	 * @param userName
	 *            user to be checked in database
	 * @param password
	 *            password connected with checked user
	 * 
	 */

	public void checkCredentials(String userName, String password) {
		if (DatabaseSubscription.checkIncorrectChars(userName) == true
				|| DatabaseSubscription.checkIncorrectChars(password) == true) {
			DatabaseSubscription.showWarning(ConfigureView.logInFailedInformation, 1.8,
					"User name or password can not contain \"\\\"", "red");
		} else if (DatabaseSubscription.checkPassword(userName, password) == true
				&& DatabaseSubscription.checkUserName(userName) == true) {
			DatabaseConnector.setUsername(userName);
			ConfigureView.userNameField.clear();
			ConfigureView.userPasswordField.clear();
			MainApplicationView mainView = new MainApplicationView();
			mainView.setMainApplicationView(ConfigureView.window);
			TableViewHandler.getAllData();
			ConfigureView.window.setScene(ConfigureView.applicationScene);
		} else
			DatabaseSubscription.showWarning(ConfigureView.logInFailedInformation, 1.8,
					"Incorrect user name or password", "red");
	}

	/**
	 * This method is used to initialize and show first window available for
	 * user. This is start-point of application. Must be invoked in MainView at
	 * the end of public void start method, because of static declaration of
	 * ConfigureView fields. Different order may occur nullPointer errors.
	 * 
	 * @param primaryStage
	 *            is used to set-up first window view
	 */

	public void invokeLogInWindow(Stage primaryStage) {
		ConfigureView.window = primaryStage;
		ConfigureView.window.setScene(ConfigureView.logInScene);
		ConfigureView.window.setTitle("Home Budget 2017");
		ConfigureView.window.show();
	}
}
