package interfaces;

import java.sql.SQLException;

import classes.Professor;
import classes.Trainer;

public interface Logeable {

    public int isUser(String username, String password) throws SQLException;

	public Professor getProfessor(int id) throws SQLException;
	
	public Trainer getTrainer(int id) throws SQLException;

	public boolean isProfessor(int id) throws SQLException;


}
