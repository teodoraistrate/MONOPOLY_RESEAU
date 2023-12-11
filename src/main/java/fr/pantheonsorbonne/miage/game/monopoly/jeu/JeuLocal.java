package fr.pantheonsorbonne.miage.game.monopoly.jeu;

import java.util.ArrayList;
import java.util.Iterator;
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
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.CannotSellException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public class JeuLocal {

    public static final Plateau plateau = Plateau.getInstance(); 
    static List<Joueur> listeJoueurs = new ArrayList<>();
    private static int nombrePrisonsAdditionnelles = 0;

    private static JeuLocal instance = new JeuLocal(); // Création de l'instance unique

    public JeuLocal() {
        // Initialisation de l'instance unique
    }

    // on a créé une méthode qui renvoie true avec une probabilité donnée en paramètre
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

    public static void reInitialiseNbPrisons() {
        nombrePrisonsAdditionnelles = 0;
    }

    public static void augmenterNbPrisonsAdd() {
        nombrePrisonsAdditionnelles++;
    }

    public static void initialiserListeJoueurs() {
        JoueurS1 joueur1 = new JoueurS1("Joueur 1");
        JoueurS2 joueur2 = new JoueurS2("Joueur 2");
        JoueurS3 joueur3 = new JoueurS3("Joueur 3");
        listeJoueurs.add(joueur1);
        listeJoueurs.add(joueur2);
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

        List<Joueur> copieListeJoueurs = new ArrayList<>(listeJoueurs);


        // le jeu s'arrête quand il reste un seul joueur
        while (copieListeJoueurs.size() > 1) {

            double loyerTotalActuel = 0;

            // on commence par demander aux proprietaires des terrains squattés s'il veulent faire le squatteur partir
            List<Terrain> listeTerrainsSquattes = plateau.getTerrainsAchetesSquattes();
            for (Terrain t : listeTerrainsSquattes) {
                if (t.getProprietaire().choixPayerOuAttendre()) {
                    try {
                        t.fairePartirSquatteur();
                    } catch (PasAssezArgentException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (Joueur joueur : listeJoueurs) {

                if (nombreTours > 200) {
                    try {
                        joueur.payer(100);
                    } catch(PasAssezArgentException e) {
                    joueur.declarerPerte();
                    }
                    if (joueur.aPerdu()) {
                        break; 
                        // Un break pour qu'il puisse sortir de la boucle
                    }
                }
            // méthode pour ne pas avoir une infinité de tours : chaque joueur va payer 100€ à debut de son tour à partir du tour 200

            if (joueur.estEnPrison()) {
                prison.sortirPrisonDoubleDe(joueur);
            }
            // le statut estEnPrison peut se changer
            if (joueur.estEnPrison() && joueur.choixSortirPrison() && joueur.getPorteMonnaie()>50) {
                prison.sortirPrisonPayer(joueur);
            }
            if (joueur.estEnPrison()) {
                prison.augmenterNombreTours(joueur);
            }

            // on ne met pas if-else parce que le statut estEnPrison peut se changer
            if (!joueur.estEnPrison()) {

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
                        try {
                            p.hypothequer();
                        } catch(CannotSellException e) {
                            e.printStackTrace();
                        }
                    }

                    Terrain choixPrisonAdd = joueur.choixTransformerProprieteEnPrison();
                    // ajouter une condition qui vérifie si 
                    if (choixPrisonAdd != null) {
                        choixPrisonAdd.transformerProprieteEnPrison();
                        augmenterNbPrisonsAdd();
                    }

                    // à la fin de son tour, le joueur va recevoir le loyer pour chacune de ses prisons additionnelles
                    for (Terrain prisonAdd : joueur.getPrisonsAdditionnelles()) {
                        System.out.println(joueur.getName()+ " a reçu le loyer pour une prison additionnelle : " + prisonAdd.getLoyerPrison() + "€");
                        joueur.ajouterArgent(prisonAdd.getLoyerPrison());
                    }

                    for (Case c : Plateau.getPlateau()) {
                        if (c instanceof Terrain && ((Terrain)c).getNombreMaisons() > 0 && ((Terrain)c).getProprietaire()==joueur) {
                            int maximumLoyerBase = 0;
                            for (Terrain t : plateau.getTerrainsMemeCouleur(((Terrain)c).getColor())) {
                                if (t.getTableauLoyer()[0] > maximumLoyerBase) {
                                    maximumLoyerBase = t.getTableauLoyer()[0];
                                }
                            }
                            double probabiliteCasseur = (1/maximumLoyerBase) - (nombrePrisonsAdditionnelles/10);
                            if (probabiliteCasseur < 0) {
                                probabiliteCasseur = 0;
                            }
                            boolean casserM = verifierProbabilite(probabiliteCasseur);
                            if (casserM) {
                                ((Terrain)c).casserMaison();
                            }
                            break;
                        }
                    }
                }
            }

            }

            // avant de mettre un nouveau squatteur sur un terrain, on va faire partir ceux qui sont restés pour 8 tours
            List<Terrain> listeTSq = plateau.getTerrainsAchetesSquattes();
            // on n'a pas utilisé la même variable qu'avant (et on n'a pas fait de remove dans la liste en haut pour ne pas avoir des exceptions ConcurrentModificationException)
            for (Terrain t : listeTSq) {
                if (nombreTours - t.getNombreToursInitialSquatteur() >= 8) {
                    t.byeSquatteur();
                }
            }

            List<Terrain> listeTerrainsAchetes = plateau.getTerrainsAchetesNonSquattes();

            // calculer le loyer total actuel 
            // est-ce qu'il faut aussi compter les terrains squattés ??? sinon on supprime la première boucle
            for (Terrain t : listeTSq) {
                loyerTotalActuel += t.getLoyer();
            }
            for (Terrain t : listeTerrainsAchetes) {
                loyerTotalActuel += t.getLoyer();
            }

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

            // Supprimer les joueurs marqués comme ayant perdu après la boucle
            Iterator<Joueur> joueurIterator = listeJoueurs.iterator();
            while (joueurIterator.hasNext()) {
                Joueur joueur = joueurIterator.next();
                if (joueur.aPerdu()) {
                    joueurIterator.remove();
                }
            }
            // faire de sorte que si un joueur perde, la boucle est arretée (indexOutOBounds pour le vainqueur si les joueurs 1 et 2 perdent au meme temps)

            // Mettre à jour la copie de la liste pour refléter les changements
            copieListeJoueurs = new ArrayList<>(listeJoueurs);
            // On a fait une copie de la liste parce que des fois il y avait une ConcurrentModificationException

        }
        System.out.println("Victoire de: " + listeJoueurs.get(0).getName());

    }
}
