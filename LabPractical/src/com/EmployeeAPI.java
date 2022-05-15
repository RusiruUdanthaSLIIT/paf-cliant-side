package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class EmployeeAPI
 */
@WebServlet("/EmployeeAPI")
public class EmployeeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Employee EmployeeObj = new Employee();



    /**
     * Default constructor. 
     */
    public EmployeeAPI() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = EmployeeObj.insertEmployee(request.getParameter("Employeeid"),
				request.getParameter("Employee_Name"),
				request.getParameter("Salary"),
				request.getParameter("Contact"),
				request.getParameter("Adress"),
				request.getParameter("Nic"));
				
		response.getWriter().write(output);


		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<?, ?> paras = getParasMap(request);
		String output = EmployeeObj.updateEmployee(paras.get("hidEmployee_IDSave").toString(),
				paras.get("Employee_ID").toString(),
				paras.get("Employee_name").toString(),
				paras.get("Salary").toString(),
				paras.get("Contact").toString(),
				paras.get("Adress").toString(),
				paras.get("Nic").toString());
				response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<?, ?> paras = getParasMap(request);
		String output = EmployeeObj.deleteEmployee(paras.get("Employee_ID").toString());
		
		response.getWriter().write(output);
	} 
	private static Map<String, String> getParasMap(HttpServletRequest request)
	{
	Map<String, String> map = new HashMap<String, String>();
	try
	{
	Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	String queryString = scanner.hasNext() ?
	scanner.useDelimiter("\\A").next() : "";
	scanner.close();
	String[] params = queryString.split("&");
	for (String param : params)
	{

	String[] p = param.split("=");
	map.put(p[0], p[1]);
	}
	}
	catch (Exception e)
	{
	}
	return map;
	}



	

}