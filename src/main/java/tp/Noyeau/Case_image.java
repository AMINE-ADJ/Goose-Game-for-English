package tp.Noyeau;

public class Case_image extends Case {
    protected final String type ="Image";
    protected final String couleur = "pink";
    protected int index; // l'index de la case d'image
    protected Joueur joueur; // le joueur a la case d'image

    // le constructeur de la case de image
    public Case_image(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de image
    public Case_image(int index) {
        super(index);
        this.index = index;
        button.setStyle(style+"-fx-background-color:"+couleur);
    }

    // retourne l'index de la case destination
    public int getIndex() {
        return this.index;
    }

    public int mouvement(int inter_index) {
        return inter_index + 2; //exemple.
    }

    public int mouvement(boolean reponse_juste) {
        System.out.print("La case " + this.getIndex() + " est une case image!");
        int new_index = this.getIndex();
        if (reponse_juste) {
            System.out.println("La reponse est juste!");
            new_index = this.getIndex() + 2;
            System.out.println(" Le joueur va atteindre la case :  " + new_index + ".");

        } else {
            System.out.println("La reponse est fausse!");
        }
        return new_index;
    }

    public int score(int score) {
        return score;
    }

    public int score(boolean reponse_juste, int score) {
        if (reponse_juste) {
            return score + 10;
        } else {
            return score;
        }

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
