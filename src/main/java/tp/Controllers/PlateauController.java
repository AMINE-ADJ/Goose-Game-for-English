package tp.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import tp.Noyeau.Plateau_jeu;

public class PlateauController {
    Plateau_jeu plateauModel;

    public Plateau_jeu getPlateauModel() {
        return plateauModel;
    }

    public void setPlateauModel(Plateau_jeu plateauModel) {
        this.plateauModel = plateauModel;
    }

    @FXML
    private GridPane grille;



    //fonction de remplissage d'une grille dans plateauView a partir de plateau.Noyeau Model.

    public void remplireGrille()
    {
        int maxC = 13;
        int maxL = 12;
        int minC = 0;
        int minL = 2;

        int column = 2;
        int ligne = 0;

        int cpt = 0;


        while(cpt < 100)
        {
            while(column <= maxC && cpt<100)
            {

                grille.add(plateauModel.getCases()[cpt].getButton(),column , ligne );
                column ++;
                cpt ++;
            }
            column --;
            ligne ++;
            maxC -=2;

            while(ligne <= maxL && cpt<100)
            {

                grille.add(plateauModel.getCases()[cpt].getButton(),column , ligne );
                ligne ++;
                cpt ++;
            }

            ligne --;
            column --;
            maxL -=2;

            while(column >= minC && cpt<100)
            {

                grille.add(plateauModel.getCases()[cpt].getButton(),column , ligne );
                column --;
                cpt ++;
            }

            column ++;
            ligne --;
            minC +=2;

            while(ligne >= minL && cpt<100)
            {

                grille.add(plateauModel.getCases()[cpt].getButton(),column , ligne );
                ligne --;
                cpt ++;
            }
            ligne ++;
            column ++;
            minL +=2;
        }

    }

}
