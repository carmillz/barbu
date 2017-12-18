package barbu;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AppFX extends Application {
    public static Group root = new Group();;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource(WelcomePage.SCREEN_FILE));
        Parent screen = myLoader.load();
        root.getChildren().addAll(screen);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
