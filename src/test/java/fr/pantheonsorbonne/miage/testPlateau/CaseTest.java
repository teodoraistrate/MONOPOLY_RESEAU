package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaseTest {
    @Test
    public void testGetName() {
        String expectedName = "Test Case";
        Case testCase = new ConcreteCase(expectedName);
        String actualName = testCase.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetIdCase() {
        Case testCase1 = new ConcreteCase("Case 1");
        Case testCase2 = new ConcreteCase("Case 2");
        int idCase1 = testCase1.getIdCase();
        int idCase2 = testCase2.getIdCase();
        assertEquals(0, idCase1);
        assertEquals(1, idCase2);
    }

    private static class ConcreteCase extends Case {
        public ConcreteCase(String name) {
            super(name);
        }
    }
}
