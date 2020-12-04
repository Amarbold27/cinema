package Admin_pane;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sample.Main;

/*
* @author Baldorj
* Enehvv class ni herew admin handaltiin erheer newtersen tohioldold
*
*
* */
public class AdminController {

    @FXML
    private ComboBox<String> CB_logout;
    @FXML
    private StackPane test_Pane;
    @FXML
    private StackPane STPane;
    @FXML
    private Button Btn_home;

    Image Img_iconHome=null;

    @FXML
    void initialize() throws  IOException {
        CB_logout.setPromptText(Main.person.getUsername());
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
            Btn_home.setText("Home");
        }
    }

    public void Branch_Clicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Branch.fxml"));
        test_Pane.getChildren().setAll(stkP);
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

    public void Logout_Clicked(ActionEvent actionEvent) throws IOException {
        switch (CB_logout.getValue().toString()) {
            case "Logout":
                Main.person.setPosition(null);
                Main.person.setUsername(null);
                StackPane stkP= FXMLLoader.load(getClass().getResource("../sample/Home.fxml"));
                STPane.getChildren().setAll(stkP);
                break;
            case "Update":
                break;
            default:
                break;
        }
    }
}