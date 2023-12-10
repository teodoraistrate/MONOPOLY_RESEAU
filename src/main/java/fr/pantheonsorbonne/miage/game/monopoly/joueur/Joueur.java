package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Start;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Taxes;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.CannotBuildException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.CannotSellException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.DejaAcheteException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public abstract class Joueur {
    public static final int SOLDE_INITIAL = 0;
    private String name;
    private double porteMonnaie;
    private int positionPlateau = 0;
    private List<Propriete> properties;
    private static boolean enPrison;
    private boolean aPerdu = false;

    public Joueur(String name) {
        this.name = name;
        // j'ajoute ça mais au cas ou il faudra supprimer
        this.properties = new ArrayList<>();
    }

    // getteurs

    public String getName() { // le nom du joueur
        return name;
    }

    public double getPorteMonnaie() { // combien il a d'argent
        return porteMonnaie;
    }

    public int getPositionPlateau() { // position du joueur dans le plateau
        return positionPlateau;
    }

    public List<Propriete> getProperties() { // combien le joueur a de propriétés
        return properties;
    }

    public List<Terrain> getPrisonsAdditionnelles() {
        List<Terrain> listePrisonsAdd = new ArrayList<>();
        for (Propriete p : this.getProperties()) {
            if (p instanceof Terrain && ((Terrain)p).estPrisonAdditionnelle()) {
                listePrisonsAdd.add((Terrain)p);
            }
        }
        return listePrisonsAdd;
    }

    // is a

    public boolean estEnPrison() { // Si le joueur est en prison (oui/non)
        return enPrison;
    }

    public boolean aPerdu() {
        return aPerdu;
    }

    public void setAPerdu() {
        this.aPerdu = true;
    }

    // méthodes concernant l'argent

    public void ajouterArgent(int montant) {
        porteMonnaie += montant;
        System.out.println(this.getName() + " a reçu " + montant + " euros");
    }

    public void payer(double montant) throws PasAssezArgentException {
        if (porteMonnaie < montant)
            throw new PasAssezArgentException("Vous n'avez pas assez d'argent pour cette action !");
        porteMonnaie -= montant;
        System.out.println(this.getName() + " a payé " + montant);
    }

    public void getStartingBonus() {
        this.ajouterArgent(Start.RECEIVE_MONEY);
        System.out.println(this.getName() + " a reçu son Bonus de 200 !");
    }

    public void payerLoyer(Propriete propriete) throws PasAssezArgentException {
        if (!propriete.getProprietaire().equals(this)) {
            if (porteMonnaie < propriete.getLoyer()) {
                this.transfererProprietes(propriete.getProprietaire());
                this.declarerPerte();
                throw new PasAssezArgentException(
                        "Vous n'avez pas assez d'argent pour payer le loyer donc vous avez perdu ! C'est vraiment la loose :()");
            }
            if (propriete instanceof Terrain && !((Terrain) propriete).estSquatte() && !((Terrain) propriete).estPrisonAdditionnelle()) {
                this.payer(propriete.getLoyer());
                System.out.println(this.getName() + " a payé le loyer pour " + propriete.getName());
                if (!propriete.estHypotheque()) {
                    propriete.getProprietaire().ajouterArgent(propriete.getLoyer());
                }
            }
        }
    }

    public void payerTaxes(Taxes taxe) throws PasAssezArgentException {
        if (porteMonnaie < taxe.getMontantAPayer()) {
            this.removeAllProprietes();
            this.declarerPerte();
            throw new PasAssezArgentException(
                    "Vous n'avez pas assez d'argent pour payer les taxes donc vous avez perdu!");
        } else {
            this.payer(taxe.getMontantAPayer());
            System.out.println(this.getName() + " a payé les taxes " + taxe.getName());
        }
    }

    // se déplacer

    Plateau plateau = Plateau.getInstance();

    // boolean avancer parce que c'est aussi possible de reculer
    public void deplacerSurPlateau(int nouvellePosition, boolean avancer) {
        if (nouvellePosition < this.positionPlateau && avancer) {
            this.getStartingBonus();
        }
        this.positionPlateau = nouvellePosition;
        System.out.println(this.getName() + " est allé à " + plateau.getCaseParId(nouvellePosition).getName());
    }

    public void deplacerNombreCases(int nombreCases, boolean avancer) {
        if (avancer) {
            if (this.positionPlateau + nombreCases > 40) {
                this.getStartingBonus();
            }
            this.positionPlateau = (this.positionPlateau + nombreCases) % 40;
        } else {
            // s'il recule il ne va pas recevoir le bonus quand il passe par la Case Start
            if (this.positionPlateau < nombreCases) {
                this.positionPlateau = 40 + this.positionPlateau - nombreCases;
            } else {
                this.positionPlateau -= nombreCases;
            }
        }
        System.out.println(this.getName() + " est allé à " + plateau.getCaseParId(this.positionPlateau).getName());
    }

    // concernant les proprietes

    public void acheterPropriete(Propriete propriete) throws PasAssezArgentException {
        if (this.getPorteMonnaie() < propriete.getPrice())
            throw new PasAssezArgentException("Vous ne pouvez pas acheter ce terrain!");
        this.payer(propriete.getPrice());
        System.out.println(this.getName() + " a acheté la propriété " + propriete.getName());

        this.ajouterPropriete(propriete);
        propriete.setProprietaire(this);
    }

    public void ajouterPropriete(Propriete propriete) {
        this.properties.add(propriete);
        propriete.setProprietaire(this);
        System.out.println(this.getName() + " est maintenant le proprietaire de " + propriete.getName());
    }

    public void transfererProprietes(Joueur gagnant) {
        for (Propriete proprieteATransferer : this.getProperties()) {
            gagnant.ajouterPropriete(proprieteATransferer);
        }
        properties.clear();
        System.out
                .println("Toutes les proprietes de " + this.getName() + " ont été transférées à " + gagnant.getName());
    }

   

    public void racheterProprieteHypothequee(Propriete propriete) throws PasAssezArgentException, DejaAcheteException {
        double prix = 1.1 * propriete.getPrixRevente(); // prixRevente + 10%
        if (porteMonnaie < prix)
            throw new PasAssezArgentException("Vous ne pouvez pas racheter cette propriete!");
        if (!propriete.estHypotheque())
            throw new DejaAcheteException("La propriété n'est pas hypothéquée!");
        this.payer(prix);
        propriete.leverHypotheque();
        System.out.println("La propriété " + propriete.getName() + " de " + this.getName() + " n'est pus hypothéquée!");
    }

    public void removePropriete(Propriete propriete) {
        this.properties.remove(propriete);
        propriete.setProprietaire(null);
        System.out.println(this.getName() + "n'est plus le proprietaire de " + propriete.getName());
    }

    public void removeAllProprietes() {
        for (Propriete p : this.getProperties()) {
            p.setProprietaire(null);
        }
        properties.clear();
        System.out.println(this.getName() + " n'a plus de propriétés!");
    }

    // déclarer perte

    public void declarerPerte() {
        this.aPerdu = true;
        System.out.println(this.getName() + " a perdu!");
        this.removeAllProprietes();
    }


    // choix à faire

    public abstract boolean choixAcheterPropriete(Propriete propriete);

    public abstract boolean choixPayerOuChance(CartePayerOuChance c);

    public abstract boolean choixSortirPrison();

    public abstract List<Propriete> choixProprietesARacheter();

    public abstract Map<Terrain, Integer> choixNombreMaisonsAVendre();

    public abstract List<Terrain> choixHotelsAVendre();

    public abstract List<Propriete> choixProprietesAHypothequer();

    public abstract boolean choixPayerOuAttendre();

    public abstract Terrain choixTransformerProprieteEnPrison();



}
