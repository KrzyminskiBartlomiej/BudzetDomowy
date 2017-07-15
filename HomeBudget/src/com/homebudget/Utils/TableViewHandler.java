package com.homebudget.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import com.homebudget.DataBaseHandler.DatabaseConnector;
import com.homebudget.MainController.ConfigureView;
import com.homebudget.MainController.MainApplicationView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * Used to handle operations connected with table in application, which means
 * CREATING, DELETING, UPDATING etc.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 *
 */

@SuppressWarnings("rawtypes")
public class TableViewHandler extends TableView {
	public static TableView tableView;
	public static ObservableList<ObservableList> data;
	public static Integer boundaryFlag;
	public static Stack deletedLines;

	/**
	 * Used to get all data from user view. For now this method downloads all
	 * data from user view but in near future it has to take data from specific
	 * views which makes this method very useful. It also have two tasks, first
	 * one to create specific tableView and store it into private field, second
	 * one is to get all data into private field ObservableList. This behavior
	 * allows to use generated data in another node e.g chart.
	 * 
	 */

	@SuppressWarnings({ "unchecked" })
	public static void getAllData() {
		tableView = new TableView();
		String getAllDataQuery = "SELECT * FROM " + DatabaseConnector.getUserName() + "View";
		PreparedStatement statement;
		Integer counter = 1;

		try {
			statement = DatabaseConnector.connect().prepareStatement(getAllDataQuery);
			ResultSet rs = statement.executeQuery();
			data = FXCollections.observableArrayList();

			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn col = new TableColumn(ConfigureView.tableColumnsNames.get(i));
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});
				tableView.getColumns().addAll(col);
			}

			while (rs.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));
				}
				row.set(0, counter.toString());
				counter++;
				data.add(row);
			}
			boundaryFlag = data.size();
			tableView.setItems(data);
			tableView.refresh();

			deletedLines = new Stack<>();
			tableView.setUserData(deletedLines);
			MenuItem deleteOne = new MenuItem("Delete cost");
			deleteOne.setOnAction(e -> deleteRow());

			tableView.setContextMenu(new ContextMenu(deleteOne));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteRow() {
		ObservableList item = data.get(tableView.getSelectionModel().getSelectedIndex());
		if(tableView.getSelectionModel().getSelectedIndex() + 1 > boundaryFlag && item != null){
			data.remove(item);
		}
	}

	/**
	 * Refreshes tableView and all content.
	 * 
	 */

	public static void tableRefresher() {
		MainApplicationView.createTableView();
	}

	/**
	 * Method used to handle tableView style.
	 * 
	 */

	@SuppressWarnings("unchecked")
	public void setTableStyle() {
		tableView.autosize();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	public TableView getTable() {
		return tableView;
	}
}
