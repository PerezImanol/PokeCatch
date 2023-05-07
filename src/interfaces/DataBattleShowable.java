package interfaces;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.MyException;
import classes.Pokemon;
import classes.PokemonExtra;

public interface DataBattleShowable {

    public LinkedHashSet<PokemonExtra> getPokemons() throws MyException;
    public LinkedHashSet<Combat> getTrainerCombatHistory(Integer trainerID) throws MyException;
    public LinkedHashMap<String, Pokemon> getPcPokemons(int trainer_id) throws MyException;
}
