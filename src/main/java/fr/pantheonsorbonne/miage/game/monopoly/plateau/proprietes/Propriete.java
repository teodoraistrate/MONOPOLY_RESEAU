package fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;

public abstract class Propriete extends Case {

    private int price;
    private boolean estHypotheque;
    protected Joueur proprietaire;

    protected Propriete(String name, int price) {
        super(name);
        this.price = price;
    }

    // getteurs

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public int getPrixRevente() {
        return this.price / 2;
    }

    public int getPrice() {
        return price;
    }

    

    public abstract int getLoyer();

    // is a 

    public boolean estLibre() {
        return proprietaire == null;
    }

    public boolean estHypotheque() {
        return estHypotheque;
    }

    // setteurs

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    // autres méthodes

    // hypothequer propriete

    public void hypothequer() throws CannotSellException{

        if (estHypotheque) {
            throw new CannotSellException("Vous ne pouvez pas hypothequer encore une fois cette propriété!");
        } else {
            this.getProprietaire().ajouterArgent(this.getPrixRevente());
            estHypotheque = true;
        }
    }

    // AJOUTER MÉTHODE PAYERARGENT POUR QU'ON CALCULE AUSSI LE MONTANT A PAYER
    public void leverHypotheque() {
        this.estHypotheque = false;
    }


    // méthode pour appliquer l'effet quand le joueur tombe sur cette case

    public void appliquerEffetCase(Joueur joueur) {
        try {
            if (this.getProprietaire()!=null) {
                joueur.payerLoyer(this);
            }
        } catch (PasAssezArgentException e) {
            e.printStackTrace();
        }
    }
    

    
}
