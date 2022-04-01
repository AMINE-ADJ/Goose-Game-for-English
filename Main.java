import java.util.*;
import java.lang.*;

// La classe d'un joueur
class Joueur {
   private int id;
   private String nom;
   private int score;

   Joueur(int id, String nom, int score) {
      this.id = id;
      this.nom = nom;
      this.score = score;
   }

   public void update_score(int  nouveau_score) {
      this.score = nouveau_score;
      System.out.println("");
         
   }
      
   @Override 
   public String toString() {
      return (nom + " [" + score + "]");
   }

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
   int D1, D2;    // les deux des 
   Case[] cases = new Case[100];   // le plateau a 100 cases

   public Plateau () {
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

}

// La classe d'une case du plateau
class Case {
     
      
}

class Case_bonus extends Case {
   private final String couleur = "VERTE";

}

class Case_malus extends Case {
   private final String couleur = "ROUGE";

}

class Case_saut extends Case {
   private final String couleur = "ORANGE";
}

class Case_definition extends Case {
   private final String couleur = "BLEU";

}

class Case_image extends Case {
   private final String couleur = "ROSE";

}

class Jeu {
   Partie[] parties = new Partie[3];
}





public class Main {
   public static void main(String args[]) {
      
   }
}
