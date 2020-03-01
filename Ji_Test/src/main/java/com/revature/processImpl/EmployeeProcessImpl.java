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
	
	@Override
	public String process(String req) {
		
		return " ";
	}
	
	public String process(String id,String type) {
//		String username = cookie.getName();
		String typeE = type;
		String prepQuery;
		System.out.println("yolo");
		
		if(type == "1") {
			String status;
			listofReimb = new ArrayList<EmployeeResponse>();
			prepQuery = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?;";
			db.setPreparedStatement(prepQuery);
			ResultSet result = db.queryStatementsInt(Integer.parseInt(id));
			try {
				while(result.next()) {
					//int id, int amount, String status, String desc, Timestamp subTime, String rimbURL
					int tmp = result.getInt("reimb_status_id");
					
					if (tmp == 1) {
						status = "Denied";
					}
					
					if (tmp == 2) {
						status = "Approved";
					}
					
					else {
						status = "Pending";
					}
					
					tableRes = new EmployeeResponse(result.getInt("reimb_id"), result.getInt("reimb_amount"), status, result.getString("reimb_description"), result.getTimestamp("reimb_submitted"), result.getString("reimb_receipt"));
					listofReimb.add(tableRes);
				}
				
				return gson.toJson(listofReimb);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else {

			listofReimbA = new ArrayList<AdminResponse>();
			prepQuery = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = 3";
			db.setPreparedStatement(prepQuery);
			ResultSet result = db.queryStatements();
			try {
				while(result.next()) {
					//int id, int amount, String status, String desc, Timestamp subTime, String rimbURL
					tableResA = new AdminResponse(result.getInt("reimb_id"), result.getInt("reimb_amount"), result.getString("reimb_description"), result.getTimestamp("reimb_submitted"), result.getString("reimb_receipt"));
					listofReimbA.add(tableResA);
				}
				
				return gson.toJson(listofReimbA);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return " ";
	}

}
