package com.homebudget.MainController;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainApplicationView {
	public void setMainApplicationView() {
		ConfigureView.mainBorderPane = new BorderPane();
		ConfigureView.mainBorderPane.setTop(addTopMenu());
		ConfigureView.mainBorderPane.setLeft(addLeftBottomMenu());

		ConfigureView.applicationScene = new Scene(ConfigureView.mainBorderPane, 600, 500);
	}

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

		Button logOut = new Button("Log Out");
		logOut.setPrefSize(100, 20);
		logOut.setOnAction(e -> ConfigureView.window.setScene(ConfigureView.logInScene));

		Button exit = new Button("Exit");
		exit.setPrefSize(100, 20);
		exit.setOnAction(e -> Platform.exit());

		topMenu.getChildren().add(hbLogoView);
		topMenu.getChildren().add(logOut);
		topMenu.getChildren().add(exit);

		return topMenu;
	}
	
	public VBox addLeftBottomMenu(){
		VBox leftBottomMenu = new VBox();
		leftBottomMenu.setPadding(new Insets(10));
		leftBottomMenu.setSpacing(8);
		leftBottomMenu.setPrefWidth(174);
		leftBottomMenu.setStyle("-fx-background-color: #D4805D");
		
		ConfigureView.addNew = new Button("Add new...");
		ConfigureView.addNew.setPrefSize(100, 20);
		
		ConfigureView.showExpense = new Button("Show...");
		ConfigureView.showExpense.setPrefSize(100, 20);
		
		ConfigureView.showJuxtaposition = new Button("Make Juxtaposition");
		ConfigureView.showJuxtaposition.setPrefSize(100, 20);
		
		leftBottomMenu.getChildren().add(ConfigureView.addNew);	
		leftBottomMenu.getChildren().add(ConfigureView.showExpense);	
		leftBottomMenu.getChildren().add(ConfigureView.showJuxtaposition);	
		
		return leftBottomMenu;
	}
}
