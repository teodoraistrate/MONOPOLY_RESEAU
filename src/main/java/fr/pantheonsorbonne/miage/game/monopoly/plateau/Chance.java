package fr.pantheonsorbonne.miage.game.monopoly.plateau;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.Pioche;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePrison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.CarteAvancerCaseNormale;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.payer.CartePayerDependant;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.payer.CartePayerFixe;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.recevoir.CarteRecevoirFixe;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer.CarteReculerNombre;


public class Chance extends Case {

    private static Pioche piocheChance = new Pioche();

    public Chance(String name) {
        super(name);
    }

    public static Pioche getPiocheChance() {
        return piocheChance;
    }

    static {

        // On ajoute les cartes Chance
        piocheChance.ajouterCarte(new CarteAvancerCaseNormale("Rendez-vous à la Rue de la Paix. Si vous passez par la case départ, recevez 200€.","Rue de la Paix"));
        piocheChance.ajouterCarte(new CarteAvancerCaseNormale("Avancer jusqu’à la case départ.","Case départ"));
        piocheChance.ajouterCarte(new CarteAvancerCaseNormale("Rendez-vous à l’Avenue Henri-Martin. Si vous passez par la case départ, recevez 200€.", "Avenue Henri-Martin"));
        piocheChance.ajouterCarte(new CarteAvancerCaseNormale("Avancez au Boulevard de la Vilette. Si vous passez par la case départ, recevez 200€.", "Boulevard de la Vilette"));
        piocheChance.ajouterCarte(new CartePayerDependant("Vous êtes imposé pour les réparations de voirie à raison de 40€ par maison et 115€ par hôtel.", 40, 115));
        piocheChance.ajouterCarte(new CarteAvancerCaseNormale("Avancez jusqu’à la Gare de Lyon. Si vous passez par la case départ, recevez 200€.", "Gare de Lyon"));
        piocheChance.ajouterCarte(new CarteRecevoirFixe("Vous avez gagné le prix de mots croisés. Recevez 100€.", 100));
        piocheChance.ajouterCarte(new CarteRecevoirFixe("La banque vous verse un dividende de 50€.", 50));
        piocheChance.ajouterCarte(new CarteReculerNombre("Reculez de trois cases.", 3));
        piocheChance.ajouterCarte(new CartePrison("Aller en prison. Rendez-vous directement en prison. Ne franchissez pas par la case départ, ne touchez pas 200€."));
        piocheChance.ajouterCarte(new CartePayerDependant("Faites des réparations dans toutes vos maisons. Versez pour chaque maison 25€. Versez pour chaque hôtel 100€.", 25, 100));
        piocheChance.ajouterCarte(new CartePayerFixe("Amende pour excès de vitesse 15€.", 15));
        piocheChance.ajouterCarte(new CartePayerFixe("Payez pour frais de scolarité 150€.", 150));
        piocheChance.ajouterCarte(new CartePayerFixe("Amende pour ivresse 20€.", 20));
        piocheChance.ajouterCarte(new CarteRecevoirFixe("Votre immeuble et votre prêt rapportent. Vous devez toucher 150€.", 150));

    }

    public void appliquerEffetCase (Joueur joueur) {
        Carte carte = piocheChance.piocherCarte();
        System.out.println(joueur.getName() + " a tiré une carte Chance : " + carte.getDescription());
        try {
            carte.appliquerEffet(joueur);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Pioche getCartesChances() {
        return piocheChance;
    }


}

