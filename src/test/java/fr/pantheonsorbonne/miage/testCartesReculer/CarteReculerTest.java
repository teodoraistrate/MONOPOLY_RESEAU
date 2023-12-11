package fr.pantheonsorbonne.miage.testCartesReculer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer.CarteReculer;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer.CarteReculerNombre;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class CarteReculerTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("TestJoueur");
    }



    @Test
    public void testAppliquerEffet() {
        CarteReculer carteTest = new CarteReculerNombre("Reculez de quelques cases",3);

        try {
            int taillePlateau = 40;
            Joueur joueurTest = new JoueurS1("Patrick");
            carteTest.appliquerEffet(joueurTest);
            assertEquals((taillePlateau -3), joueurTest.getPositionPlateau());
        } catch (NomPasValideException e) {
            fail("Exception non attendue pendant le test");
        }
    }

}
