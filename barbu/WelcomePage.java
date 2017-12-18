package barbu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomePage implements Initializable {

    public static String SCREEN_NAME = "start-ui";
    public static String SCREEN_FILE = "/welcome.fxml";



    @FXML
    private AnchorPane jeuPane;

    @FXML
    private ComboBox<String> nbJoueurs;

    @FXML
    private ComboBox<String> nbCartes;

    @FXML
    private ComboBox<String> choixContrat;

    @FXML
    private Button jouerbtn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        jeuPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        nbCartes.getItems().add(0, "32");

        nbCartes.getItems().add(1, "52");

        nbJoueurs.getItems().add(0, "3");
        nbJoueurs.getItems().add(1, "4");
        choixContrat.getItems().add(0, "Barbu");
        choixContrat.getItems().add(1, "Dames");
        choixContrat.getItems().add(2, "Coeur");
        choixContrat.getItems().add(3, "Plis");
        choixContrat.getItems().add(4, "Salade");
        choixContrat.getItems().add(5, "Reussite");
        nbCartes.getSelectionModel().select(0);
        nbJoueurs.getSelectionModel().select(0);
        choixContrat.getSelectionModel().select(0);

    }


    @FXML
    public void jeuClicked() {
        Paquet paquet = new Paquet(Integer.valueOf(nbCartes.getSelectionModel().getSelectedItem()), Integer.valueOf(nbJoueurs.getSelectionModel().getSelectedItem()));
        Jeu jeu = new Jeu(Integer.valueOf(nbCartes.getSelectionModel().getSelectedItem()));
        System.out.println(jeu.nbCartes);
        jeu.creationJoueurs(Integer.valueOf(nbJoueurs.getSelectionModel().getSelectedItem()));
        System.out.println(nbJoueurs);
        jeu.creationJoueurs(Integer.valueOf(choixContrat.getSelectionModel().getSelectedItem()));
        System.out.println(choixContrat);
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource(JeuController.SCREEN_FILE));
        Parent screen = null;
        try {

            screen = myLoader.load();
            AppFX.root.getChildren().clear();
            AppFX.root.getChildren().addAll(screen);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
