package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.CannotSellException;

import static org.junit.jupiter.api.Assertions.*;

public class CannotSellExceptionTest {

    @Test
    public void testConstructorAndGetMessage() {
        String message = "Test exception message";
        CannotSellException exception = new CannotSellException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testDefaultConstructor() {
        CannotSellException exception = new CannotSellException(null);
        assertNull(exception.getMessage());
    }

    // Ajoutez d'autres tests si nécessaire
}
