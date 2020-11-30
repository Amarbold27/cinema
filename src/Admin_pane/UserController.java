package Admin_pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;
import sample.Database;

public class UserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    private TableView TV_User;

    @FXML
    private TableColumn<Data, Integer> Col_ID;

    @FXML
    private TableColumn<Data, String> Col_fName;

    @FXML
    private TableColumn<Data, String> Col_Lname;

    @FXML
    private TableColumn<Data, String> Col_userName;

    @FXML
    private TableColumn<Data, String> Col_regNum;

    @FXML
    private TableColumn<Data, String> Col_mail;

    @FXML
    private TableColumn<Data, String> Col_phoneN;

    @FXML
    private TableColumn<Data, String> Col_age;

    @FXML
    private TableColumn<Data, String> Col_pass;

    @FXML
    private TextField TF_search;
    Window owner;

    @FXML
    void Btn_seachAll_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Data> Data=getAllRecords();
        populateTable(Data);
    }

    @FXML
    void Btn_search_clicked(ActionEvent event) {
        int i=0;
        String sql="select * from cinema.user where  "+
                "userId='"+TF_search.getText()+"' or fName='"+TF_search.getText()+"' or lName='"+TF_search.getText()+
                "' or userName='"+TF_search.getText()+"' or rNum='"+TF_search.getText()+"' or gMail='"+TF_search.getText()+
                "' or phoneNum='"+TF_search.getText()+"' or age='"+TF_search.getText()+"' or password='"+TF_search.getText()+"'";

        try {
            System.out.println(sql);
            ResultSet rsSet=Database.dbExecute(sql);
            ObservableList<Data> list=getEmployeeObjects(rsSet);
            if (list.size()>0){
                populateTable(list);
            }else{
                showAlert(Alert.AlertType.INFORMATION,owner,"Мэдэгдэл","Хэрэглэгч олдсонгүй");
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        Col_ID.setCellValueFactory(new PropertyValueFactory<>("Int1"));
        Col_fName.setCellValueFactory(new PropertyValueFactory<>("String1"));
        Col_Lname.setCellValueFactory(new PropertyValueFactory<>("String2"));
        Col_userName.setCellValueFactory(new PropertyValueFactory<>("String3"));
        Col_regNum.setCellValueFactory(new PropertyValueFactory<>("String4"));
        Col_mail.setCellValueFactory(new PropertyValueFactory<>("String5"));
        Col_phoneN.setCellValueFactory(new PropertyValueFactory<>("String6"));
        Col_age.setCellValueFactory(new PropertyValueFactory<>("String7"));
        Col_pass.setCellValueFactory(new PropertyValueFactory<>("String8"));
        ObservableList<Data> dataList=getAllRecords();
        populateTable(dataList);

    }
    private void populateTable(ObservableList<Data> dataList) throws SQLException, ClassNotFoundException {
        TV_User.setItems(dataList);
    }

    public  ObservableList<Data>getAllRecords() throws ClassNotFoundException, SQLException {
        String sql="select * from cinema.user";

        try {
            ResultSet rsSet=null;
            rsSet= Database.dbExecute(sql);
            ObservableList<Data> dataList =getEmployeeObjects(rsSet);
            if (rsSet==null){
                showAlert(Alert.AlertType.ERROR,owner,"Алдаа","Өгөгдөл байхгүй байна");
            }
            return dataList;
        }catch (SQLException e){
            System.out.println("Error occured while fetching the reacords from DB"+e);
            e.printStackTrace();
            throw e;
        }

    }

    private  ObservableList<Data> getEmployeeObjects(ResultSet rsSet) throws ClassNotFoundException,SQLException{
        try {
            ObservableList<Data> dataList= FXCollections.observableArrayList();

            while (rsSet.next()){
                Data d=new Data();
                d.setInt1(rsSet.getInt("userId"));
                d.setString1(rsSet.getString("fName"));
                d.setString2(rsSet.getString("lName"));
                d.setString3(rsSet.getString("userName"));
                d.setString4(rsSet.getString("rNum"));
                d.setString5(rsSet.getString("gMail"));
                d.setString6(rsSet.getString("phoneNum"));
                d.setString7(rsSet.getString("age"));
                d.setString8(rsSet.getString("password"));
                dataList.add(d);
            }
            return dataList;
        }catch (SQLException e){
            System.out.println("Error occured while fetching the reacords from DB"+e);
            e.printStackTrace();
            throw e;
        }
    }
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
