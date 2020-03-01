package com.revature.erservlet;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.revature.processImpl.AlterImpl;
import com.revature.processImpl.EmployeeProcessImpl;
import com.revature.processImpl.LoginProcessImpl;
import com.revature.util.SuccessOrFail;
import com.revature.util.loginResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



/**
 * Servlet implementation class testServ
 */
public class ERServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private DAO db;
	private Gson gson = new Gson();
	private LoginProcessImpl processTransaction;
	private EmployeeProcessImpl employeeTransaction;
	private AlterImpl alterTransaction;
	
	/**
	 * @throws ServletException
	 * @see HttpServlet#HttpServlet()
	 */

	public void init() throws ServletException {
		
		try {
			Class.forName("org.postgresql.Driver");
		}

		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// db = new DAO();
		
		processTransaction = new LoginProcessImpl();
		employeeTransaction = new EmployeeProcessImpl();
		alterTransaction = new AlterImpl();
		super.init();
	}
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		resp.addHeader("Access-Control-Allow-Headers", "authorization");		
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.addHeader("Access-Control-Allow-Methods", "GET POST PUT DELETE");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		
		// TODO Auto-generated method stub
		super.service(req, resp);
	}
	

	public ERServlet() throws ServletException, ClassNotFoundException {
		this.init();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		//getting the cookie
        Cookie[] cookies = request.getCookies();

        int reqType = Integer.parseInt(request.getParameter("reqType"));
		String returnVal = " ";
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		//checking if client logged in the last 10 minutes
        
		switch(reqType) {
			case 0: 
				loginResponse soff = new loginResponse("a", "a" ,"false");
				String json2Send;
				if(cookies == null) {
					json2Send = gson.toJson(soff);
				}
				
				else {
					soff.setConfirm("true");
					json2Send = gson.toJson(soff);
				}
				System.out.println(json2Send);
				response.getWriter().print(json2Send);
				break;
				
			case 1:
				String username = request.getParameter("user");
				String password = request.getParameter("pass");
				
				
				returnVal = processTransaction.process(username, password);
				
				if(!returnVal.equals("no")) {
					String cookID = "Login";
					Cookie cookie = new Cookie(processTransaction.getRoleID(),processTransaction.getEmpID());
					cookie.setMaxAge(10 * 60);
					response.addCookie(cookie);
					System.out.println(cookie.getValue() + " " + cookie.getName());
					response.getWriter().print(returnVal);
				}
				break;
			
			case 2:
				returnVal = employeeTransaction.process("2","2");
				response.getWriter().print(returnVal);
				break;
				
				
			case 3:
				for(Cookie e: cookies) {
					e.setMaxAge(0);
					response.addCookie(e);
				}
				break;

			default:
				break;
		
		}
//

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int type = 0;

		//getting the cookie
        Cookie[] cookies = request.getCookies();
        int reqType = Integer.parseInt(request.getParameter("reqType"));
		String returnVal = " ";

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
        
		switch(reqType){
		
			case 0:
				loginResponse soff = new loginResponse("", "" ,"false");
				String json2Send;
				if(cookies == null) {
					json2Send = gson.toJson(soff);
				}
				
				else {
					soff.setConfirm("true");
					json2Send = gson.toJson(soff);
				}
					response.getWriter().print(json2Send);
				break;

			case 1://Integer amount, String desc, String url
				returnVal = alterTransaction.process(cookies[0],request.getParameter("reimb_amount"), request.getParameter("reimb_description"), request.getParameter("reimb_recept"));
				response.getWriter().print(returnVal);
				break;
				
			case 2://Cookie cookie, Integer id, String status

				System.out.println("its getting here 22 ");
				returnVal = alterTransaction.process(request.getParameter("reimb_id"), request.getParameter("reimb_status"));
				response.getWriter().print(returnVal);
				break;
				
			default:
				break;
		}
		
	}

}
