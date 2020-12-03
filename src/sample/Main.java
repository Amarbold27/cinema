package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
     public static class Person{
        private static String username;
        private static String position;
        Person(){
//            this.username=null;
//            this.position=null;
            this.username="bataa";
            this.position="user";
            System.out.println("Person object baiguulagdlaa");
        }

        public  String getUsername() {
            return username;
        }

        public  void setUsername(String name) {
            username=name;
        }

        public    String getPosition() {
            return position;
        }

        public  void setPosition(String pos) {
            position=pos;
        }
    }

    public static Person person=new Person();

    @Override
    public void start(Stage primaryStage) throws Exception{

        System.out.println("Main class iin start funcion ajillaj baina");
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        root.getStylesheets().add(getClass().getResource("RegisterCss.css").toExternalForm());
        root.getStylesheets().add(getClass().getResource("LoginCss.css").toExternalForm());
        primaryStage.setTitle("Cinema");

        primaryStage.setScene(new Scene(root, 1350 , 700));
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
            homeController.bool=false;
        });
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
