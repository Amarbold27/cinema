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
    private String username=null;


    @FXML
    void RegisterBackClicked(ActionEvent event) throws IOException {  // butsah button daragdah uyd ajillana
        StackPane stkP= FXMLLoader.load(getClass().getResource("Login.fxml"));
        SPane.getChildren().setAll(stkP);
    }
    @FXML
    void RegisterSaveClicked(ActionEvent event) throws IOException {    //hereglech input uudiig bugluud hadgalah button darah uyd ajillana
        String insertFields = "INSERT INTO cinema.user(fName,lName,username,rNum,gMail,phoneNum,age,password)VALUES('";  //insert hiih query
        String sqlManager = "SELECT* FROM manager  WHERE username='"+registerUsernameId.getText()+"'";   //username dawhardaj bui eshiig manager table ees shalgah query
        String sqlManager1 = "SELECT* FROM user  WHERE username='"+registerUsernameId.getText()+"'";       //username dawhardaj bui eshiig user table ees shalgah query
        String insertValues = registerFNameId.getText()+"','"+registerLNameId.getText()+"','"+registerUsernameId.getText()+"','"+registerRNumId.getText()+"','"+registerGmailId.getText()+"','"+registerPhoneNumberId.getText()+"','"+registerAgeId.getText()+"','"+registerPasswordId.getText()+"')";
        String insertToRegister = insertFields+insertValues;

        try {
            CachedRowSet crs,crs1;
            crs1 = (CachedRowSet) Database.dbExecute(sqlManager1);
            crs = (CachedRowSet) Database.dbExecute(sqlManager);
            check(crs);         //data gaar guij username attribute bwal username d onooj ugnu
            check(crs1);
            System.out.println("username:"+username);
            if(username==null){   //herwee ymar neg dawhardsan username manager bolon user hoyroos oldoogui tohioldold burtgeliig database d insert hiij ugnu
                Database.dbExecuteQuery(insertToRegister);
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                        "Бүртгэл амжилттай. Тавтай морил " +registerUsernameId.getText());
            }
            else{
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",  //username dawhatssan uyd delgetsend jijig tsonhd aldaanii msg- iig haruulna
                        "Бүртгэл амжилтгүй боллоо!!! " );
                registerLNameId.clear();        //input uudiig tsewerlene
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
    private  void check(CachedRowSet crs) throws SQLException {  //orj irsen data naas username gsen attribute bga bol username d utgiig onooh func
        while (crs.next() ) {
            username = crs.getString("username");
        }
    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {//delgetsend jijig delgetseer msg gargah func
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

