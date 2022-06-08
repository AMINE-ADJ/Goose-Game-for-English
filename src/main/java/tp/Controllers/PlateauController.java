package tp.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tp.Noyeau.*;
import tp.Ui.CongratsManagement;

import java.io.IOException;
import java.util.Optional;

public class PlateauController {

    private Partie PartieModel;
    private boolean DiceEnrolled=false;
    private int DicesResult;
    private Stage MainStage;
    private boolean force = false;
    @FXML
    private GridPane grille;

    @FXML
    private Button allerBtn;

    @FXML
    private Label GuideMessage;

    @FXML
    private Label Nom_Joueur;

    @FXML
    private Label Score_Joueur;

    @FXML
    private ImageView diceImg1;

    @FXML
    private ImageView diceImg2;

    @FXML
    private Button rollDicesBtn;

    @FXML
    private Button QuitterBtn;
//    public void setMainStage(Stage MainStage){
//        this.MainStage = MainStage;
//    }
    public Partie getPartieModel() {
        return PartieModel;
    }

    public void setPartieModel(Partie partieModel) {
        PartieModel = partieModel;
        Nom_Joueur.setText(PartieModel.getJoueur().getNom());
        int Currentscore = PartieModel.getScore();
        Score_Joueur.setText(Integer.toString(Currentscore));
    }

