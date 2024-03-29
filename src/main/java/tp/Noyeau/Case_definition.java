package tp.Noyeau;

import tp.Ui.CaseDefinitionManagement;

import java.io.IOException;

public class Case_definition extends Case {
    protected final String type ="Definition";
    protected final String couleur = "blue";
    protected int index; // l'index de la case de definition
    protected Joueur joueur; // le joueur a la case de definition
//    protected CaseDefinitionManagement CaseDefManage = new CaseDefinitionManagement();

    // le constructeur de la case de definition
    public Case_definition(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de definition
    public Case_definition(int index) {
        super(index);
        this.index = index;
        button.setStyle(style+"-fx-background-color:"+couleur);
    }

    // retourne l'index de la case destination
    public int getIndex() {
        return this.index;
    }

    public int mouvement(int inter_index) {
        return inter_index +4;
    }


    public int mouvement(boolean reponse_juste) {
        int new_index = this.getIndex();
        System.out.print("La case " + this.getIndex() + " est une case definition!");
        if (reponse_juste) {
            System.out.println("La reponse est juste!");
            new_index = this.getIndex() + 4;
            System.out.println(" Le joueur va atteindre la case :  " + new_index + ".");

        } else {
            System.out.println("La reponse est fausse!");
        }
        return new_index;
    }

    public int score(int score) {
        return score;
    }

    public void score(boolean reponse_juste) {
        if (reponse_juste) {
            this.joueur.setScore(joueur.getScore() + 20);
        } else {
            this.joueur.setScore(joueur.getScore() - 10);
        }
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    // retourne vrai si le joueur est dans cette case 
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
