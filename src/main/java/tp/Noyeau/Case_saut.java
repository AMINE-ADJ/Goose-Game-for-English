package tp.Noyeau;

import javafx.scene.control.Alert;

import java.util.concurrent.ThreadLocalRandom;

public class Case_saut extends Case {
    protected final String type ="Saut";
    protected final String couleur = "orange";
    protected int index; // l'index de la case de saut
    protected int contenu; // le contenu de la case saut
    protected Joueur joueur; // le joueur a la case de saut

    // le constructeur de la case de saut
    public Case_saut(int index, int contenu, Joueur joueur) {
        this.index = index;
        this.contenu = contenu;
        this.joueur = joueur;
    }

    // le constructeur de la case de saut
    public Case_saut(int index, int contenu) {
        super(index);
        this.index = index;
        this.contenu = contenu;
        button.setStyle(style+"-fx-background-color:"+couleur);
    }

    public int getIndex() {
        return this.index;
    }

    // retourne l'index de la case destination
    public int mouvement(int inter_index) {
        System.out.print("La case " + this.getIndex() + " est une case saut!");
        int new_index = ThreadLocalRandom.current().nextInt(1, 99 + 1);
        System.out.println(" Le joueur va atteindre la case :  " + new_index + ".");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Vous devez allez a la case "+new_index);
        alert.show();
        return new_index;
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

    // retourne vrai si le joueur est dans cette case //ca sert fl'affichage blk
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
