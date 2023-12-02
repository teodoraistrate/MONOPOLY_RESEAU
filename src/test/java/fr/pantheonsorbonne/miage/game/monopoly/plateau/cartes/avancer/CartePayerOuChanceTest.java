package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS2;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartePayerOuChanceTest {

    @Test
    public void testAppliquerEffetChanceT() {
        Joueur joueur = new JoueurS2("Anastasia"); 
        CartePayerOuChance carte = new CartePayerOuChance("Test Carte", -100); // Montant négatif pour indiquer un paiement
        assertDoesNotThrow(() -> carte.appliquerEffet(joueur));
        assertTrue(joueur.getPorteMonnaie() < 0);
    }


    @Test
    public void testAppliquerEffetChance() {
        Joueur joueur = new JoueurS2("Anastasia"); 
        CartePayerOuChance carte = new CartePayerOuChance("Test Carte", 100);
        assertDoesNotThrow(() -> carte.appliquerEffet(joueur));
        assertTrue(joueur.getPorteMonnaie() >= 0);
    }

}
