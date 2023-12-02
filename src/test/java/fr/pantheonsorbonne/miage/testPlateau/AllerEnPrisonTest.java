package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.AllerEnPrison;

import static org.junit.jupiter.api.Assertions.*;

public class AllerEnPrisonTest {

    @Test
    public void testGetName() {
        AllerEnPrison allerEnPrison = new AllerEnPrison("Test AllerEnPrison");
        assertEquals("Test AllerEnPrison", allerEnPrison.getName());
    }

    @Test
    public void testGetIdCase() {
        AllerEnPrison allerEnPrison = new AllerEnPrison("Test AllerEnPrison");
        assertEquals(2, allerEnPrison.getIdCase());
    }

}
