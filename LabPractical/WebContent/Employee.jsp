<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
 <%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.cj.jdbc.Driver"); %>
 
<%@page import="com.Employee"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee managment</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/employee.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Employee Management </h1>
<form id="formemployee" name="formemployee">
 Employee id:
 <input id="Employeeid" name="Employeeid" type="text"
 class="form-control form-control-sm">
 <br> Employee name:
 <input id="Employee_Name" name="Employee_Name" type="text"
 class="form-control form-control-sm">
 <br> Salary:
 <input id="Salary" name="Salary" type="text"
 class="form-control form-control-sm">
 <br>  Contact:
 <input id="Contact" name="Contatct" type="text"
 class="form-control form-control-sm">
 <br> Adress :
 <input id="Adress" name="Adress" type="text"
 class="form-control form-control-sm">
 <br> Nic :
 <input id="Nic" name="Nic" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidEmployeeIDSave"
 name="hidEmployeeIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divEmployeeGrid">
 <%
 Employee employeeObj = new Employee();
 out.print(employeeObj.readEmployee());
 %>
</div>
</div> </div> </div>
</body>
</html>