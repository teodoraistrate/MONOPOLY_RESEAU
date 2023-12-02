package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public class JoueurS1 extends Joueur {

    // Joueur qui achète tout le temps

    public JoueurS1(String name) {
        super(name);
    }

    public boolean choixAcheterPropriete(Propriete propriete) {
        if (this.getPorteMonnaie()<propriete.getPrice()) {
            return false;
        }
        return true;
    }

    public boolean choixPayerOuChance(CartePayerOuChance c) {
        // true : tirer carte chance
        // false : payer

        if (this.getPorteMonnaie()<c.getMontantAPayer()*10) {
            return true;
        }
        return false;
    }

    @Override
    public boolean choixSortirPrison() {
        if (this.getPorteMonnaie()>10*Prison.MONTANT_SORTIR) return true;
        return false;
    }

    @Override
    public List<Propriete> choixProprietesAHypothequer() {

        List<Propriete> choixProprietesAHypothequer = new ArrayList();
        int nombrePrHypotheques = 0;

        // à faire : calculer le loyer maximal et remplacer 500 par 3*loyerMax
        if (this.getPorteMonnaie() < 500) {
            for (Propriete p : this.getProperties()) {
                if (nombrePrHypotheques < 3) {
                    choixProprietesAHypothequer.add(p);
                    nombrePrHypotheques++;
                }
            }
        }

        return choixProprietesAHypothequer;

    }

    @Override
    public Map<Terrain, Integer> choixNombreMaisonsAVendre() {
        Map <Terrain, Integer> choixNombreMaisonsAVendre = new HashMap<>();
        if (this.getPorteMonnaie() < 500) {
            for (Propriete p : this.getProperties()) {
                if (p instanceof Terrain) {
                    int nombreMaisonsP = ((Terrain)p).getNombreMaisons();
                    if (nombreMaisonsP > 0) {
                        choixNombreMaisonsAVendre.put((Terrain)p, nombreMaisonsP);
                        if (this.getPorteMonnaie() > 500) {
                            break;
                        }
                    }
                }
            }
        }

        return choixNombreMaisonsAVendre;
    }

    @Override
    public List<Terrain> choixHotelsAVendre() {
        List <Terrain> choixHotelsAVendre = new ArrayList<>();
        if (this.getPorteMonnaie() < 500) {
            for (Propriete p : this.getProperties()) {
                if (p instanceof Terrain) {
                    if (((Terrain)p).estHotel()) {
                        choixHotelsAVendre.add((Terrain)p);
                    }
                    if (this.getPorteMonnaie() > 500) {
                        break;
                    }
                }
            }
        }

        return choixHotelsAVendre;
    }

    @Override
    public boolean payerOuAttendre() {
        return false;
        // il ne  va pas payer ET risquer d'aller en prison
    }

    @Override
    public boolean transformerProprieteEnPrison(Terrain terrain) {
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
