package com.homebudget.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.homebudget.DataBaseHandler.DatabaseConnector;
import com.homebudget.DataBaseHandler.DatabaseSubscription;
import com.homebudget.MainController.ConfigureView;
import com.homebudget.MainController.MainApplicationView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * Class that is responsible for support expensive addition handling procedure.
 * Each method which is taking part in adding expensive procedure should be
 * placed here.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 *
 */

public class ExpensiveAdditionHandler {
	static VBox decisionBox;

	/**
	 * It updates region with expensive creation (left side box).
	 * 
	 */

	public void createExpensive() {
		decisionBox = new VBox();
		decisionBox.setPadding(new Insets(10));
		decisionBox.setSpacing(8);
		decisionBox.setPrefWidth(154);
		decisionBox.setStyle("-fx-background-color: #D4805D");

		ListView<String> expensiveCategory = new ListView<String>();
		expensiveCategory.setItems(ConfigureView.costTypeItems);
		expensiveCategory.setPrefHeight(100);

		Label costName = new Label("Cost name");
		TextField costNameField = new TextField();

		Label costValue = new Label("Cost value");
		TextField costValueField = new TextField();

		Label costCreatorAlert = new Label("");
		costCreatorAlert.setVisible(false);
		costCreatorAlert.setTextAlignment(TextAlignment.CENTER);
		costCreatorAlert.setMaxWidth(200);		

		Button create = new Button("Create!");
		create.setMaxWidth(200);
		create.setAlignment(Pos.BOTTOM_CENTER);
		create.setOnAction(e -> costAddValidator(expensiveCategory.getSelectionModel().getSelectedItem(),
				costNameField.getText(), costValueField.getText(), costCreatorAlert));

		MainApplicationView goBack = new MainApplicationView();

		Button cancel = new Button("Cancel");
		cancel.setMaxWidth(200);
		cancel.setAlignment(Pos.BOTTOM_CENTER);
		cancel.setOnAction(e -> ConfigureView.mainBorderPane.setLeft(goBack.addLeftBottomMenu()));

		decisionBox.getChildren().add(expensiveCategory);
		decisionBox.getChildren().add(costName);
		decisionBox.getChildren().add(costNameField);
		decisionBox.getChildren().add(costValue);
		decisionBox.getChildren().add(costValueField);
		decisionBox.getChildren().add(create);
		decisionBox.getChildren().add(cancel);
		decisionBox.getChildren().add(costCreatorAlert);

		ConfigureView.mainBorderPane.setLeft(decisionBox);
	}

	/**
	 * Adds new expense into database. It also updates userView, and refreshes
	 * tableView for instant cost view. At the end it refreshes left side of
	 * BorderPane.
	 * 
	 * @param costType
	 *            type of cost chosen from ListView.
	 * @param costName
	 *            name of cost written by user
	 * @param costValue
	 *            value of cost written by user
	 * 
	 */

	public static void addDescribedExpenseToDatabase(String costType, String costName, String costValue) {
		DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();

		String insertCostQuery = "INSERT INTO " + "costs (userName, typeCost, nameCost, valueCost, dateCost) VALUES ('"
				+ DatabaseConnector.getUserName() + "', '" + costType + "', '" + costName + "', '" + costValue + "', '"
				+ currentDate.format(localDate) + "');";

		String updateUserViewQuery = "CREATE OR REPLACE VIEW " + DatabaseConnector.getUserName()
				+ "View AS SELECT idCost, typeCost, nameCost, valueCost, dateCost FROM costs WHERE userName = '"
				+ DatabaseConnector.getUserName() + "';";

		DatabaseSubscription.executeNewUpdateQuery(insertCostQuery);
		DatabaseSubscription.executeNewUpdateQuery(updateUserViewQuery);

//		MainApplicationView goBack = new MainApplicationView();
//		ConfigureView.mainBorderPane.setLeft(goBack.addLeftBottomMenu());
//		TableViewHandler.tableRefresher();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void addDescribedExpenseToDataTable(String costType, String costName, String costValue){
		DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		
		ObservableList toBeInserted = FXCollections.observableArrayList();
		toBeInserted.add(TableViewHandler.data.size() + 1);
		toBeInserted.add(costType);
		toBeInserted.add(costName);
		toBeInserted.add(costValue);
		toBeInserted.add(currentDate.format(localDate));
		
		TableViewHandler.data.add(TableViewHandler.data.size(), toBeInserted);
		MainApplicationView goBack = new MainApplicationView();
		ConfigureView.mainBorderPane.setLeft(goBack.addLeftBottomMenu());
		TableViewHandler.tableRefresher();
	}

	/**
	 * Checks if all mandatory field are correctly filled. If not it raises a
	 * specific alert, otherwise it allows to create new outcome.
	 * 
	 * @param costType
	 *            value from expensiveCategory, if there are no options marked
	 *            it passes null
	 * @param costName
	 *            name of cost
	 * @param costValue
	 *            value of cost
	 * @param showTo
	 *            label where the warring will be shown
	 * 
	 */

	public void costAddValidator(String costType, String costName, String costValue, Label showTo) {
		if (costType == null)
			DatabaseSubscription.showWarning(showTo, 1.8, "Check cost Type!", "red");
		else if (DatabaseSubscription.checkCredintialsLength(costName, 1) == false)
			DatabaseSubscription.showWarning(showTo, 1.8, "Fill cost name field!", "red");
		else if (DatabaseSubscription.checkCredintialsLength(costValue, 0) == false)
			DatabaseSubscription.showWarning(showTo, 1.8, "Set cost value!", "red");
		else {
			if (costValue.contains(",")) costValue = costValue.replace(",",".");			
			addDescribedExpenseToDataTable(costType, costName, costValue);
		}
	}
}
