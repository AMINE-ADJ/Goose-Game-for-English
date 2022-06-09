package tp.Ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp.Controllers.CaseDefinitionController;
import tp.Noyeau.Partie;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CaseDefinitionManagement {

    public Parent root;
    private FXMLLoader loader;
    private CaseDefinitionController controller;
    private Parent previousRoot;
//    private ArrayList<Definition> definitions;

    public CaseDefinitionManagement() throws IOException
    {
        File file = new File(getClass().getResource("/tp/definitions.txt").getFile());
        BufferedReader in = new BufferedReader(new FileReader(file));
        String def;
        String reponse;
        int nbLettres;
        StringTokenizer tok;
        while((def = in.readLine() )!= null)
        {
            tok = new StringTokenizer(def,",");
            def = tok.nextToken();
            reponse = tok.nextToken();
            nbLettres = Integer.parseInt(tok.nextToken());
            definitions.add(new Definition(def, reponse, nbLettres));
        }
    }


    ArrayList<Definition> definitions = new ArrayList<Definition>();
    int currentQuestionInd = 0;

    public Definition getQuestion() {
        int i = currentQuestionInd;
        currentQuestionInd++;
        if (currentQuestionInd > definitions.size() - 1)
            currentQuestionInd = 0;
        return definitions.get(i);
    }


    public int getCurrentQuestionInd() {
        return currentQuestionInd;
    }


    public void setCurrentQuestionInd(int currentQuestionInd) {
        this.currentQuestionInd = currentQuestionInd;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<Definition> definitions) {
        this.definitions = definitions;
    }


    public class Definition{

        String definition;
        String reponse;
        int nbLettres;

        public Definition(String definition, String reponse, int nbLettres) {
            super();
            this.definition = definition;
            this.reponse = reponse;
            this.nbLettres = nbLettres;
        }

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }

        public String getReponse() {
            return reponse;
        }

        public void setReponse(String reponse) {
            this.reponse = reponse;
        }

        public int getNbLettres() {
            return nbLettres;
        }

        public void setNbLettres(int nbLettres) {
            this.nbLettres = nbLettres;
        }


    }


    public void LoadCaseDefinitionView(Partie PartieModel,Stage MainStage) throws IOException {
        previousRoot = MainStage.getScene().getRoot();
        loader= new FXMLLoader(getClass().getResource("/tp/CaseDefinitionView.fxml"));
        root = loader.load();
        controller = loader.getController();
//        controller.
//        Stage CaseDefStage = new Stage();
        controller.setDefinitions(definitions);
        controller.getRandomQuestion();
        controller.RemplirDefinition();
        controller.setPartieModel(PartieModel);
        controller.setMainStage(MainStage);
        controller.setPreviousRoot(previousRoot);
//        MainStage.setScene(new Scene(root,1307,719));
//        controller.remplirQuestion(definitions);
        MainStage.getScene().setRoot(root);
//        MainStage.show();

    }
}
