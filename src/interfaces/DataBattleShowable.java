package interfaces;

import java.sql.SQLException;
import java.util.LinkedHashSet;

import classes.Combat;
import classes.PokemonExtra;

public interface DataBattleShowable {

    public LinkedHashSet<PokemonExtra> getPokemons() throws SQLException;
    public LinkedHashSet<Combat> getTrainerCombatHistory(Integer trainerID) throws SQLException;
}
