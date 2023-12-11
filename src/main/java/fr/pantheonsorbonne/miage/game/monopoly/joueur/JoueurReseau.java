package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.game.monopoly.jeu.MonopolyHost;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public class JoueurReseau extends Joueur {
    private static PlayerFacade playerFacade;
    private static Game game;
    private String strategieChoisie; // une stratégie S1 ou S2
    private Joueur joueur;

    public JoueurReseau(String idJoueur, String strategieChoisie) {
        super(idJoueur);
        this.strategieChoisie = strategieChoisie;

        switch (strategieChoisie) {
            case "S1":
                joueur = new JoueurS1(idJoueur);
                break;
            case "S2":
                joueur = new JoueurS2(idJoueur);
                break;
        }
    }

    public static void main(String[] args) {

        playerFacade = Facade.getFacade();
        playerFacade.waitReady();

        final String nomJoueur = "Joueur" + new Random().nextInt(); 

        game = playerFacade.autoJoinGame("Monopoly");

        JoueurReseau joueurReseau = new JoueurReseau(nomJoueur, "S1");


        // boucle infinie pour que le joueur reçoive les commandes tout au long de la partie
        boolean continuer = true;
        while (continuer) {
            GameCommand commande = playerFacade.receiveGameCommand(game);
            String nomCommande = commande.name();

            String reponse = "";

            switch(nomCommande) {
                case "askBuyProperty" :
                    String nomPropriete = commande.body();
                    try {
                        Case c = Plateau.getCaseParId(Plateau.getInstance().getCaseParNom(nomPropriete));
                        if (c instanceof Propriete) {
                            if (joueurReseau.choixAcheterPropriete((Propriete)c)) {
                                reponse = "YesBuy";
                            } else {
                                reponse = "NoDont";
                            }
                        }
                    } catch (NomPasValideException e) {
                        e.printStackTrace();
                    }
                    break;
                case "askRemoveInstantlySquat" :
                    String nomProprieteSquattee = commande.body();
                    try {
                        Case c = Plateau.getCaseParId(Plateau.getInstance().getCaseParNom(nomProprieteSquattee));
                        if (c instanceof Terrain) {
                            if (joueurReseau.choixPayerOuAttendre()) {
                                reponse = "YesGetRid";
                            } else {
                                reponse = "NoDont";
                            }
                        }
                    } catch (NomPasValideException e) {
                        e.printStackTrace();
                    }
                    break;
                case "askGetOutOfJail" :
                    if (joueurReseau.choixSortirPrison()) {
                        reponse = "YesOut";
                    } else {
                        reponse = "NoIn";
                    }
                    break;
                case "gameOver" : 
                    continuer = false;
                    break;
            }

            GameCommand commandeReponse = new GameCommand(reponse);
            
            playerFacade.sendGameCommandToPlayer(game, "Host", commandeReponse);

        }
    }

    @Override
    public boolean choixAcheterPropriete(Propriete propriete) {
        return joueur.choixAcheterPropriete(propriete);
    }

    @Override
    public boolean choixPayerOuChance(CartePayerOuChance c) {
        // à implémenter
        return false;
    }

    @Override
    public boolean choixSortirPrison() {
        return joueur.choixSortirPrison();
    }

    @Override
    public List<Propriete> choixProprietesARacheter() {
        return joueur.choixProprietesARacheter();
    }

    @Override
    public Map<Terrain, Integer> choixNombreMaisonsAVendre() {
        return joueur.choixNombreMaisonsAVendre();
    }

    @Override
    public List<Terrain> choixHotelsAVendre() {
        return joueur.choixHotelsAVendre();
    }

    @Override
    public List<Propriete> choixProprietesAHypothequer() {
        return joueur.choixProprietesAHypothequer();
    }

    @Override
    public boolean choixPayerOuAttendre() {
        return joueur.choixPayerOuAttendre();
    }

    @Override
    public Terrain choixTransformerProprieteEnPrison() {
        return joueur.choixTransformerProprieteEnPrison();
    }
}
