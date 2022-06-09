package tp.Noyeau;

import java.util.concurrent.ThreadLocalRandom;
import java.io.*;

public class Joueur implements Serializable {
    protected int id;
    protected String nom;
    protected Case case_actuelle;
    protected int score;


    public Joueur(){

    }
    public Joueur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // le constructeur de la classe Joueur
    public Joueur(String nom, Case case_actuelle, int score) {
        this.nom = nom;
        this.case_actuelle = case_actuelle;
        this.score = score;
    }

    // le constructeur de la classe Joueur
    public Joueur(String nom, Case case_actuelle) {
        this.nom = nom;
        this.case_actuelle = case_actuelle;
    }

    // le constructeur de la classe Joueur
    public Joueur(String nom) {
        this.nom = nom;
        this.case_actuelle = null;
    }

    // retourne le nom du joueur
    public String toString() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    // retourne la case actuelle du joueur
    public Case getCase() {
        return this.case_actuelle;
    }

    // change la case actuelle du joueur
    public void setCase(Case case_actuelle) {
        this.case_actuelle = case_actuelle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int lance_de() {
        return ThreadLocalRandom.current().nextInt(1, 6 + 1);
    }

    public int lance_des() {
        return lance_de() + lance_de();
    }


}
