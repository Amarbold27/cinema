package Admin_pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class BranchController {

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
        System.out.println("logggggg");
        StackPane stkP= FXMLLoader.load(getClass().getResource("Login.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void TicketClicked(MouseEvent event) throws IOException {
    }

    @FXML
    void initialize() {
        assert SPane != null : "fx:id=\"SPane\" was not injected: check your FXML file 'Ticket.fxml'.";

    }

    public void Btn_add_clicked(ActionEvent actionEvent) {
    }
}
