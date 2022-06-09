package tp.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tp.Noyeau.Jeu;
import tp.Noyeau.Plateau_jeu;
import tp.Ui.PlateauManagement;

import java.io.IOException;

public class JeuController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String PlayerName;
    @FXML
    private TextField TextFieldId;

    @FXML
    private Button commencerId;

    @FXML
    private Button reprendreId;

    @FXML
    void CommencerBtn(ActionEvent event) {

    }

    @FXML
    void ReprendrePartie(ActionEvent event) {

    }

    @FXML
    void onEntrezNom(ActionEvent event) {

    }
    @FXML
    public void SwitchToPlateau(ActionEvent event ) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("/tp/PlateauView.fxml"));
        PlayerName = TextFieldId.getText();
        if (PlayerName !="") {
            PlateauManagement plateauManage = new PlateauManagement(PlayerName);
            root = plateauManage.root;
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,1307,719);
            String css = this.getClass().getResource("/tp/CssStyles/WelcomePage.css").toExternalForm();
//        scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Erreur : Veuillez entrer votre nom svp !");
            alert.show();
        }
    }
    @FXML
    void Quitterjeu(ActionEvent event) {
        Platform.exit();
    }




}
