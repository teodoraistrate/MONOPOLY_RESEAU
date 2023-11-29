package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Compagnie;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompagnieTest {

    @Test
    public void testGetName() {
        Compagnie compagnie = new Compagnie("Test Compagnie", 200);
        String name = compagnie.getName();
        assertEquals("Test Compagnie", name);
    }

    @Test
    public void testGetPrice() {
        Compagnie compagnie = new Compagnie("Test Compagnie", 200);
        int price = compagnie.getPrice();
        assertEquals(200, price);
    }

    @Test
    public void testGetLoyer() {
        Compagnie compagnie = new Compagnie("Test Compagnie", 200);
        int loyer = compagnie.getLoyer();
        assertEquals(75, loyer);
    }

   
}
