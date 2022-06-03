package tp.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp.Ui.PlateauManagement;

import java.io.IOException;

public class JeuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void SwitchToPlateau(ActionEvent event ) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("/tp/PlateauView.fxml"));
        PlateauManagement plateauManage = new PlateauManagement();
        root = plateauManage.root;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1307,719);
        String css = this.getClass().getResource("/tp/CssStyles/WelcomePage.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }



}
