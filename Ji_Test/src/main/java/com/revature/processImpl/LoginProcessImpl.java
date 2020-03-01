package com.revature.processImpl;

import com.revature.util.loginResponse;
import com.revature.util.Process;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;

public class LoginProcessImpl implements Process{
	public loginResponse storage;
	Gson gson = new Gson();
	private String roleID;
	private String empID;
	
	@Override
	public String process(String req) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String process(String user, String pass) {
		
		String confirm = "no";

		String userHash = Hashing.sha1().hashString(user, StandardCharsets.UTF_8).toString();
		String passHash = Hashing.sha1().hashString(pass, StandardCharsets.UTF_8).toString();
		
		
		String query = "SELECT * FROM ers_users WHERE ers_username = ? AND ers_password = ?";
		
		db.setPreparedStatement(query);
		ResultSet result = db.queryStatements(userHash,passHash);
		
		try {
			if(result.next()) {
				empID = result.getString("ers_user_id").toString();
				roleID = result.getString("user_role_id").toString();
				storage = new loginResponse(result.getString("ers_user_id"), result.getString("user_role_id"), "yes");
				String gson = new Gson().toJson(storage);
				
				return gson;
			}
			
			else {
				storage = new loginResponse("", "" , "no");
				String gson = new Gson().toJson(storage);
				return gson;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return confirm;
	}
	
	public String getRoleID() {
		return roleID;
	}
	
	public String getEmpID() {
		return empID;
	}
}
