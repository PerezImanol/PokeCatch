package main;

import java.sql.SQLException;

import classes.Trainer;
import controller.LogeableDBimplementation;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stu
		
		LogeableDBimplementation pepe = new LogeableDBimplementation();
		Trainer pepe2 = new Trainer();
		
		pepe2 = pepe.isUser("Epeldex", "abcd");
		System.out.println(pepe2);
		

	}

}
