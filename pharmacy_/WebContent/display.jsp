<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<html>
<head>
</head>
<body>

<%


	String driverName = "oracle.jdbc.driver.OracleDriver";
	String connectionUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	Class.forName(driverName);
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
	try {
		connection = DriverManager.getConnection(connectionUrl,"scott","tiger");
		System.out.println("connection established successfully");
		statement = connection.createStatement();
		String query="select * from medicin";

		rs = statement.executeQuery(query);
		
		

		
%>


<TABLE cellpadding="15" border="1" style="background-color: #ffffcc;">
<%
while (rs.next()) {
%>
<TR>
<TD><%=rs.getString(1)%></TD>
<TD><%=rs.getString(2)%></TD>
<TD><%=rs.getString(3)%></TD>
<TD><%=rs.getString(4)%></TD>
</TR>
<% } %>
<%

rs.close();
statement.close();
connection.close();
	} catch (Exception e) {
		e.printStackTrace();

		
%>
</font>
<font size="+3" color="red"></b>
<%
out.println("Unable to connect to database.");
	}
%>
</TABLE><TABLE>
<TR>
<TD><FORM ACTION="homepage_4.html" method="get" >
<button type="submit">Home</button></TD>

</TR>
</TABLE>
</font>
</body>
</html>