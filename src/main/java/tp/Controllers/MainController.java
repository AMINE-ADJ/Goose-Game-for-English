package tp.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp.Ui.PlateauManagement;
import java.io.IOException;

public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void SwitchToPlateau(ActionEvent event ) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("/tp/PlateauView.fxml"));
            PlateauManagement plateauManage = new PlateauManagement();
            root = plateauManage.root;
        stage = (Stage) ((Node )event.getSource()).getScene().getWindow();
        scene = new Scene(root,1307,719);
        stage.setScene(scene);
        stage.show();
    }


}
