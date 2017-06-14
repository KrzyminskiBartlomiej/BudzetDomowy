package com.homebudget.MainController;

import javafx.application.Application;
import javafx.stage.Stage;

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
		MainApplicationView mainView = new MainApplicationView();

		createUserView.setCreateUserWindow();
		logInView.setLogInWindow();
		mainView.setMainApplicationView();
		logInView.invokeLogInWindow(primaryStage);
	}
}