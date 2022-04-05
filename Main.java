import java.util.*;
import java.lang.*;

// La classe d'un joueur
class Joueur {
   private int id;
   private String nom;
   private int score;

   Joueur(int id, String nom) {
      this.id = id;
      this.nom = nom;
   }

   public void update_score(int  nouveau_score) {
      this.score = nouveau_score;
      System.out.println("");
   }
      
   public int get_id() { return id;}
   public String get_nom() { return nom;}

   public void set_id(int id) { this.id = id;}
   public void set_nom(String nom) { this.nom = nom;}

}


// La classe des des
class De {

   public static int lancer_de () {
      return 1 + (int) (Math.random() % 6);
   }


   public void afficher_de (int value) {
      int[][][] face = {{{ 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }, 
                             { { 0, 0, 1 }, { 0, 0, 0 }, { 1, 0, 0 } },
                             { { 0, 0, 1 }, { 0, 1, 0 }, { 1, 0, 0 } }, 
                             { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } },
                             { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 1 } }, 
                             { { 1, 0, 1 }, { 1, 0, 1 }, { 1, 0, 1 } } };


      int[][] display = face[value - 1];
      System.out.println("-----");

      for (int i = 0; i < 3; i++) {
         System.out.print("|");
         for (int j = 0; j < 3; j++) {
            if (display[i][j] == 1) {
               System.out.print("o");
            } else {
               System.out.print(" ");
               }
         }
         System.out.println("|");
      }
      System.out.println("-----");
   }
}


// La classe du plateau
class Plateau {

/*
        Le plateau :

          -------  -------  -------  -------  -------  -------
          |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|
          |+   +|<-|+ R +|<-|+   +|<-|+ V +|<-|+   +|<-|+ J +| 
          |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|
          -------  -------  -------  -------  -------  -------
             |                                          Debut         
          -------  -------  -------  -------  -------  -------
          |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|
          |+ O +|  |+ R +|<-|+   +|<-|+   +|<-|+ V +|<-|+ R +|
          |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +| 
          -------  -------  -------  -------  -------  -------
             |        |                                   |
          -------  -------  -------  -------  -------  -------
          |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|
   |      |+   +|  |+ R +|  |+ V +|<-|+   +|<-|+ O +|  |+   +|    /|\
   |      |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|     |
  \|/     -------  -------  -------  -------  -------  -------     |
             |        |        |        |        |        |
          -------  -------  -------  -------  -------  -------
          |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|
          |+   +|  |+ R +|  |+ B +|->|+ N +|  |+   +|  |+   +|
          |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|
          -------  -------  -------  -------  -------  -------
             |        |                Fin       |        | 
          -------  -------  -------  -------  -------  -------
          |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|
          |+ B +|  |+   +|->|+   +|->|+ B +|->|+ - +|  |+ - +|
          |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|
          -------  -------  -------  -------  -------  -------
             |                                            |
          -------  -------  -------  -------  -------  -------
          |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|
          |+   +|->|+ V +|->|+   +|->|+   +|->|+ B +|->|+ O +|
          |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|  |+ - +|
          -------  -------  -------  -------  -------  -------

          Les deux des :

            D1              D2          
          +++++++        +++++++
          +     +        + O O +
          +  O  +        +  O  +
          +     +        + O O +   
          +++++++        +++++++

*/

   // Le plateau contient deux des et 100 cases disposees en spirale
   De D1, D2;    // les deux des 
   Case[] cases = new Case[100];   // le plateau a 100 cases

   public Plateau () {
      D1 = new De(De.lancer_de());
      D2 = new De(De.la);

      Set<Integer> set = new HashSet<Integer> ();    // l'ensemble des cases
      Random rand = new Random();

      // On genere 25 cases aleatoirement sans la permiere et
      // la derniere case, on change la nature de es cases de facon a avoir 
      // 5 cases : bonus, malus, saut, definition et image
      while (set.size() < 25) {
         set.add(rand.nextInt(99 + 1));
      }

      Iterator value = set.iterator();

      // 5 cases de type bonus
      for (int i = 0; i < 5; i++) {
         cases[(int) value.next()] = new Case_bonus();
      } 
      // 5 cases de type malus
      for (int i = 0; i < 5; i++) {
         cases[(int) value.next()] = new Case_malus();
      } 
      // 5 cases de type saut      
      for (int i = 0; i < 5; i++) {
         cases[(int) value.next()] = new Case_saut();
      } 
      // 5 cases de type definition     
      for (int i = 0; i < 5; i++) {
         cases[(int) value.next()] = new Case_definition();
      } 
      // 5 cases de type image 
      for (int i = 0; i < 5; i++) {
         cases[(int) value.next()] = new Case_image();
      } 
   }
}

