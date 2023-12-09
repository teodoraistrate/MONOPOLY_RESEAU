package fr.pantheonsorbonne.miage.game.monopoly.jeu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

public class JoueurReseau extends Joueur {

    private PlayerFacade playerFacade;
    private Game game;

    public JoueurReseau(String name, PlayerFacade facade, Game jeu) {
        super(name);
        this.playerFacade = facade;
        this.game = jeu;
    }

    @Override
    public boolean choixAcheterPropriete(Propriete propriete) {
/*
        StringBuilder bodyCommande = new StringBuilder();
        bodyCommande.append(propriete.getName());
        bodyCommande.append(";");
        bodyCommande.append(this.getPorteMonnaie());
        bodyCommande.append(";");
        bodyCommande.append(this.getPositionPlateau());

        Map <String,String> parametres = new HashMap<>();

        GameCommand commande = new GameCommand("askBuyProperty", bodyCommande.toString(), parametres);
*/
    }

    @Override
    public boolean choixPayerOuChance(CartePayerOuChance c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixPayerOuChance'");
    }

    @Override
    public boolean choixSortirPrison() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixSortirPrison'");
    }

    @Override
    public List<Propriete> choixProprietesARacheter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixProprietesARacheter'");
    }

    @Override
    public Map<Terrain, Integer> choixNombreMaisonsAVendre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixNombreMaisonsAVendre'");
    }

    @Override
    public List<Terrain> choixHotelsAVendre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixHotelsAVendre'");
    }

    @Override
    public List<Propriete> choixProprietesAHypothequer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixProprietesAHypothequer'");
    }

    @Override
    public boolean choixPayerOuAttendre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixPayerOuAttendre'");
    }

    @Override
    public Terrain choixTransformerProprieteEnPrison() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixTransformerProprieteEnPrison'");
    }
    
}
