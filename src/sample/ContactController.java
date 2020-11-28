package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
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
    private ComboBox<String> CBmovie;

    @FXML
    private ComboBox<Integer> CBrating;

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

    }

    @FXML
    void pinIcon_Clicked(MouseEvent event) {

    }

    @FXML
    void sendBtn_Clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert = null;
        if (Main.person.getPosition()=="user"){
            if (CBmovie.getSelectionModel().isEmpty()||CBrating.getSelectionModel().isEmpty()||CommentTA.getText().isBlank()){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                String s = "Hooson baina";
                alert.setContentText(s);
                alert.showAndWait();
            }else {
                String userIdsql,movieIdsql;
                ResultSet rsSet,rsSet2;
                Integer userId=null,movieId=null;
                try{
                    userIdsql="select userId from cinema.user where userName='"+Main.person.getUsername()+"'";
                    movieIdsql="select movieId from cinema.movie where movieName='"+CBmovie.getValue()+"'";
                    rsSet=Database.dbExecute(userIdsql);
                    rsSet2=Database.dbExecute(movieIdsql);
                    while (rsSet.next()){
                        userId=rsSet.getInt(1);
                    }
                    while (rsSet2.next()){
                        movieId=rsSet2.getInt(1);
                    }
                    if (userId==null&movieId==null){
                        alert =new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error");
                        String s = "Sql aldaa";
                        alert.setContentText(s);
                        alert.showAndWait();
                    }else {
                        String insertFields="Insert into cinema.comment(userId,movieId,Rating,commentDesc)values('";
                        String insertValues=userId+"','"+movieId+"','"+CBrating.getValue()+"','"+CommentTA.getText()+"')";
                        String insertSQL=insertFields+insertValues;
                        Database.dbExecuteQuery(insertSQL);
                        CBrating.getSelectionModel().clearSelection();
                        CBmovie.getSelectionModel().clearSelection();
                    }
                    System.out.println("----------------------"+Integer.toString(userId)+"   "+movieId);
                }catch (SQLException | ClassNotFoundException e){
                    System.out.println(e);
                    e.printStackTrace();
                    throw e;
                }
            }
        }else {
            alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            String s = "Newtrene vv";
            alert.setContentText(s);
            alert.showAndWait();
        }
    }

    @FXML
    void twtIcon_Clicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        String sql="Select movieName from cinema.movie";
        if (Main.person.getPosition().equals("manager")) {
            SendBtn.setVisible(false);
        }
        try{
            ResultSet rsSet=Database.dbExecute(sql);
            ObservableList<String> MovieNames= FXCollections.observableArrayList();
            while (rsSet.next()){
                MovieNames.add(rsSet.getString(1));
            }
            CBmovie.setItems(MovieNames);

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
}