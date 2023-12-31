package fr.pantheonsorbonne.miage.essaiReseau;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.DeDouble;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.CannotSellException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public abstract class JeuMonopoly {

    public static final Plateau plateau = Plateau.getInstance();
    List<Joueur> listeJoueurs = new ArrayList<>();
    private static int nombrePrisonsAdditionnelles = 0;
    private static int nombreTours = 0;

    public JeuMonopoly() {
        // Initialisation de l'instance unique
    }

    // on a créé une méthode qui renvoie true avec une probabilité donnée en
    // paramètre
    public static boolean verifierProbabilite(double probabilite) {
        Random random = new Random();
        double valeurAleatoire = random.nextDouble();
        // Génère un nombre aléatoire entre 0 et 1
        return valeurAleatoire < probabilite;
    }

    public void removeJoueur(Joueur joueur) {
        joueur.setAPerdu();
    }

    public int getNombrePrisonsAdditionnelles() {
        return nombrePrisonsAdditionnelles;
    }

    public static void reInitialiseNbPrisons() {
        nombrePrisonsAdditionnelles = 0;
    }

    public static void augmenterNbPrisonsAdd() {
        nombrePrisonsAdditionnelles++;
    }

    public abstract List<Joueur> getListeJoueurs();

    // ça va retourner le nom du gagnant
    public void jouerMonopoly(List<Joueur> listeJoueurs) {

        //MonopolyHostLocal hostLocal = MonopolyHostLocal.getInstance();
        //MonopolyHost hostReseau = MonopolyHost.getInstance();

        Prison prison = Prison.getInstance("Prison");

        // jeu = hostReseau pour jouer en réseau
        // jeu = hostLocal pour jouer en local
        //JeuMonopoly jeu = hostLocal;

        //listeJoueurs = this.getListeJoueurs();

        for (Joueur j : listeJoueurs) {
            j.ajouterArgent(1500);
            System.out.println("Solde de " + j.getName() + " : " + j.getPorteMonnaie());
        }

        int nombreJoueursQuiJouent = listeJoueurs.size();
        // Au début, tous les joueurs jouent
        // A la fin de la boucle while, on va compter les joueurs dont l'attribut aPerdu
        // est false

        // le jeu s'arrête quand il reste un seul joueur
        while (nombreJoueursQuiJouent > 1) {

            double loyerTotalActuel = 0;

            // on commence par demander aux proprietaires des terrains squattés s'il veulent
            // faire le squatteur partir
            List<Terrain> listeTerrainsSquattes = plateau.getTerrainsAchetesSquattes();
            for (Terrain t : listeTerrainsSquattes) {
                if (this.choixPayerOuAttendre(t.getProprietaire().getName(), t)) {
                    try {
                        t.fairePartirSquatteur();
                    } catch (PasAssezArgentException e) {
                        e.printStackTrace();
                    }
                }
            }

            // pas de for each pour vérifier si le joueur a perdu
            for (int i=0; i<listeJoueurs.size() && !listeJoueurs.get(i).aPerdu(); i++) {

                Joueur joueur = listeJoueurs.get(i);

                if (nombreTours > 150) {
                    try {
                        joueur.payer(50);
                    } catch (PasAssezArgentException e) {
                        joueur.declarerPerte();
                    }
                    if (joueur.aPerdu()) {
                        break;
                        // Un break pour qu'il puisse sortir de la boucle
                    }
                }
                // méthode pour ne pas avoir une infinité de tours

                if (joueur.estEnPrison()) {
                    prison.sortirPrisonDoubleDe(joueur);
                }
                // le statut estEnPrison peut se changer
                if (joueur.estEnPrison() && this.choixSortirPrison(joueur.getName()) && joueur.getPorteMonnaie() > 50) {
                    prison.sortirPrisonPayer(joueur);
                }
                if (joueur.estEnPrison()) {
                    prison.augmenterNombreTours(joueur);
                }

                // on ne met pas if-else parce que le statut estEnPrison peut se changer
                if (!joueur.estEnPrison()) {

                    boolean lancerDes = true;
                    // on a ajouté cette variable pour qu'un joueur puisse lancer les dés plusieurs
                    // fois si c'est la même valeur

                    int nombreFoisMemeValeur = 0;
                    // le but de cette variable est de mettre le joueur en prison s'il a la même
                    // valeur 3 fois

                    while (lancerDes) {
                        DeDouble des = new DeDouble();
                        des.lancerDes();
                        if (!des.memeValeur()) {
                            lancerDes = false; // si les deux dés n'ont pas la même valeur alors il ne peut lancer les
                                               // dés qu'une seule fois
                        } else {
                            nombreFoisMemeValeur++;
                        }
                        if (nombreFoisMemeValeur == 3) {
                            prison.mettreJoueurEnPrison(joueur);
                            prison.sortirPrisonDoubleDe(joueur);
                            if (this.choixSortirPrison(joueur.getName()) && joueur.getPorteMonnaie() > 50) {
                                prison.sortirPrisonPayer(joueur);
                            }
                            break;
                        }
                        joueur.deplacerNombreCases(des.resultatDe(), true);
                        Case nouvelleCase = Plateau.getCaseParId(joueur.getPositionPlateau());
                        nouvelleCase.appliquerEffetCase(joueur);

                        if (joueur.aPerdu()) {
                            break;
                            // Un break pour qu'il puisse sortir de la boucle
                        }

                        if (nouvelleCase instanceof Propriete) {
                            Propriete propriete = (Propriete) nouvelleCase;
                            if (propriete.getProprietaire() == null && this.choixAcheterPropriete(joueur.getName(), propriete)) {
                                try {
                                    joueur.acheterPropriete(propriete);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        this.thinkAndDo(joueur.getName());
                    }
                }

            }

            // avant de mettre un nouveau squatteur sur un terrain, on va faire partir ceux
            // qui sont restés pour 8 tours
            List<Terrain> listeTSq = plateau.getTerrainsAchetesSquattes();
            // on n'a pas utilisé la même variable qu'avant (et on n'a pas fait de remove
            // dans la liste en haut pour ne pas avoir des exceptions
            // ConcurrentModificationException)
            for (Terrain t : listeTSq) {
                if (nombreTours - t.getNombreToursInitialSquatteur() >= 8) {
                    t.byeSquatteur();
                }
            }

            List<Terrain> listeTerrainsAchetes = plateau.getTerrainsAchetesNonSquattes();

            // calculer le loyer total actuel
            // est-ce qu'il faut aussi compter les terrains squattés ??? sinon on supprime
            // la première boucle
            for (Terrain t : listeTSq) {
                loyerTotalActuel += t.getLoyer();
            }
            for (Terrain t : listeTerrainsAchetes) {
                loyerTotalActuel += t.getLoyer();
            }

            double probabiliteSquatteur = loyerTotalActuel / 20000;

            if (verifierProbabilite(probabiliteSquatteur) && listeTerrainsAchetes != null) {
                Random random = new Random();
                int indexAleatoire = random.nextInt(listeTerrainsAchetes.size());
                Terrain proprieteASquatter = listeTerrainsAchetes.get(indexAleatoire);
                proprieteASquatter.squatter(); // on squatte la propriété aléatoire
                proprieteASquatter.setNombreToursInitialSquatteur(nombreTours); // on met le nb Tours Initial
                System.out.println("La propriété " + proprieteASquatter.getName() + " est squatté! ");
                if (this.choixPayerOuAttendre(proprieteASquatter.getProprietaire().getName(), proprieteASquatter)) {
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

            // faire de sorte que si un joueur perde, la boucle est arretée (indexOutOBounds
            // pour le vainqueur si les joueurs 1 et 2 perdent au meme temps)

            nombreJoueursQuiJouent = 0;
            for (Joueur j : listeJoueurs) {
                if (!j.aPerdu()) {
                    nombreJoueursQuiJouent++;
                }
            }
        }
        for (Joueur j : listeJoueurs) {
            if (!j.aPerdu()) {
                System.out.println("Gagnant : " + j.getName());
                declarerFinJeu();
            }
        }

    }

    protected abstract boolean choixSortirPrison(String idJoueur);

    protected abstract boolean choixAcheterPropriete(String idJoueur, Propriete propriete);

    protected abstract boolean choixPayerOuAttendre(String idJoueur, Terrain proprieteSquatee);

    protected abstract void thinkAndDo(String idJoueur);

    protected abstract void declarerFinJeu();

}
