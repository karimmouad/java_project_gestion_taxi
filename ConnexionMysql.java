package appTransport;
import java.sql.DriverManager;

import javax.swing.*;
import com.mysql.jdbc.Connection;
public class ConnexionMysql {
	Connection conn = null;
public static Connection ConnectDb () {
	try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/gestiontaxi","root","");
	return conn;
	}catch(Exception e) {
		JOptionPane.showMessageDialog(null, e);
		return null;
	} 
}
}
