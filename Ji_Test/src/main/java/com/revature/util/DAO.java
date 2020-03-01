package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {


	private Connection db;
	private PreparedStatement prepState;
	private ResultSet result;
	
	public DAO(){
		//get the sql connected :D
		try{
			do {
			//jdbc:postgresql://host:port/database_name
			db = DriverManager.getConnection("jdbc:postgresql://database-1.cfgsjckjokdt.us-east-2.rds.amazonaws.com:5432/postgres","jkong20", "1234" );
			}while (db == null);
		}
		catch (SQLException ex) {
			System.out.println("Connection has Timed out, Please verify username or firewall on your database");
			System.out.println(ex);
		}
	}
	

	public void setPreparedStatement(String query) {
		
		try {
			prepState = db.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//get result set
	public ResultSet queryStatements(String... query) {
		try{
			int i = 1;
			for(String e:query) {
				prepState.setString(i,e);
				i++;
			}
			result = prepState.executeQuery();
		}
		
		catch(SQLException ex) {
			System.out.println(ex);
		}
		return result;
	}	
	
	public ResultSet queryStatementsInt(int... query) {
		try{
			int i = 1;
			for(int e:query) {
				prepState.setInt(i,e);
				i++;
			}
			result = prepState.executeQuery();
		}
		
		catch(SQLException ex) {
			System.out.println(ex);
		}
		return result;
	}
	
	public int alterQuery(String... query) {
		try {
			int i = 1;
			for(String e:query) {
				prepState.setString(i,e);
				i++;
			}
			
			return prepState.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	

	public int alterQuery(int parseInt, String desc, int parseInt2, String url) {
		try {
			prepState.setInt(1, parseInt);
			prepState.setString(2, desc);
			prepState.setInt(3, parseInt2);
			prepState.setString(4, url);
			

			return prepState.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int alterQuery1(int... query) {
		try {
			int i = 1;
			for(int e:query) {
				prepState.setInt(i,e);
				i++;
			}
			
			return prepState.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}


}
