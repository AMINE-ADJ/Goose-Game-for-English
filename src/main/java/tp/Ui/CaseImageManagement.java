package tp.Ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import tp.Controllers.CaseDefinitionController;
import tp.Controllers.CaseImageController;
import tp.Noyeau.Partie;

import java.io.IOException;


public class CaseImageManagement {

    public Parent root;
    private FXMLLoader loader;
    private CaseImageController controller;
    private Parent previousRoot;

    public void LoadCaseImageView(Partie PartieModel, Stage MainStage) throws IOException {

        previousRoot = MainStage.getScene().getRoot();
        loader= new FXMLLoader(getClass().getResource("/tp/CaseImageView.fxml"));
        root = loader.load();
        controller = loader.getController();
//        controller.
//        Stage CaseDefStage = new Stage();
        controller.setMainStage(MainStage);
        controller.setPreviousRoot(previousRoot);
//        MainStage.setScene(new Scene(root,1307,719));
        MainStage.getScene().setRoot(root);
//        MainStage.show();


    }

}

