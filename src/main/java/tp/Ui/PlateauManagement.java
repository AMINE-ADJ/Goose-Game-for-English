package tp.Ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import tp.Controllers.PlateauController;
import tp.Noyeau.Plateau_jeu;

import java.io.IOException;

public class PlateauManagement {

 public Parent root;
 private FXMLLoader loader;
 private Plateau_jeu plateauModel;
 private  PlateauController controller;
    public PlateauManagement() throws IOException {
    loader= new FXMLLoader(getClass().getResource("/tp/PlateauView.fxml"));
     root =  loader.load();
         controller = loader.getController();
         //plateau jeu test brk...sinn ra7 yji hada m3mr.
        plateauModel = new Plateau_jeu();//for testing brk
        plateauModel.init_plateau();

        controller.setPlateauModel(plateauModel);
        controller.remplireGrille();
    }

    public void Load(){

    }

}
