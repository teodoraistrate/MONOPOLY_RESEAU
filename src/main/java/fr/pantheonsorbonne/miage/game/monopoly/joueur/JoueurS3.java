package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

// joueur con

public class JoueurS3 extends Joueur {

    public JoueurS3(String name) {
        super(name);
    }

    // il va acheter tout le temps
    public boolean choixAcheterPropriete(Propriete propriete) {
        return (this.getPorteMonnaie()>propriete.getPrice());
    }

    public boolean choixPayerOuChance(CartePayerOuChance c) {
        // false : payer
        return false;
    }

    @Override
    public boolean choixSortirPrison() {
        return false;
    }

    @Override
    public List<Propriete> choixProprietesARacheter() {
        return new ArrayList<>();
    }

    @Override
    public List<Propriete> choixProprietesAHypothequer() {
        return new ArrayList<>();
    }

    @Override
    public Map<Terrain, Integer> choixNombreMaisonsAVendre() {
        return new HashMap<>();
    }

    @Override
    public List<Terrain> choixHotelsAVendre() {
        return new ArrayList<>();
    }

    @Override
    public boolean choixPayerOuAttendre() {
        return true;
        // il va payer ET risquer d'aller en prison
    }

    @Override
    public Terrain choixTransformerProprieteEnPrison() {
        return null;
    }

}
