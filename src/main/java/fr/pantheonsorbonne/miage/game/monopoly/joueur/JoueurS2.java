package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;

public class JoueurS2 extends Joueur {

    public JoueurS2(String name) {
        super(name);
    }

    // il achète une propriété s'il pourrait en acheter 10
    @Override
    public boolean choixAcheterPropriete(Propriete propriete) {
        if(propriete.getLoyer()*10<this.getPorteMonnaie()) return true;
        return false;
    }

    @Override
    public boolean choixPayerOuChance() {
        // true : il tire une carte Chance
        // false : il va payer le montant

        return true; 
        // il va toujours tirer une carte Chance
    }

    @Override
    public boolean choixSortirPrison() {
        if(this.getPorteMonnaie()>5*Prison.MONTANT_SORTIR) return true;
        return false;
    }

    @Override
    public List<Propriete> choixProprietesAHypothequer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixProprietesAHypothequer'");
    }

    @Override
    public Map<Terrain, Integer> choixNombreMaisonsAVendre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixNombreMaisonsAVendre'");
    }

    @Override
    public List<Terrain> choixHotelsAVendre() {
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
