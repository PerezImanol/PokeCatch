package interfaces;


import classes.MyException;
import classes.Trainer;

public interface Logeable {

	public Trainer getPerson(String username, String password) throws MyException;


}
