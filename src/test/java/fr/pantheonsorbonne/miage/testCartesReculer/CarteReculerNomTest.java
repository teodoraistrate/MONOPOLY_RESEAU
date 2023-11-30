package fr.pantheonsorbonne.miage.testCartesReculer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer.CarteReculerNom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarteReculerNomTest {

    @Test
    public void testGetNouvellePosition() {
        Joueur joueurTest = new JoueurS1("Teodora");
        CarteReculerNom carteTest = new CarteReculerNom("Reculez jusqu'à une case spécifique", "CaseTest");
        int positionActuelle = 10;
        try {
            int nouvellePosition = carteTest.getNouvellePosition(joueurTest);
                        assertEquals(-1, nouvellePosition);
        } catch (NomPasValideException e) {
            fail("Exception non attendue pendant le test");
        }
    }
}
