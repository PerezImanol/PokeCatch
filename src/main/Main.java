package main;

import classes.MyException;
import classes.Trainer;
import controller.LogeableDBimplementation;

public class Main {

	public static void main(String[] args) throws MyException {

		LogeableDBimplementation pepe = new LogeableDBimplementation();
		Trainer pepe2 = null;

		try {
			pepe2 = pepe.getPerson("Oak", "abcd*1234");
		} catch (MyException e) {
		}
		System.out.println(pepe2);

	}

}
