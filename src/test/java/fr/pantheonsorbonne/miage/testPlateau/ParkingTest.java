package fr.pantheonsorbonne.miage.testPlateau;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Parking;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;

class ParkingTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("mickeal jackson");
    }

    @Test
    void testAppliquerEffetCase() {
        joueur = new JoueurS1("mickeal jackson");
        Parking parking = new Parking("Parking Test");
        assertDoesNotThrow(() -> parking.appliquerEffetCase(joueur));
        assertEquals(0, joueur.getPorteMonnaie());
        assertEquals("mickeal jackson", joueur.getName());

    }

}
