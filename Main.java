import java.util.*;
// import java.lang.*;
import java.util.concurrent.ThreadLocalRandom;

// La classe d'un joueur
class Joueur {
    protected int id;
    protected String nom;
    protected Case case_actuelle;
    protected int score;

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

    private int lance_de() {
        return ThreadLocalRandom.current().nextInt(1, 6 + 1);
    }

    public int lance_des() {
        return lance_de() + lance_de();
    }
}

// La classe du plateau
abstract class Plateau {
    /*
     * Le plateau :
     * 
     * ------- ------- ------- ------- ------- -------
     * |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +|
     * |+ +|<-|+ R +|<-|+ +|<-|+ V +|<-|+ +|<-|+ J +|
     * |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +|
     * ------- ------- ------- ------- ------- -------
     * | Debut
     * ------- ------- ------- ------- ------- -------
     * |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +|
     * |+ O +| |+ R +|<-|+ +|<-|+ +|<-|+ V +|<-|+ R +|
     * |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +|
     * ------- ------- ------- ------- ------- -------
     * | | |
     * ------- ------- ------- ------- ------- -------
     * |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +|
     * | |+ +| |+ R +| |+ V +|<-|+ +|<-|+ O +| |+ +| /|\
     * | |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |
     * \|/ ------- ------- ------- ------- ------- ------- |
     * | | | | | |
     * ------- ------- ------- ------- ------- -------
     * |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +|
     * |+ +| |+ R +| |+ B +|->|+ N +| |+ +| |+ +|
     * |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +|
     * ------- ------- ------- ------- ------- -------
     * | | Fin | |
     * ------- ------- ------- ------- ------- -------
     * |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +|
     * |+ B +| |+ +|->|+ +|->|+ B +|->|+ - +| |+ - +|
     * |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +|
     * ------- ------- ------- ------- ------- -------
     * | |
     * ------- ------- ------- ------- ------- -------
     * |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +|
     * |+ +|->|+ V +|->|+ +|->|+ +|->|+ B +|->|+ O +|
     * |+ - +| |+ - +| |+ - +| |+ - +| |+ - +| |+ - +|
     * ------- ------- ------- ------- ------- -------
     * 
     * Les deux des :
     * 
     * D1 D2
     * +++++ +++++
     * + + +O O+
     * + O + + O +
     * + + +O O+
     * +++++ +++++
     * 
     */
    // Le plateau contient deux des et 100 cases disposees en spirale
    protected int nombre_cases; // le nombre de cases du plateau
    protected Case[] cases; // le cases du plateau

    // Constructeur du plateau avec le nombre de cases donne
    public Plateau(int nombre_cases) {
        this.nombre_cases = nombre_cases;
    }

    // Construit le plateau selon le plateau voulou
    public abstract void init_plateau();

    // Retourne les cases du plateau
    public Case[] getCases() {
        return this.cases;
    }

    // Modifie les cases du plateau
    public void setCases(Case[] cases) {
        this.cases = cases;
    }

    // Retourne la case specifiee par l'index
    public Case getCase(int index) {
        return this.getCases()[index];
    }

    // Retourne le nombre de cases du plateau
    public int getNombre_cases() {
        return this.nombre_cases;
    }
}

class Plateau_jeu extends Plateau {

    // Constructeur du plateau du jeu
    public Plateau_jeu() {
        super(100);
    }

    public void init_plateau() {
        Case[] cases = new Case[100];
        Set<Integer> set = new HashSet<Integer>(); // l'ensemble des cases
        Random rand = new Random();

        cases[0] = new Case_depart(0); // la case de depart a pour index 0

        for (int i = 1; i < 99; i++) {
            cases[i] = new Case_parcours(i);
        }
        // On genere 25 cases aleatoirement sans la permiere et
        // la derniere case, on change la nature de es cases de facon a avoir
        // 5 cases : bonus, malus, saut, definition et image
        while (set.size() < 25) {
            set.add(1 + rand.nextInt(97));
        }
        Iterator<Integer> it = set.iterator();
        // 5 cases de type bonus
        for (int i = 0; i < 5; i++) {
            int j = it.next();
            cases[j] = new Case_bonus(j);
        }
        // 5 cases de type malus
        for (int i = 0; i < 5; i++) {
            int j = it.next();
            cases[j] = new Case_malus(j);
        }
        // 5 cases de type saut
        for (int i = 0; i < 5; i++) {
            int j = it.next();
            cases[j] = new Case_saut(j, 1 + rand.nextInt(97));
        }
        // 5 cases de type definition
        for (int i = 0; i < 5; i++) {
            int j = it.next();
            cases[j] = new Case_definition(j);
        }
        // 5 cases de type image
        for (int i = 0; i < 5; i++) {
            int j = it.next();
            cases[j] = new Case_image(j);
        }

        cases[99] = new Case_fin(99); // la case de fin a pour index 99
        this.setCases(cases);
    }
}

