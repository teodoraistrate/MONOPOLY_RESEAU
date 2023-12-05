package fr.pantheonsorbonne.miage.game.monopoly.jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS2;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS3;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public class JeuLocal {

    public static final Plateau plateau = Plateau.getInstance(); //erreur aussi maybe
    static List<Joueur> listeJoueurs = new ArrayList<>();
    private int nombrePrisonsAdditionnelles = 0;

    private static JeuLocal instance = new JeuLocal(); // Création de l'instance unique

    public JeuLocal() {
        // Initialisation de l'instance unique
    }

    // on a créé une méthode pour qui renvoie true avec une probabilité donnée en paramètre
    public static boolean verifierProbabilite(double probabilite) {
        Random random = new Random();
        double valeurAleatoire = random.nextDouble(); 
        // Génère un nombre aléatoire entre 0 et 1

        return valeurAleatoire < probabilite;
    }

    public static JeuLocal getInstance() {
        return instance;
    }

    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public void removeJoueur(Joueur joueur) {
        listeJoueurs.remove(joueur);
    }

    public int getNombrePrisonsAdditionnelles() {
        return nombrePrisonsAdditionnelles;
    }

    public static void initialiserListeJoueurs() {
        JoueurS1 joueur1 = new JoueurS1("Joueur 1");
        //JoueurS2 joueur2 = new JoueurS2("Joueur 2");
        JoueurS3 joueur3 = new JoueurS3("Joueur 3");
        listeJoueurs.add(joueur1);
        listeJoueurs.add(joueur3);
    }
    
    public static void main(String[] args) {

        initialiserListeJoueurs();

        for (Joueur j : listeJoueurs) {
            j.ajouterArgent(1500);
            System.out.println("Solde de " + j.getName() + " : " + j.getPorteMonnaie());
        }

        Prison prison = Prison.getInstance("Prison");

        int nombreTours = 0;

        // le jeu s'arrête quand il reste un seul joueur
        while (listeJoueurs.size()>1) {
            double loyerTotalActuel = 0;
            for (Joueur joueur : listeJoueurs) {
                if (nombreTours > 200) {
                try {
                    joueur.payer(150);
                } catch(PasAssezArgentException e) {
                    joueur.declarerPerte();
                }
            }
                boolean lancerDes = true; 
                // on a ajouté cette variable pour qu'un joueur puisse lancer les dés plusieurs fois si c'est la même valeur
                int nombreFoisMemeValeur = 0;
                // le but de cette variable est de mettre le joueur en prison s'il a la même valeur 3 fois
                while(lancerDes) {
                    DeDouble des = new DeDouble();
                    des.lancerDes();
                    if (!des.memeValeur()) {
                        lancerDes = false; // si les deux dés n'ont pas la même valeur alors il ne peut lancer les dés qu'une seule fois
                    }
                    else {
                        nombreFoisMemeValeur++;
                    }
                    if (nombreFoisMemeValeur == 3) {
                        prison.mettreJoueurEnPrison(joueur);
                    }
                    joueur.deplacerNombreCases(des.resultatDe(), true);
                    Case nouvelleCase = Plateau.getCaseParId(joueur.getPositionPlateau());
                    nouvelleCase.appliquerEffetCase(joueur);

                    if (nouvelleCase instanceof Propriete) {
                        Propriete propriete = (Propriete)nouvelleCase;
                        if (propriete.getProprietaire() == null && (joueur.choixAcheterPropriete(propriete))) {
                                try {
                                    joueur.acheterPropriete(propriete);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                        }
                    }

                    // on vend les hotels choisis par le joueur
                    for (Terrain t : joueur.choixHotelsAVendre()) {
                        try {
                            t.vendreHotel();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    // on vend les maisons choisis par le joueur
                    for (Terrain t : joueur.choixNombreMaisonsAVendre().keySet()) {
                        for (int i=0; i<joueur.choixNombreMaisonsAVendre().get(t); i++) {
                            try {
                                t.vendreMaison();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    // on va hypothéquer les propriétés (terrains, compagnies ou gares) choisies par le joueur
                    for (Propriete p : joueur.choixProprietesAHypothequer()) {
                        p.hypothequer();
                    }

                }
            }

            // ajouter méthode pour demander aux proprietaires des terrains squattés s'il veulent faire partir le squatteur

            // ajouter méthode pour faire partir le squatteur à partir de 8 tours!!

            List<Terrain> listeTerrainsAchetes = plateau.getTerrainsAchetesNonSquattes();
            double probabiliteSquatteur = loyerTotalActuel/15000;

            if (verifierProbabilite(probabiliteSquatteur) && listeTerrainsAchetes!=null) {
                Random random = new Random();
                int indexAleatoire = random.nextInt(listeTerrainsAchetes.size());
                Terrain proprieteASquatter = listeTerrainsAchetes.get(indexAleatoire);
                proprieteASquatter.squatter(); // on squatte la propriété aléatoire
                proprieteASquatter.setNombreToursInitialSquatteur(nombreTours); // on met le nb Tours Initial
                System.out.println("La propriété " + proprieteASquatter.getName() + " est squatté! ");
                if(proprieteASquatter.getProprietaire().choixPayerOuAttendre()) {
                    try {
                        proprieteASquatter.fairePartirSquatteur();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            for (Joueur j : listeJoueurs) {
                System.out.println("Solde de " + j.getName() + " : " + j.getPorteMonnaie());
                System.out.println("Nb de proprietes de " + j.getName() + " : " + j.getProperties().size());
            }
            System.out.println("Nombre tours: " + nombreTours);
            System.out.println();
            nombreTours++;
        }
        System.out.println("Victoire de: " + listeJoueurs.get(0).getName());

    }

    // il met pas en prison les joueurs !!!!
    // il va pas payer un loyer s'il est le proprietaire - a changer
}
