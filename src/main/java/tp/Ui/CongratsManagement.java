package tp.Ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp.Controllers.CongratsController;
import tp.Controllers.PlateauController;
import tp.Noyeau.Joueur;
import tp.Noyeau.Partie;
import tp.Noyeau.Plateau_jeu;

import java.io.IOException;
public class CongratsManagement {
    public Parent root;
    private FXMLLoader loader;
//    private Plateau_jeu plateauModel;
    private int BestScore=100;
    private CongratsController controller;
//    private Partie PartieModel;
//    private Partie partieModel;
//    private Stage MainStage;
    public CongratsManagement(Stage MainStage,Partie PartieModel) throws IOException {

//        this.PartieModel = PartieModel;
//        this.MainStage = MainStage;
        Stage Congratsstage =new Stage();
        loader= new FXMLLoader(getClass().getResource("/tp/Congratulations.fxml"));
        root =  loader.load();
        controller = loader.getController();
//        controller.setBestScore();
//        controller.ShowCongratsStage(PartieModel,MainStage);
//        Congratsstage.setTitle("Game Of Goose");
//        String css = this.getClass().getResource("/tp/CssStyles/WelcomePage.css").toExternalForm();
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(css);
        Congratsstage.setScene(scene);

        controller.ShowCongratsStage(PartieModel,MainStage,Congratsstage);
        Congratsstage.setResizable(false);

        Congratsstage.show();

    }

}
