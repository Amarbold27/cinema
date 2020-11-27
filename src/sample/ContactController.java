package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class ContactController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    private Button HomeIcon;

    @FXML
    private TextField UserNameTA;

    @FXML
    private TextField EmailTA;

    @FXML
    private TextArea CommentTA;

    @FXML
    private Button SendBtn;

    @FXML
    private Button fbIcon;

    @FXML
    private Button twitIcon;

    @FXML
    private Button CallIcon;

    @FXML
    private Button PinIcon;

    @FXML
    private Button LinkedIcon;

    @FXML
    void HomeIcon_Clicked(MouseEvent event) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Home.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    @FXML
    void LinkedIcon_Clicked(MouseEvent event) {

    }

    @FXML
    void callIcon_Clicked(MouseEvent event) {

    }

    @FXML
    void fbIcon_Clicked(MouseEvent event) {
        Main.openWebpage("google.com");
    }

    @FXML
    void pinIcon_Clicked(MouseEvent event) {

    }

    @FXML
    void sendBtn_Clicked(ActionEvent event) {

    }

    @FXML
    void twtIcon_Clicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert SPane != null : "fx:id=\"SPane\" was not injected: check your FXML file 'Contact.fxml'.";
        assert HomeIcon != null : "fx:id=\"HomeIcon\" was not injected: check your FXML file 'Contact.fxml'.";
        assert UserNameTA != null : "fx:id=\"UserNameTA\" was not injected: check your FXML file 'Contact.fxml'.";
        assert EmailTA != null : "fx:id=\"EmailTA\" was not injected: check your FXML file 'Contact.fxml'.";
        assert CommentTA != null : "fx:id=\"CommentTA\" was not injected: check your FXML file 'Contact.fxml'.";
        assert SendBtn != null : "fx:id=\"SendBtn\" was not injected: check your FXML file 'Contact.fxml'.";
        assert fbIcon != null : "fx:id=\"fbIcon\" was not injected: check your FXML file 'Contact.fxml'.";
        assert twitIcon != null : "fx:id=\"twitIcon\" was not injected: check your FXML file 'Contact.fxml'.";
        assert CallIcon != null : "fx:id=\"CallIcon\" was not injected: check your FXML file 'Contact.fxml'.";
        assert PinIcon != null : "fx:id=\"PinIcon\" was not injected: check your FXML file 'Contact.fxml'.";
        assert LinkedIcon != null : "fx:id=\"LinkedIcon\" was not injected: check your FXML file 'Contact.fxml'.";

    }
}
