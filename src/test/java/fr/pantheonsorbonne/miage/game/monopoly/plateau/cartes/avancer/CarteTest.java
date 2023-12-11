package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class CarteTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("TestJoueur");
    }



    @Test
    public void testCarteConstruction() {
        String description = "Test Carte";
        Carte carte = new Carte(description) {
            @Override
            public void appliquerEffet(Joueur joueur) throws PasAssezArgentException, NomPasValideException {
            }
        };

        assertNotNull(carte);
        assertEquals(description, carte.description);
    }

    @Test
    public void testAppliquerEffet() {
        Joueur joueurTestTest = new JoueurS1("gege"); 
        Carte carte = new Carte("Test Carte") {
            @Override
            public void appliquerEffet(Joueur joueur) throws PasAssezArgentException, NomPasValideException {
            }
        };
        assertDoesNotThrow(() -> carte.appliquerEffet(joueurTestTest));
    }

    

}
