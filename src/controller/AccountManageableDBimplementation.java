package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.Professor;
import classes.Trainer;
import interfaces.AccountManageable;

public class AccountManageableDBimplementation implements AccountManageable {

    private Connection con;
    private PreparedStatement stmt;
    private OpenCloseConnection occ = new OpenCloseConnection();
    private String query = null;

    @Override
    public void addTrainer(Trainer trainer) throws SQLException {

        query = "call addTrainer(?, ?, ?, ?, ?)";
        try {
            con = occ.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stmt = con.prepareStatement(query);
        stmt.setString(1, trainer.getName());
        stmt.setDate(2, trainer.getAge());
        stmt.setString(3, trainer.getGender());
        stmt.setString(4, trainer.getOriginCity());
        stmt.setInt(5, trainer.getBadges());
        stmt.executeUpdate();
    }

    @Override
    public void deleteTrainer(int trainerID) throws SQLException {
        query = "call deleteTrainer(?)";

        try {
            con = occ.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stmt = con.prepareStatement(query);
        stmt.setInt(1, trainerID);
        stmt.executeUpdate();
    }

    @Override
    public LinkedHashSet<Trainer> getTrainers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void givePokeballs(Trainer trainer) {
        // TODO Auto-generated method stub

    }

    @Override
    public LinkedHashSet<Combat> listCombats(Integer trainerID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void modifyTrainer(Trainer trainer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void upgradeToProfessor(Professor professor) {
        // TODO Auto-generated method stub

    }

}
