package com.homebudget.MainController;

import com.homebudget.DataBaseHandler.DatabaseSubscription;
import com.homebudget.Utils.TextEdit;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
 * This public class is used to create specific create user window view and
 * transitions to other views. It is done by initialization declared fields from
 * ConfigureView class. It also consists a methods that are responsible for
 * transitions to other views, secured by specific logic.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 * 
 */

public class CreateUserView {

	/**
	 * Core method responsible for ConfigureView fields initialization. Any
	 * additional element of window must be first declared into ConfihureView
	 * class. This manipulation provides easy access to specific fields from
	 * outer classes.
	 * 
	 */

	public void setCreateUserWindow(Stage primaryStage) {
		ConfigureView.createUserLabel = new Label("Create new user");
		ConfigureView.createUserLabel.setFont(new Font(TextEdit.fontType, TextEdit.titleFontSize));

		ConfigureView.createNewUser = new Button("Create User");
		ConfigureView.createNewUser.setFont(new Font(TextEdit.fontType, TextEdit.plainFontSize));
		ConfigureView.createNewUser.setStyle("-fx-background-color: #83D18D");
		ConfigureView.createNewUser.setOnAction(e -> createUser(ConfigureView.setUserField.getText(),
				ConfigureView.createUserPasswordField.getText(), ConfigureView.createUserFailed));
		ConfigureView.createNewUser.setDefaultButton(true);

		ConfigureView.backToLogInScene = new Button("Back");
		ConfigureView.backToLogInScene.setFont(new Font(TextEdit.fontType, TextEdit.plainFontSize));
		ConfigureView.backToLogInScene.setStyle("-fx-background-color: #FFA7A0");
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
		ConfigureView.setUserName.setFont(new Font(TextEdit.fontType, TextEdit.credFontSize));
		ConfigureView.setUserField = new TextField();
		ConfigureView.setUserField.setStyle("-fx-background-color: #DCDCDC");

		ConfigureView.setUserPassword = new Label("Your user password:");
		ConfigureView.setUserPassword.setFont(new Font(TextEdit.fontType, TextEdit.credFontSize));
		ConfigureView.createUserPasswordField = new PasswordField();
		ConfigureView.createUserPasswordField.setStyle("-fx-background-color: #DCDCDC");

		ConfigureView.rewriteUserPassword = new Label("Rewrite your password:");
		ConfigureView.rewriteUserPassword.setFont(new Font(TextEdit.fontType, TextEdit.credFontSize));
		ConfigureView.rewriteUserPasswordField = new PasswordField();
		ConfigureView.rewriteUserPasswordField.setStyle("-fx-background-color: #DCDCDC");

		ConfigureView.layout2 = new VBox(20);
		ConfigureView.layout2.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				MainView.xOffset = event.getSceneX();
				MainView.yOffset = event.getSceneY();
			}
		});

		ConfigureView.layout2.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() - MainView.xOffset);
				primaryStage.setY(event.getScreenY() - MainView.yOffset);
			}
		});

		ConfigureView.layout2.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		ConfigureView.layout2.getChildren().addAll(ConfigureView.createUserLabel, ConfigureView.createUserGrid,
				ConfigureView.userButtonsGrid, ConfigureView.createUserFailed);
		ConfigureView.layout2.setAlignment(Pos.CENTER);
		ConfigureView.layout2.setStyle("-fx-background-color: #FFFFFF");
		ConfigureView.signUpScene = new Scene(ConfigureView.layout2, 400, 250);
		primaryStage.setScene(ConfigureView.signUpScene);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();

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

	/**
	 * This public static method is responsible for validation new user
	 * credentials. Secure mechanism for this method is more complicated than
	 * mechanism used in LogInView class because of additional fields to be
	 * handled. First step is to check if given user already exists, and then if
	 * both given passwords are identical. User name and password must be also
	 * longer that four characters. Any wrong credential will be occur proper
	 * warning visible by user. Positive validation creates also new database
	 * view for current user.
	 * 
	 * @param userName
	 *            user name to be created
	 * @param userPassword
	 *            user password to compare with another one
	 * @param showLabel
	 *            label which show specific warning
	 */

	public void createUser(String userName, String userPassword, Label showLabel) {
		if (DatabaseSubscription.checkIncorrectChars(userName) == true
				|| DatabaseSubscription.checkIncorrectChars(userPassword) == true) {
			DatabaseSubscription.showWarning(showLabel, 1.8, "User name or password can not contain \"\\\"", "red");
		} else if (DatabaseSubscription.checkUserName(userName) == true) {
			DatabaseSubscription.showWarning(showLabel, 1.8, "User already exist", "red");
		} else if (DatabaseSubscription.checkCredintialsLength(userName, 4) == false) {
			DatabaseSubscription.showWarning(showLabel, 1.8, "Username must contain at least 5 characters", "red");
		} else if (DatabaseSubscription.checkCredintialsLength(userPassword, 4) == false) {
			DatabaseSubscription.showWarning(showLabel, 1.8, "Your password must contain at least 5 characters", "red");
		} else if (ConfigureView.rewriteUserPasswordField.getText().equals(userPassword) == false) {
			DatabaseSubscription.showWarning(showLabel, 1.8, "Both passwords must be the same", "red");
		} else {
			DatabaseSubscription.insertNewUser(userName, userPassword);
			DatabaseSubscription.showWarning(showLabel, 3.5, "Congratulations ! User Created, please Sign In : )",
					"green");
			DatabaseSubscription.executeNewUpdateQuery(
					"GRANT ALL PRIVILEGES ON basiccosts.* TO '" + userName + "' IDENTIFIED BY '" + userPassword + "'");
			DatabaseSubscription.executeNewUpdateQuery("CREATE VIEW " + userName
					+ "View AS SELECT idCost, typeCost, nameCost, valueCost, dateCost FROM costs WHERE userName='"
					+ userName + "'");
			ConfigureView.setUserField.clear();
			ConfigureView.createUserPasswordField.clear();
			ConfigureView.rewriteUserPasswordField.clear();
		}
	}
}
