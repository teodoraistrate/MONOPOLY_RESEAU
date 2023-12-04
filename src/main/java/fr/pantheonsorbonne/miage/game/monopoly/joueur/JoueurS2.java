package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;

public class JoueurS2 extends Joueur {

    public JoueurS2(String name) {
        super(name);
    }

    // il achète une propriété s'il pourrait en acheter 10
    @Override
    public boolean choixAcheterPropriete(Propriete propriete) {
        return (propriete.getLoyer()*10<this.getPorteMonnaie());
    }

    @Override
    public boolean choixPayerOuChance(CartePayerOuChance c) {
        // true : il tire une carte Chance
        // false : il va payer le montant

        return true; 
        // il va toujours tirer une carte Chance
    }

    @Override
    public boolean choixSortirPrison() {
        return (this.getPorteMonnaie()>5*Prison.MONTANT_SORTIR);
    }

    @Override
    public List<Propriete> choixProprietesARacheter() {
        List<Propriete> listeP = new ArrayList<>();
        int montantDepense = 0;
        for (Propriete p : this.getProperties()) {
            if (p.estHypotheque() &&  (this.getPorteMonnaie - montantDepense > 700)) {
                listeP.add(p);
                montantDepense += 1.1 * p.getPrixRevente();
            }
        }
    }

    @Override
    public List<Propriete> choixProprietesAHypothequer() {

        List<Propriete> choixProprietesAHypothequer = new ArrayList<>();
        int montantRecu = 0;

        // à faire : calculer le loyer maximal et remplacer 500 par 3*loyerMax
        for (Propriete p : this.getProperties()) {
            if (this.getPorteMonnaie() + montantRecu < 500) {
                choixProprietesAHypothequer.add(p);
                montantRecu += p.getPrixRevente();
            }
            else break;
        }

        return choixProprietesAHypothequer;

    }

    @Override
    public Map<Terrain, Integer> choixNombreMaisonsAVendre() {
        int montantRecu = 0;
        Map <Terrain, Integer> choixNombreMaisonsAVendre = new HashMap<>();
        for (Propriete p : this.getProperties()) {
            if (this.getPorteMonnaie() + montantRecu < 500) {
                int nombreMaisonsP = ((Terrain)p).getNombreMaisons();
                if (nombreMaisonsP > 0) {
                    choixNombreMaisonsAVendre.put((Terrain)p, nombreMaisonsP);
                    montantRecu += nombreMaisonsP*((Terrain)p).getPrixMaison()/2;
                }
            }
            else break;
        }

        return choixNombreMaisonsAVendre;
    }

    @Override
    public List<Terrain> choixHotelsAVendre() {
        List <Terrain> choixHotelsAVendre = new ArrayList<>();
        int montantRecu = 0;
        
        for (Propriete p : this.getProperties()) {
            if (this.getPorteMonnaie() + montantRecu < 500) {
                if (p instanceof Terrain && (((Terrain)p).estHotel())) {
                        choixHotelsAVendre.add((Terrain)p);
                        montantRecu += ((Terrain)p).getPrixMaison()/2;
                }
            }
            else break;
        }
        return choixHotelsAVendre;
    }

    @Override
    public boolean choixPayerOuAttendre() {
        return false;
        // il ne  va pas payer ET risquer d'aller en prison
    }

    @Override
    public boolean choixTransformerProprieteEnPrison(Terrain terrain) {
        Plateau plateau = Plateau.getInstance();
        if (terrain.tousTerrainsMemeCouleur(terrain.getColor())) return false;
        else {
            List<Terrain> listeT = plateau.getTerrainsMemeCouleur(terrain.getColor());
            for (Terrain t : listeT) {
                if (t.getProprietaire() != terrain.getProprietaire() && t.getProprietaire() != null) {
                    return true;
                }
            }
        }
        return false;
        // il ne veut transformer son terrain en prison que s'il y a un autre joueur qui a un des terrains de la meme couleur
        // sinon il va espérer qu'il va pouvoir acquerir tous les terrains de cette couleur
    }


}
