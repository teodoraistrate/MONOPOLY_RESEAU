
package fr.pantheonsorbonne.miage.testCartesAvancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.CarteAvancerGare;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class CarteAvancerGareTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("TestJoueur");
    }


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
