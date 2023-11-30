
package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarteAvancerGareTest {

    @Test
    public void testGetNouvellePosition() {
        String expectedDescription = "Avancez jusqu'à la prochaine gare.";
        CarteAvancerGare carte = new CarteAvancerGare(expectedDescription);
        Joueur joueur = new JoueurS1("TestPlayer");
        joueur.deplacerSurPlateau(12, true);

        int nouvellePosition = carte.getNouvellePosition(joueur);

        assertEquals(15, nouvellePosition);
    }
}
