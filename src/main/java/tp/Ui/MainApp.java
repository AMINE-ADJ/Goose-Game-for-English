package tp.Ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp.Noyeau.Jeu;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/tp/WelcomePage.fxml"));
            stage.setTitle("Game Of Goose");
            String css = this.getClass().getResource("/tp/CssStyles/WelcomePage.css").toExternalForm();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
            Jeu jeu = new Jeu();
        } catch ( Exception e) { e.fillInStackTrace();}

    }

    public static void main(String[] args) {
        launch(args);
    }
}
