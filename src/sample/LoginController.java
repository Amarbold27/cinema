package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;



    @FXML
    void LoginClicked(MouseEvent event) throws IOException {

    }

    @FXML
    void LogClicked(ActionEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void SignClicked(ActionEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Register.fxml"));
        SPane.getChildren().setAll(stkP);

    }
    @FXML
    void BackClicked(ActionEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Home.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void initialize() {

    }
}
