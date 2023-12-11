package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.testJeu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.Pioche;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.payer.CartePayerFixe;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.recevoir.CarteRecevoirFixe;

public class PiocheTest {

    private Pioche pioche;

    @BeforeEach
    public void setUp() {
        pioche = new Pioche();
    }

    @Test
    public void testAjouterCarte() {
        Carte carte = new CartePayerFixe("Description de la carte de test", 25);

        pioche.ajouterCarte(carte);

        assertTrue(pioche.getPioche().contains(carte));
    }

    @Test
    public void testPiocherCarte() {
        Carte carte1 = new CartePayerFixe("Description de la carte 1", 150);
        Carte carte2 = new CartePayerFixe("Description de la carte 2", 200);

        pioche.ajouterCarte(carte1);
        pioche.ajouterCarte(carte2);
        Carte cartePiochee = pioche.piocherCarte();
        assertTrue(pioche.getPioche().contains(cartePiochee));

        assertEquals(2, pioche.getPioche().size());
    }

    @Test
    public void testMelangerPioche() {
        Carte carte1 = new CarteRecevoirFixe("Description de la carte 1", 75);
        Carte carte2 = new CarteRecevoirFixe("Description de la carte 2", 25);

        pioche.ajouterCarte(carte1);
        pioche.ajouterCarte(carte2);

        List<Carte> piocheAvantMelange = new ArrayList<>(pioche.getPioche());

        pioche.melangerPioche();

        assertEquals(piocheAvantMelange.size(), pioche.getPioche().size());

        
    }
}