abstract class Case {

    // retourne l'index de cette case
    public abstract int getIndex();

    // retourne l'index de la case arrivee
    public abstract int mouvement(int inter_index);// int mouvement(int inter_index)

    // se que se passe quand un joueur est dans cette case
    public abstract int score(int score);

    public abstract Joueur getJoueur();

    public abstract void setJoueur(Joueur joueur);

    public abstract String getCouleur();

}

class Case_depart extends Case {
    protected final String couleur = " JAUNE ";
    protected int index; // l'index de la case de depart
    protected Joueur joueur; // le joueur a la case de depart //to know wether this cell is occuped or not

    //// le constructeur de la case de depart
    public Case_depart(int index_init, Joueur joueur) {
        index = index_init;
        this.joueur = joueur;
    }

    // le constructeur de la case de depart // utilise dans la creation du plateau
    public Case_depart(int index_init) {
        index = index_init;
    }

    public int getIndex() {
        return this.index;
    }

    // retourne l'index de cette case
    public int mouvement(int inter_index) {
        System.out.print("La case " + this.getIndex() + " est la case de depart!");
        int new_index = inter_index + 0;
        System.out.println(" Le joueur va atteindre la case :  " + new_index + ".");
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
}

class Case_parcours extends Case {
    protected final String couleur = " BLANCHE ";
    protected int index; // l'index de la case de parcours
    protected Joueur joueur; // le joueur a la case de parcours

