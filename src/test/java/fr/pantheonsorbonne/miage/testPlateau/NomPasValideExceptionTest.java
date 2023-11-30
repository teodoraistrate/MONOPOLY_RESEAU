package fr.pantheonsorbonne.miage.testPlateau;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;

public class NomPasValideExceptionTest {

    @Test
    public void testNomPasValideException() {
        String message = "Le nom n'est pas valide.";
        NomPasValideException exception = new NomPasValideException(message);
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

}
