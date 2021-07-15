package method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class validation_update {
	
	static boolean a =false;
	static Connection con = null;
	
	public boolean  check(String medicin_id, String title , String qty,String unit_price) {
	
		String get = "select * from medicin where medicin_id=?";
		
		
		try {
			
			 System.out.println("inside validation_ update  form");
			 
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
				System.out.println("connection established successfully");
			
			PreparedStatement ps = con.prepareStatement(get);
			ps.setString(1,medicin_id);
			ResultSet rs =ps.executeQuery();
			a =rs.next();                            // when medicine exist then it returns true
			
	if(a==true) {
				
				String update = "UPDATE medicin SET title=? , qty=?,unit_price=? where medicin_id=?";
				
				PreparedStatement pt = con.prepareStatement(update);
								
				pt.setString(1, title);
				pt.setString(2, qty);
				pt.setString(3, unit_price);
				pt.setString(4, medicin_id);
				pt.executeUpdate();
			}else {
				a=false;
			}
	
			
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
	return a;
		
		
		
		
		
		
	}

}
