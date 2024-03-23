<%@page import="java.util.Date"%>
<%@page import="javax.xml.crypto.Data"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>footer</title>

<style type="text/css">
*{
  margin:0;
    padding: 0;
    box-sizing: border-box;
    font-family: sans-serif;
}
.contain{
height: 36px;
padding: 1px 0px 0px;
background: none repeat scroll 0% 0% #A3A3A3 ;
border-top: 1px solid #1885B0;
font-family: cursive;
margin-bottom:2px;
width: 100%;
font-size:20px;
color: black;
margin-left: 0px;
margin-right: 3px;
}

</style>
</head>
<body>
<center>
<br><br>
	<div class="contain fixed-bottom">
		Copyright &copy; 2000-<%= new Date().getYear()+1900 %> by Gym Management Pvt.Ltd.
	</div>
</center>
</body>
</html>