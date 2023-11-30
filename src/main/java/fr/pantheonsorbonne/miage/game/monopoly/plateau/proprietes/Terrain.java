package fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;

public class Terrain extends Propriete {

    Plateau plateau = Plateau.getInstance();

    private int[] tableauLoyer;
    private int nombreMaisons = 0;
    private boolean estHotel = false;
    private int prixMaison;
    private Color color;

    public Terrain(String name, int price, Color color, int[] tableauLoyer, int prixMaison) {
        super(name, price);
        this.color = color;
        this.tableauLoyer = tableauLoyer;
        this.prixMaison = prixMaison;
    }

    // getteurs

    public int getPrixMaison() {
        return prixMaison;
    }

    public int[] getTableauLoyer() {
        return tableauLoyer;
    }

    public int getNombreMaisons() {
        return nombreMaisons;
    }

    public void augmenterNbMaisons() {
        this.nombreMaisons++;
    }

    @Override
    public int getLoyer() {
        if (this.tousTerrainsMemeCouleur(this.getColor())) {
            if (this.estHotel())
                return tableauLoyer[5];
            else if (this.getNombreMaisons()>1) {
                return tableauLoyer[this.getNombreMaisons()];
            } else {
                return tableauLoyer[0]*2;
            }
        }
        return tableauLoyer[0];
    }

    public boolean tousTerrainsMemeCouleur(Color couleur) {
        boolean resultat = true;
        Plateau plateau = Plateau.getInstance();
        List<Terrain> listeT = plateau.getTerrainsMemeCouleur(couleur);
        for (Terrain t : listeT) {
            if(t.getProprietaire() != this.getProprietaire()) {
                resultat = false;
            }
        }
        return resultat;
    }

    public Map<Terrain,Integer> getListeNombreMaisons() {
        Map<Terrain,Integer> listeNombreMaisons = new HashMap<>();
        List<Terrain> terrains = plateau.getTerrainsMemeCouleur(this.getColor());
        for (Terrain t : terrains) {
            listeNombreMaisons.put(t, t.getNombreMaisons());
        }
        return listeNombreMaisons;
    }

    public Color getColor() {
        return color;
    }
    
    // is a

    public boolean estHotel() {
        return estHotel;
    }

    // autres methodes

    public void acheterMaison() throws CannotBuildException, PasAssezArgentException {
        if (this.getProprietaire().getPorteMonnaie()<this.getPrixMaison()) {
            throw new PasAssezArgentException("Vous n'avez pas assez d'argent pour acheter une maison");
        } else {
            if (!this.tousTerrainsMemeCouleur(this.getColor())) {
                throw new CannotBuildException("Vous n'avez pas tous les terrains de la couleur "+this.getColor()+", donc vous ne pouvez pas construire la maison!");
            } else {
                Map<Terrain,Integer> listeNombreMaisons = this.getListeNombreMaisons();
                int minimumNbMaisons = 4;
                for (Terrain t : listeNombreMaisons.keySet()) {
                    if (t.getNombreMaisons() < minimumNbMaisons) {
                        minimumNbMaisons = t.getNombreMaisons();
                    }
                }
                for (Terrain tr : listeNombreMaisons.keySet()) {
                    if (listeNombreMaisons.get(tr) == minimumNbMaisons) {
                        tr.getProprietaire().payer(tr.getPrixMaison());
                        tr.augmenterNbMaisons();
                        break; // il ne va acheter qu'une maison
                    }
                }
            }
        }
    }

    public void acheterHotel() throws CannotBuildException, PasAssezArgentException {
        if (this.getProprietaire().getPorteMonnaie()<this.getPrixMaison()) {
            throw new PasAssezArgentException("Vous n'avez pas assez d'argent pour acheter une maison");
        } else if (!this.tousTerrainsMemeCouleur(this.getColor())) {
            throw new CannotBuildException("Vous n'avez pas tous les terrains de la couleur "+this.getColor()+", donc vous ne pouvez pas construire l'hotel!");
        } else {
            Map<Terrain,Integer> listeNombreMaisons = this.getListeNombreMaisons();
            for (Terrain t : listeNombreMaisons.keySet()) {
                if (listeNombreMaisons.get(t) != 4 && listeNombreMaisons.get(t) != 0) {
                    throw new CannotBuildException("Vous n'avez construit le maximum de maisons sur les terrains de cette couleur!");
                }
            }

            this.getProprietaire().payer(prixMaison);
            estHotel = true;
            nombreMaisons = 0;
        }
    }

    public void vendreHotel() throws CannotSellException {
        if (!this.estHotel())
            throw new CannotSellException("Vous n'avez pas d'hotel sur ce terrain!");
        this.estHotel = false;
        this.getProprietaire().ajouterArgent(prixMaison / 2);
        this.nombreMaisons = 4;
    }

    public void vendreMaison() throws CannotSellException {
        if (this.getNombreMaisons() == 0)
            throw new CannotSellException("Vous n'avez pas de maison sur ce terrain!");
        this.getProprietaire().ajouterArgent(prixMaison / 2);
        this.nombreMaisons--;
    }

}
