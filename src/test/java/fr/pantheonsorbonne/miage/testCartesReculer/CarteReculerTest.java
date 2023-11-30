package fr.pantheonsorbonne.miage.testCartesReculer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer.CarteReculer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarteReculerTest {

    @Test
    public void testAppliquerEffet() {
        Joueur joueurTest = new JoueurS1("Patrick");
        CarteReculer carteTest = new CarteReculer("Reculez de quelques cases") {
            @Override
            public int getNouvellePosition(Joueur joueur) throws NomPasValideException {
                return joueur.getPositionPlateau()-3;
            }
        };
        int positionInitiale = -3;

        joueurTest.setPosition(positionInitiale);
        try {
            carteTest.appliquerEffet(joueurTest);
            assertEquals(positionInitiale, joueurTest.getPositionPlateau());
        } catch (NomPasValideException e) {
            fail("Exception non attendue pendant le test");
        }
    }
}
