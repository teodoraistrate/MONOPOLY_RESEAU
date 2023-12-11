package fr.pantheonsorbonne.miage.testPlateau;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Chance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.CarteAvancerCaseNormale;

public class ChanceTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("TestJoueur");
    }    

    @Test
    public void testAppliquerEffetCase() {
        Joueur joueur = new JoueurS1("coco");
        Chance chance = new Chance("Chance");

        assertDoesNotThrow(() -> chance.appliquerEffetCase(joueur));
    }

    @Test
    public void testAppliquerEffetCarteAvancerCaseNormale() {
        Joueur joueur = new JoueurS1("coco");
        CarteAvancerCaseNormale carte = new CarteAvancerCaseNormale("Test", "Case Test");
    }

}
