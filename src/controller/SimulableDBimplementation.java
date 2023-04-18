package controller;

import java.util.LinkedHashSet;

import classes.Combat;
import classes.Pokemon;
import classes.Trainer;
import interfaces.Simulable;

public class SimulableDBimplementation implements Simulable  {

    @Override
    public void addCaughtPokemons(LinkedHashSet<Pokemon> pokemon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCaughtPokemons'");
    }

    @Override
    public void updateCombatHistory(LinkedHashSet<Combat> combate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCombatHistory'");
    }

    @Override
    public LinkedHashSet<Pokemon> getTeamPokemons(Trainer entrenador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTeamPokemons'");
    }

    @Override
    public void updateTeam(Trainer trainer, LinkedHashSet<Pokemon> pokemons) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTeam'");
    }

}
