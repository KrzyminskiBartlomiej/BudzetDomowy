package com.homebudget.MainController;

import java.util.Optional;

import com.homebudget.DataBaseHandler.DatabaseConnector;
import com.homebudget.DataBaseHandler.DatabaseSubscription;
import com.homebudget.Utils.ExpensiveAdditionHandler;
import com.homebudget.Utils.TableViewHandler;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Used to create layout for main application. Created via BorderPane because of
 * practical approach.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 * 
 */

public class MainApplicationView {

	/**
	 * Used only to set regions in BorderPane, and assignment new scene to
	 * Configure class.
	 * 
	 */

	public void setMainApplicationView(Stage primaryStage) {
		ConfigureView.mainBorderPane = new BorderPane();
		ConfigureView.mainBorderPane.setOnMousePressed(e -> ConfigureView.mousePressHandler(e));
		ConfigureView.mainBorderPane.setOnMouseDragged(e -> ConfigureView.mouseDragHandler(e, primaryStage));

		ConfigureView.mainBorderPane.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		ConfigureView.mainBorderPane.setTop(addTopMenu());
		ConfigureView.mainBorderPane.setLeft(addLeftBottomMenu());

		ConfigureView.applicationScene = new Scene(ConfigureView.mainBorderPane, 600, 500);
		primaryStage.setScene(ConfigureView.applicationScene);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
	}

	/**
	 * Creates content for top menu.
	 * 
	 * @return created and configured HBox
	 * 
	 */

	public HBox addTopMenu() {
		HBox topMenu = new HBox();
		topMenu.setPadding(new Insets(15, 12, 15, 12));
		topMenu.setSpacing(10);
		topMenu.setStyle("-fx-background-color: #FBB496");

		Image homeBudgetLogo = new Image("File:resources/HBLogo.png");
		ImageView hbLogoView = new ImageView();
		hbLogoView.setImage(homeBudgetLogo);
		hbLogoView.setFitHeight(150);
		hbLogoView.setFitWidth(150);

		Button deleteUser = new Button("Delete User");
		deleteUser.setPrefSize(100, 20);
		deleteUser.setOnAction(e -> deleteUser(DatabaseConnector.getUserName()));

		Button logOut = new Button("Log Out");
		logOut.setPrefSize(100, 20);
		logOut.setOnAction(e -> ConfigureView.window.setScene(ConfigureView.logInScene));

		Button exit = new Button("Exit");
		exit.setPrefSize(100, 20);
		exit.setOnAction(e -> Platform.exit());

		topMenu.getChildren().add(hbLogoView);
		topMenu.getChildren().add(deleteUser);
		topMenu.getChildren().add(logOut);
		topMenu.getChildren().add(exit);

		return topMenu;
	}

	/**
	 * Creates content for left side of application.
	 * 
	 * @return created and configured HBox
	 * 
	 */

	public VBox addLeftBottomMenu() {
		VBox leftBottomMenu = new VBox();
		leftBottomMenu.setPadding(new Insets(10));
		leftBottomMenu.setSpacing(8);
		leftBottomMenu.setPrefWidth(174);
		leftBottomMenu.setStyle("-fx-background-color: #D4805D");

		Button addNew = new Button("Add new...");
		addNew.setPrefSize(130, 20);
		addNew.setOnAction(e -> setExpenseType());

		Button showExpense = new Button("Show...");
		showExpense.setPrefSize(130, 20);
		showExpense.setOnAction(e -> createTableView());

		Button showJuxtaposition = new Button("Make Juxtaposition");
		showJuxtaposition.setPrefSize(130, 20);

		leftBottomMenu.getChildren().add(addNew);
		leftBottomMenu.getChildren().add(showExpense);
		leftBottomMenu.getChildren().add(showJuxtaposition);

		return leftBottomMenu;
	}

	/**
	 * Used only when user want to (e.g by click on button), it avoids
	 * unnecessary application resources usage.
	 * 
	 */

	public static void createTableView() {
		TableViewHandler newTable = new TableViewHandler();
		ScrollPane scrolledTable = new ScrollPane();
		VBox tableBox = new VBox();

		newTable.getAllData();
		newTable.setTableStyle();
		scrolledTable.setContent(newTable.getTable());
		scrolledTable.autosize();
		scrolledTable.fitToHeightProperty();
		scrolledTable.fitToWidthProperty();
		tableBox.getChildren().add(scrolledTable);
		tableBox.setPadding(new Insets(8, 8, 8, 8));

		ConfigureView.mainBorderPane.setCenter(tableBox);
	}

	/**
	 * Method used in create expense procedure, it updates left BorderPane
	 * region with decision buttons.
	 * 
	 */

	public void setExpenseType() {
		VBox decisionBox = new VBox();
		ExpensiveAdditionHandler createExpensiveProcedure = new ExpensiveAdditionHandler();

		decisionBox.setPadding(new Insets(10));
		decisionBox.setSpacing(8);
		decisionBox.setPrefWidth(174);
		decisionBox.setStyle("-fx-background-color: #D4805D");

		Button income = new Button("Income");
		Button outcome = new Button("Outcome");
		outcome.setOnAction(e -> createExpensiveProcedure.createExpensive());

		decisionBox.getChildren().add(income);
		decisionBox.getChildren().add(outcome);

		ConfigureView.mainBorderPane.setLeft(decisionBox);
	}

	/**
	 * Before it deletes user, it throws an alert to ensure if user is aware of
	 * what happens. After positive user deletion it forwards user to logInView
	 * and show proper information.
	 * 
	 * @param userName
	 *            user to be deleted
	 */

	public void deleteUser(String userName) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("By choosing ok, your account will be deleted");
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			DatabaseSubscription.pernamentDeleteUser(userName);
			ConfigureView.window.setScene(ConfigureView.logInScene);
			DatabaseSubscription.showWarning(ConfigureView.logInFailedInformation, 2.4,
					"Your account has been deleted, see You again!", "green");
		}
	}
}
