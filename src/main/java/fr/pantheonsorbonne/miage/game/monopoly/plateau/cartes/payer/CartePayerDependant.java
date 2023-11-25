package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.payer;

import java.util.List;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public class CartePayerDependant extends CartePayer {
    
    private int montantMaison;
    private int montantHotel;

    public CartePayerDependant(String description, int montantMaison, int montantHotel) {
        super(description);
        this.montantMaison = montantMaison;
        this.montantHotel = montantHotel;
    }


    @Override
    public int getMontantAPayer(fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur joueur) {
        int nombreMaisons = 0;
        int nombreHotels = 0;
        List<Propriete> listeProprietes = joueur.getProperties();
        for (Propriete propriete : listeProprietes) {
            if (propriete instanceof Terrain) {
                Terrain terrain = (Terrain) propriete;
                nombreMaisons += terrain.getNombreMaisons();
                if(terrain.estHotel()) {
                nombreHotels++;
            }
            }
        }

        return (nombreMaisons*montantMaison + nombreHotels*montantHotel);

    }

}
