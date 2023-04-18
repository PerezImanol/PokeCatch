package interfaces;

import java.util.LinkedHashSet;

import classes.Combat;
import classes.Pokemon;
import classes.Trainer;

public interface Simulable {
    public void addCaughtPokemons(LinkedHashSet<Pokemon> pokemon);

    public void updateCombatHistory(LinkedHashSet<Combat> combate);

    public LinkedHashSet<Pokemon>  getTeamPokemons(Trainer entrenador); 

    public void updateTeam(Trainer trainer, LinkedHashSet<Pokemon> pokemons);
    
}
