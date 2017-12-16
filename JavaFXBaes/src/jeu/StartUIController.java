package jeu;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class StartUIController implements Initializable {

    public static String SCREEN_NAME = "start-ui";
    public static String SCREEN_FILE = "/startUI.fxml";

    @FXML
    private Button mybtn;



    @FXML
    private VBox vbox;

    @FXML
    private StackPane stack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageView imageView1 = new ImageView("/14s.gif");
        imageView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stack.getChildren().add(imageView1);
                vbox.getChildren().remove(imageView1);


            }
        });
        ImageView imageView2 = new ImageView("/14s.gif");
        ImageView imageView3 = new ImageView("/14s.gif");
        vbox.getChildren().add(imageView1);
        vbox.getChildren().add(imageView2);
        vbox.getChildren().add(imageView3);


    }

    /**
     * triggered when the user click on the 'Jouer' Button
     */
    @FXML
    private void onJouerClicked() {

    }

    @FXML
    public void onclick(){
        System.out.println("ahahahah");
    }
}
