package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;

public class CarteReculerNom extends CarteReculer {

    private String nomCase;

    public CarteReculerNom(String description, String nomCase) {
        super(description);
        this.nomCase = nomCase;
    }

    @Override
    public int getNouvellePosition(Joueur joueur) throws NomPasValideException {
        try {
            Plateau plateau = Plateau.getInstance();
            return plateau.getCaseParNom(nomCase);
        } catch (NomPasValideException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
