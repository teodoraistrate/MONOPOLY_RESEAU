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
        //Joueur joueurTest = new JoueurS1("Patrick");
        CarteReculer carteTest = new CarteReculerNombre("Reculez de quelques cases",3);
            /*@Overrid
            public int getNouvellePosition(Joueur joueur) {
                // Supposez que la nouvelle position soit trois cases en arrière, même si la position actuelle est différente
                return joueur.getPositionPlateau() ;
            }
            */
        

        // Position initiale différente de -3

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
