package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarteTest {

    @Test
    public void testCarteConstruction() {
        String description = "Test Carte";
        Carte carte = new Carte(description) {
            @Override
            public void appliquerEffet(Joueur joueur) throws PasAssezArgentException, NomPasValideException {
                // Ne rien faire dans ce test, c'est juste une carte de test
            }
        };

        assertNotNull(carte);
        assertEquals(description, carte.description);
    }

    @Test
    public void testAppliquerEffet() {
        Joueur joueur = new JoueurS1("PlayerTest Caroline");
        Carte carte = new Carte("Test Carte") {
            @Override
            public void appliquerEffet(Joueur joueur) throws PasAssezArgentException, NomPasValideException {
            }
        };
        assertDoesNotThrow(() -> carte.appliquerEffet(joueur));

    }

}
