package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.testCartePayer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.payer.CartePayerDependant;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartePayerDependantTest {

    @Test
    public void testGetMontantAPayer() {
        Joueur joueurTest = new JoueurS1("Nour");

        CartePayerDependant carteTest = new CartePayerDependant("Description de test", 50, 100);

        Terrain terrain1 = new Terrain("Terrain1", 200, 50, 2);
        Terrain terrain2 = new Terrain("Terrain2", 300, 100, 1);
        Terrain terrain3 = new Terrain("Terrain3", 400, 150, 0);
        Terrain terrainHotel = new Terrain("TerrainHotel", 500, 200, 0);

        joueurTest.acheterPropriete(terrain1);
        joueurTest.acheterPropriete(terrain2);
        joueurTest.acheterPropriete(terrain3);
        joueurTest.acheterPropriete(terrainHotel);

        terrain1.acheterMaison();
        terrain1.acheterMaison();
        terrain2.acheterMaison();
        terrain3.acheterHotel();

        int montantAPayer = carteTest.getMontantAPayer(joueurTest);
        assertEquals((2 * 50) + (1 * 50) + (1 * 100), montantAPayer);
    }
}
