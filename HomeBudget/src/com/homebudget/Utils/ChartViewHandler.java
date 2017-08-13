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
	private Double sumOfAllExpensives;

	/**
	 * This method generates data from specific observableArrayList, by addition
	 * specific costs for each cost type.
	 * 
	 */

	public void createDataForPieChart(int dateSpan) {
		pieChartData = FXCollections.observableArrayList();
		sumOfAllExpensives = 0.0;
		for (int i = 0; i < ConfigureView.costTypeItems.size(); i++) {
			if (getSumForType(ConfigureView.costTypeItems.get(i), dateSpan) != 0) {
				pieChartData.add(new PieChart.Data(ConfigureView.costTypeItems.get(i),
						getSumForType(ConfigureView.costTypeItems.get(i), dateSpan)));
			}
			sumOfAllExpensives += getSumForType(ConfigureView.costTypeItems.get(i), dateSpan);
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
	
	public Integer getDateInt(List<?> toSplit){
		String date = (String) toSplit.get(4);
		int month = Integer.parseInt(date.substring(5,7));
		return month;
	}

	/**
	 * Creates a sum for given type.
	 * 
	 * @param type
	 *            given name for values addition
	 * @return sum for given type
	 * 
	 */

	public Double getSumForType(String type, int dateSpan) {
		Double sumResult = 0.00;
		
		for (int i = 0; i < TableViewHandler.data.size(); i++) {
			if (type.equals(dataSplitter(TableViewHandler.data.get(i), 1))) {
				if(dateSpan == 0){
					sumResult += Double.parseDouble(dataSplitter(TableViewHandler.data.get(i), 3));
				}	
				else if(dateSpan == getDateInt(TableViewHandler.data.get(i))){
					sumResult += Double.parseDouble(dataSplitter(TableViewHandler.data.get(i), 3));
				}
			}
		}
		sumResult = Math.floor(sumResult * 100) / 100;
		return sumResult;
	}

	public Double getSumOfAllExpensives() {
		return sumOfAllExpensives;
	}

	public void setSumOfAllExpensives(Double sumOfAllExpensives) {
		this.sumOfAllExpensives = sumOfAllExpensives;
	}
}
