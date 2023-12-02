package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Start;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class StartTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("TestJoueur");
    }


    @Test
    public void testGetName() {
        Start start = new Start("Test Start");
        assertEquals("Test Start", start.getName());
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
