package method;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;



public class servlet5 extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  HttpSession sh = request.getSession(false);  	
		  
		  String fnm = (String) sh.getAttribute("safe_key"); 
	
			System.out.println("username= "+fnm);
 
          if(sh!=null) {
        	  String safe_key_from_prev_servlet = (String)sh.getAttribute("safe_key"); 
     		 
   		   System.out.println(safe_key_from_prev_servlet);
		   if(safe_key_from_prev_servlet.equals(fnm)) { 
			   
			   
		
		String medicin_id = request.getParameter("medicin_id");
		String title = request.getParameter("title");
		String qty = request.getParameter("qty");
		String unit_price = request.getParameter("unit_price");
		
		PrintWriter pr = response.getWriter();
		
		try                        // always inside try catch
		{		

			validation_medicin vm = new validation_medicin();                    // create object and call java class
			
			 if(vm.check(medicin_id,title,qty,unit_price))
			 {
				 String st="Medicin already exist";
					response.setContentType("text/html");
					 pr.println("<html><head>"+"<p>"+st+"</p>"+"</head></html>");
							RequestDispatcher rs = request.getRequestDispatcher("/add_5.html");   // redirect the page
							rs.include(request, response); 
			 }
			 else{
				 
                  String st="Medicine added successfully";             
          		response.setContentType("text/html");
			 pr.println("<html><head>"+"<p>"+st+"</p>"+"</head></html>");
					RequestDispatcher rs = request.getRequestDispatcher("/add_5.html");   // redirect the page
					rs.include(request, response); 
			 }
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}}
		   else {
				PrintWriter pr = response.getWriter();
			   pr.println("session expired");
			   response.sendRedirect("./login_1.html"); 
	}
		
	}

}
