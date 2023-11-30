/*package fr.pantheonsorbonne.miage.testPlateau;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.DeDouble;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class PrisonTest {

    

    private Prison prison;
    private Joueur joueur;

    @BeforeEach
    public void setUp() {
        prison = new Prison("prison de Herbaut");
        joueur = new JoueurS1("Nicolas Herbaut");
    }

    @Test
    public void testMettreJoueurEnPrison() {
        prison.mettreJoueurEnPrison(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));
        assertEquals(0, prison.getNombreToursPrison(joueur));
    }

    @Test
    public void testAugmenterNombreTours() {
        prison.mettreJoueurEnPrison(joueur);
        prison.augmenterNombreTours(joueur);
        assertEquals(1, prison.getNombreToursPrison(joueur));
    }

    @Test
    public void testSortirPrison() {
        prison.mettreJoueurEnPrison(joueur);
        prison.sortirPrison(joueur);
        assertFalse(prison.getListeNbTours().containsKey(joueur));
    }

    @Test
    public void testSortirPrisonDoubleDe() {
        DeDoubleMock deMock = new DeDoubleMock();
        prison.mettreJoueurEnPrison(joueur);
        prison.sortirPrisonDoubleDe(joueur);
        assertFalse(prison.getListeNbTours().containsKey(joueur));
        deMock.setMemeValeur(true);
        prison.mettreJoueurEnPrison(joueur);
        prison.sortirPrisonDoubleDe(joueur);
        assertFalse(prison.getListeNbTours().containsKey(joueur));

        deMock.setMemeValeur(false);
        prison.sortirPrisonDoubleDe(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));
    }

    @Test
    public void testSortirPrisonNbTours() {
        prison.mettreJoueurEnPrison(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));

        prison.augmenterNombreTours(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));

        prison.augmenterNombreTours(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));

        prison.augmenterNombreTours(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));

        prison.augmenterNombreTours(joueur);
        assertFalse(prison.getListeNbTours().containsKey(joueur));
        
    }

    @Test
    public void testSortirPrisonPayer() {
        joueur.ajouterArgent(300);
        double soldeAvantPrison = joueur.getPorteMonnaie();
        prison.mettreJoueurEnPrison(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));
       

        prison.sortirPrisonPayer(joueur);
        
        assertFalse(prison.getListeNbTours().containsKey(joueur));

        // Test que le joueur a bien payé le montant approprié
        assertEquals(soldeAvantPrison - Prison.MONTANT_SORTIR, joueur.getPorteMonnaie());

    }

    // Classe de mock pour simuler le comportement du dé
    private class DeDoubleMock extends DeDouble {
        private boolean memeValeur;

        public void setMemeValeur(boolean memeValeur) {
            this.memeValeur = memeValeur;
        }

        @Override
        public boolean memeValeur() {
            return memeValeur;
        }
    }
}

*/
package fr.pantheonsorbonne.miage.testPlateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.DeDouble;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;

import static org.junit.jupiter.api.Assertions.*;

public class PrisonTest {

    private Prison prison;
    private Joueur joueur;

    @BeforeEach
    public void setUp() {
        prison = new Prison("prison de Herbaut");
        joueur = new JoueurS1("Nicolas Herbaut");
    }

    @Test
    public void testMettreJoueurEnPrison() {
        prison.mettreJoueurEnPrison(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));
        assertEquals(0, prison.getNombreToursPrison(joueur));
    }

    @Test
    public void testAugmenterNombreTours() {
        prison.mettreJoueurEnPrison(joueur);
        prison.augmenterNombreTours(joueur);
        assertEquals(1, prison.getNombreToursPrison(joueur));
    }

    @Test
    public void testSortirPrison() {
        prison.mettreJoueurEnPrison(joueur);
        prison.sortirPrison(joueur);
        assertFalse(prison.getListeNbTours().containsKey(joueur));
    }

    @Test
    public void testSortirPrisonDoubleDe() {
        DeDoubleMock deMock = new DeDoubleMock();
        prison.mettreJoueurEnPrison(joueur);
        prison.sortirPrisonDoubleDe(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));
        deMock.setMemeValeur(true);
        prison.mettreJoueurEnPrison(joueur);
        prison.sortirPrisonDoubleDe(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));

        deMock.setMemeValeur(false);
        prison.sortirPrisonDoubleDe(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));
    }

    @Test
    public void testSortirPrisonNbTours() {
        prison.mettreJoueurEnPrison(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));

        prison.augmenterNombreTours(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));

        prison.augmenterNombreTours(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));

        prison.augmenterNombreTours(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));

        prison.sortirPrisonNbTours(joueur); // réinitialisation ici
        assertFalse(prison.getListeNbTours().containsKey(joueur));
    }

    @Test
    public void testSortirPrisonPayer() {
        joueur.ajouterArgent(300);
        double soldeAvantPrison = joueur.getPorteMonnaie();
        prison.mettreJoueurEnPrison(joueur);
        assertTrue(prison.getListeNbTours().containsKey(joueur));

        prison.sortirPrisonPayer(joueur);

        assertFalse(prison.getListeNbTours().containsKey(joueur));

        // Test que le joueur a bien payé le montant approprié
        assertEquals(soldeAvantPrison - Prison.MONTANT_SORTIR, joueur.getPorteMonnaie());
    }

    // Classe de mock pour simuler le comportement du dé
    private class DeDoubleMock extends DeDouble {
        private boolean memeValeur;

        public void setMemeValeur(boolean memeValeur) {
            this.memeValeur = memeValeur;
        }

        @Override
        public boolean memeValeur() {
            return memeValeur;
        }
    }
}
