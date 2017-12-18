
package barbu;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class JeuController implements Initializable {

    public static String SCREEN_NAME = "start-ui";
    public static String SCREEN_FILE = "/Jeu.fxml";

    @FXML
    private BorderPane mainPane;

    @FXML
    private HBox topBox;

    @FXML
    private HBox bottomBox;

    @FXML
    private VBox leftBox;

    @FXML
    private VBox rightBox;

    @FXML
    private ImageView carteleft;

    @FXML
    private ImageView carteRight;

    @FXML
    private ImageView carteTop;

    @FXML
    private ImageView carteBottom;

    @FXML
    private ImageView j1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        Joueur joueur = new Joueur("Cassandre", "1");



        for (int i = 2; i < 14; i++) {
            joueur.getMain().add(new Carte("Pique", i));
        }

        ArrayList<ImageView> listeCarte = creerMain(joueur);
        bottomBox.getChildren().addAll(listeCarte);
        rightBox.getChildren().addAll(listeCarte);
        leftBox.getChildren().addAll(listeCarte);
        topBox.getChildren().addAll(listeCarte);

    }


    public ArrayList<ImageView> creerMain(Joueur joueur) {
        ArrayList<ImageView> listeCartes = new ArrayList<ImageView>();
        for (int i = 0; i < joueur.getMain().size(); i++) {
            String cheminCarte = "/";
            cheminCarte += String.valueOf(joueur.getMain().get(i).getValeur());
            if (joueur.getMain().get(i).getSymbole().equals("Coeur")) {
                cheminCarte += "C";
            } else if (joueur.getMain().get(i).getSymbole().equals("Carreau")) {
                cheminCarte += "Ca";
            } else if (joueur.getMain().get(i).getSymbole().equals("Pique")) {
                cheminCarte += "P";
            } else {
                cheminCarte += "T";
            }

            cheminCarte += ".gif";
            ImageView image = new ImageView(cheminCarte);
            Image image1 = new Image(cheminCarte);
            image.setPreserveRatio(true);
            image.setFitWidth(50);
            //image.setOnMouseClicked(new EventHandler<MouseEvent>() {
                //@Override
                //public void handle(MouseEvent event) {
                    //centreBox.getChildren().add(image);
                    //Pane pane = (Pane) image.getParent();
                    //pane.getChildren().remove(image);
               // }
            //});
            image.setFitWidth(100);
            image.setFitHeight(200);
            listeCartes.add(image);
        }
        return listeCartes;
    }
}