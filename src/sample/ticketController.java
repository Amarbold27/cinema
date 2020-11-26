package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ticketController {
    public void HomeClicked(ActionEvent actionEvent) {
        try{
            Parent tebleViewParent= FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene tableViewScene=new Scene(tebleViewParent);


            //this line gets the Stage information
            Stage window=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
