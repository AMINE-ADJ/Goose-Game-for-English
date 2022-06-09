package tp.Noyeau;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

// La classe du jeu
public class Jeu {
    Set<Partie> parties_joueurs;    // on associe une partie a chaque joueur
    Map<Joueur, Integer> meilleurs_scores;  // on associe a chaque joueur son meilleur score
    // pour sauvegarder une partie
    public void sauvegader_partie(Partie partie) {
        try {
            FileOutputStream fileOut = new FileOutputStream("partie.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(partie);
            out.close();
            fileOut.close();
            System.out.print("Partie sauvegardee");
            this.parties_joueurs.add(partie);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // pour restituer une partie sauvegardee
    public Partie restituer_partie() {
        Partie partie = null;
        try {
            FileInputStream fileIn = new FileInputStream("partie.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            partie = (Partie) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Partie inexistante");
            c.printStackTrace();
        }
        return partie;
    }
    // constructeur du jeu
    public Jeu() {
        parties_joueurs = new HashSet<Partie>();
    }

    public void commencer_partie(String nom_joueur) {
        Plateau_jeu plateau = new Plateau_jeu();
        Joueur joueur = new Joueur(nom_joueur, plateau.getCase(0));
        Partie PartieModel = new Partie(plateau, joueur);

//        if (partie.getScore() > joueur.score) {
//            joueur.setScore(partie.getScore());
//            meilleurs_scores.put(joueur, partie.getScore());
//        }
//        this.meilleurs_scores.put(joueur, partie.getScore());
    }

    public void reprendre_partie(Partie partie, Joueur joueur) {
//        partie.jouer();
        this.parties_joueurs.add(partie);
    }

//    public void CreatePartie() {
//
//        plateau = new Plateau_jeu();//for testing brk
//        plateau.init_plateau();
//        Joueur joueurModel = new Joueur("Nassiiim", plateau.getCase(0));
//        PartieModel = new Partie(plateau,joueurModel);//la partie mattcryach hna...t3mrha mn berra w npassiha direct ll controller psk rahi cham
//    }
}