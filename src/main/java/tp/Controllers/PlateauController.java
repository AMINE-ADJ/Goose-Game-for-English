package tp.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import tp.Noyeau.Case;
import tp.Noyeau.Joueur;
import tp.Noyeau.Partie;
import tp.Noyeau.Plateau_jeu;

public class PlateauController {


//   private Plateau_jeu plateauModel;
//   private Joueur joueurModel;
    private Partie PartieModel;
    private boolean DiceEnrolled=false;
    private int DicesResult;
//    private int currentIndex =0;
//    private int interIndex;
//    private int indexDestination;
    @FXML
    private GridPane grille;

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
    void rollTheDices(ActionEvent event)  {
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
       PartieModel.setInterCase(PartieModel.getPlateau().getCase(interIndex));
      GuideMessage.setText("Cliquez sur la case "+interIndex);
        rollDicesBtn.setDisable(true); //jusqu'a qu'il clique sur une case.
//      Clickhandler() te3 chaque bouton; ida clicka 3la la bonne case nkml njwzlo la PartieModel.jouerune, puis nro7 njib mn partiemodel le nouveau guideMessage bach yro7 yclicki syed 3la destination w nssetih lte3 principal + ki yclicki ( wella 7etta ykoun fl parcours ) nro7 ngolo enrolli les des a nouveau.
//        PartieModel.getJoueur().afficherJoueurDansGrille();
//        PartieModel.CurrentCase.

//        if(PartieModel.isPartie_finie()){
//            Alert Congrats = new Alert(Alert.AlertType.INFORMATION);
//            Congrats.setContentText("Vous avez fini la partie !");
//            Congrats.setTitle("Congratz !");
//            Congrats.setHeaderText("Bsahtek Kho");
//            Congrats.showAndWait().ifPresent(rs -> {
//                if (rs == ButtonType.OK) {
//                    System.out.println("Pressed OK.");
//                    //eddih l la scene li 9belha with Switchtojeu.
//
//                }
//            });        }
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

            if(object == PartieModel.getInterCase().getButton()){ // cliqua 3la la case intermediaire li 9otlo 3liha m3a lwl
//                PartieModel.getCaseDestination().ActivateJoueur();
                PartieModel.getInterCase().ActivateJoueur(); //hnnnaaaaaaaaaaaa psk marakch tmisajori l intercase fl cas case speciale.
                PartieModel.getCurrentCase().DisableJoueur();

                PartieModel.JouerUneFois(DicesResult); //misajorit les cases....current dok hiya inter l9dima w 3ndi destination lekher ga3 avec l effet de intercase..
              int  indexDestination = PartieModel.getCaseDestination().getIndex();
                PartieModel.getInterCase().ActivateJoueur();
                Score_Joueur.setText(Integer.toString(PartieModel.getScore()));
//                PartieModel.getPlateau().getCase(currentIndex).DisableJoueur();
//                PartieModel.getCaseDestination().DisableJoueur();
//                PartieModel.getPlateau().getCase(interIndex).ActivateJoueur();
//                currentIndex = interIndex;
//                interIndex = indexDestination;
                System.out.println("currenCase dans partie model "+PartieModel.getCurrentCase().getIndex());
//                PartieModel.getCurrentCase().ActivateJoueur();
//                PartieModel.getInterCase().ActivateJoueur();
                PartieModel.getCurrentCase().ActivateJoueur();
                PartieModel.getInterCase().ActivateJoueur();
                PartieModel.getCaseDestination().ActivateJoueur();

                while (PartieModel.getCurrentCase().getCouleur()!="white"){ //ce n'est pas une case de parcourt //hadi l current mor maclicka seyed...fiha l inter l9dim
//                    GuideMessage.setText("Cliquer sur la case "+ PartieModel.getCaseDestination().getIndex());
//                    PartieModel.getInterCase().ActivateJoueur();
//                   GuideMessage.setText("Oh c'est une Case Speciale !");
//                    PartieModel.JouerUneFois(DicesResult);
                    //dirlha l'impact wella segem une fonction fla partie li tkhdm le cas simple...la case courante tdirlha l'impact.

//                    try {
//
//                        GuideMessage.setText("Oh c'est une Case Speciale !");
//                        Thread.sleep(1000);
//                        System.out.println("First waiting 01");
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

//                    PartieModel.getCurrentCase().ActivateJoueur();
//                    PartieModel.setInterCase(PartieModel.getCaseDestination());
                    try {

                        GuideMessage.setText("Oh c'est une Case Speciale !");
                        Thread.sleep(1000);
                        System.out.println("First waiting 01");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    PartieModel.getInterCase().DisableJoueur();
                    PartieModel.getCaseDestination().ActivateJoueur();
                    PartieModel.getCurrentCase().DisableJoueur();
                    PartieModel.setCurrentCase(PartieModel.getCaseDestination()); //hna current = destination te3 iteration lwla...li 7ssbtha m3a lwl nl jouer l9dima
//                    if()
                    PartieModel.JouerEncore();

//                    PartieModel.get
//                    try {
//
//                        GuideMessage.setText("Oh c'est une Case Speciale !");
//                        Thread.sleep(2000);
//                        System.out.println("Second waiting 02");
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                  PartieModel.getCurrentCase().DisableJoueur(); //disablit l current l9dim.
                    System.out.println("index case destination from la boucle : "+ PartieModel.getCaseDestination().getIndex());
                  PartieModel.setCurrentCase(PartieModel.getCaseDestination()); //pour pouvoir avancer.
                    System.out.println("index current case  from la boucle apres le setting : "+ PartieModel.getCurrentCase().getIndex());
                  PartieModel.getCaseDestination().ActivateJoueur();

                    System.out.println("Caaaaaaaaaaase speciale. ");


                }
                DiceEnrolled=false;
                rollDicesBtn.setDisable(false);
            } else{
                GuideMessage.setText("NON!,Vous devez sur la case "+PartieModel.getInterCase().getIndex());
            }
//            Case CasetoGo = PartieModel.getInterCase();



        }


    }
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
