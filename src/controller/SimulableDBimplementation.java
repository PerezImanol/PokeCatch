package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.MyException;
import classes.Pokemon;
import classes.Trainer;
import interfaces.Simulable;

public class SimulableDBimplementation implements Simulable {

	private Connection con;
	private PreparedStatement stmt;
	private OpenCloseConnection occ = new OpenCloseConnection();

	@Override
	public void addCaughtPokemons(Pokemon pokemon, int trainerID) throws MyException {
		final String queryCatch = "INSERT INTO Pokemon(pokemon_id, trainer_id, pokemon_lvl, nickname, location) VALUES( ?, ?, ?, ?, ?)";

		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(queryCatch);
			stmt.setInt(1, pokemon.getPokedexID());
			stmt.setInt(2, trainerID);
			stmt.setInt(3, pokemon.getLevel());
			stmt.setString(4, pokemon.getNickname());
			stmt.setBoolean(5, pokemon.isTeam());
			stmt.executeUpdate();

		} catch (SQLException e) {
			String error = "Error adding caught pokemon";
			MyException er = new MyException(error);
			throw er;
		}

		occ.closeConnection(stmt, con);
	}

	@Override
	public void updateCombatHistory(Combat combat) throws MyException {
		final String insertBattleData = "INSERT VALUES INTO Combat(trainer_id1, trainer_id2, winner) VALUES (?, ?, ?)";

		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(insertBattleData);
			stmt.setInt(1, combat.getTrainer1());
			stmt.setInt(2, combat.getTrainer2());
			stmt.setInt(3, combat.getWinnerTrainerID());
			stmt.executeUpdate();

		} catch (SQLException e) {
			String error = "Error updating combat history";
			MyException er = new MyException(error);
			throw er;
		}

		occ.closeConnection(stmt, con);
	}

	@Override
	public LinkedHashSet<Pokemon> getTeamPokemons(Trainer trainer) throws MyException {
		ResultSet rs = null;
		LinkedHashSet<Pokemon> pokemons = new LinkedHashSet<>();
		final String queryPokemons = "select * from Pokemon where location = true and trainer_id = ?";

		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(queryPokemons);
			stmt.setInt(1, trainer.getTrainerID());
			rs = stmt.executeQuery();

			while (rs.next()) {
				Pokemon p = new Pokemon();
				p.setPokedexID(rs.getInt("pokedex_id"));
				p.setRegion(rs.getString("region"));
				p.setName(rs.getString("pokemon_name"));
				p.setNickname(rs.getString("nickname"));
				p.setType1(rs.getString("type1"));
				p.setType2(rs.getString("type2"));
				p.setLevel(rs.getInt("pokemon_lvl"));
				pokemons.add(p);
			}
		} catch (SQLException e) {
			String error = "Error getting team pokemons";
			MyException er = new MyException(error);
			throw er;
		}
		occ.closeConnection(stmt, con);

		return pokemons;
	}

	@Override
	public void changePosition(Trainer trainer, Pokemon pokemon) throws MyException {
		final String queryUpdateTeam = "UPDATE Pokemon SET location = ? WHERE trainer_id = ? AND pokemon_id = ?";

		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(queryUpdateTeam);
			stmt.setBoolean(1, !pokemon.isTeam());
			stmt.setInt(2, trainer.getTrainerID());
			stmt.setInt(3, pokemon.getPokedexID());
			stmt.executeUpdate();

		} catch (SQLException e) {
			String error = "Error getting professor";
			MyException er = new MyException(error);
			throw er;
		}

		occ.closeConnection(stmt, con);
	}

	@Override
	public void switchPosition(Trainer trainer, Pokemon pokemon1, Pokemon pokemon2) throws MyException {
		final String switchQuery = "UPDATE Pokemon SET location = ? WHERE trainer_id = ? AND pokemon_id = ?";
		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(switchQuery);
			stmt.setBoolean(1, !pokemon1.isTeam());
			stmt.setInt(2, trainer.getTrainerID());
			stmt.setInt(3, pokemon1.getPokedexID());
			stmt.executeUpdate();

			stmt = con.prepareStatement(switchQuery);
			stmt.setBoolean(1, !pokemon2.isTeam());
			stmt.setInt(2, trainer.getTrainerID());
			stmt.setInt(3, pokemon2.getPokedexID());
			int flag = stmt.executeUpdate();
			if (flag > 0){
				MyException message = new MyException("Pokemons switched successfully");
				throw message;
			}
		} catch (SQLException e) {
			String error = "Error swithing positions";
			MyException er = new MyException(error);
			throw er;
		}

		occ.closeConnection(stmt, con);
	}
}