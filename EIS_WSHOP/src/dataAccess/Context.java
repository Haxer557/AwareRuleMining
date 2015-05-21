package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import logic.Configuration;

public class Context {
	public static Connection getConnection() {
		String myDriver = "org.gjt.mm.mysql.Driver";
	    try {
			Class.forName(myDriver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			return DriverManager.getConnection(
					Configuration.getInstance().databaseUrl, 
					Configuration.getInstance().username, 
					Configuration.getInstance().password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
