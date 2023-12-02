package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public boolean choixPayerOuChance() {
        // true : tirer carte chance
        // false : payer

        if (this.getPorteMonnaie()<CartePayerOuChance.getMontantAPayer()*10) {
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
                    int nombreMaisonsP = ((Terrain)p).getNombreMaisons();
                    if (nombreMaisonsP > 0) {
                        choixHotelsAVendre.add((Terrain)p);
                        if (this.getPorteMonnaie() > 500) {
                            break;
                        }
                    }
                }
            }
        }

        return choixHotelsAVendre;
    }

    @Override
    public boolean payerOuAttendre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'payerOuAttendre'");
    }

    @Override
    public boolean transformerProprieteEnPrison() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transformerProprieteEnPrison'");
    }

    
}
