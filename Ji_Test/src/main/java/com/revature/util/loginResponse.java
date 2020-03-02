package com.revature.util;

public class loginResponse{
	private String username;
	private  String password;
	private String confirm;
	private String name;
	
	public loginResponse(String fName, String lName, String Confirm) {
		username = fName;
		password = lName;
		confirm = Confirm;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}