package fr.pantheonsorbonne.miage.testPlateau;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Start;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlateauTest {

    private static final int EXPECTED_PLATEAU_SIZE = Plateau.DIMENSION_PLATEAU;
    private static final String TERRAIN_NAME = "Boulevard de BelleVille";
    private static final int TERRAIN_ID = 1;

    @Test
    public void testGetCaseParId() {
        Case caseDepart = new Start("Case départ");
        Case terrain = new Terrain(TERRAIN_NAME, 60, Color.BLACK, new int[]{2, 4, 10, 30, 90, 160}, 50);
        Case caseRecupereeDepart = Plateau.getCaseParId(0);
        Case caseRecupereeTerrain = Plateau.getCaseParId(TERRAIN_ID);
        
        assertEquals(caseDepart, caseRecupereeDepart);
        assertEquals(terrain, caseRecupereeTerrain);
    }

    @Test
    public void testPlateauSize() {
        int nombreCasesRecuperees = Plateau.getPlateau().size();
        assertEquals(EXPECTED_PLATEAU_SIZE, nombreCasesRecuperees);
    }

    @Test
    public void testGetCaseParNom() throws NomPasValideException {
        Plateau plateau = Plateau.getInstance();
        int idRecupere = plateau.getCaseParNom(TERRAIN_NAME);
        assertEquals(TERRAIN_ID, idRecupere);
    }

    @Test
    public void testGetCaseParNomNomPasValide() {
        String nomCaseNonExistant = "Case Inexistante";
        Plateau plateau = Plateau.getInstance();
        assertThrows(NomPasValideException.class, () -> plateau.getCaseParNom(nomCaseNonExistant));
    }
}
