package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.Style;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class registerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;




    @FXML
    void RegisterBackClicked(ActionEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Login.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void RegisterSaveClicked(ActionEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Home.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void initialize() {


    }
}
