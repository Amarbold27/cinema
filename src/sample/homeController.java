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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import static sample.Main.person;


public class homeController implements Initializable {

    @FXML
    void ContactClicked(MouseEvent event) throws IOException {
        this.bool=false;
        System.out.println("Thread shutdown");
        StackPane stkP= FXMLLoader.load(getClass().getResource("Contact.fxml"));
        SPane.getChildren().setAll(stkP);
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
        StackPane stkP;
//        StackPane stkP= FXMLLoader.load(getClass().getResource("../Admin_pane/Admin.fxml"));
        if(person.getPosition()=="user"){
            stkP= FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        }
        else if (person.getPosition()=="manager"){
             stkP= FXMLLoader.load(getClass().getResource("Admin.fxml"));
        }
        else{
            stkP= FXMLLoader.load(getClass().getResource("Login.fxml"));
        }
        SPane.getChildren().setAll(stkP);
    }
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
    private Pane HomeImgPane;

    @FXML
    private ImageView HomeImgV;

    @FXML
    private Label Lbl_hello;

    private ExecutorService tasks;
    //huwisagchid
    static boolean bool = true;
    ArrayList<Button> movies;
    Image image1=null, image2=null, image3=null, image4=null;

    public void HomeClicked(MouseEvent mouseEvent) {
    }


    class changeMovie implements Runnable {

        private void pause() {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        int i = 1;

        @Override
        public void run() {
            while (bool) {
                switch (i % 4) {
                    case 1:
                        System.out.println("Kino1");
                        movies.get(0).setOpacity(0.5);
                        if (i != 1) {
                            movies.get(3).setOpacity(1);
                            HomeImgV.setImage(image1);
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
                    default:
                        break;
                }


                i++;
                pause();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bool=true; //enehvv huwisagch ni threadiin togsgolgvi ajillah nohtsol

        HomeImgPane.getChildren().clear();

        try {
            image1 = new Image(getClass().getResourceAsStream("./image/home1.jpg")); //zurguugiig unshix
            image2 = new Image(getClass().getResourceAsStream("./image/home2.jpg")); //zurguugiig unshix
            image3 = new Image(getClass().getResourceAsStream("./image/home3.jpg")); //zurguugiig unshix
            image4 = new Image(getClass().getResourceAsStream("./image/home4.jpg")); //zurguugiig unshix
        } catch (Exception e) {
            System.out.println("Home hesgiin zurag unshix aldaa:"+e);
        }

        if (image1!=null){
            HomeImgV.setFitWidth(1350);
            HomeImgV.setFitHeight(700);
            HomeImgV.setImage(image1);
            HomeImgPane.getChildren().add(HomeImgV);

            movies = new ArrayList();
            movies.add(HomImgBtn1);
            movies.add(HomImgBtn2);
            movies.add(HomImgBtn3);
            movies.add(HomImgBtn4);
            ArrayList<ImageView> ImgViews = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                ImgViews.add(new ImageView());
                ImgViews.get(i).setFitWidth(160);
                ImgViews.get(i).setFitHeight(102);
                ImgViews.get(i).setPickOnBounds(true);
            }

            ImgViews.get(0).setImage(image1);
            movies.get(0).setGraphic(ImgViews.get(0));
            ImgViews.get(1).setImage(image2);
            movies.get(1).setGraphic(ImgViews.get(1));
            ImgViews.get(2).setImage(image3);
            movies.get(2).setGraphic(ImgViews.get(2));
            ImgViews.get(3).setImage(image4);
            movies.get(3).setGraphic(ImgViews.get(3));
        }else {
            HomeImgPane.setStyle("-fx-background-color: #51C0EA");
            HomImgBtn1.setVisible(false);HomImgBtn2.setVisible(false);
            HomImgBtn3.setVisible(false);HomImgBtn4.setVisible(false);
            Lbl_hello.setVisible(true);
            HomeImgPane.getChildren().add(HomeImgV);
        }

        if (image1!=null&&image2!=null&&image3!=null&&image4!=null){
            System.out.println("thread ajillalaa");
            tasks = Executors.newFixedThreadPool(2);
            tasks.execute(new changeMovie());
            tasks.shutdown();
            System.out.println("Shutdonw hiigdlee");
        }else{
            HomImgBtn1.setVisible(false);HomImgBtn2.setVisible(false);
            HomImgBtn3.setVisible(false);HomImgBtn4.setVisible(false);
            System.out.println("thread ajillahgvi");
        }
    }


}