package com.homebudget.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.homebudget.DataBaseHandler.DatabaseConnector;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

@SuppressWarnings("rawtypes")
public class TableViewHandler extends TableView{
	private TableView tableView;
	private ObservableList<ObservableList> data;

	@SuppressWarnings({ "unchecked" })
	public void getAllData() {
		tableView = new TableView();
		String getAllDataQuery = "SELECT * FROM costs";
		PreparedStatement statement;

		try {
			statement = DatabaseConnector.connect().prepareStatement(getAllDataQuery);
			ResultSet rs = statement.executeQuery();
			data = FXCollections.observableArrayList();

			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
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
				data.add(row);
			}
			tableView.setItems(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TableView getTable(){
		return tableView;
	}
}
