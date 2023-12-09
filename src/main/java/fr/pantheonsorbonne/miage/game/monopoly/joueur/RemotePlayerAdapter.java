package fr.pantheonsorbonne.miage.game.monopoly.joueur;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Taxes;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.CartePayerOuChance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;


public class RemotePlayerAdapter extends Joueur {


    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;


    public RemotePlayerAdapter(String name, Socket socket) {
        super(name);
        this.socket = socket;
        try {
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Méthode pour envoyer une commande au serveur
    public boolean sendCommandToServer(String command) {
        try {
            outputStream.writeObject(command);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    // Méthode pour recevoir des mises à jour du serveur
    public void receiveUpdateFromServer() {
        try {
            Object update = inputStream.readObject();
            // Appliquer la mise à jour au joueur local
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Reste des méthodes de la classe Joueur à adapter en fonction de la communication réseau


    @Override
    public void ajouterArgent(int montant) {
        super.ajouterArgent(montant);
        sendCommandToServer("AJOUTER_ARGENT " + montant);
    }


    @Override
    public void payer(double montant) throws PasAssezArgentException {
        super.payer(montant);
        sendCommandToServer("PAYER " + montant);
    }




    @Override
    public boolean choixAcheterPropriete(Propriete propriete) {
        // pour implemnter la logique pour choisir d'acheter ou non la propriété
        boolean choix = false;  // on initialise le choix du joueur en fonction de la logique du jeu


        // Si le joueur a assez d'argent pour acheter la propriété, alors il choisit d'acheter
        if (getPorteMonnaie() >= propriete.getPrice()) {
            choix = true;
        }
        // on envoie la commande au serveur
        sendCommandToServer("ACHETER_PROPRIETE " + propriete.getName() + " " + (choix ? "OUI" : "NON"));


        // Retourner le choix
        return choix;
    }






    @Override
    public boolean choixPayerOuChance(CartePayerOuChance c) {
        try {
            // Envoyer une requête au serveur pour obtenir la décision du joueur
            sendCommandToServer("DEMANDE_PAYER_OU_CHANCE " + c.getMontantAPayer());
           
            // Recevoir la réponse du serveur
            String reponse = (String) inputStream.readObject();
           
            boolean choix = "OUI".equalsIgnoreCase(reponse);


            // on Retourne le choix
            return choix;
        } catch (Exception e) {
            e.printStackTrace();
            // En cas d'erreur, vous pouvez choisir une valeur par défaut ou lever une exception
            return false;
        }
    }




    @Override
    public boolean choixSortirPrison() {
        try {
            // Envoyer une requête au serveur pour obtenir la décision du joueur
            sendCommandToServer("DEMANDE_SORTIR_PRISON");
           
            // Recevoir la réponse du serveur
            String reponse = (String) inputStream.readObject();
           
            // Analyser la réponse pour déterminer le choix du joueur
            boolean choix = "OUI".equalsIgnoreCase(reponse);
            // Retourner le choix
            return choix;
        } catch (Exception e) {
            e.printStackTrace();
            // En cas d'erreur, vous pouvez choisir une valeur par défaut ou lever une exception
            return false;
        }
    }






    @Override
    public List<Propriete> choixProprietesARacheter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixProprietesARacheter'");
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
    public List<Propriete> choixProprietesAHypothequer() {
    try {
        // Envoyer une requête au serveur pour obtenir la liste des noms de propriétés disponibles
        sendCommandToServer("DEMANDE_PROPRIETES_DISPONIBLES_HYPOTHEQUE");


        // Recevoir la réponse du serveur (la liste des noms de propriétés disponibles)
        List<String> nomsProprietesDisponibles = (List<String>) inputStream.readObject();


        // Convertir les noms en objets Propriete
        List<Propriete> proprietesDisponibles = new ArrayList<>();
        for (String nomPropriete : nomsProprietesDisponibles) {
            Propriete propriete = Plateau.getCaseParNom(nomPropriete);
            if (propriete != null) {
                proprietesDisponibles.add(propriete);
            } else {
                System.out.println("La propriété n'est pas disponible : " + nomPropriete);
                // Gérer le cas où la propriété n'est pas disponible
            }
        }


        List<Propriete> choixProprietesAHypothequer = new ArrayList<>();
        int montantRecu = 0;


        // Choix des propriétés à hypothéquer en fonction du montant total pouvant être reçu
        for (Propriete p : proprietesDisponibles) {
            if (this.getPorteMonnaie() + montantRecu < 500 && !p.estHypotheque()) {
                choixProprietesAHypothequer.add(p);
                montantRecu += p.getPrixRevente();
            } else {
                break;
            }
        }


        return choixProprietesAHypothequer;


    } catch (Exception e) {
        e.printStackTrace();
        // En cas d'erreur, vous pouvez choisir une valeur par défaut ou lever une exception
        return Collections.emptyList(); // Retourne une liste vide en cas d'erreur
    }
}




    @Override
    public boolean choixPayerOuAttendre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choixPayerOuAttendre'");
    }


    @Override
    public Terrain choixTransformerProprieteEnPrison() {
        try {
            // Assume que vous avez une instance de Plateau nommée plateauInstance dans votre classe
            // Remplacez cela par la façon dont vous obtenez une instance de Plateau dans votre classe
            Plateau plateauInstance = Plateau.getInstance();


            // Envoyer une requête au serveur pour obtenir la liste des noms de propriétés disponibles
            sendCommandToServer("DEMANDE_PROPRIETES_DISPONIBLES");


            // Recevoir la réponse du serveur (la liste des noms de propriétés disponibles)
            List<String> nomsProprietesDisponibles = (List<String>) inputStream.readObject();


            // Convertir les noms en objets Propriete
            List<Propriete> proprietesDisponibles = new ArrayList<>();
            for (String nomPropriete : nomsProprietesDisponibles) {
                Propriete propriete = plateauInstance.getCaseParNom(nomPropriete);
                if (propriete instanceof Terrain) {
                    proprietesDisponibles.add((Terrain) propriete);
                } else {
                    System.out.println("La propriété n'est pas un terrain : " + nomPropriete);
                    // Gérer le cas où la propriété n'est pas un terrain
                }
            }


            System.out.println("Aucun terrain disponible pour la transformation en prison.");
            // Gérer le cas où aucune propriété disponible n'est pas bonne pour la transformation
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            // En cas d'erreur, on peut choisir une valeur par défaut ou lever une exception
            return null;
        }


}






}


