package tp.Ui;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp.Noyeau.Joueur;

import java.io.IOException;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class JeuManagement implements Serializable{


    public static HashMap<String, Joueur> players = new HashMap<String,Joueur>();

    private Joueur best = new Joueur();

    private Joueur currentPlayer;

    public static int meilleur_score_global;

    public void addPlayer(Joueur j) {

        players.put(j.getNom(),j);

    }





    public Joueur getPlayer(String nom) {
        return this.players.get(nom);
    }

    public Joueur getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(Joueur j) {
        this.currentPlayer = j;
    }





    public void gettabjoueur() {
        try {
            FileInputStream fileIn = new FileInputStream("data");
            ObjectInputStream OBF = new ObjectInputStream(fileIn);
            players = (HashMap<String, Joueur>) OBF.readObject();
            OBF.close();
            fileIn.close();

            FileInputStream bestIn = new FileInputStream("bestPlayer");
            ObjectInputStream best = new ObjectInputStream(bestIn);
            this.best = (Joueur) best.readObject();
            best.close();
            bestIn.close();
            System.out.println("\ndeSerialisation terminÃ©e avec succÃ¨s...\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void settabjoueur() {
        try {
            FileOutputStream fileOut = new FileOutputStream("data");
            ObjectOutputStream OBF = new ObjectOutputStream(fileOut);
            OBF.writeObject(players);
            OBF.close();
            fileOut.close();

            FileOutputStream filebest = new FileOutputStream("bestPlayer");
            ObjectOutputStream Best = new ObjectOutputStream(filebest);
            Best.writeObject(this.best);
            Best.close();
            filebest.close();

            System.out.println("\nSerialisation terminÃ©e avec succÃ¨s...\n");

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public Joueur authenticate(String username) {
        Joueur joueur1;
        gettabjoueur();
        joueur1 = players.get(username);
        if (joueur1 == null) {
            System.out.println("new gamer");
            joueur1 = new Joueur(username);
            players.put(username, joueur1);
            settabjoueur();
        }

        return joueur1;
    }
    public void saveEtat(Joueur joueur) {
//        joueur.Afficher_infos();
        joueur.setScore(joueur.getScore());

        if(joueur.getScore()>joueur.getScore()) {
            joueur.setScore(joueur.getScore());
        }

        if (joueur.getScore()>best.getScore()) {
            this.best = joueur;
        }
        players.put(joueur.getNom(), joueur);

        settabjoueur();
    }

    public String set_meilleur_score_global() {
        List<Integer> intValues = new ArrayList<>();
        for (String name : players.keySet()) {
            intValues.add(players.get(name).getScore());
        }
        JeuManagement.meilleur_score_global = Collections.max(intValues);
        return Integer.toString(Collections.max(intValues));
    }





    public Joueur getBestPlater() {
        return this.best;
    }

    public void setBestPlayer(Joueur j) {

        this.best = j;

    }





}

