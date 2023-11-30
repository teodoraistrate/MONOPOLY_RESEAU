package fr.pantheonsorbonne.miage.testPlateau;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.DeDouble;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;


import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.DejaAcheteException;

import static org.junit.jupiter.api.Assertions.*;

public class PrisonTest {

    private Prison prison;
    private Joueur joueur;

    @Before
    public void setUp() {
        prison = new Prison("Prison");
        joueur = new JoueurS1("Alice");
    }

    @Test
    public void testMettreJoueurEnPrison() {
        prison.mettreJoueurEnPrison(joueur);
        assertTrue(prison.getNombreToursPrison().containsKey(joueur));
        assertEquals(0, prison.getNombreToursPrison().get(joueur).intValue());
    }

    @Test
    public void testAugmenterNombreTours() {
        prison.mettreJoueurEnPrison(joueur);
        prison.augmenterNombreTours(joueur);
        assertEquals(1, prison.getNombreToursPrison().get(joueur).intValue());
    }

    @Test
    public void testSortirPrison() {
        prison.mettreJoueurEnPrison(joueur);
        prison.sortirPrison(joueur);
        assertFalse(prison.getNombreToursPrison().containsKey(joueur));
    }

    @Test
    public void testSortirPrisonDoubleDe() {
        DeDoubleMock deMock = new DeDoubleMock();
        prison.mettreJoueurEnPrison(joueur);
        prison.sortirPrisonDoubleDe(joueur, deMock);
        assertFalse(prison.getNombreToursPrison().containsKey(joueur));

        deMock.setMemeValeur(true);
        prison.mettreJoueurEnPrison(joueur);
        prison.sortirPrisonDoubleDe(joueur, deMock);
        assertFalse(prison.getNombreToursPrison().containsKey(joueur));

        deMock.setMemeValeur(false);
        prison.sortirPrisonDoubleDe(joueur, deMock);
        assertTrue(prison.getNombreToursPrison().containsKey(joueur));
    }

    @Test
    public void testSortirPrisonNbTours() {
        prison.mettreJoueurEnPrison(joueur);
        assertFalse(prison.getNombreToursPrison().containsKey(joueur));

        prison.augmenterNombreTours(joueur);
        assertFalse(prison.getNombreToursPrison().containsKey(joueur));

        prison.augmenterNombreTours(joueur);
        assertFalse(prison.getNombreToursPrison().containsKey(joueur));

        prison.augmenterNombreTours(joueur);
        assertTrue(prison.getNombreToursPrison().containsKey(joueur));
    }

    @Test
    public void testSortirPrisonPayer() {
        prison.mettreJoueurEnPrison(joueur);
        assertFalse(prison.getNombreToursPrison().containsKey(joueur));

        prison.sortirPrisonPayer(joueur);
        
        assertTrue(prison.getNombreToursPrison().containsKey(joueur));
        assertEquals(0, prison.getNombreToursPrison().get(joueur).intValue());

        // Test que le joueur a bien payé le montant approprié
        assertEquals(Joueur.SOLDE_INITIAL - Prison.MONTANT_SORTIR, joueur.getName());
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
