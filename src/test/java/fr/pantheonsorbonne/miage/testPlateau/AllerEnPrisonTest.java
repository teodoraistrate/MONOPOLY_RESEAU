
package fr.pantheonsorbonne.miage.testPlateau;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.AllerEnPrison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;

class AllerEnPrisonTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("nv joueur");
    }

    @Test
    void testAppliquerEffetCase() {
        Joueur joueur = new JoueurS1("sarah");
        AllerEnPrison allerEnPrison = new AllerEnPrison("Aller en prison");
        Prison prison = Prison.getInstance("Prison");
        assertDoesNotThrow(() -> allerEnPrison.appliquerEffetCase(joueur));
        assertTrue(joueur.estEnPrison());

    }

}
