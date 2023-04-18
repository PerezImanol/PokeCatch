package interfaces;

import java.util.LinkedHashSet;

import classes.Combat;
import classes.Professor;
import classes.Trainer;

public interface AccountManageable {

    public void addTrainer(Trainer trainer);
    public void modifyTrainer(Trainer trainer);
    public void deleteTrainer(Trainer trainer);
    public void upgradeToProfessor(Professor professor);
    public void givePokeballs(Trainer trainer);
    public LinkedHashSet<Combat> listCombats(Integer trainerID);
    public LinkedHashSet<Trainer> getTrainers();
}
