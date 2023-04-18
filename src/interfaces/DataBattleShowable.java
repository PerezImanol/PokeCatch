package interfaces;

import java.util.LinkedHashSet;

import classes.Combat;
import classes.Pokemon;

public interface DataBattleShowable {

    public LinkedHashSet<Pokemon> getPokemons();
    public LinkedHashSet<Combat> getTrainerCombatHistory(Integer trainerID);
}
