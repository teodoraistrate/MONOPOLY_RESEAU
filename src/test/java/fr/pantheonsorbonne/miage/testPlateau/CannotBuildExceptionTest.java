package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.CannotBuildException;

import static org.junit.jupiter.api.Assertions.*;

public class CannotBuildExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Test exception message";
        CannotBuildException exception = new CannotBuildException(message);

        assertEquals(message, exception.getMessage());
    }

}
