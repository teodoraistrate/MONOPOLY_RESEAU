package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.joueur;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Compagnie;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JoueurS1Test {

    @Test
    public void testChoixAcheterPropriete() {
        JoueurS1 joueur = new JoueurS1("TestJoueur");
        Propriete proprieteChere = new Compagnie("Propriete Chère", 1000);
        Propriete proprieteAbordable = new Compagnie("Propriete Abordable", 200);

        assertTrue(joueur.choixAcheterPropriete(proprieteAbordable));
        assertFalse(joueur.choixAcheterPropriete(proprieteChere));
    }

    @Test
    public void testChoixPayerOuChance() {
        JoueurS1 joueur = new JoueurS1("TestJoueur");
        CartePayerOuChance c = new CartePayerOuChance("carte", 20);

        int montantSauv = c.getMontantAPayer();

        // Le joueur a suffisamment d'argent pour payer
        joueur.ajouterArgent(300);
        assertFalse(joueur.choixPayerOuChance(c));

        // Le joueur n'a pas assez d'argent pour payer
        joueur.ajouterArgent(150);
        assertTrue(joueur.choixPayerOuChance(c));
    }

    @Test
    public void testChoixSortirPrison() {
        JoueurS1 joueur = new JoueurS1("TestJoueur");

        // Le joueur a suffisamment d'argent pour sortir de prison
        joueur.ajouterArgent(110);
        assertTrue(joueur.choixSortirPrison());

        // Le joueur n'a pas assez d'argent pour sortir de prison
        joueur.ajouterArgent(100);
        assertFalse(joueur.choixSortirPrison());
    }

    @Test
    public void testChoixProprietesAHypothequer() {
        JoueurS1 joueur = new JoueurS1("TestJoueur");

        // Création de propriétés avec des prix variés
        Propriete p1 = new Compagnie("Comp", 100);
        Propriete p2 = new Compagnie("Comp", 200);
        Propriete p3 = new Compagnie("Comp", 300);
        Propriete p4 = new Compagnie("Comp", 400);

        // Le joueur a moins de 500 d'argent, donc il devrait choisir les trois premières propriétés
        joueur.ajouterArgent(400);
        joueur.ajouterPropriete(p1);
        joueur.ajouterPropriete(p2);
        joueur.ajouterPropriete(p3);
        joueur.ajouterPropriete(p4);

        List<Propriete> choix = joueur.choixProprietesAHypothequer();
        assertEquals(3, choix.size());
        assertTrue(choix.contains(p1));
        assertTrue(choix.contains(p2));
        assertTrue(choix.contains(p3));
        assertFalse(choix.contains(p4));
    }

}
