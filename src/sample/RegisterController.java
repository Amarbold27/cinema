package sample;


import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;

import javax.sql.rowset.CachedRowSet;



public class RegisterController implements Initializable {
    @FXML
    private Button registerBackButtonId;
    @FXML
    private Button registerSaveButtonId;

    @FXML
    private TextField registerLNameId;

    @FXML
    private TextField registerFNameId;
    @FXML
    private TextField registerGmailId;

    @FXML
    private TextField registerRNumId;
    @FXML
    private TextField registerPhoneNumberId;
    @FXML
    private TextField registerAgeId;
    private Window owner;
    @FXML
    private TextField registerUsernameId;
    @FXML
    private PasswordField registerPasswordId;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;
    @FXML
    private Label registerMessageLabel;


    @FXML
    void RegisterBackClicked(ActionEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Login.fxml"));
        SPane.getChildren().setAll(stkP);
    }
    @FXML
    void RegisterSaveClicked(ActionEvent event) throws IOException {


        String insertFields = "INSERT INTO cinema.user(fName,lName,username,rNum,gMail,phoneNum,age,password)VALUES('";
        String sqlManager = "SELECT* FROM manager WHERE username='"+registerUsernameId.getText()+"'";
        String insertValues = registerFNameId.getText()+"','"+registerLNameId.getText()+"','"+registerUsernameId.getText()+"','"+registerRNumId.getText()+"','"+registerGmailId.getText()+"','"+registerPhoneNumberId.getText()+"','"+registerAgeId.getText()+"','"+registerPasswordId.getText()+"')";
        String insertToRegister = insertFields+insertValues;

        try {
            CachedRowSet crs;
            String username=null;
            crs = (CachedRowSet) Database.dbExecute(sqlManager);
            while (crs.next()) {
                username = crs.getString("username");
            }
            System.out.println(username);
            if(username==null){
                Database.dbExecuteQuery(insertToRegister);
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                        "Бүртгэл амжилттай. Тавтай морил " +registerUsernameId.getText());
            }
            else{
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                        "Бүртгэл амжилтгүй боллоо!!! " );
                registerLNameId.clear();
                registerFNameId.clear();
                registerRNumId.clear();
                registerPhoneNumberId.clear();
                registerAgeId.clear();
                registerGmailId.clear();
                registerUsernameId.clear();
                registerPasswordId.clear();

            }

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            }
    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    @FXML
    void initialize() {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

