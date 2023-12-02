package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePrison;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class CartePrisonTest {

    private Plateau plateau;
        private JoueurS1 joueur;

        @BeforeEach
        public void setUp() {
            plateau = Plateau.getInstance();
            joueur = new JoueurS1("TestJoueur");
        }



    @Test
    public void testAppliquerEffet() {
        Joueur testPlayer = new JoueurS1("Sarah"); 
        CartePrison testCard = new CartePrison("Go to prison");
        try {
            testCard.appliquerEffet(testPlayer);
        } catch (PasAssezArgentException | NomPasValideException e) {
            fail("Exception not expected during the test");
        }

        assertFalse(testPlayer.estEnPrison());
    }
}
