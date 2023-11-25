package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.CaisseDeCommunaute;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaisseDeCommunauteTest {

    @Test
    public void testGetName() {
        CaisseDeCommunaute caisseDeCommunaute = new CaisseDeCommunaute("Test Caisse de Communaute");
        String name = caisseDeCommunaute.getName();
        assertEquals("Test Caisse de Communaute", name);
    }

    @Test
    public void testGetIdCase() {
        CaisseDeCommunaute caisseDeCommunaute = new CaisseDeCommunaute("Test Caisse de Communaute");
        int idCase = caisseDeCommunaute.getIdCase();
        assertEquals(Case.getNombreCases() - 1, idCase);
    }
}
