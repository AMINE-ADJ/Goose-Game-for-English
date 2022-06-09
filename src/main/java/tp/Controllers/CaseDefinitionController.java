package tp.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tp.Noyeau.Case;
import tp.Noyeau.Partie;
import tp.Ui.CaseDefinitionManagement;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CaseDefinitionController {

    private Stage MainStage;
    private Parent previousRoot;
    private Partie PartieModel;
    private ArrayList<CaseDefinitionManagement.Definition> definitions;
    private CaseDefinitionManagement.Definition definition;

    public ArrayList<CaseDefinitionManagement.Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<CaseDefinitionManagement.Definition> definitions) {
        this.definitions = definitions;
    }

    public void setMainStage(Stage stage){
        this.MainStage = stage;
    }
    public void setPartieModel(Partie partieModel) {
        this.PartieModel = partieModel;
    }
    public void setPreviousRoot(Parent previousRoot){
        this.previousRoot = previousRoot;
    }
    public void getRandomQuestion(){
//        CaseDefinitionManagement.Definition definitionModel;
        int i = ThreadLocalRandom.current().nextInt(1, 5);
        this.definition= definitions.get(i);
        System.out.println(definition.getDefinition());
        System.out.println(definition.getReponse());

    }
    @FXML
    private Label DefQuestion;

    @FXML
    private Button validateBtn;

    @FXML
    private TextField AnswerField;
    @FXML
    void onValidateClick(ActionEvent event) {
            String Answer = AnswerField.getText();
            if(Answer!=""){
                boolean correct =( Answer == definition.getReponse());
                    if(correct){
                        int currentindex = PartieModel.getCurrentCase().getIndex() + 4;
                        PartieModel.setScore(PartieModel.getScore() + 20);
                        Case newCase = PartieModel.getPlateau().getCase(currentindex +4);

                        PartieModel.setGuideMessage("Bonne reponse!, vous gagnez 20 points et vous etes avancer a la case "+newCase.getIndex());
                        PartieModel.setCurrentCase(newCase);
                        PartieModel.setInterCase(newCase);
                        PartieModel.setCaseDestination(newCase);
                        PartieModel.getJoueur().setCase(newCase);

                    } else {
                        PartieModel.setScore(PartieModel.getScore() - 10);
                        PartieModel.setGuideMessage("Mauvaise reponse!, vous perdez 10 points ");
                    }
                MainStage.getScene().setRoot(previousRoot);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Erreur : Veuillez remplir le champ de reponse!");
                alert.show();
            }

//        MainStage.setScene(new Scene(previousRoot));


    }
    public void RemplirDefinition(){
        DefQuestion.setText(definition.getDefinition());
    }

}
