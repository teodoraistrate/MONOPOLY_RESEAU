package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.testJeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.JeuLocal;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS2;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public class JeuLocalTest {

    private JeuLocal jeuLocal;

    @BeforeEach
    public void setUp() {
        jeuLocal = new JeuLocal();
    }
    

    @Test
    public void testAchatPropriete() {
        JeuLocal jeu = JeuLocal.getInstance();
        JoueurS1 joueur1 = new JoueurS1("Joueur1");
        Terrain propriete = new Terrain("Boulevard de Belleville", 60, Color.BLACK, new int[] { 2, 4, 10, 30, 90, 160 }, 50);

        jeu.getListeJoueurs().add(joueur1);
        joueur1.ajouterArgent(1000);

        // Achat de la propriété
        try {
            joueur1.acheterPropriete(propriete);
        } catch (Exception e) {
            fail("Exception inattendue lors de l'achat de propriété : " + e.getMessage());
        }
        assertEquals(joueur1, propriete.getProprietaire());
        assertTrue(joueur1.getProperties().contains(propriete));
        assertEquals(940, joueur1.getPorteMonnaie(), 0.001); // Assurez-vous de mettre le montant correct après l'achat
    }



    @Test
    public void testRemoveJoueur() {
        Joueur joueur1 = new JoueurS1("Joueur 1");
        Joueur joueur2 = new JoueurS2("Joueur 2");
        jeuLocal.getListeJoueurs().add(joueur1);
        jeuLocal.getListeJoueurs().add(joueur2);

        jeuLocal.removeJoueur(joueur1);

        assertFalse(jeuLocal.getListeJoueurs().contains(joueur1));
        assertTrue(jeuLocal.getListeJoueurs().contains(joueur2));
    }

    @Test
    public void testGetNombrePrisonsAdditionnelles() {
        jeuLocal.reInitialiseNbPrisons();

        int nombrePrisons = jeuLocal.getNombrePrisonsAdditionnelles();

        assertEquals(0, nombrePrisons);

        JeuLocal.augmenterNbPrisonsAdd();

        assertEquals(1, jeuLocal.getNombrePrisonsAdditionnelles());
    }

     @Test
    public void testInitialiserListeJoueurs() {
        assertNotNull(jeuLocal); 

        jeuLocal.initialiserListeJoueurs();

        assertEquals(3, jeuLocal.getListeJoueurs().size());
        assertTrue(jeuLocal.getListeJoueurs().get(0) instanceof JoueurS1);
        assertTrue(jeuLocal.getListeJoueurs().get(1) instanceof JoueurS2);
    }
    


    
    @Test
    public void testJeuLocalMain() {
        // Rediriger la sortie standard vers un flux pour capturer les résultats de l'exécution
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Exécuter la méthode main
        JeuLocal.main(null);

        // Récupérer la sortie
        String output = outContent.toString();

        // Rétablir la sortie standard
        System.setOut(System.out);

        // Ajoutez vos assertions ici en fonction des résultats attendus dans la sortie
        assertTrue(output.contains("Victoire de:"));
        // Ajoutez d'autres assertions en fonction de la structure de votre sortie
    }

    @Test
    public void testSortirPrison() {
        Prison prison = Prison.getInstance("Prison");
        Joueur joueur = new JoueurS1("j1");

        prison.mettreJoueurEnPrison(joueur);

        assertTrue(joueur.estEnPrison());
        assertEquals(0,prison.getListeNbTours().get(joueur));

        prison.augmenterNombreTours(joueur);

        assertEquals(1,prison.getListeNbTours().get(joueur));
    }


}
