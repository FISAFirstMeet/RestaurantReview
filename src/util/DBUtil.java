package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	private static Properties p = new Properties();
	
	static {
		try {
			p.load(new FileInputStream("db.properties"));
			Class.forName(p.getProperty("db.driver"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(p.getProperty("db.url"), 
											p.getProperty("db.id"),
											p.getProperty("db.pw"));
	}
	
	public static void close(Connection con, Statement stmt) {
		try {
			if (con != null) {
				con.close();
				con = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public static void close(Connection con, Statement stmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
				rset = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
}
