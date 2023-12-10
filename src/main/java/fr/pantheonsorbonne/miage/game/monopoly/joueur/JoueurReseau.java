package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.game.monopoly.jeu.MonopolyHost;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public class JoueurReseau extends Joueur{
    private static PlayerFacade playerFacade = Facade.getFacade();
    private static Game game;
    private String strategieChoisie; // une stratégie S1 ou S2
    private Joueur joueur;

    public JoueurReseau(String idJoueur, String strategieChoisie) {
        super(idJoueur);
        this.strategieChoisie = strategieChoisie;

        switch (strategieChoisie) {
            case "S1" : 
                joueur = new JoueurS1(idJoueur);
                break;
            case "S2" : 
                joueur = new JoueurS2(idJoueur);
                break;
        }
    }

    public static void main(String[] args) {

        

        // boucle infinie pour que le joueur reçoive les commandes tout au long de la partie
        for (;;) {
            GameCommand commande = playerFacade.receiveGameCommand(game);
            String nomCommande = commande.name();

            String reponse;

            switch(nomCommande) {
                case "askBuyProperty" :
                    String nomPropriete = commande.body();
                    Case c = Plateau.getCaseParId(Plateau.getInstance().getCaseParNom(nomPropriete));
                    if (c instanceof Propriete) {
                        if (choixAcheterPropriete((Propriete)c))
                    }
                case "gameOver" : 
                    break;
            }
        }
    }

    @Override
    public boolean choixAcheterPropriete(Propriete propriete) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixAcheterPropriete'");
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