    @FXML
    void SwitchBackToJeu(ActionEvent event) throws IOException {
//        DialogStage.close();
        MainStage = (Stage) ((Node )event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tp/JeuView.fxml"));
        Scene scene = new Scene(root,1307,719);
        MainStage.setScene(scene);
    }

    @FXML
    void rollTheDices(ActionEvent event)  {
        MainStage = (Stage) ((Node )event.getSource()).getScene().getWindow();
        DiceEnrolled = true;
      int de1= PartieModel.getJoueur().lance_de();
        Image De1Image = new Image(getClass().getResourceAsStream("/tp/Dices/dice0"+de1+".png"));
      diceImg1.setImage(De1Image);
      int de2= PartieModel.getJoueur().lance_de();
      Image De2Image = new Image(getClass().getResourceAsStream("/tp/Dices/dice0"+de2+".png"));
      diceImg2.setImage(De2Image);

      DicesResult = de1+de2;
//        currentIndex = PartieModel.getCurrentCase().getIndex();
      int  interIndex = PartieModel.getCurrentCase().getIndex()+DicesResult;
        int taille_plateau=PartieModel.getPlateau().getNombre_cases();
        if (interIndex < taille_plateau - 1) {
            PartieModel.setInterCase(PartieModel.getPlateau().getCase(interIndex));
            GuideMessage.setText("Cliquez sur la case "+interIndex);

        } else if (interIndex> taille_plateau - 1){
            int index_fin = taille_plateau - 1;
            int supplementaire = (DicesResult - ((taille_plateau - 1) - PartieModel.getCurrentCase().getIndex()));
            GuideMessage.setText("Vous avez depassé la case fin avec "+supplementaire+" cases");

            int  index_destination = index_fin - supplementaire;
//            CaseDestination = this.getPlateau().getCase(index_destination);
            PartieModel.setInterCase(PartieModel.getPlateau().getCase(index_destination));
            GuideMessage.setText("Cliquez sur la case "+ index_destination);
        }else {
            PartieModel.setPartie_finie(true);
            PartieModel.setInterCase(PartieModel.getPlateau().getCase(taille_plateau - 1));
//            PartieModel.getInterCase().ActivateJoueur();
//            PartieModel.getCurrentCase().DisableJoueur();
//                Alert Congrats = new Alert(Alert.AlertType.INFORMATION);
//                Congrats.setContentText("Vous avez fini la partie !");
//                Congrats.setTitle("Congratz !");
//                Congrats.setHeaderText("Bsahtek Kho");
//                Congrats.showAndWait().ifPresent(rs -> {
//                    if (rs == ButtonType.OK) {
//                        System.out.println("Pressed OK.");
//                        //eddih l la scene li 9belha with Switchtojeu.
//
//
//                    }
//                });
            }
        rollDicesBtn.setDisable(true); //jusqu'au clique d'une case.


    }
    //fonction de remplissage d'une grille dans plateauView a partir de plateau.Noyeau Model.

    public void remplireGrille()
    {
        int maxC = 13;
        int maxL = 12;
        int minC = 0;
        int minL = 2;

        int column = 2;
        int ligne = 0;

        int cpt = 0;


        while(cpt < 100)
        {
            while(column <= maxC && cpt<100)
            {
                PartieModel.getPlateau().getCases()[cpt].getButton().setOnAction(new onCaseClickHandle());
                grille.add(PartieModel.getPlateau().getCases()[cpt].getButton(),column , ligne );
                column ++;
                cpt ++;
            }
            column --;
            ligne ++;
            maxC -=2;

            while(ligne <= maxL && cpt<100)
            {
                PartieModel.getPlateau().getCases()[cpt].getButton().setOnAction(new onCaseClickHandle());

                grille.add(PartieModel.getPlateau().getCases()[cpt].getButton(),column , ligne );
                ligne ++;
                cpt ++;
            }

            ligne --;
            column --;
            maxL -=2;

            while(column >= minC && cpt<100)
            {
                PartieModel.getPlateau().getCases()[cpt].getButton().setOnAction(new onCaseClickHandle());

                grille.add(PartieModel.getPlateau().getCases()[cpt].getButton(),column , ligne );
                column --;
                cpt ++;
            }

            column ++;
            ligne --;
            minC +=2;

            while(ligne >= minL && cpt<100)
            {
                PartieModel.getPlateau().getCases()[cpt].getButton().setOnAction(new onCaseClickHandle());
                grille.add(PartieModel.getPlateau().getCases()[cpt].getButton(),column , ligne );
                ligne --;
                cpt ++;
            }
            ligne ++;
            column ++;
            minL +=2;
        }

    }

private class onCaseClickHandle implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent isClicked) {
//        System.out.println("Im Cliiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiicked !!!");
        if( !DiceEnrolled){
            GuideMessage.setText("Roulez les des svp !");
        }else{ //il essaye de cliquer sur le plateau.
            Object object = isClicked.getSource();
            MainStage = (Stage) ((Node )isClicked.getSource()).getScene().getWindow();

            if(object == PartieModel.getInterCase().getButton() ){
                System.out.println("Inter "+PartieModel.getInterCase()+" Is Clicked !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                PartieModel.getInterCase().ActivateJoueur();
                PartieModel.getCaseDestination().ActivateJoueur();
                PartieModel.getCurrentCase().DisableJoueur(); //---------------------------------------------------------------------------------tdisabla mor un peu du temps

//
                PartieModel.getInterCase().ActivateJoueur();
                if (PartieModel.getInterCase().getIndex()!=99){
                  if(!force) { PartieModel.JouerUneFois(DicesResult);} //misajorit les cases....current dok hiya inter l9dima w 3ndi destination lekher ga3 avec l effet de intercase..
//              int  indexDestination = PartieModel.getCaseDestination().getIndex();
                    System.out.println("2 Mor Jouer Une Fois. ");
                    System.out.println("La Current Case  : "+ PartieModel.getCurrentCase().getIndex());
                    System.out.println("La Case inter : "+ PartieModel.getInterCase().getIndex());
                    System.out.println("La Case Destination  : "+ PartieModel.getCaseDestination().getIndex());
                    Score_Joueur.setText(Integer.toString(PartieModel.getScore()));
//                    PartieModel.getCurrentCase().ActivateJoueur();

                    while (PartieModel.getInterCase().getCouleur()!="white" && PartieModel.getInterCase().getCouleur()!="black" ){ //ce n'est pas une case de parcourt //hadi l current mor maclicka seyed...fiha l inter l9dim

                        PartieModel.getInterCase().ActivateJoueur();
                        System.out.println("Inter Case = "+ PartieModel.getInterCase().getIndex() +"is activated ");
                        try {
                            PartieModel.getInterCase().DisableJoueur();
                            GuideMessage.setText("Oh C'est une Case "+PartieModel.getInterCase().getType()+" !");

                            Thread.sleep(500);
                            System.out.println("First waiting 01");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        PartieModel.getCurrentCase().DisableJoueur(); //--------------------------------------------------
                        PartieModel.setInterCase(PartieModel.getCaseDestination());
                        PartieModel.setCurrentCase(PartieModel.getInterCase()); //hna current = destination te3 iteration lwla...li 7ssbtha m3a lwl nl jouer l9dima


                        System.out.println("4 Mor Setting CurrentCase <--- Case Destination. ");
                        System.out.println("La Current Case  : "+ PartieModel.getCurrentCase().getIndex());
                        System.out.println("La Case inter : "+ PartieModel.getInterCase().getIndex());
                        System.out.println("La Case Destination  : "+ PartieModel.getCaseDestination().getIndex());
//                    PartieModel.getCurrentCase().ActivateJoueur();
                        if (PartieModel.getInterCase().getIndex()!=99){
                            PartieModel.JouerEncore();
                        }


                        PartieModel.getCurrentCase().ActivateJoueur();
                        System.out.println("5 Mor jouer Encore. ");
                        System.out.println("La Current Case  : "+ PartieModel.getCurrentCase().getIndex());
                        System.out.println("La Case inter : "+ PartieModel.getInterCase().getIndex());
                        System.out.println("La Case Destination  : "+ PartieModel.getCaseDestination().getIndex());

                        System.out.println("U r not disabeling current l9dim ");
                        PartieModel.setCurrentCase(PartieModel.getCaseDestination()); //pour pouvoir avancer.
                        PartieModel.getCurrentCase().DisableJoueur();
                        PartieModel.getCaseDestination().ActivateJoueur();



                    }
                    force = false;
                    DiceEnrolled=false;
                    rollDicesBtn.setDisable(false);
                }else{
                    //fin partie.
                    PartieModel.getInterCase().ActivateJoueur();
                    PartieModel.getCurrentCase().DisableJoueur();
//                    Alert Congrats = new Alert(Alert.AlertType.INFORMATION);
//                    Congrats.setContentText("Vous avez fini la partie !");
//                    Congrats.setTitle("Congratz !");
//                    Congrats.setHeaderText("Bsahtek Kho");
//                    Congrats.showAndWait().ifPresent(rs -> {
//                        if (rs == ButtonType.OK) {
//                            System.out.println("Pressed OK.");
//                            //eddih l la scene li 9belha with Switchtojeu.
//
//
//                        }
//                    });
                    try {
                        CongratsManagement congratsManagement = new CongratsManagement(MainStage,PartieModel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            } else{
                GuideMessage.setText("NON!,Vous devez sur la case "+PartieModel.getInterCase().getIndex());
            }
        }
    }
}



    @FXML
    void AllerACase(ActionEvent event) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Veuillez entrez le numero de la case que vous souhaitez tester");
        dialog.setContentText("Numero d'une case : ");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent((numero)-> {
            force = true;
            int numCase = Integer.parseInt(numero);
            PartieModel.getCurrentCase().DisableJoueur();
            PartieModel.setCurrentCase(PartieModel.getPlateau().getCase(numCase));
            PartieModel.setInterCase(PartieModel.getPlateau().getCase(numCase));
            PartieModel.setCaseDestination(PartieModel.getPlateau().getCase(numCase));
            PartieModel.getJoueur().setCase(PartieModel.getPlateau().getCase(numCase));
            GuideMessage.setText("Veuillez cliquer sur la case "+ numCase);
            DiceEnrolled = true;
            DicesResult = 0;
            rollDicesBtn.setDisable(true);
        });



    }

    public GridPane getGrille() {
        return grille;
    }

    public void setGrille(GridPane grille) {
        this.grille = grille;
    }

    public Label getGuideMessage() {
        return GuideMessage;
    }

    public void setGuideMessage(Label guideMessage) {
        GuideMessage = guideMessage;
    }

    public Label getNom_Joueur() {
        return Nom_Joueur;
    }

    public void setNom_Joueur(Label nom_Joueur) {
        Nom_Joueur = nom_Joueur;
    }

    public Label getScore_Joueur() {
        return Score_Joueur;
    }

    public void setScore_Joueur(Label score_Joueur) {
        Score_Joueur = score_Joueur;
    }

    public ImageView getDiceImg2() {
        return diceImg2;
    }

    public void setDiceImg2(ImageView diceImg2) {
        this.diceImg2 = diceImg2;
    }

    public Button getRollDicesBtn() {
        return rollDicesBtn;
    }

    public void setRollDicesBtn(Button rollDicesBtn) {
        this.rollDicesBtn = rollDicesBtn;
    }

    public ImageView getDiceImg1() {
        return diceImg1;
    }

    public void setDiceImg1(ImageView diceImg1) {
        this.diceImg1 = diceImg1;
    }



}
