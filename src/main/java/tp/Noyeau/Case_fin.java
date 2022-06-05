package tp.Noyeau;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Case_fin extends Case {
    protected final String type ="Fin";
    protected final String couleur = "black";

    protected int index; // l'index de la case de fin
    protected Joueur joueur; // le joueur a la case de fin

    // le constructeur de la case de fin
    public Case_fin(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de fin
    public Case_fin(int index) {
        super(index);
        this.index = index;
        button.setStyle(style+"-fx-background-color:"+couleur);
    }

    public int getIndex() {
        return this.index;
    }

    // retourne l'index de la case destination
    public int mouvement(int inter_index) {
        System.out.print("La case " + this.getIndex() + " est une case fin!");
        return 0;
    }

    public int score(int score) {
        return score;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    // retourne vrai si le joueur est dans cette case //
    public boolean occupe() {
        return this.joueur != null;
    }

    public String getCouleur() {
        return this.couleur;
    }
    public String getType() {
        return type;
    }
}