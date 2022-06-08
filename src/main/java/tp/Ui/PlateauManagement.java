package tp.Ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import tp.Controllers.PlateauController;
import tp.Noyeau.Joueur;
import tp.Noyeau.Partie;
import tp.Noyeau.Plateau_jeu;

import java.io.IOException;

public class PlateauManagement {


    public Parent root;
 private FXMLLoader loader;
 private Plateau_jeu plateauModel;
 private  PlateauController controller;
 private Partie partieModel;
    public PlateauManagement() throws IOException {
    loader= new FXMLLoader(getClass().getResource("/tp/PlateauView.fxml"));
     root =  loader.load();
         controller = loader.getController();
         //plateau jeu test brk...sinn ra7 yji hada m3mr.
        plateauModel = new Plateau_jeu();//for testing brk
        plateauModel.init_plateau();
        Joueur joueurModel = new Joueur("Amine", plateauModel.getCase(0));
         partieModel = new Partie(plateauModel,joueurModel);//la partie mattcryach hna...t3mrha mn berra w npassiha direct ll controller psk rahi champs....

//        controller.setPlateauModel(plateauModel);
        controller.setPartieModel(partieModel);
//        controller.setMainStage();
        controller.remplireGrille();
    }

    public void Load(){

    }


    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Plateau_jeu getPlateauModel() {
        return plateauModel;
    }

    public void setPlateauModel(Plateau_jeu plateauModel) {
        this.plateauModel = plateauModel;
    }

    public PlateauController getController() {
        return controller;
    }

    public void setController(PlateauController controller) {
        this.controller = controller;
    }

    public void setPartieModel(Partie partieModel) {
        this.partieModel = partieModel;
    }
    public Partie getPartieModel() {
        return partieModel;
    }
}
