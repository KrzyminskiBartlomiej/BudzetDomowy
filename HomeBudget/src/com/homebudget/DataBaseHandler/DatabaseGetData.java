package com.homebudget.DataBaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatabaseGetData {
	public static List<Integer> testData = new ArrayList<Integer>();
	public static Set<String> userName = new HashSet<String>();
	
	public static void getIntData(ResultSet rs) throws SQLException{
		while(rs.next()){
			testData.add(rs.getInt("valueCost"));
		}
	}
	
	public static void getStringData(ResultSet rs) throws SQLException{
		while(rs.next()){
			userName.add(rs.getString("User"));
		}
	}
}
