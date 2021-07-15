package method;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class servlet2 extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		 response.setContentType("text/html"); 
		 	 
		 
		String  name= request.getParameter("username1");
		String  email= request.getParameter("email1");
		String  question= request.getParameter("question1");
		String  password= request.getParameter("password1");
		String  password2= request.getParameter("password2");

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String sql="insert into customer(username,password,email,question) values(?,?,?,?)";
	
		String get="select * from customer where username=?";
		
		PrintWriter pr = response.getWriter();
		
		try
		{		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"scott","tiger");
			System.out.println("connection established successfully");
				
			boolean a;
			PreparedStatement p1 = con.prepareStatement(get);
			p1.setString(1, name);			
			ResultSet re =p1.executeQuery();
	        a = re.next();	
	         
	        if(a==true) {
	        	
	       	 String st="User Already Exist";
	       	PrintWriter out = response.getWriter();
	       	out.print("<html><head>");
	       	out.print("<script type=\"text/javascript\">alert(" + st + ");</script>");
	       	out.print("</head><body></body></html>");
			 
			 
			 RequestDispatcher rs = request.getRequestDispatcher("/registration_2.html");
				rs.forward(request, response);
	        	
	        }else
	        {
	        
			
			if(!password.equals(password2)) {
				
				 String st="Password Mismatch";
				 PrintWriter out = response.getWriter();
				 out.print("<html><head>");
				 out.print("<script type=\"text/javascript\">alert(" + st + ");</script>");
				 out.print("</head><body></body></html>");
				 
				 
				 RequestDispatcher rs = request.getRequestDispatcher("/registration_2.html");
					rs.forward(request, response);
				
			}else
			{
					
			PreparedStatement p2 = con.prepareStatement(sql);
			p2.setString(1, name);
			p2.setString(2, password);
			p2.setString(3, email);
			p2.setString(4, question);
			p2.executeUpdate();
			 String st="Registered Successfully";
			 pr.println("<html> alert("+st+")</html>");
			 
			 RequestDispatcher rs = request.getRequestDispatcher("/login_1.html");
				rs.forward(request, response);
			}
			}	
				
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	
		
	}

}
