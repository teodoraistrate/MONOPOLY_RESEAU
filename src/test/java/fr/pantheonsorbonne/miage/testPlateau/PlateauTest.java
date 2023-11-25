package fr.pantheonsorbonne.miage.testPlateau;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Start;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlateauTest {

    @Test
    public void testGetCaseParId() {
        Case caseDepart = new Start("Case départ");
        Case terrain = new Terrain("Boulevard de BelleVille", 60, Color.BLACK, new int[]{2, 4, 10, 30, 90, 160}, 50);
        Case caseRecupereeDepart = Plateau.getCaseParId(0);
        Case caseRecupereeTerrain = Plateau.getCaseParId(1);
        
        assertEquals(caseDepart, caseRecupereeDepart);
        assertEquals(terrain, caseRecupereeTerrain);
    }


    @Test
    public void testGetPlateau() {
        int nombreCasesAttendu = Plateau.DIMENSION_PLATEAU;
        int nombreCasesRecuperees = Plateau.getPlateau().size();
        assertEquals(nombreCasesAttendu, nombreCasesRecuperees);
    }

    @Test
public void testGetCaseParNom() throws NomPasValideException {
    String nomCase = "Boulevard de BelleVille";
    int idAttendu = 1;
    Plateau plateau = new Plateau();
    int idRecupere = plateau.getCaseParNom(nomCase);
    assertEquals(idAttendu, idRecupere);
}


   @Test
    public void testGetCaseParNomNomPasValide() {
        String nomCaseNonExistant = "Case Inexistante";
        Plateau plateau = new Plateau();
        assertThrows(NomPasValideException.class, () -> plateau.getCaseParNom(nomCaseNonExistant));
    }
}
