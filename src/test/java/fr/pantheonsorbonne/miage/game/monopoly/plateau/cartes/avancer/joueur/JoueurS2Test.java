package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.joueur;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.*;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.awt.Color;


public class JoueurS2Test {

    private Plateau plateau;
    private JoueurS2 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS2("TestJoueur");
    }

    @Test
    public void testChoixAcheterPropriete() {
        JoueurS2 joueur = new JoueurS2("TestJoueur");
        Propriete propriete = new Terrain("TerrainTest", 100, Color.BLUE, new int[]{10, 20, 30, 40, 50, 60}, 50);

        assertFalse(joueur.choixAcheterPropriete(propriete));
    }

    @Test
    public void testChoixPayerOuChance() {
        JoueurS2 joueur = new JoueurS2("TestJoueur");
        CartePayerOuChance carte = new CartePayerOuChance("Test Carte", 20);

        assertTrue(joueur.choixPayerOuChance(carte));
    }

    @Test
    public void testChoixSortirPrison() {
        JoueurS2 joueur = new JoueurS2("TestJoueur");
        joueur.ajouterArgent(Prison.MONTANT_SORTIR * 6);

        assertTrue(joueur.choixSortirPrison());
    }

    





}
