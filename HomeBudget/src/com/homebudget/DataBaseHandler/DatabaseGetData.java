package com.homebudget.DataBaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseGetData {
	public static List<Integer> testData = new ArrayList<Integer>();
	
	public static void getIntData(ResultSet rs) throws SQLException{
		while(rs.next()){
			testData.add(rs.getInt("valueCost"));
		}
	}
}
