package com.revature.util;


import com.revature.util.DAO;
import java.sql.ResultSet;

public interface Process {
	public static DAO db = new DAO();
	
	public String process(String req);
	
}