    // le constructeur de la case de parcours
    public Case_parcours(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de parcours
    public Case_parcours(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    // retourne l'index de la case destination
    public int mouvement(int inter_index) {
        System.out.print("La case " + this.getIndex() + " est une case parcours!");
        int new_index = inter_index; // elle va rien faire.
        System.out.println(" Le joueur va atteindre la case :  " + new_index + ".");
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
}

class Case_bonus extends Case {
    protected final String couleur = " VERTE ";
    protected int index; // l'index de la case de bonus
    protected Joueur joueur; // le joueur a la case de bonus

    // le constructeur de la case de bonus
    public Case_bonus(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de bonus
    public Case_bonus(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    // retourne l'index de la case destination
    public int mouvement(int inter_index) {
        System.out.print("La case " + this.getIndex() + " est une case bonus!");
        int new_index = inter_index + 2; // avancement de deux cases.
        System.out.println(" Le joueur va atteindre la case :  " + new_index + ".");
        return new_index;
    }

    public int score(int score) {
        return score + 10;
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
}

class Case_malus extends Case {
    protected final String couleur = " ROUGE ";
    protected int index; // l'index de la case de malus
    protected Joueur joueur; // le joueur a la case de malus

    // le constructeur de la case de malus
    public Case_malus(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de malus
    public Case_malus(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    // retourne l'index de la case destination
    public int mouvement(int inter_index) {
        System.out.print("La case " + this.getIndex() + " est une case malus!");
        int new_index = inter_index - 2; // recule de deux cases.
        System.out.println(" Le joueur va atteindre la case :  " + new_index + ".");
        return new_index;
    }

    public int score(int score) {
        return score - 10;
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
}

class Case_saut extends Case {
    protected final String couleur = " ORANGE ";
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
        this.index = index;
        this.contenu = contenu;
    }

    public int getIndex() {
        return this.index;
    }

    // retourne l'index de la case destination
    public int mouvement(int inter_index) {
        System.out.print("La case " + this.getIndex() + " est une case saut!");
        int new_index = ThreadLocalRandom.current().nextInt(0, 99 + 1);
        System.out.println(" Le joueur va atteindre la case :  " + new_index + ".");
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
}

class Case_definition extends Case {
    protected final String couleur = " BLEU ";
    protected int index; // l'index de la case de definition
    protected Joueur joueur; // le joueur a la case de definition

    // le constructeur de la case de definition
    public Case_definition(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de definition
    public Case_definition(int index) {
        this.index = index;
    }

    // retourne l'index de la case destination
    public int getIndex() {
        return this.index;
    }

    public int mouvement(int inter_index) {
        return inter_index;
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

    // retourne vrai si le joueur est dans cette case //ca sert fl'affichage blk
    public boolean occupe() {
        return this.joueur != null;
    }

    public String getCouleur() {
        return this.couleur;
    }
}

class Case_image extends Case {
    protected final String couleur = " ROSE ";
    protected int index; // l'index de la case d'image
    protected Joueur joueur; // le joueur a la case d'image

    // le constructeur de la case de image
    public Case_image(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de image
    public Case_image(int index) {
        this.index = index;
    }

    // retourne l'index de la case destination
    public int getIndex() {
        return this.index;
    }

    public int mouvement(int inter_index) {
        return inter_index;
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
}

class Case_fin extends Case {
    protected final String couleur = " NOIRE ";
    protected int index; // l'index de la case de fin
    protected Joueur joueur; // le joueur a la case de fin

    // le constructeur de la case de fin
    public Case_fin(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de fin
    public Case_fin(int index) {
        this.index = index;
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

    // retourne vrai si le joueur est dans cette case //ca sert fl'affichage blk
    public boolean occupe() {
        return this.joueur != null;
    }

    public String getCouleur() {
        return this.couleur;
    }
}

// La classe d'une partie
class Partie {
    private Joueur joueur;
    private Plateau_jeu plateau;
    private boolean partie_finie;
    private int score = 0;

    public Partie(Plateau_jeu plateau, Joueur joueur) {
        this.plateau = plateau;
        this.joueur = joueur;
        this.partie_finie = false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public boolean isPartie_finie() {
        return partie_finie;
    }

    public void setPartie_finie(boolean partie_finie) {
        this.partie_finie = partie_finie;
    }

    public Plateau_jeu getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau_jeu plateau) {
        this.plateau = plateau;
    }

    public void jouer() {
        int taille_plateau = this.getPlateau().getNombre_cases();
        while (!partie_finie) {
            int resultat = this.joueur.lance_des();
            // calculer le nouveau index du joueur
            int index_actuel = joueur.getCase().getIndex();
            int inter_index = index_actuel + resultat;
            // int index_intermediare;
            int index_destination;
            System.out.println(joueur.toString() + " est dans la case : " + index_actuel + ".");
            System.out.println("Le resultat de la lance du de est : " + resultat);
            if (inter_index < taille_plateau - 1) { // 99
                System.out.println("Le joueur doit se deplacer premierement en clickant sur la case :" + inter_index);
                System.out.println(" la case :" + inter_index + " est une case intermediaire de couleur "
                        + this.getPlateau().getCase(inter_index).getCouleur());
                index_destination = this.getPlateau().getCase(inter_index).mouvement(inter_index); //on le deplace mla valeur intermediaire li clicka seyed bach ra7lha
                this.score = this.getPlateau().getCase(inter_index).score(this.score);
                System.out.println("le score de joueur apres le parcours d'une case " + this.getPlateau().getCase(inter_index).getCouleur() + "est : " + this.score);
                    if (index_destination > taille_plateau - 1) { //case bonus a 99
                        index_destination = taille_plateau - 1; 
                    } else if (index_destination < 0) {
                        index_destination = 0;
                    }
            } else {
                int index_fin = taille_plateau-1;
                int supplementaire =  (resultat - ((taille_plateau - 1) - index_actuel));
                // index_destination = index_actuel - (resultat - ((taille_plateau - 1) - index_actuel)); 
                index_destination = index_fin -  supplementaire  ; 
                System.out.println("le joueur a depasser la case Fin avec "+ supplementaire +" Cases");
                System.out.println("donc il va se trouver maintenant a la case : "+index_destination);
            }
          
            Case case_destination = this.getPlateau().getCase(index_destination);
            Case case_actuelle = this.getPlateau().getCase(index_actuel);
            case_actuelle.setJoueur(null); // to change the occupied state.
            case_destination.setJoueur(joueur);
            joueur.setCase(case_destination);
            // this.getPlateau().getCase(index_destination).setJoueur(joueur);

            if (index_destination == taille_plateau - 1) {
                System.out.println(joueur.toString() + " est dans la case " + index_destination + " qui est la case fin!");
                System.out.println("La partie est finie!");
                System.out.println("Le Meilleur score du joueur " + joueur.toString() + " est : " + joueur.getScore());
                partie_finie = true;
            }
            System.out.println();
        }

        System.out.println("\n Votre score final de cette partie \n :" + this.score);
        if (this.score > joueur.getScore()) {
            System.out.println("\n Votre meilleur score avant cette partie : " + joueur.getScore());
            joueur.setScore(this.score);
            System.out.println(" \n Congrats! vous avez battu votre meilleur score !");
            System.out.println("\n Votre nouveau meilleur score  : " + joueur.getScore());
        } else {
            System.out.println("meilleur score li kan 3ndk khir, Good luck next time!");
        }
    }

}

// La classe du jeu
class Jeu {
    public static int high_score = 0;

}

public class Main {
    public static void main(String[] args) {
        // on cree le pleteau du jeu
        Plateau_jeu plateau = new Plateau_jeu();
        plateau.init_plateau();
        // on definie le joueur de la partie
        Joueur joueur = new Joueur("Amine", plateau.getCase(0));

        // on initialise une partie de jeu
        Partie partie = new Partie(plateau, joueur);

        // on joue la partie
        partie.jouer();
    }
}
