package tp.Noyeau;

import java.util.*;

public class Plateau_jeu extends Plateau {

    // Constructeur du plateau du jeu
    public Plateau_jeu() {
        super(100);
        init_plateau();
    }

//    public void init_plateau() {
//        Case[] cases = new Case[100];
//        Set<Integer> set = new HashSet<Integer>(); // l'ensemble des cases
//        Random rand = new Random();
//
//        cases[0] = new Case_depart(0); // la case de depart a pour index 0
//        cases[0].ActivateJoueur();
//        for (int i = 1; i < 99; i++) {
//            cases[i] = new Case_parcours(i);
//        }
//        // On genere 25 cases aleatoirement sans la permiere et
//        // la derniere case, on change la nature de es cases de facon a avoir
//        // 5 cases : bonus, malus, saut, definition et image
//        while (set.size() < 25) {
//            set.add(1 + rand.nextInt(97));
//        } //to generate twenty-five different numbers in a set
//        Iterator<Integer> it = set.iterator();
//        // 5 cases de type bonus
//        for (int i = 0; i < 5; i++) {
//            int j = it.next();
//            cases[j] = new Case_bonus(j);
//        }
//        // 5 cases de type malus
//        for (int i = 0; i < 5; i++) {
//            int j = it.next();
//            cases[j] = new Case_malus(j);
//        }
//        // 5 cases de type saut
//        for (int i = 0; i < 5; i++) {
//            int j = it.next();
//            cases[j] = new Case_saut(j, 1 + rand.nextInt(97));
//        }
//        // 5 cases de type definition
//        for (int i = 0; i < 5; i++) {
//            int j = it.next();
//            cases[j] = new Case_definition(j);
//        }
//        // 5 cases de type image
//        for (int i = 0; i < 5; i++) {
//            int j = it.next();
//            cases[j] = new Case_image(j);
//        }
//
//        cases[99] = new Case_fin(99); // la case de fin a pour index 99
//        this.setCases(cases);
//    }

    public void init_plateau() {
        Case[] cases = new Case[100];
        Set<Integer> set = new HashSet<Integer> ();    // l'ensemble des cases
        Random rand = new Random();

        cases[0] = new Case_depart(0);    // la case de depart a pour index 0
        cases[0].ActivateJoueur();
        for (int i = 1; i < 99; i++) {
            cases[i] = new Case_parcours(i);
        }
        // On genere 25 cases aleatoirement sans la permiere et
        // la derniere case, on change la nature de es cases de facon a avoir
        // 5 cases : bonus, malus, saut, definition et image
        while (set.size() < 35) {
            set.add(1 + rand.nextInt(97));
        }
        Iterator<Integer> it = set.iterator();
        Vector<Integer> cases_bonus = new Vector<>();
        // 5 cases de type bonus
        for (int i = 0; i < 5; i++) {
            int j = it.next();
            cases_bonus.add(j);
            cases[j] = new Case_bonus(j);
        }
        // 5 cases de type malus
        int cpt = 0;
        while (cpt < 5) {
            int j = it.next();
            if (cases_bonus.contains(j - 2)) {
                continue;
            }
            cases[j] = new Case_malus(j);
            cpt++;
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

        cases[99] = new Case_fin(99);      // la case de fin a pour index 99
        this.setCases(cases);
    }
}