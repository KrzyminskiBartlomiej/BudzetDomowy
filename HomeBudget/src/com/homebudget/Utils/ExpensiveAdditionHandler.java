package com.homebudget.Utils;

import com.homebudget.MainController.ConfigureView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ExpensiveAdditionHandler {
	public void createExpensive(){
		VBox decisionBox = new VBox();
		decisionBox.setPadding(new Insets(10));
		decisionBox.setSpacing(8);
		decisionBox.setPrefWidth(174);
		decisionBox.setStyle("-fx-background-color: #D4805D");
		
		ListView<String> expensiveCategory = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList (
		    "Food", "Junk Food", "Car", "Home", "Clothes", "Health", "Alcohol");
		expensiveCategory.setItems(items);
		expensiveCategory.getSelectionModel().getSelectedItem();
		expensiveCategory.setPrefHeight(100);
		
		Label costName = new Label("Cost name");
		TextField costNameField = new TextField();
		
		Label costValue = new Label("Cost value");
		TextField costValueField = new TextField();
		
		Button create = new Button("Create!");
		//create.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		decisionBox.getChildren().add(expensiveCategory);	
		decisionBox.getChildren().add(costName);
		decisionBox.getChildren().add(costNameField);
		decisionBox.getChildren().add(costValue);
		decisionBox.getChildren().add(costValueField);
		decisionBox.getChildren().add(create);		
		
		ConfigureView.mainBorderPane.setLeft(decisionBox);
	}
}
