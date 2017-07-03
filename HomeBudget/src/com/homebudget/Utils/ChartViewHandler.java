package com.homebudget.Utils;

import java.util.List;

import com.homebudget.MainController.ConfigureView;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class ChartViewHandler {
	public static ObservableList<PieChart.Data> pieChartData;

	public void createDataForPieChart() {
		pieChartData = FXCollections.observableArrayList();
		for (int i = 0; i < ConfigureView.costTypeItems.size(); i++) {
			if (getSumForType(ConfigureView.costTypeItems.get(i)) != 0) {
				pieChartData.add(new PieChart.Data(ConfigureView.costTypeItems.get(i),
						getSumForType(ConfigureView.costTypeItems.get(i))));
			}
		}

		pieChartData.forEach(
				data -> data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), " zl")));
	}

	public String dataSplitter(List<?> toSplit, int place) {
		String sumData = (String) toSplit.get(place);
		return sumData;
	}

	public Double getSumForType(String type) {
		Double sumResult = 0.00;
		for (int i = 0; i < TableViewHandler.data.size(); i++) {
			if (type.equals(dataSplitter(TableViewHandler.data.get(i), 1))) {
				sumResult += Double.parseDouble(dataSplitter(TableViewHandler.data.get(i), 3));
			}
		}
		return sumResult;
	}
}
