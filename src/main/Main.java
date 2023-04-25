package main;

import java.sql.SQLException;

import classes.Trainer;
import controller.LogeableDBimplementation;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		LogeableDBimplementation pepe = new LogeableDBimplementation();
		Trainer pepe2= null;
		
		pepe2 = pepe.getPerson("Ash", "abcd*1234");
		System.out.println(pepe2);
		
	}

}
