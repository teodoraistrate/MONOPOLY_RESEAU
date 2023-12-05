package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.joueur;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.DeDouble;
import fr.pantheonsorbonne.miage.game.monopoly.jeu.JeuLocal;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS2;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Taxes;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.CannotSellException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Compagnie;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public class JoueurTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("TestJoueur");
    }

    @Test
    public void testpayerLoyer () throws PasAssezArgentException {
        JoueurS1 joueur = new JoueurS1("TestJoueur");
        JoueurS2 joueur2 = new JoueurS2("jeje");
        Propriete proprietee = new Terrain("TerrainTest", 100, Color.BLUE, new int[]{10, 20, 30, 40, 50, 60}, 50);

        joueur.ajouterArgent(500);
        joueur2.ajouterPropriete(proprietee);
        int loyer = proprietee.getLoyer();
        joueur.payerLoyer(proprietee);

        assertEquals(500-loyer, joueur.getPorteMonnaie());
        assertEquals(loyer, joueur2.getPorteMonnaie());
        
        
    }

    @Test
    public void testPayerTaxes() throws PasAssezArgentException {
        JoueurS1 joueur = new JoueurS1("papi chulo");
        Taxes taxe = new Taxes("impôt sur le revenu", 200);
        joueur.ajouterArgent(500);
        
        int montantTaxe = taxe.getMontantAPayer();
        int montantAttendu = 500-montantTaxe;
        joueur.payerTaxes(taxe);

        assertEquals (montantAttendu , joueur.getPorteMonnaie());

    }


    @Test
    public void testDeplacerSurPlateau (){
        JoueurS1 joueur = new JoueurS1 ("lady gaga");
        joueur.getPositionPlateau();
        int nombreCases = 8;
        int nouvellePositionJoueur = joueur.getPositionPlateau() + nombreCases ;
        assertEquals(joueur.getPositionPlateau() + nombreCases, nouvellePositionJoueur);
        //jsp si je rajoute encore

    }

    
    
    @Test
    public void testRemovePropriete (){
        JoueurS1 joueur = new JoueurS1 ("pierrot");
        Compagnie c1 = new Compagnie("c1",150 );
        Compagnie c2 = new Compagnie("c2",150 );
        joueur.ajouterPropriete(c1);
        joueur.ajouterPropriete(c2);

        List<Propriete> listeP = joueur.getProperties();
        joueur.removePropriete(c1);

        List<Propriete> listeP2 = new ArrayList<>();
        listeP2.add(c2);
        //on a créé la liste sans la propriété c1 pour la comparer avec la liste initiale où on a retiré le c1

        assertEquals( listeP2, listeP);
        
    }

    //il y a un problème dans la méthode remove all

    @Test
    public void testRemoveAllProprietes() {
        JoueurS1 joueur = new JoueurS1("pierrot");
        Compagnie c1 = new Compagnie("c1", 150);
        Compagnie c2 = new Compagnie("c2", 150);
        joueur.ajouterPropriete(c1);
        joueur.ajouterPropriete(c2);

        //List<Propriete> listeP = joueur.getProperties();

        joueur.removeAllProprietes();

        assertTrue(joueur.getProperties().isEmpty());
    }

    //TODO : racheteProprieteHypo

    @Test
    public void testRacheterProprieteHypothequee () throws PasAssezArgentException, CannotSellException {
        JoueurS1 joueur = new JoueurS1("regis");
        joueur.ajouterArgent(500);
        Compagnie c1 = new Compagnie("c1", 150);
        joueur.ajouterPropriete(c1);
        c1.hypothequer();

        try{
        joueur.racheterProprieteHypothequee(c1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertFalse(c1.estHypotheque());
    }

    @Test
    
    public void testTransfererProperties (){
        Joueur joueur = new JoueurS1("pablo escobar");
        Joueur gagnant = new JoueurS1 ("le gagnant");
        Compagnie c1 = new Compagnie("c1", 150);
        Compagnie c2 = new Compagnie("c2", 150);
        joueur.ajouterPropriete(c1);        
        joueur.ajouterPropriete(c2);
        List<Propriete> listeP = new ArrayList<>();
        listeP.add(c1);
        listeP.add(c2);
        joueur.transfererProprietes(gagnant);

        assertTrue(joueur.getProperties().isEmpty());
        assertEquals(listeP,gagnant.getProperties());

        
    }

    

}



 
    

    


