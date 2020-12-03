package sample;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

public class ContactController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    private Button HomeIcon;

    @FXML
    private ComboBox<String> CBmovie;

    @FXML
    private ComboBox<Integer> CBrating;

    @FXML
    private TextArea CommentTA;

    @FXML
    private Button SendBtn;

    @FXML
    private Button fbIcon;

    @FXML
    private Button twitIcon;

    @FXML
    private Button CallIcon;

    @FXML
    private Button PinIcon;

    @FXML
    private Button LinkedIcon;

    @FXML
    private ImageView ImgV_map;
    @FXML
    private WebView WB_map;
    @FXML
    private Pane Pane_bg;
    @FXML
    private Pane Pane_map;
    Image Img_iconHome=null;

    @FXML
    private ImageView ImgV_back;
    private Image  imageBack=null,map=null;
    Image Icon_image1=null, Icon_image2=null, Icon_image3=null, Icon_image4=null,Icon_image5=null;

    @FXML
    void HomeIcon_Clicked(MouseEvent event) throws IOException {
        StackPane stkP = FXMLLoader.load(getClass().getResource("Home.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void LinkedIcon_Clicked(MouseEvent event) {

    }

    @FXML
    void callIcon_Clicked(MouseEvent event) {

    }

    @FXML
    void fbIcon_Clicked(MouseEvent event) {

    }

    @FXML
    void pinIcon_Clicked(MouseEvent event) {

    }


    @FXML
    void sendBtn_Clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert = null;
        if (Main.person.getPosition() == "user") {
            if (CBmovie.getSelectionModel().isEmpty() || CBrating.getSelectionModel().isEmpty() || CommentTA.getText().isBlank()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                String s = "Hooson baina";
                alert.setContentText(s);
                alert.showAndWait();
            } else {
                String userIdsql, movieIdsql;
                ResultSet rsSet, rsSet2;
                Integer userId = null, movieId = null;
                try {
                    userIdsql = "select userId from cinema.user where userName='" + Main.person.getUsername() + "'";
                    movieIdsql = "select movieId from cinema.movie where movieName='" + CBmovie.getValue() + "'";
                    rsSet = Database.dbExecute(userIdsql);
                    rsSet2 = Database.dbExecute(movieIdsql);
                    while (rsSet.next()) {
                        userId = rsSet.getInt(1);
                    }
                    while (rsSet2.next()) {
                        movieId = rsSet2.getInt(1);
                    }
                    if (userId == null & movieId == null) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error");
                        String s = "Sql aldaa";
                        alert.setContentText(s);
                        alert.showAndWait();
                    } else {
                        String insertFields = "Insert into cinema.comment(userId,movieId,Rating,commentDesc)values('";
                        String insertValues = userId + "','" + movieId + "','" + CBrating.getValue() + "','" + CommentTA.getText() + "')";
                        String insertSQL = insertFields + insertValues;
                        Database.dbExecuteQuery(insertSQL);
                        CBrating.getSelectionModel().clearSelection();
                        CBmovie.getSelectionModel().clearSelection();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Successfully");
                        alert.setContentText("Амжилттай илгээлээ танд баярлалаа");
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println(e);
                    e.printStackTrace();
                    throw e;
                }
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            String s = "Та бүртгүүлнэ үү";
            alert.setContentText(s);
            alert.showAndWait();
        }
    }

    @FXML
    void twtIcon_Clicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        String sql = "Select movieName from cinema.movie";
        if (Main.person.getPosition() == "manager") {
            SendBtn.setVisible(false);
        }
        try {
            ResultSet rsSet = Database.dbExecute(sql);
            ObservableList<String> MovieNames = FXCollections.observableArrayList();
            while (rsSet.next()) {
                MovieNames.add(rsSet.getString(1));
            }
            CBmovie.setItems(MovieNames);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        try{
            imageBack = new Image(getClass().getResourceAsStream("./image/home4.jpg"));
            map=new Image(getClass().getResourceAsStream("./image/mapgoogle.jpg"));
            Img_iconHome=new Image(getClass().getResourceAsStream("./icon/home.png"));
            Icon_image1=new Image(getClass().getResourceAsStream("./icon/facebook.png"));
            Icon_image2=new Image(getClass().getResourceAsStream("./icon/twitter.png"));
            Icon_image3=new Image(getClass().getResourceAsStream("./icon/youtube.png"));
            Icon_image4=new Image(getClass().getResourceAsStream("./icon/instagram.png"));
            Icon_image5=new Image(getClass().getResourceAsStream("./icon/linkedin.png"));
            ArrayList<ImageView> ImgViews = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                ImgViews.add(new ImageView());
                ImgViews.get(i).setFitWidth(160);
                ImgViews.get(i).setFitHeight(102);
                ImgViews.get(i).setPickOnBounds(true);
            }
        }catch (Exception e){System.out.println(e);}
        if (imageBack==null){
            Pane_bg.setStyle("-fx-background-color: #51C0EA");
        }else {ImgV_back.setImage(imageBack);}
        if (map==null){
            Pane_map.setStyle("-fx-background-color: green");
        }else {ImgV_map.setImage(map);}

        if (Img_iconHome!=null){
            ImageView btn=new ImageView(Img_iconHome);
            btn.setPickOnBounds(true);
            btn.setFitHeight(50);
            btn.setFitWidth(50);
            HomeIcon.setGraphic(btn);
        }else {
            HomeIcon.setText("Home");
        }
        if (Icon_image1!=null&&Icon_image2!=null&&Icon_image3!=null&&Icon_image4!=null&&Icon_image5!=null){
            ArrayList<Button> Socials = new ArrayList();
            Socials.add(fbIcon);
            Socials.add(twitIcon);
            Socials.add(CallIcon);
            Socials.add(PinIcon);
            Socials.add(LinkedIcon);
            ArrayList<ImageView> ImgViews = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                ImgViews.add(new ImageView());
                ImgViews.get(i).setFitWidth(80);
                ImgViews.get(i).setFitHeight(80);
                ImgViews.get(i).setPickOnBounds(true);
                ImgViews.get(0).setImage(Icon_image1);
            }
            Socials.get(0).setGraphic(ImgViews.get(0));
            ImgViews.get(1).setImage(Icon_image2);
            Socials.get(1).setGraphic(ImgViews.get(1));
            ImgViews.get(2).setImage(Icon_image3);
            Socials.get(2).setGraphic(ImgViews.get(2));
            ImgViews.get(3).setImage(Icon_image4);
            Socials.get(3).setGraphic(ImgViews.get(3));
            ImgViews.get(4).setImage(Icon_image5);
            Socials.get(4).setGraphic(ImgViews.get(4));
        }
    }

}