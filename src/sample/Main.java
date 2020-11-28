package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

<<<<<<< HEAD
import java.io.FileInputStream;
import java.io.IOException;
=======
>>>>>>> 6e3669bd5eac01b7d1c70316032b70edb1228437
public class Main extends Application {
     static class Person{
        private  String username;
        private  String position;
        Person(){
//            this.username=null;
//            this.position=null;
            this.username="bataa";
            this.position="user";
        }

        public  String getUsername() {
            return username;
        }

        public  void setUsername(String username) {
            this.username=username;
        }

        public  String getPosition() {
            return position;
        }

        public  void setPosition(String position) {
            this.position=position;
        }
    }

    public static Person person=new Person();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        root.getStylesheets().add(getClass().getResource("RegisterCss.css").toExternalForm());
        root.getStylesheets().add(getClass().getResource("LoginCss.css").toExternalForm());
        primaryStage.setTitle("Cinema");
        primaryStage.setScene(new Scene(root, 1350 , 700));
        primaryStage.show();
    }
    public static void openWebpage(String url) {

    }


    public static void main(String[] args) {
        launch(args);
    }
}
