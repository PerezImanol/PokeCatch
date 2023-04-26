package interfaces;

import java.util.LinkedHashSet;

import classes.MyException;
import classes.Professor;
import classes.Trainer;

public interface AccountManageable {

    public void addTrainer(Trainer trainer) throws MyException;
    public void modifyTrainer(Trainer trainer) throws MyException;
    public void deleteTrainer(int trainerID) throws MyException;
    public void upgradeToProfessor(Professor professor) throws MyException;
    public void givePokeballs(Trainer trainer) throws MyException;
    public LinkedHashSet<Trainer> getTrainers() throws MyException;
}
