package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Trainer;
import interfaces.Logeable;

public class LogeableDBimplementation implements Logeable {

	private Connection con;
	private PreparedStatement stmt;
	private OpenCloseConnection occ = new OpenCloseConnection();
	final String QueryUsers = "Select user_id from Login where username=? and passwd=?";

	@Override
	public Trainer isUser(String username, String password) throws SQLException {
		
		Trainer t = null;
		ResultSet rs;
		ResultSet rti = null;

		try {
			con = occ.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		stmt = con.prepareStatement(QueryUsers);
		stmt.setString(1, username);
		stmt.setString(2, password);
		rs = stmt.executeQuery();

		if (rs != null) {
			rs.next();
			int id = rs.getInt("user_id");
			final String QueryInfo = "Select trainer_name, age, gender, city, badges from Trainer where trainer_id = ?";
			stmt = con.prepareStatement(QueryInfo);
			stmt.setInt(1, id);
			rti = stmt.executeQuery();
			if (rti.next())
				
		}
		return t;
	}
}
