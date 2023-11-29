

package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Parking;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingTest {

    @Test
    public void testGetName() {
        Parking parking = new Parking("Test Parking");
        assertEquals("Test Parking", parking.getName());
    }

    @Test
    public void testGetIdCase() {
        Parking parking = new Parking("Test Parking");
        assertEquals(1, parking.getIdCase());
    }
}
