package tp.Noyeau;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // on cree le pleteau du jeu
        Plateau_jeu plateau = new Plateau_jeu();
        plateau.init_plateau();
        // on definie le joueur de la partie
        Joueur joueur = new Joueur("Amine", plateau.getCase(0));

        // on initialise une partie de jeu
        Partie partie = new Partie(plateau, joueur);

        // on joue la partie
        partie.jouer();

//        FileOutputStream fileout = new FileOutputStream("Joueur.ser");
//        ObjectOutputStream out = new ObjectOutputStream(fileout);
//        out.writeObject(joueur);
//        System.out.println("Joueur is saved ! ");


        Joueur Amine = null;
        FileInputStream filein = new FileInputStream("Joueur.ser");
        ObjectInputStream in  = new ObjectInputStream(filein);
              Amine= (Joueur) in.readObject();
        System.out.println("Moi c'est : "+Amine.getNom()+"mon score : "+Amine.getScore());





    }
}
