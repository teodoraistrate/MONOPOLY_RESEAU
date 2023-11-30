package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.testJeu;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.Pioche;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;
import fr.pantheonsorbonne.miage.testPlateau.Before;

public class PiocheTest {

    private Pioche pioche;

    @Before
    public void setUp() {
        // Initialisation de la pioche avant chaque test
        pioche = new Pioche();
    }

    @Test
    public void testAjouterCarte() {
        // Création d'une carte de test
        Carte carte = new Carte("Description de la carte de test");

        // Ajout de la carte à la pioche
        pioche.ajouterCarte(carte);

        // Vérification que la carte a été ajoutée avec succès
        assertTrue(pioche.getPioche().contains(carte));
    }

    @Test
    public void testPiocherCarte() {
        Carte carte1 = new Carte("Description de la carte 1");
        Carte carte2 = new Carte("Description de la carte 2");

        pioche.ajouterCarte(carte1);
        pioche.ajouterCarte(carte2);
        Carte cartePiochee = pioche.piocherCarte();
        assertTrue(pioche.getPioche().contains(cartePiochee));

        assertEquals(1, pioche.getPioche().size());
    }

    @Test
    public void testMelangerPioche() {
        // Création de cartes de test
        Carte carte1 = new Carte("Description de la carte 1");
        Carte carte2 = new Carte("Description de la carte 2");

        // Ajout des cartes à la pioche
        pioche.ajouterCarte(carte1);
        pioche.ajouterCarte(carte2);

        // Copie de la pioche avant mélange
        List<Carte> piocheAvantMelange
