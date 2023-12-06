package fr.pantheonsorbonne.miage.testPlateau;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

import java.awt.Color;
import java.util.List;

public class PlateauTest {

    @Test
    public void testGetCaseParId() {
        Plateau plateau = Plateau.getInstance();
        Case caseDepart = plateau.getCaseParId(0);
        assertNotNull(caseDepart);
        assertEquals("Case départ", caseDepart.getName());
    }

    @Test
    public void testGetInstance() {
        Plateau plateau1 = Plateau.getInstance();
        Plateau plateau2 = Plateau.getInstance();
        assertSame(plateau1, plateau2);
    }

    @Test
    public void testGetPlateau() {
        Plateau plateau = Plateau.getInstance();
        List<Case> cases = plateau.getPlateau();
        assertNotNull(cases);
        assertEquals(43, cases.size());
    }

    @Test
    public void testGetCaseParNom() throws NomPasValideException {
        Plateau plateau = Plateau.getInstance();
        int id = plateau.getCaseParNom("Case départ");
        assertEquals(0, id);

        String nom = plateau.getPlateau().get(39).getName();
        assertEquals("Rue de la Paix", nom);
        int id2 = plateau.getCaseParNom("Rue de la Paix");
        assertEquals(39, id2);
    }

    @Test
    public void testGetTerrainsMemeCouleur() {
        Plateau plateau = Plateau.getInstance();
        List<Terrain> terrainsNoirs = plateau.getTerrainsMemeCouleur(Color.BLACK);
        assertNotNull(terrainsNoirs);
        assertEquals(3, terrainsNoirs.size());
    }

}