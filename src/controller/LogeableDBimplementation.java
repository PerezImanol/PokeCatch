package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.Pokemon;
import classes.Trainer;
import interfaces.Logeable;

public class LogeableDBimplementation implements Logeable {

	private Connection con;
	private PreparedStatement stmt;
	private OpenCloseConnection occ = new OpenCloseConnection();
	final String QueryUsers = "Select user_id from Login where username=? and passwd=?";

	@Override
	public Trainer isUser(String username, String password) throws SQLException {

		LinkedHashSet<Pokemon> aux = new LinkedHashSet<>();
		LinkedHashSet<Combat> auxC = new LinkedHashSet<>();
		Trainer t = new Trainer();

		ResultSet rs;
		ResultSet rti = null;
		ResultSet rte = null;
		ResultSet rtc = null;

		try {
			con = occ.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		stmt = con.prepareStatement(QueryUsers);
		stmt.setString(1, username);
		stmt.setString(2, password);
		rs = stmt.executeQuery();

		while(rs.next()){
			int id = rs.getInt("user_id");
			final String QueryInfo = "Select trainer_name, age, gender, city, badges from Trainer where trainer_id = ?";
			stmt = con.prepareStatement(QueryInfo);
			stmt.setInt(1, id);
			rti = stmt.executeQuery();
			while (rti.next()) {
				t.setName(rti.getString("trainer_name"));
				t.setGender(rti.getString("gender"));
				t.setOriginCity(rti.getString("city"));
				t.setBadges(rti.getInt("badges"));
				final String QueryTeam = "Select pokedex_id, region, pokemon_name, nickname, type1, type2, pokemon_lvl from Pokemon_static join Pokemon on pokemon_id=pokedex_id where trainer_id=?";
				stmt = con.prepareStatement(QueryTeam);
				stmt.setInt(1, id);
				rte = stmt.executeQuery();

				final String QueryCombat = "Select trainer_id1, trainer_id2, winner from combat where trainer_id1=? or trainer_id2=?";
				stmt = con.prepareStatement(QueryCombat);
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
					c.setWinner(rtc.getInt("winner"));
					auxC.add(c);
				}
				
				t.setTeam(aux);
				t.setCombatHistory(auxC);
			}

		}
		return t;
	}
}
