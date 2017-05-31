package com.homebudget.MainController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.homebudget.DataBaseHandler.*;

public class MainView {
	
	public static void main(String[] args) {
		DatabaseConnector mysqlConnect = new DatabaseConnector();
		String sqlTestCommand = "SELECT valueCost FROM costs;";
		
		try{
			PreparedStatement statement = DatabaseConnector.connect().prepareStatement(sqlTestCommand);
			ResultSet rs = statement.executeQuery();
			DatabaseGetData.getIntData(rs);
			
			System.out.println(DatabaseGetData.testData);
		} catch (SQLException e){
			e.printStackTrace();
		}finally{
			mysqlConnect.disconnect();
		}
		
		
	}
}
