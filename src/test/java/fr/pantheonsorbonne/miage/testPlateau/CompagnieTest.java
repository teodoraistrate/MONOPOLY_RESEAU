package fr.pantheonsorbonne.miage.testPlateau;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Compagnie;

public class CompagnieTest {

    @Test
    public void testCreationCompagnie() {
        Compagnie compagnie = new Compagnie("Compagnie Test", 200);
        assertNotNull(compagnie);
        assertEquals("Compagnie Test", compagnie.getName());
        assertEquals(200, compagnie.getPrice());
    }

    @Test
    public void testGetLoyer() {
        Compagnie compagnie = new Compagnie("Compagnie Test", 200);
        assertEquals(75, compagnie.getLoyer());
    }

}
