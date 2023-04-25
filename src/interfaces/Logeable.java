package interfaces;

import java.sql.SQLException;

import classes.Trainer;

public interface Logeable {

	public Trainer getPerson(String username, String password) throws SQLException;


}
