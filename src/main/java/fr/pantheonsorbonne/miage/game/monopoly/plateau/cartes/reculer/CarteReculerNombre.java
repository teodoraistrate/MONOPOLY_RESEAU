package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer;

public class CarteReculerNombre extends CarteReculer {

    private int nombreCases;

    public CarteReculerNombre(String description, int nombreCases) {
        super(description);
        this.nombreCases = nombreCases;
    }

    @Override
    public int getNouvellePosition(fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur joueur){
        int nouvellePosition;
        if (joueur.getPositionPlateau() < nombreCases) {
            nouvellePosition = 40 + joueur.getPositionPlateau() - nombreCases;
        }
        else {
            nouvellePosition = joueur.getPositionPlateau()-nombreCases;
        }
        return nouvellePosition;
    }
    
}
