package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import classes.MyException;

public class OpenCloseConnection {
	private ResourceBundle configFile;
	private String url;
	private String user;
	private String pass;

	// CONSTRUCTOR
	public OpenCloseConnection() {
		configFile = ResourceBundle.getBundle("resources.Config");
		url = configFile.getString("URL");
		user = configFile.getString("USER");
		pass = configFile.getString("PASSWORD");
	}

	public Connection openConnection() throws MyException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pass);

		} catch (SQLException e) {
			String error = "Error connecting to the database";
			MyException er = new MyException(error);
			throw er;

		}
		return con;
	}

	public void closeConnection(PreparedStatement stmt, Connection con) throws MyException {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				String error = "Error closing statement";
				MyException er = new MyException(error);
				throw er;
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				String error = "Error closing the flux to the database";
				MyException er = new MyException(error);
				throw er;
			}
		}
	}
}
