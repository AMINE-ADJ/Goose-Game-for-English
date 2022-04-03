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

// La classe d'une case du plateau
interface Case {
   public void mouvement();   // la methode qui englobe l'avancement et le recul
   public void score();       // la methode qui englobe le gain et la perte

}

class Case_bonus implements Case {
   private final String couleur = "VERTE";   
   public void mouvement() {
      
         
   }
   public void score() {
   }
}

class Case_malus implements Case {
   private final String couleur = "ROUGE";

}

class Case_saut implements Case {
   private final String couleur = "ORANGE";
}

class Case_definition implements Case {
   private final String couleur = "BLEU";

}

class Case_image implements Case {
   private final String couleur = "ROSE";

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
