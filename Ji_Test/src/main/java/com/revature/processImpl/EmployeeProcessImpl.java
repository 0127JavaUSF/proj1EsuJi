package com.revature.processImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.Cookie;

import com.google.gson.Gson;
import com.revature.util.Process;
import com.revature.util.AdminResponse;
import com.revature.util.EmployeeResponse;

public class EmployeeProcessImpl implements Process {

	private EmployeeResponse tableRes;
	private AdminResponse tableResA;
	private ArrayList<EmployeeResponse> listofReimb;
	private ArrayList<AdminResponse> listofReimbA;
	Gson gson = new Gson();
	
	String[] statusArray = new String[]{ "", "Denied", "Approved", "Pending"}; 
	String[] typeArray = new String[]{ "", "LODING", "TRAVEL", "FOOD", "OTHER"}; 
	
	@Override
	public String process(String req) {
		
		return " ";
	}
	
	public String process(String type, String id) {
//		String username = cookie.getName();
		String typeE = type;
		String prepQuery;
		
		if(typeE.equals("1")) {
			String status;
			System.out.println(typeE + "asdfasdf");
			listofReimb = new ArrayList<EmployeeResponse>();
			prepQuery = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?;";
			db.setPreparedStatement(prepQuery);
			ResultSet result = db.queryStatementsInt(Integer.parseInt(id));
			try {
				while(result.next()) {
					//int id, int amount, String status, String desc, Timestamp subTime, String rimbURL
					int tmp = result.getInt("reimb_status_id");
					int tmp2 = result.getInt("reimb_type_id");
					
					System.out.println(result.getString("reimb_description") + "asdfasdfasdf");
					tableRes = new EmployeeResponse(result.getInt("reimb_id"), result.getInt("reimb_amount"), statusArray[tmp], typeArray[tmp2], result.getString("reimb_description"), result.getTimestamp("reimb_submitted"),result.getTimestamp("reimb_resolved"),result.getString("reimb_receipt"));
					System.out.println(gson.toJson(tableRes));
					listofReimb.add(tableRes);
				}
				
				return gson.toJson(listofReimb);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else {

			listofReimb = new ArrayList<EmployeeResponse>();
			prepQuery = "SELECT * FROM ers_reimbursement ORDER BY reimb_status_id DESC";
			db.setPreparedStatement(prepQuery);
			ResultSet result = db.queryStatements();
			try {
				while(result.next()) {

					int tmp = result.getInt("reimb_status_id");
					int tmp2 = result.getInt("reimb_type_id");
					//int id, int amount, String status, String desc, Timestamp subTime, String rimbURL
					tableRes = new EmployeeResponse(result.getInt("reimb_id"), result.getInt("reimb_amount"), statusArray[tmp], typeArray[tmp2], result.getString("reimb_description"), result.getTimestamp("reimb_submitted"),result.getTimestamp("reimb_resolved"),result.getString("reimb_receipt"));
					listofReimb.add(tableRes);
				}
				
				return gson.toJson(listofReimb);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return " ";
	}
	
	public String getName(String id) {
		int tmpid = Integer.parseInt(id);
		String prepQuery = "SELECT * FROM ers_users WHERE ers_user_id = ?";
		db.setPreparedStatement(prepQuery);
		ResultSet result = db.queryStatementsInt(tmpid);
		try {
			if(result.next()) {
				return result.getString("user_first_name") + " " + result.getString("user_last_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

}
