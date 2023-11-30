package fr.pantheonsorbonne.miage.testPlateau;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.CaisseDeCommunaute;

/*classe de test interne => pour pouvoir testé une méthode abstraite de la classe abstraite Case */

class CaisseDeCommunauteDeTest extends CaisseDeCommunaute {
    public CaisseDeCommunauteDeTest(String name) {
        super(name);
    }

    @Override
    public void appliquerEffetCase(Joueur joueur) {
    }
}

public class CaisseDeCommunauteTest {

    @Test
    public void testAppliquerEffetCase() {
        CaisseDeCommunaute caseTest = new CaisseDeCommunauteDeTest("Caisse de test");
        Joueur joueur = new JoueurS1("manon");
        assertDoesNotThrow(() -> caseTest.appliquerEffetCase(joueur));
    }

}
