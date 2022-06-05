package tp.Noyeau;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.Serializable;
import java.text.NumberFormat;

public abstract class Case implements Serializable {


    protected Button button;
    protected boolean isClicked =false;
//    protected ImageView JoueurAvatar = new ImageView();
    protected Image JoueurAvatar = new Image(getClass().getResourceAsStream("/tp/Assets/avatar.png"));
    protected String style= new String("-fx-border-radius:7 ;"
            + "-fx-border-color: black;"
            +	"-fx-border-width: 2 2 2 2 ;"
            + "-fx-background-radius:7;"
            +	"-fx-background-insets: 0;");

    public Case(){

    }
    public Case(int numCase ){ ///------------------------here
        ImageView avatarGraphic = new ImageView(JoueurAvatar);
        avatarGraphic.setFitHeight(40);
        avatarGraphic.setFitWidth(40);
        button  = new Button();
        button.setGraphic(avatarGraphic);
        button.getGraphic().setVisible(false);
        button.setContentDisplay(ContentDisplay.CENTER);
        button.setText(Integer.toString(numCase));
        button.setTextFill(Color.BLACK);
        button.setFont(new Font(18));
//        GridPane.setFillWidth(button, true);
//        GridPane.setFillHeight(button, true);
        button.setMinSize(0, 0);
        button.setMaxSize(100, 100);
//        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        button.setOnAction();
    }
    public void ClickCase(){
        isClicked = true;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public void DisableJoueur(){
        button.getGraphic().setVisible(false);
        System.out.println("Is disactivated.................................");
    }
    public void ActivateJoueur(){
        button.getGraphic().setVisible(true);
        System.out.println("Is Activated....");
    }
    // retourne l'index de cette case
    public abstract int getIndex();

    // retourne l'index de la case arrivee
    public abstract int mouvement(int inter_index);// int mouvement(int inter_index)

    // se que se passe quand un joueur est dans cette case
    public abstract int score(int score);

    public abstract Joueur getJoueur();

    public abstract void setJoueur(Joueur joueur);

    public abstract String getCouleur();

    public Button getButton() {
        return button;
    }

    public abstract String getType();
}
