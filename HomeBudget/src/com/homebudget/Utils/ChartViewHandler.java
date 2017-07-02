package com.homebudget.Utils;

import java.util.ArrayList;

import com.homebudget.MainController.ConfigureView;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class ChartViewHandler {
	public static ObservableList<PieChart.Data> pieChartData;

	public void createDataForPieChart() {
		pieChartData = FXCollections.observableArrayList(new PieChart.Data(ConfigureView.costTypeItems.get(0), 12),
				new PieChart.Data(ConfigureView.costTypeItems.get(1), 42),
				new PieChart.Data(ConfigureView.costTypeItems.get(2), 12),
				new PieChart.Data(ConfigureView.costTypeItems.get(3), 32),
				new PieChart.Data(ConfigureView.costTypeItems.get(4), 12),
				new PieChart.Data(ConfigureView.costTypeItems.get(5), 12),
				new PieChart.Data(ConfigureView.costTypeItems.get(6), 122));

		pieChartData.forEach(
				data -> data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), " zl")));
	}

	public void getSumForType(String type, ArrayList<?> dataToFilter) {
		//Double sumResult = 0.00;
		
		for(int i = 0; i < dataToFilter.size(); i++){
			System.out.println(ConfigureView.costTypeItems.get(6));
		}
		
		//return sumResult;
	}
}
