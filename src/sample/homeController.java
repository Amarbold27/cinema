package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class homeController {


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
    void HomeClicked(MouseEvent event) {

    }

    @FXML
    void LoginClicked(MouseEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Login.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void TicketClicked(MouseEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void initialize() {

    }
}
