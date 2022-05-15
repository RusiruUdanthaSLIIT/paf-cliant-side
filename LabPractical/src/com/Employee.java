package com;

import java.sql.*;

public class Employee {
	
	private Connection connect() {
		Connection con = null;
		try	{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplymanagement",
					"root", "rusiru");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public String insertEmployee(String Employee_ID, String Employee_name, String Salary, String Contact, String Adress, String Nic) { 
        String output = ""; 
        try { 
            Connection con = connect(); 
            if (con == null) {
                return "Error while connecting to the database for inserting."; 
            } 

            // create a prepared statement
            String query = " insert into employee " +
                            "(`Employee_ID`,`Employee_name`,`Salary`,`Contact`,`Adress`,`Nic`)"
                            + " values (?, ?, ?, ?, ?, ?)"; 

            PreparedStatement preparedStmt = con.prepareStatement(query); 

            // binding values 
            preparedStmt.setInt(1, Integer.parseInt(Employee_ID)); 
            preparedStmt.setString(2, Employee_name); 
            preparedStmt.setDouble(3, Double.parseDouble(Salary)); 
            preparedStmt.setString(4, Contact); 
			preparedStmt.setString(5, Adress); 
            preparedStmt.setString(6, Nic); 

            // execute the statement
            preparedStmt.execute(); 
            con.close(); 
            String newEmployee = readEmployee();
            output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}"; 

        } catch (Exception e) { 
        	
        	output = "{\"status\":\"error\", \"data\":\"Error while inserting the Employee.\"}";


            System.err.println(e.getMessage()); 
        } 
        
        return output; 
    }
	
	public String readEmployee() {
		String output = ""; 

		try { 

			Connection con = connect(); 
			if (con == null) {
				return "Error while connecting to the database for reading."; 
			} 

			// Prepare the html table to be displayed
			output = "<table border='1'><tr>"+
			"<th>Employee_ID</th>"+
			"<th>Employee_name</th>" +
			"<th>Salary</th>" + 
			"<th>Contact</th>" +
			"<th>Adress</th>" + 
			"<th>Nic</th>" +
			"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from employee"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 

			// iterate through the rows in the result set
			while (rs.next()) { 

				String Employee_ID = rs.getString("Employee_ID"); 
				String Employee_name = rs.getString("Employee_name"); 
				String Salary = rs.getString("Salary"); 
				String Contact = rs.getString("Contact"); 
				String Adress = rs.getString("Adress"); 
				String Nic = rs.getString("Nic"); 

				// Add into the html table
				/*output += "<tr><td>< id='hidEmployee_IDUpdate'name='hidEmployee_IDUpdate' type='hidden' value='" + Employee_ID + "'>"
						+ Employee_name + "</td>";*/
				output += "<tr><td>" +Employee_ID + "</td>";
				 output += "<td>" + Employee_name + "</td>"; 
			    output += "<td>" + Salary + "</td>"; 
				output += "<td>" + Contact + "</td>";
				output += "<td>" + Adress + "</td>"; 
				output += "<td>" + Nic + "</td>";				

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-supid='" + Employee_ID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-supid='" + Employee_ID + "'></td></tr>";
			} 
			con.close(); 

			// Complete the html table
			output += "</table>"; 

		} catch (Exception e) { 
			output = "Error while reading the employee."; 
			System.err.println(e.getMessage()); 
		} 

		return output; 
	}
	
	public String updateEmployee(String ID, String Employee_ID, String Employee_name, String Salary, String Contact, String Adress, String Nic) { 

        String output = ""; 

        try { 

            Connection con = connect(); 

            if (con == null) {
                return "Error while connecting to the database for updating."; 
            } 

            // create a prepared statement
            String query = "UPDATE employee SET Employee_ID=?, Employee_name=?, Salary=?, Contact=?, Adress=?, Nic=? WHERE ID=?"; 
            PreparedStatement preparedStmt = con.prepareStatement(query); 

            // binding values
            preparedStmt.setInt(1, Integer.parseInt(Employee_ID)); 
            preparedStmt.setString(2, Employee_name); 
            preparedStmt.setDouble(3, Double.parseDouble(Salary));
            preparedStmt.setString(4, Contact);  
            preparedStmt.setString(5, Adress); 
            preparedStmt.setString(6, Nic); 
            preparedStmt.setInt(7, Integer.parseInt(Employee_ID));
            // execute the statement
            preparedStmt.execute(); 
            con.close(); 
            String newEmployee = readEmployee();
            output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}"; 

        } catch (Exception e) { 
        	
        	output = "{\"status\":\"error\", \"data\":\"Error while updating the employee.\"}";

 
            System.err.println(e.getMessage()); 
        } 

        return output; 
    }
	
	public String deleteEmployee(String Employee_ID) { 

        String output = ""; 

        try { 

            Connection con = connect(); 

            if (con == null) {
                return "Error while connecting to the database for deleting."; 
            }

            // create a prepared statement
            String query = "delete from employee where ID=?"; 
            PreparedStatement preparedStmt = con.prepareStatement(query); 

            // binding values
            preparedStmt.setInt(1, Integer.parseInt(Employee_ID)); 

            // execute the statement
            preparedStmt.execute(); 
            con.close(); 
            String newEmployee = readEmployee();
            output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}"; 

        } catch (Exception e) { 
        	output = "{\"status\":\"error\", \"data\":\"Error while deleteing the employee.\"}"; 
            System.err.println(e.getMessage()); 
        } 

        return output; 
    } 
		
}


