
package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.testCartePayer;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.payer.CartePayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class CartePayerTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("TestJoueur");
    }

z

    @Test
    public void testAppliquerEffet() {
        Joueur joueurTest = new JoueurS1("Juliette");
        CartePayer carteTest = new CartePayer("Description de test") {
            @Override
            public int getMontantAPayer(Joueur joueur) {
                return 100;
            }
        };
        int argentInitial = 200;
        joueurTest.ajouterArgent(argentInitial);
        try {
            carteTest.appliquerEffet(joueurTest);
            assertEquals(argentInitial - 100, joueurTest.getPorteMonnaie());
        } catch (PasAssezArgentException e) {
            fail("Pas d'exception attendue pendant le test");
        }
    }
}
