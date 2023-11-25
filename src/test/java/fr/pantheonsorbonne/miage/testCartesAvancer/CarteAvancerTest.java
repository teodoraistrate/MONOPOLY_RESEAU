package fr.pantheonsorbonne.miage.testCartesAvancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.CarteAvancer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class CarteAvancerTest {

    private static class TestCarteAvancer extends CarteAvancer {
        private final int nouvellePosition;

        TestCarteAvancer(String description, int nouvellePosition) {
            super(description);
            this.nouvellePosition = nouvellePosition;
        }

        @Override
        public int getNouvellePosition(Joueur joueur) {
            return nouvellePosition;
        }
    }

    @Test
    public void testAppliquerEffet() {
        Joueur joueur = new Joueur(null);
        CarteAvancer carte = new TestCarteAvancer("Description", 5);
        try {
            carte.appliquerEffet(joueur);
            int nouvellePositionAttendue = 5; 
            assertEquals(nouvellePositionAttendue, joueur.getPositionPlateau());
        } catch (PasAssezArgentException e) {
            fail("Pas censé lancer une exception ici.");
        }
    }
}
