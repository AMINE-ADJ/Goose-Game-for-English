package tp.Noyeau;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.Serializable;
import java.text.NumberFormat;

public abstract class Case implements Serializable {


    protected Button button;
    protected String style= new String("-fx-border-radius:10 ;"
            + "-fx-border-color: black;"
            +	"-fx-border-width: 2 2 2 2;"
            + "-fx-background-radius:10;"
            +	"-fx-background-insets: 0;");

    public Case(){

    }
    public Case(int numCase ){
        button  = new Button();
        button.setText(Integer.toString(numCase));
        button.setTextFill(Color.WHITE);
        button.setFont(new Font(18));
        GridPane.setFillWidth(button, true);
        GridPane.setFillHeight(button, true);
        button.setMinSize(0, 0);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


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
}
