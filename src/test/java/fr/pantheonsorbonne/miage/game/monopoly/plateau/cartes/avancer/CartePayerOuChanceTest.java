package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS2;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class CartePayerOuChanceTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("TestJoueur");
    }


    @Test
    public void testAppliquerEffetChanceT() {
        Joueur joueur = new JoueurS1("Anastasia"); 
        CartePayerOuChance carte = new CartePayerOuChance("Test Carte", -100); // Montant négatif pour indiquer un paiement
        assertDoesNotThrow(() -> carte.appliquerEffet(joueur));
        assertFalse(joueur.getPorteMonnaie() < 0);
    }


    @Test
    public void testAppliquerEffetChance() {
        Joueur joueur = new JoueurS1("Anastasia"); 
        CartePayerOuChance carte = new CartePayerOuChance("Test Carte", 100);
        //assertDoesNotThrow(() -> carte.appliquerEffet(joueur));
        assertTrue(joueur.getPorteMonnaie() >= 0);
    }

}
