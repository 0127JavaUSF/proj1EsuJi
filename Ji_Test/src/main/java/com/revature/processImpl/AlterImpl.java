package com.revature.processImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;

import com.google.gson.Gson;
import com.revature.util.EmployeeResponse;
import com.revature.util.Process;
import com.revature.util.SuccessOrFail;;

public class AlterImpl implements Process{
	private SuccessOrFail sof;
	Gson gson = new Gson();
	
	@Override
	public String process(String req) {

		return " ";
	}
	
	public String process(Cookie cookie, String amount, String desc, String types,String url) {
		String username = cookie.getName();
		String prepQuery;

		
		prepQuery = "INSERT INTO ers_reimbursement VALUES(DEFAULT, ? , DEFAULT, NULL, ? , ? ,NULL , DEFAULT, ?, ? )";
		db.setPreparedStatement(prepQuery);
		int result = db.alterQuery(Integer.parseInt(amount),desc, Integer.parseInt(username), Integer.parseInt(types),url);
		
		if(result < 1) {
			System.out.println("Insertion Failed");
			sof = new SuccessOrFail("false");
			return gson.toJson(sof);
		}


		sof = new SuccessOrFail("true");
		return gson.toJson(sof);
	}
	
	public String process(String id, String status) {
		String prepQuery = "UPDATE ers_reimbursement SET reimb_status_id  = ? WHERE reimb_id = ?";
		db.setPreparedStatement(prepQuery);
		int result = db.alterQuery1(Integer.parseInt(status), Integer.parseInt(id));
		
		if(result < 1) {
			System.out.println("Alter Failed");
			sof = new SuccessOrFail("false");
			return gson.toJson(sof);
		}	

		sof = new SuccessOrFail("true");
		return gson.toJson(sof);
		
	}
}
