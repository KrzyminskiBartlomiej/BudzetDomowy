package com.homebudget.MainController;

import com.homebudget.DataBaseHandler.DatabaseSubscription;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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

	public void setCreateUserWindow() {
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
		if (DatabaseSubscription.checkUserName(userName) == true) {
			DatabaseSubscription.showWarning(showLabel, 1.8, "User already exist", "red");
		} else if (DatabaseSubscription.checkCredintialsLength(userName) == false) {
			DatabaseSubscription.showWarning(showLabel, 1.8, "Username must contain at least 5 characters", "red");
		} else if (DatabaseSubscription.checkCredintialsLength(userPassword) == false) {
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
