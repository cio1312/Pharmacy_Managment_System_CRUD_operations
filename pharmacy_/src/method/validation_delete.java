package method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class validation_delete {

	static	Connection con = null;
	static	boolean a = false;
	public boolean check(String medicin_id) {
		
		String get ="Select * from medicin where medicin_id=?";
		
		try {
			 System.out.println("inside validation_ medicin  form");
			
	 		Class.forName("oracle.jdbc.driver.OracleDriver");
			con=  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			System.out.println("connection established successfully");

			PreparedStatement pr = con.prepareStatement(get);
			pr.setString(1, medicin_id);
			ResultSet rs = pr.executeQuery();
			a=rs.next();
			
			if(a==true) {
				
				String delete = "DELETE FROM medicin WHERE medicin_id=?";				
				PreparedStatement pt = con.prepareStatement(delete);			
				pt.setString(1, medicin_id);
				pt.executeUpdate();
			}else {
				a=false;
			}
		
		}catch(Exception e) {
	        e.printStackTrace();
	    }
			
		return a;
	
}
}
