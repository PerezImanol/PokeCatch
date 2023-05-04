package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.Login;
import classes.MyException;
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
	public void addTrainer(Trainer trainer) throws MyException {

		query = "insert into Trainer (trainer_name, birthdate, gender, city, badges, pokeball) values (?, ?, ?, ?, ?, ?)";
		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, trainer.getName());
			stmt.setDate(2, trainer.getBirthdate());
			stmt.setString(3, trainer.getGender());
			stmt.setString(4, trainer.getOriginCity());
			stmt.setInt(5, trainer.getBadges());
			stmt.setInt(6, trainer.getPokeballs());
			stmt.executeUpdate();

		} catch (SQLException e) {
			String error = "Error inserting data to the database";
			MyException er = new MyException(error);
			throw er;
		}
		occ.closeConnection(stmt, con);
	}

	@Override
	public void deleteTrainer(int trainerID) throws MyException {
		query = "delete from Trainer where trainer_id = ?";

		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, trainerID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			String error = "Error executing deleting from database";
			MyException er = new MyException(error);
			throw er;
		}
		occ.closeConnection(stmt, con);
	}

	public LinkedHashSet<Trainer> getTrainers() throws MyException {

		LinkedHashSet<Trainer> trainers = new LinkedHashSet<>();

		ResultSet rst;
		ResultSet rsp;
		ResultSet rsc;
		Trainer t = null;
		query = "SELECT * FROM Trainer WHERE trainer_id NOT IN (SELECT professor_id FROM Professor)";
		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(query);
			rst = stmt.executeQuery();

			while (rst.next()) {
				LinkedHashSet<Pokemon> pok = new LinkedHashSet<>();
				LinkedHashSet<Combat> com = new LinkedHashSet<>();
				int id = 0;
				t = new Trainer();
				id = rst.getInt("trainer_id");
				t.setTrainerID(id);
				t.setName(rst.getString("trainer_name"));
				t.setGender(rst.getString("gender"));
				t.setBirthdate(rst.getDate("birthdate"));
				t.setOriginCity(rst.getString("city"));
				t.setBadges(rst.getInt("badges"));
				t.setPokeballs(rst.getInt("pokeball"));

				try {
					query = "Select pokedex_id, region, pokemon_name, nickname, type1, type2, pokemon_lvl, location from Pokemon_static join Pokemon on pokemon_id=pokedex_id where trainer_id=?";
					stmt = con.prepareStatement(query);
					stmt.setInt(1, id);
					rsp = stmt.executeQuery();
					while (rsp.next()) {

						Pokemon p = null;
						p = new Pokemon();
						p.setPokedexID(rsp.getInt("pokedex_id"));
						p.setRegion(rsp.getString("region"));
						p.setName(rsp.getString("pokemon_name"));
						p.setNickname(rsp.getString("nickname"));
						p.setType1(rsp.getString("type1"));
						p.setType2(rsp.getString("type2"));
						p.setLevel(rsp.getInt("pokemon_lvl"));
						p.setTeam(rsp.getBoolean("location"));
						pok.add(p);

					}
					t.setTeam(pok);
				} catch (SQLException e) {
					String error = "Error getting trainer's pokemon team";
					MyException er = new MyException(error);
					throw er;
				}
				try {
					query = "Select trainer_id1, trainer_id2, winner from Combat where trainer_id1=? or trainer_id2=?";
					stmt = con.prepareStatement(query);
					stmt.setInt(1, id);
					stmt.setInt(2, id);
					rsc = stmt.executeQuery();

					while (rsc.next()) {
						Combat c = new Combat();
						c.setTrainer1(rsc.getInt("trainer_id1"));
						c.setTrainer2(rsc.getInt("trainer_id2"));
						c.setWinnerTrainerID(rsc.getInt("winner"));
						com.add(c);

					}
					t.setCombatHistory(com);
					trainers.add(t);

				} catch (SQLException e) {
					String error = "Error getting trainer's combat history";
					MyException er = new MyException(error);
					throw er;
				}
			}
		} catch (SQLException e) {
			String error = "Error getting trainers";
			MyException er = new MyException(error);
			throw er;
		}

		occ.closeConnection(stmt, con);
		return trainers;

	}

	@Override
	public void givePokeballs(Trainer trainer) throws MyException {
		query = "UPDATE Trainer SET pokeballs = ? WHERE trainer_id = ?";

		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, trainer.getPokeballs());
			stmt.setInt(2, trainer.getTrainerID());
			stmt.executeUpdate();
		} catch (SQLException e) {

			String error = "Error updating the pokeball amount";
			MyException er = new MyException(error);
			throw er;
		}

		occ.closeConnection(stmt, con);
	}

	@Override
	public void modifyTrainer(Trainer trainer) throws MyException {
		query = "update Trainer set trainer_name = ?, birthdate = ?, gender = ?, city = ?, badges = ?, pokeball = ? WHERE trainer_id = ?";

		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, trainer.getName());
			stmt.setDate(2, trainer.getBirthdate());
			stmt.setString(3, trainer.getGender());
			stmt.setString(4, trainer.getOriginCity());
			stmt.setInt(5, trainer.getBadges());
			stmt.setInt(6, trainer.getPokeballs());
			stmt.setInt(7, trainer.getTrainerID());

			int id = stmt.executeUpdate();
			if (id == 0) {
				MyException er = new MyException("Nothing updated");
				throw er;
			} else if (id == 1) {
				MyException success = new MyException("Trainer updated successfully");
				throw success;
			}
		} catch (SQLException e) {

			String error = "Error modifying trainer's data";
			MyException er = new MyException(error);
			throw er;
		}

		occ.closeConnection(stmt, con);

	}

	@Override
	public void upgradeToProfessor(Professor professor) throws MyException {
		query = "insert into Professor (professor_id, region) values (?, ?)";
		ArrayList<Pokemon> aux = professor.getInitialSelection();

		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, professor.getTrainerID());
			stmt.setString(2, professor.getRegion());
			stmt.executeUpdate();
		} catch (SQLException e) {
			String error = "Error creating new professor";
			MyException er = new MyException(error);
			throw er;
		}
		try {
			query = "call upgradeToProfessor(?, ?, ?, ?)";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, professor.getTrainerID());
			stmt.setInt(2, aux.get(0).getPokedexID());
			stmt.setInt(3, aux.get(1).getPokedexID());
			stmt.setInt(4, aux.get(2).getPokedexID());
			int id = stmt.executeUpdate();
			if (id >= 1) {
				MyException er = new MyException("Trainer upgraded successfully");
				throw er;
			}
		} catch (SQLException e) {
			String error = e.getMessage();
			MyException er = new MyException(error);
			throw er;
		}

		occ.closeConnection(stmt, con);
	}

	@Override
	public void setLogin(Login log) throws MyException {
		query = "select trainer_id from Trainer order by trainer_id desc limit 1";

		con = occ.openConnection();
		int id = -1;

		try {
			stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("trainer_id");
			}

			query = "insert into Login(user_id, username, passwd) values (?, ?, ?)";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.setString(2, log.getUsername());
			stmt.setString(3, log.getPassword());
			id = stmt.executeUpdate();
			if (id == 1) {
				MyException message = new MyException("Trainer added successfully");
				throw message;
			}
		} catch (SQLException e) {
			String error = "Error setting the login information";
			MyException er = new MyException(error);
			throw er;
		}

	}
}
