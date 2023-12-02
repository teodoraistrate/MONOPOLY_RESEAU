package fr.pantheonsorbonne.miage.testPlateau;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;

class CaseDeTest extends Case {
    public CaseDeTest(String name) {
        super(name);
    }

    @Override
    public void appliquerEffetCase(Joueur joueur) {
    }
}

public class CaseTest {

    @Test
    public void testGetters() {
        Case caseTest = new CaseDeTest("Case de test");

        assertEquals(0, caseTest.getIdCase());
        assertEquals("Case de test", caseTest.getName());
    }
    /* 
    @Test
    public void testAppliquerEffetCase() {
        Case caseTest = new CaseDeTest("Case de test");
        Joueur joueur = new JoueurS1("manon");
        assertDoesNotThrow(() -> caseTest.appliquerEffetCase(joueur));
    }
    */

}
