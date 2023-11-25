package fr.pantheonsorbonne.miage.game.monopoly.plateau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Compagnie;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Gare;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public final class Plateau {
    private static final List<Case> plateauJeu = initialiserPlateau();
    public static final int DIMENSION_PLATEAU = 40;

    private Plateau() { 
    }

    public static Case getCaseParId(int id) {
        return plateauJeu.get(id);
    }

    public static List<Case> getPlateau() {
        return plateauJeu;
    }

    private static List<Case> initialiserPlateau() {
        List <Case> cases = new ArrayList<>();
        cases.add(new Start("Case départ"));
        cases.add(new Terrain("Boulevard de BelleVille",60, Color.BROWN, new int[] { 2, 4, 10, 30, 90, 160 }, 50 ));
        cases.add(new CaisseDeCommunaute("Caisse de Communauté"));
        cases.add(new Terrain("Rue LeCourbe",60, Color.BROWN,  new int[] { 4, 8, 20, 60, 180, 320 }, 50));
        cases.add(new Taxes ("Impôts sur le Revenu", 200));
        cases.add(new Gare ("Gare de Montparnasse", 200));
        cases.add(new Terrain("Rue LeCourbe",60, Color.BROWN,  new int[] { 4, 8, 20, 60, 180, 320 }, 50));
        cases.add(new Taxes("Impôts sur le Revenu", 200));
        cases.add(new Gare("Gare de Montparnasse", 200));
        cases.add(new Terrain("Rue de Vaugirard", 100, Color.LIGHT_BLUE, new int[] { 6, 12, 30, 90, 270, 400 }, 50));
        cases.add(new Chance("Chance"));
        cases.add(new Terrain("Rue de Courcelles", 100, Color.LIGHT_BLUE, new int[] { 2, 12, 30, 90, 270, 400 }, 50));
        cases.add(new Terrain("Avenue de la République", 120, Color.LIGHT_BLUE, new int[] { 8, 16, 40, 100, 300, 450}, 50));
        cases.add(new Prison("Jail, simple visite"));
        cases.add(new Terrain("Boulevard de la Vilette", 140, Color.PINK, new int[] { 10, 20, 50, 150, 450, 625 }, 100));
        cases.add(new Compagnie("Compagnie de distribution d'électricité",150));
        cases.add(new Terrain("Avenue de Neuilly", 140, Color.PINK, new int[] { 10, 20, 50, 150, 450, 625 }, 100));
        cases.add(new Terrain("Rue de Paradis", 160, Color.PINK, new int [] { 12, 24, 60, 180, 500, 700}, 100));
        cases.add(new Gare("Gare de Lyon", 200));
        cases.add(new Terrain("Avenue Mozart", 180, Color.ORANGE, new int[] { 14, 28, 70, 200, 550,750 }, 100));
        cases.add(new CaisseDeCommunaute("Caisse de communauté"));
        cases.add(new Terrain("Boulevard Saint-Michel", 180,Color.ORANGE, new int []{ 14, 28, 70, 200, 550, 750}, 100));
        cases.add(new Terrain("Place Pigalle", 200, Color.ORANGE, new int[] { 16, 32, 80, 220, 600, 800 }, 100));
        cases.add(new Parking("Parc Gratuit"));
        cases.add(new Terrain("Avenue Matignon", 220, Color.RED, new int[] { 18, 36, 90, 250, 700, 875 }, 150));
        cases.add(new Chance("Chance"));
        cases.add(new Terrain("Boulevard Malesherbes", 220, Color.RED, new int[] { 18, 36, 90, 250, 700,875 }, 150));
        cases.add(new Terrain("Avenue Henri-Martin", 240, Color.RED, new int[] { 20, 40, 100, 300, 750,925 }, 150));
        cases.add(new Gare("Gare du Nord", 200));
        cases.add(new Terrain("Faubourg Saint-Honoré", 260, Color.YELLOW, new int[] { 22, 44, 110, 330, 800,975 }, 150));
        cases.add(new Terrain("Place de la Bourse", 260, Color.YELLOW, new int[] { 22, 44, 110, 330, 800,975 }, 150));
        cases.add(new Compagnie("Compagnie de distribution des eaux",150));
        cases.add(new Terrain("Rue de la Fayette", 280, Color.YELLOW, new int[] { 24, 48, 120, 360, 850,1025 }, 150));
        cases.add(new AllerEnPrison("Allez en prison bye bye !"));
        cases.add(new Terrain("Avenue de Breteuil", 300, Color.GREEN, new int[] { 26, 52, 130, 390, 900, 1100 }, 200));
        cases.add(new Terrain("Avenue Foch", 300,Color.GREEN, new int[] { 26, 52, 130, 390, 900,1100 }, 200));
        cases.add(new CaisseDeCommunaute("Caisse de Communauté"));
        cases.add(new Terrain("Boulevard des Capucines", 320, Color.GREEN, new int[] { 28, 56, 150, 450, 1000, 1200 }, 200));
        cases.add(new Gare("Gare de Saint-Lazare", 200));
        cases.add(new Chance("Chance"));
        cases.add(new Terrain("Avenue des Champs - Elysées", 350, Color.BLUE, new int[] { 35, 70, 175, 500, 1100, 1300 }, 200));
        cases.add(new Taxes("Taxe de Luxe", 100));
        cases.add(new Terrain("Rue de la Paix", 400, Color.BLUE, new int[] { 50, 100, 200, 600, 1400,1700 }, 200));
        return cases;
    }

    public int getCaseParNom(String nom) throws NomPasValideException{
        for (int i=0; i<DIMENSION_PLATEAU; i++) {
            if(nom.equals(plateauJeu.get(i).getName())) {
                return i;
            }
        }
        throw new NomPasValideException("Le nom de la case n'est pas valide!");
    }

}
