package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Start;

import static org.junit.jupiter.api.Assertions.*;

public class StartTest {

    @Test
    public void testGetName() {
        Start start = new Start("Test Start");
        assertEquals("Test Start", start.getName());
    }

    @Test
    public void testGetIdCase() {
        Start start = new Start("Test Start");
        assertEquals(0, start.getIdCase());
    }

    @Test
    public void testReceiveMoney() {
        Joueur joueur = new JoueurS1("Test Joueur");
        double argentAvant = joueur.getPorteMonnaie();
        joueur.getStartingBonus();
        double argentApres = joueur.getPorteMonnaie();
        
        assertEquals(argentAvant + Start.RECEIVE_MONEY, argentApres);
    }
}
