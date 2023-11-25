package fr.pantheonsorbonne.miage.game.monopoly.jeu;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePrison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.CarteAvancerCaseNormale;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer.CarteAvancerGare;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.payer.CartePayerDependant;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.payer.CartePayerFixe;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.recevoir.CarteRecevoirFixe;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.recevoir.CarteRecevoirJoueurs;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer.CarteReculerNom;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer.CarteReculerNombre;

public class JeuMonopoly {
    public static void main (String [] args) {

        Pioche piocheChance = new Pioche();
        Pioche piocheCaisseCommunaute = new Pioche();

        // On ajoute les cartes Chance
        piocheChance.ajouterCarte(new CarteAvancerCaseNormale("Rendez-vous à la Rue de la Paix. Si vous passez par la case départ, recevez 200€.","Rue de la Paix"));
        piocheChance.ajouterCarte(new CarteAvancerCaseNormale("Avancer jusqu’à la case départ.","Case Départ"));
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

        //On ajoute les cartes Caisse de communauté
        piocheCaisseCommunaute.ajouterCarte(new CarteAvancerCaseNormale("Placez-vous sur la case départ.","Case Départ"));
        piocheCaisseCommunaute.ajouterCarte(new CarteRecevoirFixe("Erreur de la banque en votre faveur. Recevez 200€.", 200));
        piocheCaisseCommunaute.ajouterCarte(new CartePayerFixe("Payez la note du médecin 50€.", 50));
        piocheCaisseCommunaute.ajouterCarte(new CarteRecevoirFixe("La vente de votre stock vous rapporte 50€.", 50));
        piocheCaisseCommunaute.ajouterCarte(new CartePrison("Aller en prison. Rendez-vous directement à la prison. Ne franchissez pas par la case départ, ne touchez pas 200€."));
        piocheCaisseCommunaute.ajouterCarte(new CarteReculerNom("Retournez à Belleville", "Boulevard de BelleVille"));
        piocheCaisseCommunaute.ajouterCarte(new CarteRecevoirFixe("Recevez votre revenu annuel 100€.", 100));
        piocheCaisseCommunaute.ajouterCarte(new CarteRecevoirJoueurs("C’est votre anniversaire. Chaque joueur doit vous donner 10€.", 10));
        piocheCaisseCommunaute.ajouterCarte(new CarteRecevoirFixe("Les contributions vous remboursent la somme de 20€.", 20));
        piocheCaisseCommunaute.ajouterCarte(new CarteRecevoirFixe("Recevez votre intérêt sur l’emprunt à 7% 25€.", 25));
        piocheCaisseCommunaute.ajouterCarte(new CartePayerFixe("Payez votre Police d’Assurance 50€.", 50));
        piocheCaisseCommunaute.ajouterCarte(new CartePayerOuChance("Payez une amende de 10€ ou bien tirez une carte « CHANCE »", 10));
        piocheCaisseCommunaute.ajouterCarte(new CarteAvancerGare("Rendez-vous à la gare la plus proche. Si vous passez par la case départ, recevez 200€."));
        piocheCaisseCommunaute.ajouterCarte(new CarteRecevoirFixe("Vous avez gagné le deuxième Prix de Beauté. Recevez 10€.", 10));
        piocheCaisseCommunaute.ajouterCarte(new CarteRecevoirFixe("Vous héritez 100€", 100));

    }
}
