package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;
import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;

public class JoueurReseau extends Joueur{
    private PlayerFacade playerFacade = Facade.getFacade();
    private Game game;

    public JoueurReseau(String id, PlayerFacade playerFacade, Game game) {
        super(id);
        this.playerFacade = playerFacade;
        this.game = game;
    }
}
