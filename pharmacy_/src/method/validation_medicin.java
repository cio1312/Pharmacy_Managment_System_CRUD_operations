package method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class validation_medicin {

	static	Connection con = null;
	static	boolean a = false;
	public boolean check(String medicin_id, String title , String qty,String unit_price ) {
		
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
			
			if(a==false) {
				
				String put = "insert into medicin (medicin_id,title,qty,unit_price) values(?,?,?,?)";
				
				PreparedStatement pt = con.prepareStatement(put);
								
				pt.setString(1, medicin_id);
				pt.setString(2, title);
				pt.setString(3, qty);
				pt.setString(4, unit_price);
				pt.executeUpdate();
			}else {
				a=true;
			}
		
		}catch(Exception e) {
	        e.printStackTrace();
	    }
			
		return a;
	
}
}
