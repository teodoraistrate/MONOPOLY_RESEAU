package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.testCartesRecevoir;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.recevoir.CarteRecevoir;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarteRecevoirTest {

    @Test
    public void testAppliquerEffet() throws PasAssezArgentException {
        Joueur joueurTest = new JoueurS1("Nono");
        CarteRecevoir carteTest = new CarteRecevoir("Description de test") {
            @Override
            protected int montantARecevoir() {
                return 200; 
            }
        };
        int argentInitial = 500;
        joueurTest.ajouterArgent(argentInitial);
        carteTest.appliquerEffet(joueurTest);
        assertEquals(argentInitial + 200, joueurTest.getPorteMonnaie());
    }
}
