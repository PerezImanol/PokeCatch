package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.MyException;
import classes.Pokemon;
import classes.PokemonExtra;
import interfaces.DataBattleShowable;

public class DataBattleShowableDBimplementation implements DataBattleShowable {

	private Connection con;
	private PreparedStatement stmt;
	private OpenCloseConnection occ = new OpenCloseConnection();
	private String query = null;

	@Override
	public LinkedHashSet<PokemonExtra> getPokemons() throws MyException {
		LinkedHashSet<PokemonExtra> pokemons = new LinkedHashSet<>();
		query = "select * from Pokemon_static";

		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PokemonExtra p = new PokemonExtra();
				p.setPokedexID(rs.getInt("pokedex_id"));
				p.setName(rs.getString("pokemon_name"));
				p.setType1(rs.getString("type1"));
				p.setType2(rs.getString("type2"));
				p.setRegion(rs.getString("region"));
				p.setCaptureRatio(rs.getFloat("capture_ratio"));
				pokemons.add(p);
			}
		} catch (SQLException e) {
			String error = "Error getting the pokedex";
			MyException er = new MyException(error);
			throw er;
		}
		occ.closeConnection(stmt, con);
		return pokemons;
	}

	@Override
	public LinkedHashSet<Combat> getTrainerCombatHistory(Integer trainerID) throws MyException {
		LinkedHashSet<Combat> combat = new LinkedHashSet<>();
		query = "select * from Combat where trainer_id1 =  ? or trainerid2 = ?";
		ResultSet rs;

		con = occ.openConnection();
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, trainerID);
			stmt.setInt(2, trainerID);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Combat c = new Combat();
				c.setTrainer1(rs.getInt("trainer_id1"));
				c.setTrainer2(rs.getInt("trainer_id3"));
				c.setWinnerTrainerID(rs.getInt("winner"));
				combat.add(c);
			}
		} catch (SQLException e) {
			String error = "Error getting combat history";
			MyException er = new MyException(error);
			throw er;
		}
		occ.closeConnection(stmt, con);

		return combat;
	}

	@Override
	public LinkedHashMap<String, Pokemon> getPcPokemons(int trainer_id) throws MyException {
		LinkedHashMap<String, Pokemon> aux = new LinkedHashMap<>();

		query = "Select pokedex_id, region, pokemon_name, nickname, type1, type2, pokemon_lvl, location from Pokemon_static join Pokemon on pokemon_id=pokedex_id where trainer_id=? and location=false";
		con = occ.openConnection();

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, trainer_id);

			ResultSet rte = stmt.executeQuery();
			// Here we give values to the set storaging the trainers pokemon
			while (rte.next()) {
				Pokemon p = new Pokemon();
				p.setPokedexID(rte.getInt("pokedex_id"));
				p.setRegion(rte.getString("region"));
				p.setName(rte.getString("pokemon_name"));
				p.setNickname(rte.getString("nickname"));
				p.setType1(rte.getString("type1"));
				p.setType2(rte.getString("type2"));
				p.setLevel(rte.getInt("pokemon_lvl"));
				aux.put(p.getName(), p);
			}
		} catch (SQLException e) {
			String error = "Error getting the trainer's pc pokemons";
			MyException er = new MyException(error);
			throw er;
		}

		return aux;
	}

}