// La classe d'une partie
class Partie {
   private Joueur joueur;
   private Plateau plateau;
   private int score;

   public Partie (int id, String nom_joueur) {
      joueur = new Joueur(id, nom_joueur);
      plateau = new Plateau();
   }

   public int get_score() { return score;}
   public void set_score(int score) { this.score = score;}

}


public interface Case {
   // retourne l'index de cette case
   public int get_index();
   // retourne l'index de la case arrivee
   public int mouvement(int lance_de);
   // retourne si la case est occupee
   public boolean occupe();
   // se que se passe quand un joueur est dans cette case
   public void joueur_present(Joueuer joueur);

   public Joueuer get_joueur();
   public void set_joueur(Joueuer joueur);
}


public class Case_depart implements Case {
   protected final String couleur = "JAUNE";
   protected int index;          // l'index de la case de depart
   protected Joueur joueur;      // le joueur a la case de depart

   // le constructeur de la case de depart
   public Case_depart(int index_init, Joueur joueur) {
      index = index_init;
      this.joueur = joueur;
   }
   // le constructeur de la case de depart
   public Case_depart(int index_init) {
      index = index_init;
   }
   public int get_index() {
      return this.index;
   }
   // retourne l'index de cette case
   public int mouvement(int lance_de) {
      return 0;
   }
   // retourne vrai si le joueur est dans cette case
   public boolean occupe() {
      return this.joueur != null;
   }
   public void score(Joueur joueur) {}
   public Joueur get_joueur() {
      return this.joueur;
   }
   public void set_joueur(Joueur joueur) {
      this.joueur = joueur;
   }
}


public class Case_bonus implements Case {
   protected final String couleur = "VERTE";
   protected int index;          // l'index de la case de bonus
   protected Joueur joueur;      // le joueur a la case de bonus

   // le constructeur de la case de bonus
   public Case_bonus(int index, Joueur joueur) {
      this.index = index;
      this.joueur = joueur;
   }
   // le constructeur de la case de bonus
   public Case_bonus(int index) {
      this.index = index;
   }
   public int get_index() {
      return this.index;
   }
   // retourne l'index de la case destination
   public int mouvement(int lance_de) {
      System.out.print("La case " + this.getIndex()+ " est une case bonus!");
      int new_index =  this.get_index() + 2;
      System.out.println(" Le joueure va atteindre la case :  " + new_index +".");
      return new_index;
   }
   // retourne vrai si le joueur est dans cette case
   public boolean occupe() {
      return this.joueur != null;
   }
   public void score(Joueur joueur) {
      joueur.score += 10;
   }
   public Joueur get_joueur() {
      return this.joueur;
   }
   public void set_joueur(Joueur joueur) {
      this.joueur = joueur;
   }
}

public class Case_malus implements Case {
   protected final String couleur = "ROUGE";
   protected int index;          // l'index de la case de malus
   protected Joueur joueur;      // le joueur a la case de malus

   // le constructeur de la case de malus
   public Case_malus(int index, Joueur joueur) {
      this.index = index;
      this.joueur = joueur;
   }
   // le constructeur de la case de malus
   public Case_malus(int index) {
      this.index = index;
   }
   public int get_index() {
      return this.index;
   }
   // retourne l'index de la case destination
   public int mouvement(int lance_de) {
      System.out.print("La case " + this.getIndex()+ " est une case malus!");
      int new_index =  this.get_index() - 2;
      System.out.println(" Le joueure va atteindre la case :  " + new_index +".");
      return new_index;
   }
   // retourne vrai si le joueur est dans cette case
   public boolean occupe() {
      return this.joueur != null;
   }
   public joueur_present(Joueur joueur) {
      joueur.score -= 10;
   }
   public Joueur get_joueur() {
      return this.joueur;
   }
   public void set_joueur(Joueur joueur) {
      this.joueur = joueur;
   }
}

