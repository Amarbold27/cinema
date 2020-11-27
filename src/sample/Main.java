package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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


    public static void main(String[] args) {
        launch(args);
//        Maneger aldar  = new Maneger("bat","aldar",35 ,true, "9888888","aldar@gmail.com");
//        System.out.println(aldar.getEmail());
    }
}
