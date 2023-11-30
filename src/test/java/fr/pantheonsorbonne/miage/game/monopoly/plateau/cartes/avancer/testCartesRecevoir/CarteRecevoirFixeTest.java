package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.testCartesRecevoir;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.recevoir.CarteRecevoirFixe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarteRecevoirFixeTest {

    @Test
    public void testAppliquerEffet() {
        Joueur joueurTest = new JoueurS1("Pepe");
        CarteRecevoirFixe carteTest = new CarteRecevoirFixe("Recevez un montant fixe", 150);
        int argentInitial = 500;
        joueurTest.ajouterArgent(argentInitial);
        carteTest.appliquerEffet(joueurTest);

        assertEquals(argentInitial + 150, joueurTest.getPorteMonnaie());
    }
}
