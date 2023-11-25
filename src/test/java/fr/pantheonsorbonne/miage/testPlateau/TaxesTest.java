package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Taxes;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxesTest {

    @Test
    public void testConstructorAndGetMontantAPayer() {
        String expectedName = "Impôts sur le Revenu";
        int expectedMontant = 200;
        Taxes taxes = new Taxes(expectedName, expectedMontant);

        assertEquals(expectedName, taxes.getName());
        assertEquals(expectedMontant, taxes.getMontantAPayer());
    }
}
