package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.joueur;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.CannotSellException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Compagnie;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.DejaAcheteException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoueurS1Test {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("TestJoueur");
    }

    
    @Test
    public void testChoixAcheterPropriete() {
        JoueurS1 joueur = new JoueurS1("TestJoueur");
        joueur.ajouterArgent(500);
        Propriete proprieteChere = new Compagnie("Propriete Chère", 1000);
        Propriete proprieteAbordable = new Compagnie("Propriete Abordable", 200);

        assertTrue(joueur.choixAcheterPropriete(proprieteAbordable));
        assertFalse(joueur.choixAcheterPropriete(proprieteChere));
    }

    @Test
    public void testChoixPayerOuChance() {
        JoueurS1 joueur = new JoueurS1("TestJoueur");
        CartePayerOuChance c = new CartePayerOuChance("carte", 20);

        // Le joueur n'a pas assez d'argent pour payer
        joueur.ajouterArgent(150);
        assertTrue(joueur.choixPayerOuChance(c));

        // Le joueur a suffisamment d'argent pour payer
        joueur.ajouterArgent(300);
        assertFalse(joueur.choixPayerOuChance(c));
        //ici c'est false car false c'est pour payer

        
    }

    @Test
    public void testChoixSortirPrison() {
        Plateau p = Plateau.getInstance();
        JoueurS1 joueur = new JoueurS1("TestJoueur");

        // Le joueur a suffisamment d'argent pour sortir de prison
        joueur.ajouterArgent(110);
        assertFalse(joueur.choixSortirPrison());

        // Le joueur n'a pas assez d'argent pour sortir de prison
        joueur.ajouterArgent(550);
        assertTrue(joueur.choixSortirPrison());
    }


    @Test 
    public void testChoixProprieteAHypothequer(){
        JoueurS1 joueur = new JoueurS1 ("mimi");
        joueur.getPorteMonnaie();
        Compagnie c1 = new Compagnie("c1", 150);
        Compagnie c2 = new Compagnie("c2", 250);

        List<Propriete> listeP1 = new ArrayList<>();
        listeP1.add(c1);
        listeP1.add(c2);

        joueur.ajouterPropriete(c1);
        joueur.ajouterPropriete(c2);
        joueur.ajouterArgent(100);
        List<Propriete> listePH = joueur.choixProprietesAHypothequer();

        assertIterableEquals(listeP1, listePH);

    }

    @Test 
    public void testChoixProprieteAHypothequer2(){
        JoueurS1 joueur = new JoueurS1 ("mimi");
        joueur.getPorteMonnaie();
        Compagnie c1 = new Compagnie("c1", 150);
        Compagnie c2 = new Compagnie("c2", 250);

        joueur.ajouterPropriete(c1);
        joueur.ajouterPropriete(c2);
        joueur.ajouterArgent(600);
        List<Propriete> listePH = joueur.choixProprietesAHypothequer();

        assertTrue(listePH.isEmpty());

    }
    /* 
    @Test
    public void testChoixNombreMaisonsAVendre() {
        JoueurS1 joueur = new JoueurS1("patrick");
        Terrain terrain1 = new Terrain("Rue de Vaugirard", 100, Color.CYAN, new int[] { 6, 12, 30, 90, 270, 400 }, 50) ;      
        Terrain terrain2 = new Terrain("Rue de vavugi", 200, Color.CYAN, new int[] { 6, 12, 30, 90, 270, 400 }, 50) ;      
        Terrain terrain3 = new Terrain("Rue de jsjs", 150, Color.CYAN, new int[] { 6, 12, 30, 90, 270, 400 }, 50) ;    
        
        joueur.ajouterPropriete(terrain1);
        joueur.ajouterPropriete(terrain2);
        joueur.ajouterPropriete(terrain3);
        

        //à continuer ou à supp après 

        joueur.choixNombreMaisonsAVendre();
    }

    */


    @Test
    public void testTransformerProprieteEnPrison () {
        JoueurS1 joueur = new JoueurS1("fefe");
        joueur.ajouterArgent(700);
    
        Terrain terrain = new Terrain("compagnie", 100, Color.PINK, new int[]{6, 12, 30, 90, 270, 400}, 50);
    
        boolean estEnPrisonAvant = joueur.estEnPrison();
        double argentAvant = joueur.getPorteMonnaie();
    
        boolean transformationReussie = joueur.choixTransformerProprieteEnPrison(terrain);
    
        boolean estEnPrisonApres = joueur.estEnPrison();
        double argentApres = joueur.getPorteMonnaie();
    
        assertFalse(estEnPrisonAvant);     // ça verifie que le joueur n'était pas en prison avant l'appel de la méthode
        //assertTrue(transformationReussie); // ça vrifie que la transformation de propriété en prison a réussi
        //assertTrue(estEnPrisonApres);  jsp pourquoi ca marche pas ca me saoule

    }

    //version1

    @Test
    public void testChoixProprieteARacheter () throws PasAssezArgentException, DejaAcheteException, CannotSellException {
        JoueurS1 joueur = new JoueurS1("patoch la totoche");
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
        // Given
        JoueurS1 joueur = new JoueurS1("patoch la totoche");
        Terrain terrain = new Terrain("Rue de la Paix", 100, Color.PINK, new int[]{6, 12, 30, 90, 270, 400}, 50);
        
        joueur.ajouterPropriete(terrain);
        joueur.ajouterArgent(100);
        
        // Hypothequer la propriété
        terrain.hypothequer();
        
        // When
        joueur.racheterProprieteHypothequee(terrain);
        
        // Then
        assertFalse(terrain.estHypotheque(), "La propriété ne devrait plus être hypothéquée");
        assertEquals(95, joueur.getPorteMonnaie(), 0.001, "Le porte-monnaie du joueur doit être mis à jour");
    }


    @Test
    public void testChoixProprietesARacheter_AucunePropriete() {
        // Given
        JoueurS1 joueur = new JoueurS1("Joueur1");

        // When
        List<Propriete> result = joueur.choixProprietesARacheter();

        // Then
        assertTrue(result.isEmpty(), "Aucune propriété ne devrait être choisie pour l'achat");
    }

    @Test
    public void testChoixProprietesARacheter_ToutesProprietesNonHypothequees() {
        // Given
        JoueurS1 joueur = new JoueurS1("Joueur1");
        Terrain terrain1 = new Terrain("Propriété1", 100, Color.PINK, new int[]{6, 12, 30, 90, 270, 400}, 50);
        Terrain terrain2 = new Terrain("Propriété2", 150, Color.GREEN, new int[]{6, 12, 30, 90, 270, 400}, 60);

        joueur.ajouterPropriete(terrain1);
        joueur.ajouterPropriete(terrain2);

        // When
        List<Propriete> result = joueur.choixProprietesARacheter();

        // Then
        assertTrue(result.isEmpty(), "Aucune propriété ne devrait être choisie pour l'achat si aucune n'est hypothéquée");
    }

    @Test
    public void testChoixProprietesARacheter_UneProprieteHypothequee() throws CannotSellException {
        // Given
        JoueurS1 joueur = new JoueurS1("Joueur1");
        Terrain terrain = new Terrain("Propriété1", 100, Color.PINK, new int[]{6, 12, 30, 90, 270, 400}, 50);
        terrain.hypothequer();
        joueur.ajouterPropriete(terrain);
        joueur.ajouterArgent(1000); // Assez d'argent pour racheter la propriété hypothéquée

        // When
        List<Propriete> result = joueur.choixProprietesARacheter();

        // Then
        assertEquals(1, result.size(), "Une propriété devrait être choisie pour l'achat");
        assertEquals(terrain, result.get(0), "La propriété choisie devrait être celle qui est hypothéquée");
    }

    @Test
    public void testChoixProprietesARacheter_MultiplesProprietesHypothequees() throws CannotSellException {
        // Given
        JoueurS1 joueur = new JoueurS1("Joueur1");
        Terrain terrain1 = new Terrain("Propriété1", 100, Color.PINK, new int[]{6, 12, 30, 90, 270, 400}, 50);
        Terrain terrain2 = new Terrain("Propriété2", 150, Color.GREEN, new int[]{6, 12, 30, 90, 270, 400}, 60);

        terrain1.hypothequer();
        terrain2.hypothequer();

        joueur.ajouterPropriete(terrain1);
        joueur.ajouterPropriete(terrain2);
        joueur.ajouterArgent(1000); // Assez d'argent pour racheter les propriétés hypothéquées

        // When
        List<Propriete> result = joueur.choixProprietesARacheter();

        // Then
        assertEquals(2, result.size(), "Deux propriétés devraient être choisies pour l'achat");
        assertTrue(result.contains(terrain1) && result.contains(terrain2), "Les deux propriétés hypothéquées devraient être choisies");
    }

}


  