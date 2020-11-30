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

public class UserHistoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    private TableView TV_UserHistory;

    @FXML
    private TableColumn<Data, Integer> Col_ID;

    @FXML
    private TableColumn<Data, String> Col_userName;

    @FXML
    private TableColumn<Data, String> Col_movieName;

    @FXML
    private TableColumn<Data, String> Col_branchName;

    @FXML
    private TableColumn<Data, String> Col_roomNum;

    @FXML
    private TableColumn<Data, String> Col_Date;

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
        String sql="SELECT cinema.user_history.history_id,cinema.user.userName,movie.movieName,\n" +
                "cinema.branch.branchName,cinema.hall.roomNum,cinema.schedule.Date\n" +
                "FROM cinema.user_history\n" +
                "LEFT JOIN cinema.user\n" +
                "  ON cinema.user_history.userId = cinema.user.userId\n" +
                "LEFT JOIN cinema.schedule\n" +
                "  ON cinema.user_history.uschedule_id = cinema.schedule.scheduleId\n" +
                "LEFT JOIN cinema.movie\n" +
                "  ON cinema.schedule.scheduleId = cinema.movie.movieId\n" +
                "LEFT JOIN cinema.hall\n" +
                "  ON cinema.schedule.hallId = cinema.hall.hallId\n" +
                "LEFT JOIN cinema.branch\n" +
                "  ON cinema.hall.branchId = cinema.branch.branchId where "+
                "history_id='"+TF_search.getText()+"' or userName='"+TF_search.getText()+"' or movieName='"+TF_search.getText()+
                "' or branchName='"+TF_search.getText()+"' or roomNum='"+TF_search.getText()+"' or  Date=Date('"+TF_search.getText()+"')";

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
        Col_userName.setCellValueFactory(new PropertyValueFactory<>("String1"));
        Col_movieName.setCellValueFactory(new PropertyValueFactory<>("String2"));
        Col_branchName.setCellValueFactory(new PropertyValueFactory<>("String3"));
        Col_roomNum.setCellValueFactory(new PropertyValueFactory<>("String4"));
        Col_Date.setCellValueFactory(new PropertyValueFactory<>("String5"));
        ObservableList<Data> dataList=getAllRecords();
        populateTable(dataList);

    }
    private void populateTable(ObservableList<Data> dataList) throws SQLException, ClassNotFoundException {
        TV_UserHistory.setItems(dataList);
    }

    public  ObservableList<Data>getAllRecords() throws ClassNotFoundException, SQLException {
        String sql="SELECT cinema.user_history.history_id,cinema.user.userName,movie.movieName,\n" +
                "cinema.branch.branchName,cinema.hall.roomNum,cinema.schedule.Date\n" +
                "FROM cinema.user_history\n" +
                "LEFT JOIN cinema.user\n" +
                "  ON cinema.user_history.userId = cinema.user.userId\n" +
                "LEFT JOIN cinema.schedule\n" +
                "  ON cinema.user_history.uschedule_id = cinema.schedule.scheduleId\n" +
                "LEFT JOIN cinema.movie\n" +
                "  ON cinema.schedule.scheduleId = cinema.movie.movieId\n" +
                "LEFT JOIN cinema.hall\n" +
                "  ON cinema.schedule.hallId = cinema.hall.hallId\n" +
                "LEFT JOIN cinema.branch\n" +
                "  ON cinema.hall.branchId = cinema.branch.branchId";

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
                d.setInt1(rsSet.getInt("history_id"));
                d.setString1(rsSet.getString("userName"));
                d.setString2(rsSet.getString("movieName"));
                d.setString3(rsSet.getString("branchName"));
                d.setString4(rsSet.getString("roomNum"));
                d.setString5(rsSet.getString("Date"));
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
