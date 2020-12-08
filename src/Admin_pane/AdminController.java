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
* Enehvv class ni herew admin handaltiin erheer newtersen tohioldold admin panel ii g haruulah bolno
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
        /*
        * enehvv classiin objectiig baiguulahad hamgiin ehend enehvv class duudagdana
        * */
        CB_logout.setPromptText(Main.person.getUsername());          //tuhain newtersen hvnii username iig haruulah
        StackPane stkP= FXMLLoader.load(getClass().getResource("Branch.fxml"));   // hamgiin tvrvvn branch fxml iig haruulah
        test_Pane.getChildren().setAll(stkP);                                           // fxml ee bairluulah

        try {                                                                            // togtmol zurguudiig awah
            Img_iconHome=new Image(getClass().getResourceAsStream("/sample/icon/home.png"));
        }catch (Exception e){
            System.out.println(e);
        }
        if (Img_iconHome!=null){                           //herew tuhain zurag oldwol
            ImageView btn=new ImageView(Img_iconHome);
            btn.setPickOnBounds(true);
            btn.setFitHeight(50);                          // buttongiin hemjeend zurgiig taaruulah
            btn.setFitWidth(50);                           // buttongiin hemjeend zurgiig taaruulah
            Btn_home.setGraphic(btn);
        }else {                                             //herew zurag oldoogvi bol text oruulj ogoh
            Btn_home.setText("Home");
        }
    }

    public void Branch_Clicked(MouseEvent mouseEvent) throws IOException {                 //branch fxml iig duudaj ajilluulah
        StackPane stkP= FXMLLoader.load(getClass().getResource("Branch.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void Hall_Clicked(MouseEvent mouseEvent) throws IOException {                 //Hall fxml iig duudaj ajilluulah
        StackPane stkP= FXMLLoader.load(getClass().getResource("Hall.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void GoToHome(ActionEvent actionEvent) throws IOException {                 //Home fxml iig duudaj ajilluulah
        StackPane stkP= FXMLLoader.load(getClass().getResource("../sample/Home.fxml"));
        STPane.getChildren().setAll(stkP);
    }

    public void Movie_Clicked(MouseEvent mouseEvent) throws IOException {                 //Movie fxml iig duudaj ajilluulah
        StackPane stkP= FXMLLoader.load(getClass().getResource("Movie.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void Comment_Clicked(MouseEvent mouseEvent) throws IOException {                 //Comment fxml iig duudaj ajilluulah
        StackPane stkP= FXMLLoader.load(getClass().getResource("Comment.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void Schedule_Clicked(MouseEvent mouseEvent) throws IOException {                 //Schedule fxml iig duudaj ajilluulah
        StackPane stkP= FXMLLoader.load(getClass().getResource("Schedule.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void ManagerClicked(MouseEvent mouseEvent) throws IOException {                 //Manager fxml iig duudaj ajilluulah
        StackPane stkP= FXMLLoader.load(getClass().getResource("Manager.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void User_Clicked(MouseEvent mouseEvent) throws IOException {                 //User fxml iig duudaj ajilluulah
        StackPane stkP= FXMLLoader.load(getClass().getResource("User.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void UserHistory_Clicked(MouseEvent mouseEvent) throws IOException {                 //UserHistory fxml iig duudaj ajilluulah
        StackPane stkP= FXMLLoader.load(getClass().getResource("UserHistory.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void Logout_Clicked(ActionEvent actionEvent) throws IOException {                 //Log out comboboxiig ali daragdsan utga ajillah
        switch (CB_logout.getValue().toString()) {
            case "Logout":                                                                  //logout daragdsan tohioldold person classiin ogogdliig null bolgoj
                Main.person.setPosition(null);                                              //hereglegch garsan bolgono
                Main.person.setUsername(null);
                StackPane stkP= FXMLLoader.load(getClass().getResource("../sample/Home.fxml"));//tegeed home fxml iig duudaj ajilluulna
                STPane.getChildren().setAll(stkP);
                break;
            case "Update":
                break;
            default:
                break;
        }
    }
}