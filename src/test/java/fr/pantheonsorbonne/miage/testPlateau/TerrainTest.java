package fr.pantheonsorbonne.miage.testPlateau;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.awt.Color;


import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.CannotSellException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public class TerrainTest {

    @Test
    public void testConstructionTerrain() {
        int[] tableauLoyer = {100, 200, 300, 400, 500, 600};
        int prixMaison = 100;
        Color color = Color.BLUE;

        Terrain terrain = new Terrain("ProprieteTest", 1000, color, tableauLoyer, prixMaison);

        assertEquals("ProprieteTest", terrain.getName());
        assertEquals(1000, terrain.getPrice());
        assertArrayEquals(tableauLoyer, terrain.getTableauLoyer());
        assertEquals(0, terrain.getNombreMaisons());
        assertFalse(terrain.estHotel());
        assertEquals(prixMaison, terrain.getPrixMaison());
        assertEquals(color, terrain.getColor());
        assertNull(terrain.getProprietaire());
    }

    @Test
    public void testCalculLoyerSansHotel() throws CannotSellException {
        int[] tableauLoyer = {100, 200, 300, 400, 500, 600};
        int prixMaison = 100;
        Color color = Color.BLUE;

        Terrain terrain = new Terrain("ProprieteTest", 1000, color, tableauLoyer, prixMaison);

        assertEquals(100, terrain.getLoyer());

        terrain.vendreMaison();
        assertEquals(200, terrain.getLoyer());

        terrain.vendreMaison();
        assertEquals(300, terrain.getLoyer());
    }

    @Test
    public void testCalculLoyerAvecHotel() throws CannotSellException {
        int[] tableauLoyer = {100, 200, 300, 400, 500, 600};
        int prixMaison = 100;
        Color color = Color.BLUE;

        Terrain terrain = new Terrain("ProprieteTest", 1000, color, tableauLoyer, prixMaison);

        terrain.vendreMaison();
        terrain.vendreMaison();
        terrain.vendreMaison();
        terrain.vendreMaison();

        assertEquals(600, terrain.getLoyer());
    }

     @Test
    public void testVendreHotel() throws CannotSellException {
        int[] tableauLoyer = {100, 200, 300, 400, 500, 600};
        int prixMaison = 100;
        Color color = Color.BLUE;

        Terrain terrain = new Terrain("ProprieteTest", 1000, color, tableauLoyer, prixMaison);

        terrain.vendreMaison();
        terrain.vendreMaison();
        terrain.vendreMaison();
        terrain.vendreMaison();

        terrain.acheterHotel();

        assertTrue(terrain.estHotel());

        terrain.vendreHotel();

        assertFalse(terrain.estHotel());
        assertEquals(4, terrain.getNombreMaisons());
    }

    @ Test //(expected = CannotSellException.class)
    public void testVendreHotelSansHotel() throws CannotSellException {
        int[] tableauLoyer = {100, 200, 300, 400, 500, 600};
        int prixMaison = 100;
        Color color = Color.BLUE;

        Terrain terrain = new Terrain("ProprieteTest", 1000, color, tableauLoyer, prixMaison);

        assertFalse(terrain.estHotel());

        terrain.vendreHotel(); // Doit lancer une exception CannotSellException
    }

    @Test
    public void testVendreMaison() throws CannotSellException {
        int[] tableauLoyer = {100, 200, 300, 400, 500, 600};
        int prixMaison = 100;
        Color color = Color.BLUE;

        Terrain terrain = new Terrain("ProprieteTest", 1000, color, tableauLoyer, prixMaison);

        terrain.vendreMaison();
        terrain.vendreMaison();

        assertEquals(2, terrain.getNombreMaisons());

        terrain.vendreMaison();

        assertEquals(1, terrain.getNombreMaisons());
    }

    @Test //(expected = CannotSellException.class)
    public void testVendreMaisonSansMaison() throws CannotSellException {
        int[] tableauLoyer = {100, 200, 300, 400, 500, 600};
        int prixMaison = 100;
        Color color = Color.BLUE;

        Terrain terrain = new Terrain("ProprieteTest", 1000, color, tableauLoyer, prixMaison);

        assertEquals(0, terrain.getNombreMaisons());

        terrain.vendreMaison(); // Doit lancer une exception CannotSellException
    }
}
