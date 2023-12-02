package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

public class TerrainTest {

    @Test
    public void testGetPrixMaison() {
        Terrain terrain = new Terrain("TerrainTest", 200, Color.GREEN, new int[]{10, 50, 150, 450, 625, 750, 875, 925, 975, 1025}, 100);
        assertEquals(100, terrain.getPrixMaison());
    }

    @Test
    public void testGetTableauLoyer() {
        int[] tableauLoyer = {10, 50, 150, 450, 625, 750, 875, 925, 975, 1025};
        Terrain terrain = new Terrain("TerrainTest", 200, Color.GREEN, tableauLoyer, 100);
        assertArrayEquals(tableauLoyer, terrain.getTableauLoyer());
    }

    @Test
    public void testGetNombreToursInitialSquatteur() {
        Terrain terrain = new Terrain("TerrainTest", 200, Color.GREEN, new int[]{10, 50, 150, 450, 625, 750, 875, 925, 975, 1025}, 100);
        assertEquals(0, terrain.getNombreToursInitialSquatteur());
    }

    @Test
    public void testGetNombreMaisons() {
        Terrain terrain = new Terrain("TerrainTest", 200, Color.GREEN, new int[]{10, 50, 150, 450, 625, 750, 875, 925, 975, 1025}, 100);
        assertEquals(0, terrain.getNombreMaisons());
    }

    @Test
    public void testAugmenterNbMaisons() {
        Terrain terrain = new Terrain("TerrainTest", 200, Color.GREEN, new int[]{10, 50, 150, 450, 625, 750, 875, 925, 975, 1025}, 100);
        terrain.augmenterNbMaisons();
        assertEquals(1, terrain.getNombreMaisons());
    }

    @Test
    public void testGetLoyerWithoutHotel() {
        int[] tableauLoyer = {10, 50, 150, 450, 625, 750, 875, 925, 975, 1025};
        Terrain terrain = new Terrain("TerrainTest", 200, Color.GREEN, tableauLoyer, 100);


        // Aucune maison
        assertEquals(20, terrain.getLoyer());

        // Ajout d'une maison
        terrain.augmenterNbMaisons();
        assertEquals(20, terrain.getLoyer());

        // Ajout de deux maisons
        terrain.augmenterNbMaisons();
        assertEquals(150, terrain.getLoyer());
    }

    
}
