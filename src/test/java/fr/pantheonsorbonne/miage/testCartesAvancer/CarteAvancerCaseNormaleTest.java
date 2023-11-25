package fr.pantheonsorbonne.miage.testCartesAvancer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.CarteAvancerCaseNormale;
import org.junit.jupiter.api.Test;



public class CarteAvancerCaseNormaleTest {

    @Test
    public void testGetNouvellePosition() throws NomPasValideException {
        String nomCase = "Boulevard de BelleVille";
        CarteAvancerCaseNormale carte = new CarteAvancerCaseNormale("Avancez à Boulevard de BelleVille", nomCase);
        Joueur joueur = new Joueur("Test");

        assertEquals(1, carte.getNouvellePosition(joueur));
    }

    @Test
    public void testGetNouvellePositionAvecNomPasValide() {
        String nomCaseInvalide = "Case Inexistante";
        CarteAvancerCaseNormale carte = new CarteAvancerCaseNormale("Avancez à Case Inexistante", nomCaseInvalide);
        Joueur joueur = new Joueur("Test");

        // Utilisez assertThrows pour vérifier si une exception est levée
        assertThrows(NomPasValideException.class, () -> carte.getNouvellePosition(joueur));
    }
}
