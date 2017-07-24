package com.homebudget.DataBaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.homebudget.MainController.ConfigureView;

import javafx.collections.FXCollections;

/**
 * Public class used to aggregate methods responsible getting data from database
 * and saving data into static fields.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 * 
 */

public class DatabaseGetData {
	public static List<Double> testData = new ArrayList<Double>();
	public static Set<String> userName = new HashSet<String>();

	/**
	 * Used to store data into ArrayList, in that case integer values.
	 * 
	 * @param rs
	 *            a table of data picked up from database
	 * @throws SQLException
	 *             in case of any unexpected database behavior
	 * 
	 */

	public static void getIntData(ResultSet rs) throws SQLException {
		while (rs.next()) {
			testData.add(rs.getDouble("valueCost"));
		}
	}

	/**
	 * Used to store data into HashSet, in that case String values.
	 * 
	 * @param rs
	 *            a table of data picked up from database
	 * @param columnName
	 *            stores name of column to work with
	 * @throws SQLException
	 *             in case of any unexpected database behavior
	 * 
	 */

	public static void getStringData(ResultSet rs, String columnName) throws SQLException {
		while (rs.next()) {
			userName.clear();
			userName.add(rs.getString(columnName));
		}
	}

	/**
	 * Gets category names from database and puts into costTypeItems container.
	 * Created after optimization to work with local data.
	 * 
	 */

	public static void getCategoryNames() {
		String getAllDataQuery = "SELECT category FROM costcategory WHERE creator = 'default' OR creator = '"
				+ DatabaseConnector.getUserName() + "'";
		PreparedStatement statement;
		ConfigureView.costTypeItems = FXCollections.observableArrayList();

		try {
			statement = DatabaseConnector.connect().prepareStatement(getAllDataQuery);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					ConfigureView.costTypeItems.add(rs.getString(i));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
