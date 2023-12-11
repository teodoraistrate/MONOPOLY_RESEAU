package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.joueur;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.*;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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

    

    @Test
    public void testRacheterProprieteHypothequee() throws PasAssezArgentException, DejaAcheteException, CannotSellException {
        JoueurS2 joueur = new JoueurS2("Joueur1");
        Terrain terrain = new Terrain("Propriété1", 100, Color.PINK, new int[]{6, 12, 30, 90, 270, 400}, 50);

        joueur.ajouterPropriete(terrain);
        terrain.hypothequer();
    
        assertTrue(terrain.estHypotheque());

        joueur.ajouterArgent(1000);
    
        joueur.racheterProprieteHypothequee(terrain);
    
        assertFalse(terrain.estHypotheque(), "La propriété devrait ne plus être hypothéquée");
        assertEquals(1000 + terrain.getPrixRevente() - 1.1*terrain.getPrixRevente(), joueur.getPorteMonnaie(), "Le joueur devrait avoir dépensé le montant correct pour le rachat");
    }

    @Test
    public void testChoixProprieteARacheter () throws PasAssezArgentException, DejaAcheteException, CannotSellException {
        JoueurS2 joueur = new JoueurS2("patoch la totoche");
        joueur.getPorteMonnaie();
        Terrain terrain = new Terrain("Rue de la Paix", 100, Color.PINK, new int[]{6, 12, 30, 90, 270, 400}, 50);
        Terrain terrain2 = new Terrain("Street of la Paix", 100, Color.PINK, new int[]{6, 12, 30, 90, 270, 400}, 50);

        joueur.ajouterPropriete(terrain);
        joueur.ajouterPropriete(terrain2);
        List<Propriete> listeP1 = new ArrayList<>();
        listeP1.add(terrain);
        listeP1.add(terrain2);
        joueur.ajouterArgent(100);
        List<Propriete> listeP = joueur.choixProprietesAHypothequer();
        listeP.add(terrain);
        terrain.hypothequer();

        //liste avec les prop qu'il peut hypothéquer 

        joueur.racheterProprieteHypothequee(terrain);

        assertFalse(terrain.estHypotheque()); // La propriété ne devrait plus être hypothéquée
        assertEquals(95, joueur.getPorteMonnaie(), 0.001);


    }

    //version 2

    @Test
    public void testRachatProprieteHypothequee() throws PasAssezArgentException, DejaAcheteException, CannotSellException {
        JoueurS2 joueur = new JoueurS2("patoch la totoche");
        Terrain terrain = new Terrain("Rue de la Paix", 100, Color.PINK, new int[]{6, 12, 30, 90, 270, 400}, 50);
        
        joueur.ajouterPropriete(terrain);
        joueur.ajouterArgent(100);
        
        // Hypothequer la propriété
        terrain.hypothequer();
        
        joueur.racheterProprieteHypothequee(terrain);
        
        assertFalse(terrain.estHypotheque(), "La propriété ne devrait plus être hypothéquée");
        assertEquals(95, joueur.getPorteMonnaie(), 0.001, "Le porte-monnaie du joueur doit être mis à jour");
    }


    @Test
    public void testChoixProprietesARacheter_AucunePropriete() {
        JoueurS2 joueur = new JoueurS2("Joueur1");
        List<Propriete> result = joueur.choixProprietesARacheter();
        assertTrue(result.isEmpty(), "Aucune propriété ne devrait être choisie pour l'achat");
    }

    @Test
    public void testChoixProprietesARacheter_ToutesProprietesNonHypothequees() {
        JoueurS2 joueur = new JoueurS2("Joueur1");
        Terrain terrain1 = new Terrain("Propriété1", 100, Color.PINK, new int[]{6, 12, 30, 90, 270, 400}, 50);
        Terrain terrain2 = new Terrain("Propriété2", 150, Color.GREEN, new int[]{6, 12, 30, 90, 270, 400}, 60);

        joueur.ajouterPropriete(terrain1);
        joueur.ajouterPropriete(terrain2);

        List<Propriete> result = joueur.choixProprietesARacheter();

        assertTrue(result.isEmpty(), "Aucune propriété ne devrait être choisie pour l'achat si aucune n'est hypothéquée");
    }

    

}

    





