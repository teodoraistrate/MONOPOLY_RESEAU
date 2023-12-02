package fr.pantheonsorbonne.miage.testPlateau;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;

public class CaseTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("TestJoueur");
    }

    @Test
    void testGetName() {
        Case caseTest = new CaseTestImpl("Test Case");
        assertEquals("Test Case", caseTest.getName());
    }

    @Test
    void testGetIdCase() {
        Case caseTest1 = new CaseTestImpl("Test Case 1");
        Case caseTest2 = new CaseTestImpl("Test Case 2");
        assertEquals(caseTest1.getIdCase() + 1, caseTest2.getIdCase());
    }

    @Test
    void testAppliquerEffetCase() {
        Case caseTest = new CaseTestImpl("Test Case");
        Joueur joueur = new JoueurS1("Test Joueur");
    }
    private static class CaseTestImpl extends Case {
        public CaseTestImpl(String name) {
            super(name);
        }

        @Override
        public void appliquerEffetCase(Joueur joueur) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'appliquerEffetCase'");
        }

    
    }

}
