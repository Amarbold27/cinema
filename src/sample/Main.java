package sample;

import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        root.getStylesheets().add(getClass().getResource("RegisterCss.css").toExternalForm());
        root.getStylesheets().add(getClass().getResource("LoginCss.css").toExternalForm());
        primaryStage.setTitle("Cinema");
        primaryStage.setScene(new Scene(root, 1300 , 750));
        primaryStage.show();
    }
    public static void openWebpage(String url) {
        try {
            new ProcessBuilder("x-www-browser", url).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
