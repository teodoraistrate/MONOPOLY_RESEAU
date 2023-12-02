package fr.pantheonsorbonne.miage.testPlateau;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProprieteTest {

    private Propriete propriete;
    private Joueur joueur;

    @BeforeEach
    public void setUp() {
        propriete = new Terrain("Test Propriete", 200, null, new int[]{10, 20, 30, 40, 50, 60}, 100);
        joueur = new JoueurS1("Test Joueur");
    }

    @Test
    public void testGetProprietaire() {
        assertNull(propriete.getProprietaire());
    }

    @Test
    public void testGetPrixRevente() {
        assertEquals(100, propriete.getPrixRevente());
    }

    @Test
    public void testGetPrice() {
        assertEquals(200, propriete.getPrice());
    }

    @Test
    public void testEstLibre() {
        assertTrue(propriete.estLibre());
    }

    @Test
    public void testEstHypotheque() {
        assertFalse(propriete.estHypotheque());
    }

    @Test
    public void testSetProprietaire() {
        propriete.setProprietaire(joueur);
        assertEquals(joueur, propriete.getProprietaire());
    }

    @Test
    public void testGetLoyer() {
        assertEquals(10, propriete.getLoyer()); 
    }

    @Test
    public void testHypothequer() {
        propriete.setProprietaire(joueur);
        propriete.hypothequer();
        assertTrue(propriete.estHypotheque());
        assertEquals(100, joueur.getPorteMonnaie());
        //ajouter le porte monnaie du joueur 
    }

    @Test
    public void testLeverHypotheque() {
        propriete.leverHypotheque();
        assertFalse(propriete.estHypotheque());
    }
}
