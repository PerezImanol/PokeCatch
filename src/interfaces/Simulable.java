package interfaces;

import java.util.LinkedHashSet;

import classes.Combat;
import classes.MyException;
import classes.Pokemon;
import classes.Trainer;

public interface Simulable {
    // add caught pokemons
    public void addCaughtPokemon(Pokemon pokemon, int trainerID) throws MyException;

    // upadate combat history after a combat bwetween two trainers
    public void updateCombatHistory(Combat combat) throws MyException;

    // obtain the whole team of a trainer as a LinkedHashSet
    public LinkedHashSet<Pokemon> getTeamPokemons(Trainer trainer) throws MyException;

    // move one pokemon between the computer and the team
    public void changePosition(Trainer trainer, Pokemon pokemons) throws MyException;

    // switch positon of one pokemon in the team with one pokemon in the computer
    public void switchPosition(Trainer trainer, Pokemon pokemon1, Pokemon pokemon2) throws MyException;

    // given all the team pokemon ID's of both trainers returns the result of the
    // fight in a boolean value
    public int getWinner(int pokemons[]) throws MyException;
}
