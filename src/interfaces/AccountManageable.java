package interfaces;

import java.sql.SQLException;
import java.util.LinkedHashSet;

import classes.Professor;
import classes.Trainer;

public interface AccountManageable {

    public void addTrainer(Trainer trainer) throws SQLException;
    public void modifyTrainer(Trainer trainer) throws SQLException;
    public void deleteTrainer(int trainerID) throws SQLException;
    public void upgradeToProfessor(Professor professor) throws SQLException;
    public void givePokeballs(Trainer trainer) throws SQLException;
    public LinkedHashSet<Trainer> getTrainers() throws SQLException;
}
