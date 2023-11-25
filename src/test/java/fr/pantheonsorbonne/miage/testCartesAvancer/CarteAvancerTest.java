package fr.pantheonsorbonne.miage.testCartesAvancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.CarteAvancer;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.CarteAvancerCaseNormale;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarteAvancerTest {

    @Test
    public void testAppliquerEffetAvecNouvellePositionValide() throws PasAssezArgentException {
        CarteAvancer carteAvancer = mock(CarteAvancerCaseNormale.class, CALLS_REAL_METHODS);
        Joueur joueur = mock(Joueur.class);

        // Simuler la nouvelle position
        when(carteAvancer.getNouvellePosition(joueur)).thenReturn(5);

        // Appeler la méthode à tester
        carteAvancer.appliquerEffet(joueur);

        // Vérifier si la méthode deplacerNombreCases a été appelée avec la nouvelle position
        verify(joueur).deplacerNombreCases(5, true);
    }

    @Test
    public void testAppliquerEffetAvecNouvellePositionInvalide() throws NomPasValideException {
        CarteAvancer carteAvancer = mock(CarteAvancerCaseNormale.class, CALLS_REAL_METHODS);
        Joueur joueur = mock(Joueur.class);

        // Simuler une exception pour une nouvelle position invalide
        when(carteAvancer.getNouvellePosition(joueur)).thenThrow(new NomPasValideException("Case invalide"));

        // Utiliser assertThrows pour vérifier si l'exception est propagée
        assertThrows(PasAssezArgentException.class, () -> carteAvancer.appliquerEffet(joueur));

        // Vérifier que la méthode deplacerNombreCases n'a pas été appelée
        verify(joueur, never()).deplacerNombreCases(anyInt(), anyBoolean());
    }
}
