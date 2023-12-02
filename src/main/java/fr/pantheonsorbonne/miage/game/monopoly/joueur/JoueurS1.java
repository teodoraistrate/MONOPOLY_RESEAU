package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixProprietesAHypothequer'");
    }

    @Override
    public Map<Propriete, Integer> choixNombreMaisonsAVendre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixNombreMaisonsAVendre'");
    }

    @Override
    public List<Propriete> choixHotelsAVendre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixHotelsAVendre'");
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
