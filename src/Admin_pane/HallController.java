package Admin_pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;
import sample.Database;

public class HallController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    private TableView TV_Manager;

    @FXML
    private TableColumn<Data, String> Col_ID;

    @FXML
    private TableColumn<Data, String> Col_RoomNum;

    @FXML
    private TableColumn<Data, String> Col_allSit;

    @FXML
    private TableColumn<Data, String> Col_Branch;

    @FXML
    private TextField TF_RoomNum;

    @FXML
    private ComboBox<String> CB_Branch;

    @FXML
    private TextField TF_AllSit;

    @FXML
    private TextField TF_id;

    Window owner;
    @FXML
    void Btn_add_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        try{
            String branchIdsql="select branchId from cinema.branch where branchName='"+CB_Branch.getValue()+"'";
            ResultSet rsSet=Database.dbExecute(branchIdsql);
            Integer id = null;
            while (rsSet.next()){
                id=rsSet.getInt(1);
            }
            String insert;
            if (TF_id.getText().isBlank()){
                insert="Insert into cinema.hall(roomNum,hallAllSit,branchId)values('"+
                        TF_RoomNum.getText()+"','"+TF_RoomNum.getText()+"','"+id+"')";
            }else{
                insert="Insert into cinema.movie(movieId,movieName,movieKind,movieAuthor,MovieDesc)values('"+
                        TF_id.getText()+"','"+TF_RoomNum.getText()+"','"+TF_AllSit.getText()+"','"+id+"')";
            }

            Database.dbExecuteQuery(insert);
            ObservableList<Data> Data=getAllRecords();
            populateTable(Data);
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Insert Successful!",
                    "Бүртгэл амжилттай." );
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    void Btn_clear(ActionEvent event) {
        TF_id.setText("");TF_RoomNum.setText("");
        TF_AllSit.setText("");CB_Branch.getSelectionModel().clearSelection();
    }

    @FXML
    void Btn_delete_clicked(ActionEvent event) {
        int i=0;
        String sql="delete from cinema.hall where ";
        if (!TF_id.getText().isBlank()){
            sql=sql+"hallId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }
        try {
            System.out.println(sql);
            Database.dbExecuteQuery(sql);
            ObservableList<Data> list=getAllRecords();
            populateTable(list);
            showAlert(Alert.AlertType.INFORMATION,owner,"Мэдэгдэл","Delete нь зөвхөн ID талбараар хиййгдэнэ");
            if (list.size()>0){
                populateTable(list);
            }else{
                showAlert(Alert.AlertType.INFORMATION,owner,"Мэдэгдэл","Хэрэглэгч олдсонгүй");
            }
        }catch (SQLException | ClassNotFoundException e){
            showAlert(Alert.AlertType.ERROR,owner,"Алдаа",e.toString());
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @FXML
    void Btn_seachAll_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Data> Data=getAllRecords();
        populateTable(Data);
    }

    @FXML
    void Btn_search_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        int i=0;
//        String sql="select * from cinema.hall where ";
        String sql="Select hall.hallId,hall.roomNum,hall.hallAllSit,branch.branchName\n" +
                "        FROM cinema.branch\n" +
                "        Inner JOIN cinema.hall\n" +
                "        ON branch.branchId = cinema.hall.branchId where";
        if (!TF_id.getText().isBlank()){
            sql=sql+" hallId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }

        if (!TF_RoomNum.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" roomNum='"+TF_RoomNum.getText()+"'";
            i++;
        }
        if (!TF_AllSit.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" hallAllSit='"+TF_AllSit.getText()+"'";
        }
        if (!CB_Branch.getSelectionModel().isEmpty()){
//            String branchIdsql="select branchId from cinema.branch where branchName='"+CB_Branch.getValue()+"'";
//            ResultSet rsSet=Database.dbExecute(branchIdsql);
//            Integer id = null;
//            while (rsSet.next()){
//                id=rsSet.getInt(1);
//            }


            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" branchName='"+CB_Branch.getValue()+"'";
        }
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
    void Btn_update_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (TF_id.getText().isBlank()){
            showAlert(Alert.AlertType.ERROR,owner,"Алдаа","ID талбарт утга оруулна уу");
        }
        int i=0;
        String sql="update cinema.hall set ";
        if (!TF_RoomNum.getText().isBlank()){
            sql=sql+" roomNum='"+TF_RoomNum.getText()+"'";
            i++;
        }
        if (!TF_AllSit.getText().isBlank()){
            if (i!=0){
                sql=sql+",";
            }else {i++;}
            sql=sql+" hallAllsit='"+TF_AllSit.getText()+"'";
        }
        if (!CB_Branch.getValue().isEmpty()){
            String branchIdsql="select branchId from cinema.branch where branchName='"+CB_Branch.getValue()+"'";
            ResultSet rsSet=Database.dbExecute(branchIdsql);
            Integer id = null;
            while (rsSet.next()){
                id=rsSet.getInt(1);
            }

            if (i!=0){
                sql=sql+",";
            }else {i++;}
            sql=sql+" branchId="+id;
        }
        sql=sql+" where hallId="+Integer.parseInt(TF_id.getText());
        try {
            Database.dbExecuteQuery(sql);
            ObservableList<Data> Data=getAllRecords();
            populateTable(Data);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        String sql="Select branchId,branchName from cinema.branch";

        ResultSet branchSet= Database.dbExecute(sql);
        ObservableList<String> Branches= FXCollections.observableArrayList();
        while (branchSet.next()){
            Branches.add(branchSet.getString(2));
        }
        CB_Branch.setItems(Branches);


        Col_ID.setCellValueFactory(new PropertyValueFactory<>("Int1"));
        Col_RoomNum.setCellValueFactory(new PropertyValueFactory<>("String1"));
        Col_allSit.setCellValueFactory(new PropertyValueFactory<>("String2"));
        Col_Branch.setCellValueFactory(new PropertyValueFactory<>("String3"));
        ObservableList<Data> dataList=getAllRecords();
        populateTable(dataList);
    }
    private void populateTable(ObservableList<Data> dataList) {
        TV_Manager.setItems(dataList);
    }

    public static ObservableList<Data>getAllRecords() throws ClassNotFoundException, SQLException {
        String sql="Select hall.hallId,hall.roomNum,hall.hallAllSit,branch.branchName\n" +
                "        FROM cinema.branch\n" +
                "        Inner JOIN cinema.hall\n" +
                "        ON branch.branchId = cinema.hall.branchId;";

        try {
            ResultSet rsSet= Database.dbExecute(sql);
            ObservableList<Data> dataList =getEmployeeObjects(rsSet);
            return dataList;
        }catch (SQLException e){
            System.out.println("Error occured while fetching the reacords from DB"+e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Data> getEmployeeObjects(ResultSet rsSet) throws ClassNotFoundException,SQLException{
        try {
            ObservableList<Data> dataList= FXCollections.observableArrayList();

            while (rsSet.next()){
                Data d=new Data();
                d.setInt1(rsSet.getInt("hallId"));
                d.setString1(rsSet.getString("roomNum"));
                d.setString2(rsSet.getString("hallAllSit"));
                d.setString3(rsSet.getString("branchName"));
                dataList.add(d);
            }
            return dataList;
        }catch (SQLException e){
            System.out.println("Error occured while fetching the reacords from DB"+e);
            e.printStackTrace();
            throw e;
        }
    }
    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
