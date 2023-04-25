package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.PokemonExtra;
import interfaces.DataBattleShowable;

public class DataBattleShowableDBimplementation implements DataBattleShowable{
    
    private Connection con;
    private PreparedStatement stmt;
    private OpenCloseConnection occ = new OpenCloseConnection();
    private String query = null;

    @Override
    public LinkedHashSet<PokemonExtra> getPokemons() throws SQLException {
        LinkedHashSet <PokemonExtra> pokemons = new LinkedHashSet<>();
        query = "select * from pokemon_static";
        ResultSet rs;
        
        try {
            con = occ.openConnection();
        } catch (Exception e) {
        }
        stmt = con.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next()){
            PokemonExtra p = new PokemonExtra();
            p.setName(rs.getString("pokemon_name"));
            p.setType1(rs.getString("type1"));
            p.setType2(rs.getString("type2"));
            p.setRegion(rs.getString("region"));
            p.setCaptureRatio(rs.getFloat("capture_ratio"));
            pokemons.add(p);
        }
        occ.closeConnection(stmt, con);
        return pokemons;
    }

    @Override   
    public LinkedHashSet<Combat> getTrainerCombatHistory(Integer trainerID) throws SQLException {
        LinkedHashSet <Combat> combat = new LinkedHashSet<>();
        query = "select * from combat where trainer_id1 =  ? or trainerid2 = ?";
        ResultSet rs;

        try {
            con = occ. openConnection();
        } catch (Exception e) {
        }

        stmt = con.prepareStatement(query);
        stmt.setInt(1, trainerID);
        stmt.setInt(2, trainerID);
        rs = stmt.executeQuery();

        while (rs.next()){
            Combat c = new Combat();
            c.setTrainer1(rs.getInt("trainer_id1"));
            c.setTrainer2(rs.getInt("trainer_id3"));
            c.setWinnerTrainerID(rs.getInt("winner"));
            combat.add(c);
        }

        occ.closeConnection(stmt, con);

        return combat;
    }

}
