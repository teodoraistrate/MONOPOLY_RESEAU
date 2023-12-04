package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.joueur;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Compagnie;
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


}


  