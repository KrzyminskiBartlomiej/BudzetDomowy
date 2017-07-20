package com.homebudget.MainController;

import com.homebudget.Utils.TextEdit;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Application class from which JavaFX applications extend. The entry point for
 * JavaFX applications is the Application class. The JavaFX runtime does the
 * following, in order, whenever an application is launched.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 * 
 */

public class MainView extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Application launches here. This is the place to configure and invoke each
	 * window used by application.
	 * 
	 */

	@Override
	public void start(Stage primaryStage) throws Exception {	
		LogInView logInView = new LogInView();
		CreateUserView createUserView = new CreateUserView();		
		TextEdit textStyle = new TextEdit();
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(new Image("/HBLogo.png"));
		
		textStyle.setTextValue("Comic Sans MS", 14.0, 12.0, 16.0);
		createUserView.setCreateUserWindow(primaryStage);
		logInView.setLogInWindow(primaryStage);		
		logInView.invokeLogInWindow(primaryStage);
	}
}