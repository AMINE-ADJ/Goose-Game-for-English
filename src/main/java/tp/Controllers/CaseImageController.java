package tp.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CaseImageController {
    private Stage MainStage;
    private Parent previousRoot;
    @FXML
    private Button choicebtn1;

    @FXML
    private Button choicebtn2;

    @FXML
    private Button choicebtn3;

    @FXML
    private Button choicebtn4;

    @FXML
    private ImageView imgPlace01;

    @FXML
    private ImageView imgPlace02;

    @FXML
    private ImageView imgPlace03;

    @FXML
    private ImageView imgPlace04;

    @FXML
    private Label wordId;

    public void setMainStage(Stage stage){
        this.MainStage = stage;
    }
    public void setPreviousRoot(Parent previousRoot){
        this.previousRoot = previousRoot;
    }
    @FXML
    void onValidateClick(ActionEvent event) {
        MainStage.getScene().setRoot(previousRoot);
//        MainStage.setScene(new Scene(previousRoot));


    }
}
