package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.DejaAcheteException;

import static org.junit.jupiter.api.Assertions.*;

public class DejaAcheteExceptionTest {

    @Test
    public void testConstructorAndGetMessage() {
        String message = "Test exception message";
        DejaAcheteException exception = new DejaAcheteException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testDefaultConstructor() {
        DejaAcheteException exception = new DejaAcheteException(null);
        assertNull(exception.getMessage());
    }

}
