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
    private boolean estSquatte;
    private int nombreToursInitialSquatteur;
    private boolean estPrisonAdditionnelle = false;
    private int loyerPrison;

    public Terrain(String name, int price, Color color, int[] tableauLoyer, int prixMaison) {
        super(name, price);
        this.color = color;
        this.tableauLoyer = tableauLoyer;
        this.prixMaison = prixMaison;
        this.loyerPrison = tableauLoyer[0]/10 +1;
    }

    // getteurs

    public int getPrixMaison() {
        return prixMaison;
    }

    public int[] getTableauLoyer() {
        return tableauLoyer;
    }

    public int getNombreToursInitialSquatteur() {
        return nombreToursInitialSquatteur;
    }

    public int getNombreMaisons() {
        return nombreMaisons;
    }

    
    public int getLoyerPrison() {
        return loyerPrison;
    }
    
    public void augmenterNbMaisons() {
        this.nombreMaisons++;
    }

    @Override
    public int getLoyer() {
        if (this.tousTerrainsMemeCouleur(this.getColor())) {
            if (this.estHotel())
                return tableauLoyer[5];
            else if (this.getNombreMaisons() > 1) {
                return tableauLoyer[this.getNombreMaisons()];
            } else {
                return tableauLoyer[0] * 2;
            }
        }
        return tableauLoyer[0];
    }

    public boolean tousTerrainsMemeCouleur(Color couleur) {
        boolean resultat = true;
        Plateau p = Plateau.getInstance();
        List<Terrain> listeT = p.getTerrainsMemeCouleur(couleur);
        for (Terrain t : listeT) {
            if (t.getProprietaire() != this.getProprietaire()) {
                resultat = false;
            }
        }
        return resultat;
    }

    public Map<Terrain, Integer> getListeNombreMaisons() {
        Map<Terrain, Integer> listeNombreMaisons = new HashMap<>();
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

    public boolean estSquatte() {
        return estSquatte;
    }

    public boolean estPrisonAdditionnelle() {
        return estPrisonAdditionnelle;
    }

    // autres methodes

    public void acheterMaison() throws CannotBuildException, PasAssezArgentException {
        if (this.getProprietaire().getPorteMonnaie() < this.getPrixMaison()) {
            throw new PasAssezArgentException("Vous n'avez pas assez d'argent pour acheter une maison");
        } else {
            if (!this.tousTerrainsMemeCouleur(this.getColor())) {
                throw new CannotBuildException("Vous n'avez pas tous les terrains de la couleur " + this.getColor()
                        + ", donc vous ne pouvez pas construire la maison!");
            } else {
                Map<Terrain, Integer> listeNombreMaisons = this.getListeNombreMaisons();

                // voir si on a déjà le nb maximum de maisons
                boolean toutesMaisonsPossibles = true; // true si le maximum (4) est déjà atteint
                for (Terrain t : listeNombreMaisons.keySet()) {
                    if ((t.getNombreMaisons() == 0 && !t.estHotel()) || t.getNombreMaisons() == 1
                            || t.getNombreMaisons() == 2 || t.getNombreMaisons() == 3) {
                        toutesMaisonsPossibles = false;
                    }
                }
                if (toutesMaisonsPossibles) {
                    throw new CannotBuildException("Vous avez construit toutes les maisons possibles!");
                }

                // chercher le nb minimum de maisons pour voir où on peut en construire une
                // autre
                int minimumNbMaisons = 5;
                Terrain terrainChoisi = null;
                for (Terrain t : listeNombreMaisons.keySet()) {
                    if (t.getNombreMaisons() < minimumNbMaisons) {
                        minimumNbMaisons = t.getNombreMaisons();
                        terrainChoisi = t;
                    }
                }
                terrainChoisi.getProprietaire().payer(terrainChoisi.getPrixMaison());
                terrainChoisi.augmenterNbMaisons();
            }
        }
    }

    public void acheterHotel() throws CannotBuildException, PasAssezArgentException {
        if (this.getProprietaire().getPorteMonnaie() < this.getPrixMaison()) {
            throw new PasAssezArgentException("Vous n'avez pas assez d'argent pour acheter une maison");
        } else if (!this.tousTerrainsMemeCouleur(this.getColor())) {
            throw new CannotBuildException("Vous n'avez pas tous les terrains de la couleur " + this.getColor()
                    + ", donc vous ne pouvez pas construire l'hotel!");
        } else {
            Map<Terrain, Integer> listeNombreMaisons = this.getListeNombreMaisons();
            for (Terrain t : listeNombreMaisons.keySet()) {
                if (listeNombreMaisons.get(t) != 4 && listeNombreMaisons.get(t) != 0) {
                    throw new CannotBuildException(
                            "Vous n'avez construit le maximum de maisons sur les terrains de cette couleur!");
                }
            }

            this.getProprietaire().payer(prixMaison);
            estHotel = true;
            nombreMaisons = 0;
        }
    }

    public void vendreHotel() throws CannotSellException {
        if (!this.tousTerrainsMemeCouleur(color)) {
            throw new CannotSellException("Vous n'avez même pas tous les terrains de cette couleur! ");
        }
        if (!this.estHotel())
            throw new CannotSellException("Vous n'avez pas d'hotel sur ce terrain!");
        this.estHotel = false;
        this.getProprietaire().ajouterArgent(prixMaison / 2);
        this.nombreMaisons = 4;
    }

    public void vendreMaison() throws CannotSellException {
        if (!this.tousTerrainsMemeCouleur(color)) {
            throw new CannotSellException("Vous n'avez même pas tous les terrains de cette couleur! ");
        }
        Map<Terrain, Integer> listeNombreMaisons = this.getListeNombreMaisons();

        // chercher le nb maximum de maisons pour voir ce qu'on peut vendre
        int maximumNbMaisons = -1;
        Terrain terrainChoisi = null;
        for (Terrain t : listeNombreMaisons.keySet()) {
            if (t.getNombreMaisons() > maximumNbMaisons) {
                maximumNbMaisons = t.getNombreMaisons();
                terrainChoisi = t;
            }
        }
        if (terrainChoisi != null) {
            if (terrainChoisi.getNombreMaisons() == 0) {
                throw new CannotSellException("Vous n'avez pas de maison sur ce terrain!");
            }
            terrainChoisi.getProprietaire().ajouterArgent(prixMaison / 2);
            terrainChoisi.nombreMaisons--;
        }
    }

    public void squatter() {
        this.estSquatte = true;
    }

    public void fairePartirSquatteur() throws PasAssezArgentException {
        if (this.getProprietaire().getPorteMonnaie() < 200) {
            throw new PasAssezArgentException("Vous n'avez pas assez d'argent pour faire le squatteur partir!");
        } else {
            this.byeSquatteur();
            this.getProprietaire().payer(200);
            double probabilite = 0.1; // une chance sur 10 d'aller en prison s'il paye les 200€
            if (JeuLocal.verifierProbabilite(probabilite)) {
                prison.mettreJoueurEnPrison(this.getProprietaire());
            }
        }
    }

    public void byeSquatteur() {
        this.estSquatte = false;
    }
    // on va vérifier si les 8 tours sont passés dans le main

}
