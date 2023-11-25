import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Color;

public class PlateauTest {

    @Test
    public void testGetCaseParId() {
        Case caseDepart = new Start("Case départ");
        Case terrain = new Terrain("Boulevard de BelleVille",60, Color.BROWN, new int[] { 2, 4, 10, 30, 90, 160 }, 50);
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
        int idRecupere = Plateau.getCaseParNom(nomCase);
        assertEquals(idAttendu, idRecupere);
    }

    @Test(expected = NomPasValideException.class)
    public void testGetCaseParNomNomPasValide() throws NomPasValideException {
        String nomCaseNonExistant = "Case Inexistante";
        Plateau.getCaseParNom(nomCaseNonExistant);
    }
}
