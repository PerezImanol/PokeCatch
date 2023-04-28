package interfaces;

import java.util.LinkedHashSet;

import classes.Combat;
import classes.MyException;
import classes.PokemonExtra;

public interface DataBattleShowable {

    public LinkedHashSet<PokemonExtra> getPokemons() throws MyException;
    public LinkedHashSet<Combat> getTrainerCombatHistory(Integer trainerID) throws MyException;
}
