package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.Pokemon;
import classes.Professor;
import classes.Trainer;
import interfaces.Logeable;

public class LogeableDBimplementation implements Logeable {

	private Connection con;
	private PreparedStatement stmt;
	private OpenCloseConnection occ = new OpenCloseConnection();
	final String queryGetProfessor = "select trainer_id, trainer_name, age, gender, city, badges, pokeball, region from Trainer join professor on trainer_id=professor_id where trainer_id=?;";

	public Trainer getPerson(String username, String password) throws SQLException {

		Trainer t = null;
		int id = isUser(username, password);

		if (id != 0) {
			boolean professor = isProfessor(id);
			if (professor) {
				t = getProfessor(id);
			} else {

				t = getTrainer(id);
			}
		}
		return t;
	}

	@Override
	public Trainer getTrainer(int id) throws SQLException {

		LinkedHashSet<Pokemon> aux = new LinkedHashSet<>();
		LinkedHashSet<Combat> auxC = new LinkedHashSet<>();
		Trainer t = new Trainer();

		ResultSet rti = null;
		ResultSet rte = null;
		ResultSet rtc = null;

		try {
			con = occ.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		final String queryInfo = "Select trainer_id, trainer_name, age, gender, city, badges from Trainer where trainer_id = ?";
		stmt = con.prepareStatement(queryInfo);
		stmt.setInt(1, id);
		rti = stmt.executeQuery();

		while (rti.next()) {
			t.setTrainerID(rti.getInt("trainer_id"));
			t.setName(rti.getString("trainer_name"));
			t.setGender(rti.getString("gender"));
			t.setOriginCity(rti.getString("city"));
			t.setBadges(rti.getInt("badges"));
			final String queryTeam = "Select pokedex_id, region, pokemon_name, nickname, type1, type2, pokemon_lvl from Pokemon_static join Pokemon on pokemon_id=pokedex_id where trainer_id=? and location=0";
			stmt = con.prepareStatement(queryTeam);
			stmt.setInt(1, id);
			rte = stmt.executeQuery();

			final String queryCombat = "Select trainer_id1, trainer_id2, winner from combat where trainer_id1=? or trainer_id2=?";
			stmt = con.prepareStatement(queryCombat);
			stmt.setInt(1, id);
			stmt.setInt(2, id);
			rtc = stmt.executeQuery();

			while (rte.next()) {

				Pokemon p = new Pokemon();
				p.setPokedexID(rte.getInt("pokedex_id"));
				p.setRegion(rte.getString("region"));
				p.setName(rte.getString("pokemon_name"));
				p.setNickname(rte.getString("nickname"));
				p.setType1(rte.getString("type1"));
				p.setType2(rte.getString("type2"));
				p.setLevel(rte.getInt("pokemon_lvl"));
				aux.add(p);
			}
			while (rtc.next()) {

				Combat c = new Combat();
				c.setTrainer1(rtc.getInt("trainer_id1"));
				c.setTrainer2(rtc.getInt("trainer_id1"));
				c.setWinnerTrainerID(rtc.getInt("winner"));
				auxC.add(c);
			}

			t.setTeam(aux);
			t.setCombatHistory(auxC);
		}

		return t;
	}

	@Override
	public int isUser(String username, String password) throws SQLException {

		final String queryUsers = "Select user_id from Login where username=? and passwd=?";
		
		ResultSet rs;
		
		int id=0;

		try {
			con = occ.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}


		stmt = con.prepareStatement(queryUsers);
		stmt.setString(1, username);
		stmt.setString(2, password);
		rs = stmt.executeQuery();
		while(rs.next()) {
		
			id = rs.getInt("user_id");
		}

		return id;
	}

	@Override
	public Professor getProfessor(int id) throws SQLException {

		ArrayList<Pokemon> aux = new ArrayList<>();
		Professor p = new Professor();
		final String queryPokePro = "Select pokedex_id, region, pokemon_name, nickname, type1, type2, pokemon_lvl from Pokemon_static join Pokemon on pokemon_id=pokedex_id where trainer_id=?";

		ResultSet rsgp;
		ResultSet rspp;

		try {
			con = occ.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		stmt = con.prepareStatement(queryGetProfessor);
		stmt.setInt(1, id);
		rsgp = stmt.executeQuery();
		
		while(rsgp.next()) {

		p.setTrainerID(rsgp.getInt("trainer_id"));
		p.setName(rsgp.getString("trainer_name"));
		p.setAge(rsgp.getDate("age"));
		p.setGender(rsgp.getString("gender"));
		p.setOriginCity(rsgp.getString("city"));
		p.setBadges(rsgp.getInt("badges"));
		p.setPokeball(rsgp.getInt("pokeball"));
		p.setRegion(rsgp.getString("region"));

		stmt = con.prepareStatement(queryPokePro);
		stmt.setInt(1, id);
		rspp = stmt.executeQuery();

		while (rspp.next()) {
			Pokemon po = new Pokemon();
			po.setPokedexID(rspp.getInt("pokedex_id"));
			po.setRegion(rspp.getString("region"));
			po.setName(rspp.getString("pokemon_name"));
			po.setNickname(rspp.getString("nickname"));
			po.setType1(rspp.getString("type1"));
			po.setType2(rspp.getString("type2"));
			po.setLevel(rspp.getInt("pokemon_lvl"));
			aux.add(po);
		}
		}
		p.setInitialSelection(aux);
		return p;
	}

	@Override
	public boolean isProfessor(int id) throws SQLException {
		boolean pro = false;
		ResultSet rsgp2;
		try {
			con = occ.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		stmt = con.prepareStatement(queryGetProfessor);
		stmt.setInt(1, id);
		rsgp2 = stmt.executeQuery();
		
		
		while(rsgp2.next()) {
		if(!rsgp2.getString("region").equals(null)) {
			pro=true;
		}
		}

		return pro;
	}
}
