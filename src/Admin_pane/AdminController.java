package Admin_pane;

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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaException;
import sample.Database;

public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> TV_Manager;

    @FXML
    private TableColumn<?, ?> Col_ID;

    @FXML
    private TableColumn<?, ?> Col_branch;

    @FXML
    private TableColumn<?, ?> Col_pos;

    @FXML
    private TableColumn<?, ?> Col_fname;

    @FXML
    private TableColumn<?, ?> Col_lname;

    @FXML
    private TableColumn<?, ?> Col_reg;

    @FXML
    private TableColumn<?, ?> Col_pnum;

    @FXML
    private TableColumn<?, ?> Col_hAdd;

    @FXML
    private TextField TF_position;

    @FXML
    private TextField TF_Fname;

    @FXML
    private TextField TF_lname;

    @FXML
    private TextField TF_register;

    @FXML
    private TextField TF_Pnumber;

    @FXML
    private TextField TF_username;

    @FXML
    private TextField TF_Password;

    @FXML
    private ComboBox<String> CB_Branch;
    @FXML
    private ComboBox<String> CB_pos;

    @FXML
    private TextArea TA_hAddres;

    @FXML
    private TextField TF_id;

    @FXML
    private StackPane test_Pane;

    @FXML
    private StackPane STPane;

    @FXML
    private Button Btn_home;

    Image Img_iconHome=null;

    @FXML
    void initialize() throws  IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Branch.fxml"));
        test_Pane.getChildren().setAll(stkP);

        try {
            Img_iconHome=new Image(getClass().getResourceAsStream("/sample/icon/home.png"));
        }catch (Exception e){
            System.out.println(e);
        }
        if (Img_iconHome!=null){
            ImageView btn=new ImageView(Img_iconHome);
            btn.setPickOnBounds(true);
            btn.setFitHeight(50);
            btn.setFitWidth(50);
            Btn_home.setGraphic(btn);
        }else {
            System.out.println("oloogvi");
            Btn_home.setText("Home");
        }
    }

    public void Branch_Clicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Branch.fxml"));
        test_Pane.getChildren().setAll(stkP);
//        1170 635
    }

    public void Hall_Clicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Hall.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void GoToHome(ActionEvent actionEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("../sample/Home.fxml"));
        STPane.getChildren().setAll(stkP);
    }

    public void Movie_Clicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Movie.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void Comment_Clicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Comment.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void Schedule_Clicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Schedule.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void ManagerClicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Manager.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void User_Clicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("User.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void UserHistory_Clicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("UserHistory.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }
}