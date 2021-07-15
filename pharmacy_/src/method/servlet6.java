package method;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class servlet6 extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
		PrintWriter pr = response.getWriter();	
		   HttpSession sh = request.getSession(false);
		   String fnm = (String) sh.getAttribute("safe_key"); 
		   System.out.println(fnm);
		   if(sh!=null) {
		   
		   String safe_key_from_prev_servlet = sh.getAttribute("safe_key").toString();
		   
		   System.out.println(safe_key_from_prev_servlet);
		   
	
		   if(safe_key_from_prev_servlet.equals(fnm)) { 
			   
			  
		
		String medicin_id = request.getParameter("medicin_id");
		String title = request.getParameter("title");
		String qty = request.getParameter("qty");
		String unit_price = request.getParameter("unit_price");
	
	
		
		try {
			
			validation_update vu = new validation_update();
			
			if(vu.check(medicin_id,title,qty,unit_price)) {
				
				 String st="Medicine Details Updated Successfully" ;
					response.setContentType("text/html");
					 pr.println("<html><head>"+"<p>"+st+"</p>"+"</head></html>");
							RequestDispatcher rs = request.getRequestDispatcher("/update_6.html");   // redirect the page
							rs.include(request, response); 
		
				
			}else {
							
				 String st="Medicine ID Not Exist" ;
					response.setContentType("text/html");
					 pr.println("<html><head>"+"<p>"+st+"</p>"+"</head></html>");
							RequestDispatcher rs = request.getRequestDispatcher("/update_6.html");   // redirect the page
							rs.include(request, response); 
				
				
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
						
		}
	}
		   }else
	{	
		   pr.println("session expired");
		   RequestDispatcher rd = request.getRequestDispatcher("/login_1.html");
			rd.forward(request, response);
     }
		   }
	}

