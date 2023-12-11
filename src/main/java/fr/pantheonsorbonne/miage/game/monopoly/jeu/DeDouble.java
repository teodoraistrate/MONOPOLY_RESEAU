package fr.pantheonsorbonne.miage.game.monopoly.jeu;

public class DeDouble {
    private int valeurDe;
    public static final int VALEUR_MAX = 12;
    protected int resultatDe;
    private boolean memeValeur;

    public DeDouble() {
        this.resultatDe = 0;
        this.valeurDe = 0;
        this.memeValeur = false;
    }

    public int getValeur() {
        return this.valeurDe;
    }

    public boolean memeValeur() {
        return memeValeur;
    }

    public int resultatDe() {
        return resultatDe;
    }
    

    public void lancerDes() {
        //lancer deux dés
        int de1 = (int) (Math.random() * 6) + 1;  // Valeur entre 1 et 6
        int de2 = (int) (Math.random() * 6) + 1;

        // résultat et la valeur du dé
        resultatDe = de1 + de2;
        valeurDe = Math.min(resultatDe, VALEUR_MAX);

        // si deux dés ont la mm valeur
        memeValeur = de1 == de2;

        System.out.println("Valeur des dés : " + de1 + " et " + de2);
    }


}

