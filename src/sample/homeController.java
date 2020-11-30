package sample;

import java.awt.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;



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
//        StackPane stkP= FXMLLoader.load(getClass().getResource("../Admin_pane/Admin.fxml"));
        StackPane stkP= FXMLLoader.load(getClass().getResource("Ticket.fxml"));
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
    ExecutorService tasks;
    //huwisagchid
    static boolean bool = true;
    ArrayList<Button> movies;
    Image image1, image2, image3, image4;

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

        bool=true;
        HomeImgPane.getChildren().clear();

        try {
            image1 = new Image("file:///C:/Users/Baldorj/Desktop/home1.jpg");
            image2 = new Image("file:///C:/Users/Baldorj/Desktop/home2.jpg");
            image3 = new Image("file:///C:/Users/Baldorj/Desktop/home3.jpg");
            image4 = new Image("file:///C:/Users/Baldorj/Desktop/home4.jpeg");
        } catch (Exception e) {
            System.out.println(e);
        }
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


        tasks = Executors.newFixedThreadPool(2);
        tasks.execute(new changeMovie());
        tasks.shutdown();
        System.out.println("Shutdonw hiigdlee");
    }


}