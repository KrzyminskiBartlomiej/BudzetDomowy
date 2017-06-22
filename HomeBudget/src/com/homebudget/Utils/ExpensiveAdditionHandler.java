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

public class ExpensiveAdditionHandler {

	static VBox decisionBox;

	public void createExpensive() {
		decisionBox = new VBox();
		decisionBox.setPadding(new Insets(10));
		decisionBox.setSpacing(8);
		decisionBox.setPrefWidth(174);
		decisionBox.setStyle("-fx-background-color: #D4805D");

		ListView<String> expensiveCategory = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList("Food", "Junk Food", "Car", "Home", "Clothes",
				"Health", "Alcohol");
		expensiveCategory.setItems(items);
		expensiveCategory.setPrefHeight(100);

		Label costName = new Label("Cost name");
		TextField costNameField = new TextField();

		Label costValue = new Label("Cost value");
		TextField costValueField = new TextField();

		Button create = new Button("Create!");
		create.setMaxWidth(200);
		create.setAlignment(Pos.BOTTOM_CENTER);
		create.setOnAction(e -> addDescribedExpenseToDatabase(expensiveCategory.getSelectionModel().getSelectedItem(),
				costNameField.getText(), costValueField.getText()));

		decisionBox.getChildren().add(expensiveCategory);
		decisionBox.getChildren().add(costName);
		decisionBox.getChildren().add(costNameField);
		decisionBox.getChildren().add(costValue);
		decisionBox.getChildren().add(costValueField);
		decisionBox.getChildren().add(create);

		ConfigureView.mainBorderPane.setLeft(decisionBox);
	}

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

		MainApplicationView goBack = new MainApplicationView();
		ConfigureView.mainBorderPane.setLeft(goBack.addLeftBottomMenu());
		TableViewHandler.tableRefresher();
	}
}
