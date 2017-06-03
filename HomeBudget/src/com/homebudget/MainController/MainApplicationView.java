package com.homebudget.MainController;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class MainApplicationView {
	public static void setSettingsMainApplicationView(){
		ConfigureView.layout3 = new VBox(20);
		//ConfigureView.layout3.getChildren().add(null);
		
		ConfigureView.applicationScene = new Scene(ConfigureView.layout3, 800, 600);
	}
}
