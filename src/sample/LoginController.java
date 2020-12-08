package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.sql.rowset.CachedRowSet;
import javax.swing.*;

import static sample.Main.person;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    private PasswordField passwordFieldId;
    @FXML
    private TextField usernameFieldId;
    private Window owner;


    @FXML
    void LoginClicked(MouseEvent event) throws IOException {

    }

    @FXML
    void LogClicked(ActionEvent event) throws IOException {//login button daragdah uyd ajillana
//        StackPane stkP= FXMLLoader.load(getClass().getResource("Ticket.fxml"));
//        SPane.getChildren().setAll(stkP);
        String sqlUser = "SELECT * FROM user WHERE username='"+usernameFieldId.getText()+"' and password='"+passwordFieldId.getText()+"'";
        String sqlManager = "SELECT * FROM manager WHERE username='"+usernameFieldId.getText()+"' and password='"+passwordFieldId.getText()+"'";
        if (passwordFieldId.getText().isEmpty()  ) {  // login form iin nuuts ug oruulah input hooson uyd ajillana

            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Нууц үгээ оруулна уу");
            return;
        }
        try{
            CachedRowSet crs,crs1;
            String username=null;
            crs = (CachedRowSet) Database.dbExecute(sqlUser);
            crs1 = (CachedRowSet) Database.dbExecute(sqlManager);
            while (crs.next()) {   //data gaar guine
               username = crs.getString("username");  //username gsen attribute bga bol username d olgono
           }                                                      //user iin username ees select hiine
            if(username==null){
                while (crs1.next()){
                    username = crs1.getString("username");
                }
                person.setPosition("manager");          //ymar hereglech newtersen eseh handaltiin tuwshin medhiin tuld  manager gsen utgiig set hiij bn
            }
            else{
                person.setPosition("user");             //manager bish tohioldold user gesen utgiig set hiij bn
            }

            person.setUsername(username);                //newtereh gej bui huniii username iig static person class iin objectiin username d utga onoooj bn
            if(person.getUsername()!=null&&person.getPosition().equals("user")){        //user tewtersen tohioldold ajillana
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                        "Тавтай морил " + person.getUsername());
                StackPane stkP= FXMLLoader.load(getClass().getResource("Ticket.fxml"));
                SPane.getChildren().setAll(stkP);
            }else if(person.getUsername()!=null&&person.getPosition().equals("manager")){//manager tewtersen tohioldold ajillana
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                        "Тавтай морил " + person.getUsername());
                StackPane stkP= FXMLLoader.load(getClass().getResource("../Admin_pane/Admin.fxml"));
                SPane.getChildren().setAll(stkP);
            }
            else{
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",        //newtreh ner eswel nuuts ug buruu tohioldold ajillana
                        "Нэвтрэх нэр эсвэл нууц үг буруу байна");
                passwordFieldId.clear();
            }
            System.out.println(person.getPosition()+" "+ person.getUsername());
        }catch (SQLException | ClassNotFoundException e){

            e.printStackTrace();
        }
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) { //delgetsend medegdeh jijig delgets iig uusgedeg func
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    @FXML
    void SignClicked(ActionEvent event) throws IOException {  //burtguuleh button daragdasan uyd ajillana
        StackPane stkP= FXMLLoader.load(getClass().getResource("Register.fxml"));
        SPane.getChildren().setAll(stkP);

    }
    @FXML
    void BackClicked(ActionEvent event) throws IOException { //butsah button daragdsan uyd ajillana
        StackPane stkP= FXMLLoader.load(getClass().getResource("Home.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void initialize() {

    }
}
