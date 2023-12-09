package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Taxes;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NetworkPlayer extends Joueur {

    private RemotePlayerAdapter remotePlayerAdapter;

    public NetworkPlayer(String name, RemotePlayerAdapter remotePlayerAdapter) {
        super(name);
        this.remotePlayerAdapter = remotePlayerAdapter;
    }

    @Override
    public void ajouterArgent(int montant) throws IOException {
        super.ajouterArgent(montant);
        remotePlayerAdapter.sendCommandToServer("AJOUTER_ARGENT " + montant);
    }

    @Override
    public void payer(double montant) throws PasAssezArgentException {
        super.payer(montant);
        remotePlayerAdapter.sendCommandToServer("PAYER " + montant);
    }

    // Autres méthodes à adapter en fonction de la communication réseau

    @Override
    public boolean choixAcheterPropriete(Propriete propriete) {
        boolean choix = false;

        if (getPorteMonnaie() >= propriete.getPrice()) {
            choix = true;
        }

        try {
            remotePlayerAdapter.sendCommandToServer("ACHETER_PROPRIETE " + propriete.getName() + " " + (choix ? "OUI" : "NON"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return choix;
    }

    // Autres méthodes à adapter en fonction de la communication réseau

    @Override
    public boolean choixPayerOuChance(CartePayerOuChance c) {
        try {
            remotePlayerAdapter.sendCommandToServer("DEMANDE_PAYER_OU_CHANCE " + c.getMontantAPayer());
            String reponse = remotePlayerAdapter.receiveUpdateFromServer();
            return "OUI".equalsIgnoreCase(reponse);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Autres méthodes à adapter en fonction de la communication réseau

    @Override
    public boolean choixSortirPrison() {
        try {
            remotePlayerAdapter.sendCommandToServer("DEMANDE_SORTIR_PRISON");
            String reponse = remotePlayerAdapter.receiveUpdateFromServer();
            return "OUI".equalsIgnoreCase(reponse);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Autres méthodes à adapter en fonction de la communication réseau

    @Override
    public List<Propriete> choixProprietesARacheter() {
        try {
            remotePlayerAdapter.sendCommandToServer("DEMANDE_PROPRIETES_A_RACHETER");
            List<String> nomsProprietesDisponibles = remotePlayerAdapter.receiveUpdateFromServer();
            // Conversion des noms en objets Propriete
            // ...
            return Collections.emptyList(); // À adapter en fonction de votre implémentation
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Autres méthodes à adapter en fonction de la communication réseau

    @Override
    public Map<Terrain, Integer> choixNombreMaisonsAVendre() {
        try {
            remotePlayerAdapter.sendCommandToServer("DEMANDE_NOMBRE_MAISONS_A_VENDRE");
            Map<String, Integer> response = remotePlayerAdapter.receiveUpdateFromServer();
            // Conversion des noms en objets Terrain et nombres en Map<Terrain, Integer>
            // ...
            return Collections.emptyMap(); // À adapter en fonction de votre implémentation
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    // Autres méthodes à adapter en fonction de la communication réseau

    @Override
    public List<Terrain> choixHotelsAVendre() {
        try {
            remotePlayerAdapter.sendCommandToServer("DEMANDE_HOTELS_A_VENDRE");
            List<String> nomsHotelsDisponibles = remotePlayerAdapter.receiveUpdateFromServer();
            // Conversion des noms en objets Terrain (Hotels)
            // ...
            return Collections.emptyList(); // À adapter en fonction de votre implémentation
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Autres méthodes à adapter en fonction de la communication réseau

    @Override
    public List<Propriete> choixProprietesAHypothequer() {
        try {
            remotePlayerAdapter.sendCommandToServer("DEMANDE_PROPRIETES_DISPONIBLES_HYPOTHEQUE");
            List<String> nomsProprietesDisponibles = remotePlayerAdapter.receiveUpdateFromServer();
            // Conversion des noms en objets Propriete
            // ...
            return Collections.emptyList(); // À adapter en fonction de votre implémentation
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Autres méthodes à adapter en fonction de la communication réseau

    @Override
    public boolean choixPayerOuAttendre() {
        try {
            remotePlayerAdapter.sendCommandToServer("DEMANDE_PAYER_OU_ATTENDRE");
            String reponse = remotePlayerAdapter.receiveUpdateFromServer();
            return "OUI".equalsIgnoreCase(reponse);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Autres méthodes à adapter en fonction de la communication réseau

    @Override
    public Terrain choixTransformerProprieteEnPrison() {
        try {
            remotePlayerAdapter.sendCommandToServer("DEMANDE_PROPRIETES_DISPONIBLES");
            List<String> nomsProprietesDisponibles = remotePlayerAdapter.receiveUpdateFromServer();
            // Conversion des noms en objets Propriete
            // ...
            System.out.println("Aucun terrain disponible pour la transformation en prison.");
            return null; // À adapter en fonction de votre implémentation
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Autres méthodes à adapter en fonction de la communication réseau
}
