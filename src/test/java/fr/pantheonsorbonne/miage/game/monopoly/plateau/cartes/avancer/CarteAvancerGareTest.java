
package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarteAvancerGareTest {

    @Test
    public void testGetNouvellePosition() {
        String expectedDescription = "Avancez jusqu'à la prochaine gare.";
        CarteAvancerGare carte = new CarteAvancerGare(expectedDescription);
        Joueur joueur = new Joueur("TestPlayer");
        joueur.deplacerSurPlateau(12, true);

        int nouvellePosition = carte.getNouvellePosition(joueur);

        assertEquals(15, nouvellePosition);
    }
}
