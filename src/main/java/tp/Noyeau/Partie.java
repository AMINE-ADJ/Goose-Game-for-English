package tp.Noyeau;

import java.io.Serializable;

public class Partie implements Serializable {
    private Joueur joueur;
    private Plateau_jeu plateau;
    private Partie PartieModel;
    private boolean partie_finie;
    private int score = 0;
    private Case CurrentCase;
    private Case interCase;
    private Case CaseDestination;

    public Case getCurrentCase() {
        return CurrentCase;
    }

    public void setCurrentCase(Case currentCase) {
        CurrentCase = currentCase;
    }

    public Case getInterCase() {
        return interCase;
    }

    public void setInterCase(Case interCase) {
        this.interCase = interCase;
    }

    public Case getCaseDestination() {
        return CaseDestination;
    }

    public void setCaseDestination(Case caseDestination) {
        CaseDestination = caseDestination;
    }



    public String getGuideMessage() {
        return GuideMessage;
    }

    public void setGuideMessage(String guideMessage) {
        GuideMessage = guideMessage;
    }

    private String GuideMessage;


    public Partie(Plateau_jeu plateau, Joueur joueur) {
        this.plateau = plateau;
        this.joueur = joueur;
        this.partie_finie = false;
        this.CurrentCase = plateau.getCase(0);
        this.CaseDestination = plateau.getCase(0);
        this.interCase = plateau.getCase(0);
//        System.out.println(CurrentCase.getIndex());
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

    public void JouerUneFois(int resultatLancesDes) {
        int taille_plateau = this.getPlateau().getNombre_cases();

            int resultat = resultatLancesDes;
            // calculer le nouveau index du joueur
        int index_actuel = joueur.getCase().getIndex();
        CurrentCase = this.getPlateau().getCase(index_actuel);
//        CurrentCase.ActivateJoueur();
        int inter_index = index_actuel + resultat;
            int index_destination;
            System.out.println(joueur.toString() + " est dans la case : " + index_actuel + ".");
            System.out.println("Le resultat de la lance du de est : " + resultat);
            if (inter_index < taille_plateau - 1) { // 99
                interCase = this.getPlateau().getCase(inter_index);// ur setting in mn berra tan.....
                System.out.println("Le joueur doit se deplacer premierement en clickant sur la case :" + inter_index);
                System.out.println(" la case :" + inter_index + " est une case intermediaire de couleur "
                        + this.getPlateau().getCase(inter_index).getCouleur());

                index_destination = this.getPlateau().getCase(inter_index).mouvement(inter_index); //ytappliqua l effect de la case intermediaire.ida kanet parcout mattbdlch

                this.score = this.getPlateau().getCase(inter_index).score(this.score); //PartieModel lzm tkoun kayna ....
                GuideMessage = "Vous devez allez vers la case "+index_destination;
                System.out.println("le score de joueur apres le parcours d'une case "
                        + this.getPlateau().getCase(inter_index).getCouleur() + "est : " + this.score);
                if (index_destination > taille_plateau - 1) { // case bonus a 99
//                    index_destination = taille_plateau - 1; //bonus y9der yrb7k direct....
                    int index_fin = taille_plateau - 1;
                    int supplementaire = (resultat - ((taille_plateau - 1) - index_actuel));
                    index_destination = index_fin - supplementaire;
                    System.out.println("le joueur a depasser la case Fin avec " + supplementaire + " Cases");
                    System.out.println("donc il va se trouver maintenant a la case : " + index_destination);
                } else if (index_destination < 0) {
                    index_destination = 0;
                }

            } else {

                int index_fin = taille_plateau - 1;
                int supplementaire = (resultat - ((taille_plateau - 1) - index_actuel));
                index_destination = index_fin - supplementaire;
                System.out.println("le joueur a depasser la case Fin avec " + supplementaire + " Cases");
                System.out.println("donc il va se trouver maintenant a la case : " + index_destination);
            }

            CaseDestination = this.getPlateau().getCase(index_destination);

            joueur.setCase(interCase);
//            joueur.setCase(CaseDestination);
            CurrentCase = interCase;

            if (index_destination == taille_plateau - 1) {
                System.out.println(
                        joueur.toString() + " est dans la case " + index_destination + " qui est la case fin!");
                System.out.println("La partie est finie!");
                System.out.println("Le Meilleur score du joueur " + joueur.toString() + " est : " + joueur.getScore());
                partie_finie = true;
            }

        }




        public void JouerEncore(){
            int indexDestination = CurrentCase.mouvement(CurrentCase.getIndex());
          int  taille_plateau = plateau.getNombre_cases();
            if (indexDestination >= taille_plateau - 1) { // case bonus a 99
                indexDestination = taille_plateau - 1; //bonus y9der yrb7k direct....
                partie_finie = true;
            } else if (indexDestination < 0) {
                indexDestination = 0;
            }
            System.out.println("Affichage indexDestination from JouerEncore : " +indexDestination);//mahoch ytbedel l indexDestination..raho y93ed = indexDestiantion
            CaseDestination = plateau.getCase(indexDestination);//Maj desitantion
            this.score = CurrentCase.score(this.score);
//            CurrentCase = CaseDestination;
            CurrentCase.setJoueur(null); // to change the occupied state.
            CaseDestination.setJoueur(joueur);
            joueur.setCase(CaseDestination);
            interCase = CurrentCase;


        }


//    }
}