package Admin_pane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaException;
import sample.Database;

public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> TV_Manager;

    @FXML
    private TableColumn<?, ?> Col_ID;

    @FXML
    private TableColumn<?, ?> Col_branch;

    @FXML
    private TableColumn<?, ?> Col_pos;

    @FXML
    private TableColumn<?, ?> Col_fname;

    @FXML
    private TableColumn<?, ?> Col_lname;

    @FXML
    private TableColumn<?, ?> Col_reg;

    @FXML
    private TableColumn<?, ?> Col_pnum;

    @FXML
    private TableColumn<?, ?> Col_hAdd;

    @FXML
    private TextField TF_position;

    @FXML
    private TextField TF_Fname;

    @FXML
    private TextField TF_lname;

    @FXML
    private TextField TF_register;

    @FXML
    private TextField TF_Pnumber;

    @FXML
    private TextField TF_username;

    @FXML
    private TextField TF_Password;

    @FXML
    private ComboBox<String> CB_Branch;
    @FXML
    private ComboBox<String> CB_pos;

    @FXML
    private TextArea TA_hAddres;

    @FXML
    private TextField TF_id;

    @FXML
    private StackPane test_Pane;

    @FXML
    private StackPane STPane;

    ResultSet branchSet;
    Alert alert;

    @FXML
    void Btn_add_clicked(ActionEvent event) throws SQLException,ClassNotFoundException {
        try{String branchIdsql="select branchId from cinema.branch where branchName='"+CB_Branch.getValue()+"'";
        ResultSet rsSet= Database.dbExecute(branchIdsql);
        Integer id = null;
        while (rsSet.next()){
            id=rsSet.getInt(1);
        }

        String insertFields="Insert into cinema.manager(`branchId`, `username`,`position`, `fName`, `lName`, `password`,`rNUm`, `phoneNumber`, `mHomeAdd`)values('";
        String insertValues=Integer.toString(id)+"','"+ TF_username.getText()+"','"+ CB_pos.getValue()+"','"+TF_Fname.getText()+"','"+TF_lname.getText()+
                "','"+TF_Password.getText()+"','"+TF_register.getText()+"','"+TF_Pnumber.getText()+"','"+TA_hAddres.getText()+"')";
        String insert=insertFields+insertValues;
            Database.dbExecuteQuery(insert);
//            ObservableList<Employee> empList=getAllRecords();
//            populateTable(empList);
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
            alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("Aldaatai baina");
            alert.setContentText(e.toString());
            alert.showAndWait();
            throw e;
        }
    }

    @FXML
    void Btn_clear(ActionEvent event) {
        CB_Branch.getSelectionModel().clearSelection();
        CB_pos.getSelectionModel().clearSelection();
        TF_Pnumber.setText("");TA_hAddres.setText("");TF_register.setText("");
        TF_lname.setText("");TF_Fname.setText("");TF_id.setText("");
        TF_Password.setText("");TF_username.setText("");
    }

    @FXML
    void Btn_delete_clicked(ActionEvent event) {

    }

    @FXML
    void Btn_seachAll_clicked(ActionEvent event) {

    }

    @FXML
    void Btn_search_clicked(ActionEvent event) {

    }

    @FXML
    void Btn_update_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {

    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        String sql="Select branchId,branchName from cinema.branch";

        branchSet=Database.dbExecute(sql);
        ObservableList<String> Branches= FXCollections.observableArrayList();
        ArrayList<ArrayList> Branch=new ArrayList<ArrayList>();
        while (branchSet.next()){
            Branches.add(branchSet.getString(2));
        }
//        CB_Branch.setItems(Branches);

    }

    public void Branch_Clicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Branch.fxml"));
        test_Pane.getChildren().setAll(stkP);

//        1170 635
    }

    public void Hall_Clicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Hall.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void GoToHome(ActionEvent actionEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("../sample/Home.fxml"));
        STPane.getChildren().setAll(stkP);
    }

    public void Movie_Clicked(MouseEvent mouseEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Movie.fxml"));
        test_Pane.getChildren().setAll(stkP);
    }

    public void Comment_Clicked(MouseEvent mouseEvent) {
    }

    public void Schedule_Clicked(MouseEvent mouseEvent) {
    }
}
