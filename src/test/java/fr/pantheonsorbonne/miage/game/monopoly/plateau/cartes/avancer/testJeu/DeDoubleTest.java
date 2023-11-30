package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.testJeu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.DeDouble;

public class DeDoubleTest {

    @Test
    public void testLancerDes() {
        DeDouble deDouble = new DeDouble();
        deDouble.lancerDes();
        assertTrue(deDouble.getValeur() >= 1 && deDouble.getValeur() <= 12);
        assertTrue(deDouble.resultatDe() >= 2 && deDouble.resultatDe() <= 12);
        assertEquals(deDouble.resultatDe(), Math.min(deDouble.getValeur(), DeDouble.VALEUR_MAX));
        assertFalse(deDouble.memeValeur() == (deDouble.getValeur() == deDouble.resultatDe()));
    }
}
