package sample;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class homeController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    private Button HomImgBtn1;

    @FXML
    private Button HomImgBtn2;

    @FXML
    private Button HomImgBtn3;

    @FXML
    private Button HomImgBtn4;

    @FXML
    private Label HomeBackImage;

    @FXML
    private Pane HomeImgPane;

    @FXML
    private ImageView HomeImgV;

    //huwisagchid
    boolean bool=true;
    ArrayList<Button> movies;
    Image image1,image2,image3,image4;

    class changeMovie implements Runnable{

        private void pause(){
            try{
                Thread.sleep(5000);
            }catch (Exception e){System.out.println(e);}
        }
        int i=1;
        @Override
        public void run() {
            while (bool){
                switch (i%4){
                    case 1:
                        System.out.println("Kino1");
                        movies.get(0).setOpacity(0.5);
                        if (i!=1){
                            movies.get(3).setOpacity(1);
                            HomeImgV.setImage(image1);
//                            HomeImgPane.getChildren().clear();
//                            HomeImgPane.getChildren().add(HomeImgV);
                        }
                        break;
                    case 2:
                        System.out.println("Kino2");
                        movies.get(1).setOpacity(0.5);
                        movies.get(0).setOpacity(1);
                        HomeImgV.setImage(image2);
                        break;
                    case 3:
                        System.out.println("Kino3");
                        movies.get(2).setOpacity(0.5);
                        movies.get(1).setOpacity(1);
                        HomeImgV.setImage(image3);
                        break;
                    case 0:
                        System.out.println("Kino4");
                        movies.get(3).setOpacity(0.5);
                        movies.get(2).setOpacity(1);
                        HomeImgV.setImage(image4);
                        break;
                    default :
                        break;
                }


                i++;
                pause();
            }
        }
    }
    class Check implements Runnable{
        private void pause(){
            try{
                Thread.sleep(5000);
            }catch (Exception e){System.out.println(e);}
        }
        int j=0;
        @Override
        public void run() {
            while (bool){
                System.out.println("Thread ajilllaj baina-"+j);
                j++;
                pause();
            }
        }
    }
    ExecutorService tasks;
    @FXML
    void initialize() {
        System.out.println("init ajillalaa");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        HomeImgPane.getChildren().clear();

        try{
             image1=new Image("file:///C:/Users/Baldorj/Desktop/home1.jpg");
             image2=new Image("file:///C:/Users/Baldorj/Desktop/home2.jpg");
             image3=new Image("file:///C:/Users/Baldorj/Desktop/home3.jpg");
             image4=new Image("file:///C:/Users/Baldorj/Desktop/home4.jpg");
        }catch (Exception e){System.out.println(e);}
        HomeImgV.setFitWidth(800);
        HomeImgV.setFitHeight(600);
        HomeImgV.setImage(image1);
        HomeImgPane.getChildren().add(HomeImgV);



        movies=new ArrayList();
        movies.add(HomImgBtn1);movies.add(HomImgBtn2);movies.add(HomImgBtn3);movies.add(HomImgBtn4);
        System.out.println("init ajillalaa2");
        tasks= Executors.newFixedThreadPool(3);
        tasks.execute(new changeMovie());
        tasks.execute(new Check());
        System.out.println("------------");
        tasks.shutdown();
        System.out.println("Shutdonw hiigdlee");
    }



    @FXML
    void ContactClicked(MouseEvent event) throws IOException {
        this.bool=false;
        System.out.println("Thread shutdown");
        StackPane stkP= FXMLLoader.load(getClass().getResource("Contact.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void HomeClicked(MouseEvent event) {

    }

    @FXML
    void LoginClicked(MouseEvent event) throws IOException {
        this.bool=false;
        System.out.println("Thread shutdown");
        StackPane stkP= FXMLLoader.load(getClass().getResource("Login.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void TicketClicked(MouseEvent event) throws IOException {
        this.bool=false;
        System.out.println("Thread shutdown");
        StackPane stkP= FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        SPane.getChildren().setAll(stkP);
    }
}
