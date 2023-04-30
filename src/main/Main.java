package main;

import java.util.LinkedHashSet;

import classes.MyException;
import classes.Trainer;
import controller.AccountManageableDBimplementation;

public class Main {

	public static void main(String[] args) throws MyException {

		AccountManageableDBimplementation pepe = new AccountManageableDBimplementation();
		LinkedHashSet<Trainer> pepe2 = null;

		try {
			pepe2 = pepe.getTrainers();
		} catch (MyException e) {
		}
		System.out.println(pepe2);

	}

}
