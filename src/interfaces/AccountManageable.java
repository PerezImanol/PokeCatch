package interfaces;

import java.sql.SQLException;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.Professor;
import classes.Trainer;

public interface AccountManageable {

    public void addTrainer(Trainer trainer) throws SQLException;
    public void modifyTrainer(Trainer trainer) throws SQLException;
    public void deleteTrainer(int trainerID) throws SQLException;
    public void upgradeToProfessor(Professor professor);
    public void givePokeballs(Trainer trainer);
    public LinkedHashSet<Combat> listCombats(Integer trainerID);
    public LinkedHashSet<Trainer> getTrainers();
}
