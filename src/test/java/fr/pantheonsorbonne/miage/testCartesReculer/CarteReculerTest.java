package fr.pantheonsorbonne.miage.testCartesReculer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class CarteReculerTest {

    /* 
    @Test
    public void testAppliquerEffet() {
        Joueur joueur = new Joueur(null);
        joueur.getPositionPlateau(); 

        case.appliquerEffetCase(joueur);
        assertEquals(2, joueur.getPositionPlateau());
    }
    */
    @Test
    public void testAppliquerEffetAvecDecalageSupérieur() {
        Plateau plateau = Plateau.getInstance();
        List<Case> cases = plateau.getPlateau();
        
        Joueur joueur = new Joueur(null);
        joueur.deplacerNombreCases(38,true);
        int nouvellePosition = joueur.getPositionPlateau();
        Case k = cases.get(nouvellePosition);

        k.appliquerEffetCase(joueur);

        assertEquals(38, joueur.getPositionPlateau());
    }
    /* 
    @Test
    public void testAppliquerEffetAvecDecalageSurLePlateau() {
        Joueur joueur = new Joueur(null);
        joueur.getPositionPlateau();

        case.appliquerEffetCase(joueur);

        assertEquals(5, joueur.getPositionPlateau());
    }
    */
}
