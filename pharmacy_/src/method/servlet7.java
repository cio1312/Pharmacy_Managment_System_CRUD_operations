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

public class servlet7 extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String medicin_id = request.getParameter("medicin_id");
	
		PrintWriter pr = response.getWriter();
		   HttpSession sh = request.getSession(false); 
		   String fnm = (String) sh.getAttribute("safe_key");
			System.out.println(fnm);
		  	   
		   if(sh!=null) {
			   
		   
		   String safe_key_from_prev_servlet = (String)sh.getAttribute("safe_key");
		   
		
		   if(safe_key_from_prev_servlet.equals(fnm)) { 
		
		try {
			
			validation_delete vd = new validation_delete();
			
			if(vd.check(medicin_id)) {
				
				 String st="Medicine Deleted Successfully" ;

			
					response.setContentType("text/html");
					 pr.println("<html><head>"+"<p>"+st+"</p>"+"</head></html>");
							RequestDispatcher rs = request.getRequestDispatcher("/delete_7.html");   // redirect the page
							rs.include(request, response); 
		
				
			}else {
							
				 String st="Medicine ID Not Exist" ;
					response.setContentType("text/html");
					 pr.println("<html><head>"+"<p>"+st+"</p>"+"</head></html>");
							RequestDispatcher rs = request.getRequestDispatcher("/delete_7.html");   // redirect the page
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
