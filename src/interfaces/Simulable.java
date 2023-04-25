package interfaces;

import java.sql.SQLException;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.Pokemon;
import classes.Trainer;

public interface Simulable {
    //add caught pokemons 
    public void addCaughtPokemons(Pokemon pokemon, int trainerID) throws SQLException;
    //upadate combat history after a combat bwetween two trainers
    public void updateCombatHistory(LinkedHashSet<Combat> combate) throws SQLException;
    //obtain the whole team of a trainer as a LinkedHashSet
    public LinkedHashSet<Pokemon> getTeamPokemons(Trainer entrenador) throws SQLException; 
    //move one pokemon between the computer and the team
    public void changePosition (Trainer trainer, Pokemon pokemons) throws SQLException;
    //switch positon of one pokemon in the team with one pokemon in the computer
    public void switchPosition (Trainer trainer, Pokemon pokemons) throws SQLException;
    
}
