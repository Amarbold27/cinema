package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    void ContactClicked(MouseEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Contact.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void HomeClicked(MouseEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Home.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void LoginClicked(MouseEvent event) throws IOException {

    }

    @FXML
    void TicketClicked(MouseEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void initialize() {
        assert SPane != null : "fx:id=\"SPane\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
