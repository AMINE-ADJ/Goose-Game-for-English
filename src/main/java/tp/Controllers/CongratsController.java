package tp.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tp.Noyeau.Joueur;
import tp.Noyeau.Partie;

import java.io.IOException;

public class CongratsController {

        private Stage MainStage;
        private Stage DialogStage;

    public int getBestScore() {
        return BestScore;
    }

    public void setBestScore(int bestScore) {
        BestScore = bestScore;
    }

    private int BestScore;
    @FXML
    private Label CongratsMsg;

    @FXML
    private Button ContinuerBtn;

    @FXML
    private Label ScoreAvant;

    @FXML
    private Label ScoreComparMsg;

    @FXML
    private Label ScoreResult;


    @FXML
    void SwitchBackToJeu(ActionEvent event) throws IOException {
        DialogStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/tp/JeuView.fxml"));
       Scene scene = new Scene(root,1307,719);
        MainStage.setScene(scene);
    }



    public void ShowCongratsStage(Partie PartieModel, Stage MainStage,Stage DialogStage){
        this.DialogStage = DialogStage;
        this.MainStage = MainStage;
        CongratsMsg.setText("Felicitations "+PartieModel.getJoueur().getNom()+"!!");
        ScoreResult.setText(""+PartieModel.getScore());
        ScoreAvant.setText(""+PartieModel.getJoueur().getScore());
        if (PartieModel.getScore() > PartieModel.getJoueur().getScore()) {
            PartieModel.getJoueur().setScore(PartieModel.getScore());
            ScoreComparMsg.setText("Olaa! Vous avez battu votre meilleur score !");
        } else {
            ScoreComparMsg.setText("Vous n'avez pas battu votre meilleur score, Good luck next time!");
        }
//                    stage.setTitle("Game Of Goose");
//                  String css = this.getClass().getResource("/tp/CssStyles/WelcomePage.css").toExternalForm();


    }


}
