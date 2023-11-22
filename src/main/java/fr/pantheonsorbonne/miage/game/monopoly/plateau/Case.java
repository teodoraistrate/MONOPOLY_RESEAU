package fr.pantheonsorbonne.miage.game.monopoly.plateau;

public class Case {
    private static int nombreCases = 0;
    private int idCase;
    private String name;

    protected Case(String name) {
        this.name = name;
        this.idCase = nombreCases++;
    }

    public String getName() {
        return name;
    }

    public int getIdCase() {
        return idCase;
    }

}