public class Case_saut implements Case {
   protected final String couleur = "ORANGE";
   protected int index;          // l'index de la case de saut
   protected int contenu;        // le contenu de la case saut
   protected Joueur joueur;      // le joueur a la case de saut

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
   public int get_index() {
      return this.index;
   }
   // retourne l'index de la case destination
   public int mouvement(int lance_de) {
      System.out.print("La case " + this.getIndex()+ " est une case saut!");
      int new_index =  this.get_index() + contenu;
      System.out.println(" Le joueur va atteindre la case :  " + new_index +".");
      return new_index;
   }
   // retourne vrai si le joueur est dans cette case
   public boolean occupe() {
      return this.joueur != null;
   }
   public void joueur_present () {}
   }
   public Joueur get_joueur() {
      return this.joueur;
   }
   public void set_joueur(Joueur joueur) {
      this.joueur = joueur;
   }
}


public class Case_definition implements Case {
   protected final String couleur = "BLEU";
   protected int index;          // l'index de la case de definition
   protected Joueur joueur;      // le joueur a la case de definition

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
   public int get_index() {
      return this.index;
   }
   public int mouvement(int lance_de) {}
   public int mouvement(boolean reponse_juste) {
      System.out.print("La case " + this.getIndex()+ " est une case definition!");
      if (reponse_juste) {
         System.out.println("La reponse est juste!");
         int new_index =  this.get_index() + 4;
         System.out.println(" Le joueure va atteindre la case :  " + new_index +".");

      } else {
         System.out.println("La reponse est fausse!");
      }
      return new_index;
   }
   // retourne vrai si le joueur est dans cette case
   public boolean occupe() {
      return this.joueur != null;
   }
   public void score(boolean reponse_juste) {
      if (reponse_juste) {
         this.joueur.score += 20;
      } else {
         this.joueur.score -= 10;
      }
   }
   public Joueur get_joueur() {
      return this.joueur;
   }
   public void set_joueur(Joueur joueur) {
      this.joueur = joueur;
   }
}



public class Case_image implements Case {
   protected final String couleur = "ROSE";
   protected int index;          // l'index de la case d'image
   protected Joueur joueur;      // le joueur a la case d'image

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
   public int get_index() {
      return this.index;
   }
   public int mouvement(int lance_de) {}
   public int mouvement(boolean reponse_juste) {
      System.out.print("La case " + this.getIndex()+ " est une case image!");
      int new_index = this.get_index();
      if (reponse_juste) {
         System.out.println("La reponse est juste!");
         new_index =  this.get_index() + 2;
         System.out.println(" Le joueure va atteindre la case :  " + new_index +".");

      } else {
         System.out.println("La reponse est fausse!");
      }
      return new_index;
   }
   // retourne vrai si le joueur est dans cette case
   public boolean occupe() {
      return this.joueur != null;
   }
   public void score(boolean reponse_juste) {
      if (reponse_juste) {
         this.joueur.score += 10;
      }
   }
   public Joueur get_joueur() {
      return this.joueur;
   }
   public void set_joueur(Joueur joueur) {
      this.joueur = joueur;
   }
}


public class Case_fin implements Case {
   protected final String couleur = "NOIRE";
   protected int index;          // l'index de la case de fin
   protected Joueur joueur;      // le joueur a la case de fin

   // le constructeur de la case de fin
   public Case_fin(int index, Joueur joueur) {
      this.index = index;
      this.joueur = joueur;
   }
   // le constructeur de la case de fin
   public Case_fin(int index) {
      this.index = index;
   }
   public int get_index() {
      return this.index;
   }
   // retourne l'index de la case destination
   public int mouvement(int lance_de) {
      System.out.print("La case " + this.getIndex()+ " est une case fin!");
      return 0;
   }
   // retourne vrai si le joueur est dans cette case
   public boolean occupe() {
      return this.joueur != null;
   }
   public joueur_present(Joueur joueur) {}

   public Joueur get_joueur() {
      return this.joueur;
   }
   public void set_joueur(Joueur joueur) {
      this.joueur = joueur;
   }
}


// La classe du jeu 
class Jeu {
   public static int high_score = 0;
   public Partie[] parties;

   public Jeu (String[] joueurs) {
      parties = new Partie[joueurs.size()];      // On lance 2 partie a la fois
      for (int i = 0; i < joueurs.size(); i++) {
         parties[0] = new Partie(i + 1, joueurs[i]);   
      }
   }

   public void jouer() {
   }


}



public class Main {
   public static void main(String args[]) {
      
   }
}
