package interfaces;

import java.sql.SQLException;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.Pokemon;
import classes.Trainer;

public interface Simulable {
    public void addCaughtPokemons(Pokemon pokemon, int trainerID) throws SQLException;

    public void updateCombatHistory(LinkedHashSet<Combat> combate) throws SQLException;

    public LinkedHashSet<Pokemon> getTeamPokemons(Trainer entrenador) throws SQLException; 

    public void updateTeam(Trainer trainer, LinkedHashSet<Pokemon> pokemons) throws SQLException;
    
}
