package com.homebudget.Utils;

import java.util.List;

import com.homebudget.MainController.ConfigureView;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * Class that aggreagte methods connected with creating specific charts e.g.
 * chart creating, data reader, data splitter. It is directly connected with
 * juxtaposition making.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 *
 */

public class ChartViewHandler {
	public static ObservableList<PieChart.Data> pieChartData;

	/**
	 * This method generates data from specific observableArrayList, by addition
	 * specific costs for each cost type.
	 * 
	 */

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

	/**
	 * Splits given list and returns wanted value.
	 * 
	 * @param toSplit
	 *            List to be splitted
	 * @param place
	 *            value from List to be returned. It is necessary to be aware of
	 *            List length to avoid outOfBoundaryException.
	 * @return splitted value
	 * 
	 */

	public String dataSplitter(List<?> toSplit, int place) {
		String sumData = (String) toSplit.get(place);
		return sumData;
	}

	/**
	 * Creates a sum for given type.
	 * 
	 * @param type
	 *            given name for values addition
	 * @return sum for given type
	 * 
	 */

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
