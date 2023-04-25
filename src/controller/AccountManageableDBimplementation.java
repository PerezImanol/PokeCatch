package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.Pokemon;
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

        query = "insert into Trainer (trainer_name, age, gender, city, badges, pokeballs) values (?, ?, ?, ?, ?, ?)";
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
        stmt.setInt(6, trainer.getPokeballs());
        stmt.executeUpdate();

        occ.closeConnection(stmt, con);
    }

    @Override
    public void deleteTrainer(int trainerID) throws SQLException {
        query = "delete from Trainer where trainer_id = ?";

        try {
            con = occ.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stmt = con.prepareStatement(query);
        stmt.setInt(1, trainerID);
        stmt.executeUpdate();

        occ.closeConnection(stmt, con);
    }

    public LinkedHashSet<Trainer> getTrainers() throws SQLException {
        LinkedHashSet<Trainer> trainers = new LinkedHashSet<>();
        ResultSet rst;
        ResultSet rsp;
        ResultSet rsc;
        query = "SELECT * FROM Trainer WHERE trainer_id NOT IN (SELECT professor_id FROM Professor)";

        try {
            con = occ.openConnection();
        } catch (Exception e) {
        }
        stmt = con.prepareStatement(query);
        rst = stmt.executeQuery();

        while (rst.next()) {
            LinkedHashSet<Pokemon> pok = new LinkedHashSet<>();
            LinkedHashSet<Combat> com = new LinkedHashSet<>();
            int id = rst.getInt("trainer_id");
            Trainer t = new Trainer();
            t.setTrainerID(id);
            t.setName(rst.getString("trainer_name"));
            t.setGender(rst.getString("gender"));
            t.setAge(rst.getDate("age"));
            t.setOriginCity(rst.getString("city"));
            t.setBadges(rst.getInt("badges"));
            query = "Select pokedex_id, region, pokemon_name, nickname, type1, type2, pokemon_lvl from Pokemon_static join Pokemon on pokemon_id=pokedex_id where trainer_id=? and location = 0";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            rsp = stmt.executeQuery();

            while (rsp.next()) {
                Pokemon p = new Pokemon();
                p.setPokedexID(rsp.getInt("pokedex_id"));
                p.setRegion(rsp.getString("region"));
                p.setName(rsp.getString("pokemon_name"));
                p.setNickname(rsp.getString("nickname"));
                p.setType1(rsp.getString("type1"));
                p.setType2(rsp.getString("type2"));
                p.setLevel(rsp.getInt("pokemon_lvl"));
                pok.add(p);
            }
            query = "Select trainer_id1, trainer_id2, winner from combat where trainer_id1=? or trainer_id2=?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            rsc = stmt.executeQuery();

            while (rsc.next()) {
                Combat c = new Combat();
                c.setTrainer1(rsc.getInt("trainer_id1"));
                c.setTrainer2(rsc.getInt("trainer_id1"));
                c.setWinnerTrainerID(rsc.getInt("winner"));
                com.add(c);
            }
            trainers.add(t);
        }
        occ.closeConnection(stmt, con);

        return trainers;
    }

    @Override
    public void givePokeballs(Trainer trainer) throws SQLException {
        query =  "UPDATE Trainer SET pokeballs = ? WHERE trainer_id = ?";
        try {
            con = occ.openConnection();
        } catch (Exception e) {
        }

        stmt = con.prepareStatement(query);
        stmt.setInt(1, trainer.getPokeballs());
        stmt.setInt(2, trainer.getTrainerID());
        stmt.executeUpdate();

        occ.closeConnection(stmt, con);
    }

    @Override
    public void modifyTrainer(Trainer trainer) throws SQLException {
        query = "UPDATE Trainer SET trainer_name = ?, age = ?, gender = ?, city = ?, badges = ?, pokeballs = ? WHERE trainer_id = ?";

        try {
            con = occ.openConnection();
        } catch (Exception e) {
        }

        stmt = con.prepareStatement(query);
        stmt.setString(1, trainer.getName());
        stmt.setDate(2, trainer.getAge());
        stmt.setString(3, trainer.getGender());
        stmt.setString(4, trainer.getOriginCity());
        stmt.setInt(5, trainer.getBadges());
        stmt.setInt(6, trainer.getPokeballs());
        stmt.setInt(7, trainer.getTrainerID());
        stmt.executeUpdate();

        occ.closeConnection(stmt, con);
    }

    @Override
    public void upgradeToProfessor(Professor professor) throws SQLException{
        query = "insert into Professor (professor_id, region) values (?, ?)";
        ArrayList <Pokemon> aux = professor.getInitialSelection();

        try {
            con = occ.openConnection();
        } catch (Exception e) {
        }

        stmt = con.prepareStatement(query);
        stmt.setInt(1, professor.getTrainerID());
        stmt.setString(2, professor.getRegion());
        stmt.executeUpdate();

        query = "call upgradeToProfessor(?, ?, ?, ?)";
        stmt.setInt(1, professor.getTrainerID());
        stmt.setInt(2, aux.get(0).getPokedexID());
        stmt.setInt(3, aux.get(1).getPokedexID());
        stmt.setInt(4, aux.get(2).getPokedexID());
        stmt.executeUpdate();

        occ.closeConnection(stmt, con);
    }

}
