package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.logging.Level;

import classes.Combat;
import classes.Pokemon;
import classes.Trainer;
import interfaces.Simulable;

public class SimulableDBimplementation implements Simulable {

    private Connection con;
    private PreparedStatement stmt;
    private OpenCloseConnection occ = new OpenCloseConnection();

    @Override
    public void addCaughtPokemons(Pokemon pokemon, int trainerID) throws SQLException {

        final String queryCazar = "INSERT INTO pokemon(pokemon_id, trainer_id, pokemon_lvl, nickname, location) VALUES( ?, ?, ?, ?, ?)";
        int teamOrPc;

        if (pokemon.isTeam()){
            teamOrPc = 0;
        } else 
            teamOrPc = 1;

            
        try {
            con = occ.openConnection();
            stmt = con.prepareStatement(queryCazar);

            stmt.setInt(1, pokemon.getPokedexID());
            stmt.setInt(2, trainerID);
            stmt.setInt(3, pokemon.getLevel());
            stmt.setString(4, pokemon.getNickname());
            stmt.setInt(5, teamOrPc);

            stmt.executeUpdate();

            con.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        occ.closeConnection(stmt, con);

    }

    @Override
    public void updateCombatHistory(LinkedHashSet<Combat> combate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCombatHistory'");
    }

    @Override
    public LinkedHashSet<Pokemon> getTeamPokemons(Trainer trainer) throws SQLException{
        ResultSet rs = null;
        LinkedHashSet<Pokemon> pokemons = new LinkedHashSet<>();

        final String queryPokemons = "select * from pokemon where location = 0 and trainer_id = ?";

        try {
			con = occ.openConnection();
            
		} catch (Exception e) {
			e.printStackTrace();
		}

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

        // Cerrar el ResultSet
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error en cierre del ResultSet");
            }
        }
        occ.closeConnection(stmt, con);
        return pokemons;
    }

    @Override
    public void changePosition(Trainer trainer, Pokemon pokemon) throws SQLException {
        final String queryActualizarEquipo = "UPDATE pokemon SET location = ?";

        
        try {
            con = occ.openConnection();
            stmt = con.prepareStatement(queryActualizarEquipo);

            stmt.setInt(1, 0);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

    @Override
    public void switchPosition(Trainer trainer, Pokemon pokemons) {
        
        
    }
}