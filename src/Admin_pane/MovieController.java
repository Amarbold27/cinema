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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;
import sample.Database;

public class MovieController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    private TableView TV_Manager;

    @FXML
    private TableColumn<Data, Integer> Col_ID;

    @FXML
    private TableColumn<Data, String> Col_Name;

    @FXML
    private TableColumn<Data, String> Col_Genre;

    @FXML
    private TableColumn<Data, String> Col_Author;

    @FXML
    private TableColumn<Data, String> Col_Desc;

    @FXML
    private TextField TF_MvName;

    @FXML
    private TextField TF_MoAuth;

    @FXML
    private TextArea TA_MoDesc;

    @FXML
    private ComboBox<?> CB_MoGen;

    @FXML
    private TextField TF_id;
    Window owner;

    @FXML
    void Btn_add_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        try{
            String insert;
            if (TF_id.getText().isBlank()){
                insert="Insert into cinema.movie(movieName,movieKind,movieAuthor,MovieDesc)values('"+
                        TF_MvName.getText()+"','"+CB_MoGen.getValue()+"','"+TF_MoAuth.getText()+"','"+TA_MoDesc.getText()+"')";
            }else{
                insert="Insert into cinema.movie(movieId,movieName,movieKind,movieAuthor,MovieDesc)values('"+
                        TF_id.getText()+"','"+TF_MvName.getText()+"','"+CB_MoGen.getValue()+"','"+TF_MoAuth.getText()+"','"+TA_MoDesc.getText()+"')";
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
        TF_id.setText("");TA_MoDesc.setText("");
        TF_MoAuth.setText("");TF_MvName.setText("");
        CB_MoGen.getSelectionModel().clearSelection();
    }

    @FXML
    void Btn_delete_clicked(ActionEvent event) {
        int i=0;
        String sql="delete from cinema.movie where ";
        if (!TF_id.getText().isBlank()){
            sql=sql+"branchId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }

        if (!TF_MvName.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+"branchName='"+TF_MvName.getText()+"'";
            i++;
        }
        try {
            System.out.println(sql);
            Database.dbExecuteQuery(sql);
            ObservableList<Data> list=getAllRecords();
            populateTable(list);
            showAlert(Alert.AlertType.INFORMATION,owner,"Мэдэгдэл","Delete нь зөвхөн ID,movieName талбараар хиййгдэн");
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
    void Btn_search_clicked(ActionEvent event) {
        int i=0;
        String sql="select * from cinema.movie where ";
        if (!TF_id.getText().isBlank()){
            sql=sql+" movieId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }

        if (!TF_MvName.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" movieName='"+TF_MvName.getText()+"'";
            i++;
        }
        if (!CB_MoGen.getSelectionModel().isEmpty()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" movieKind='"+CB_MoGen.getValue()+"'";
        }
        if (!TA_MoDesc.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" MovieDesc='"+TA_MoDesc.getText()+"'";
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
    void Btn_update_clicked(ActionEvent event) {
        if (TF_id.getText().isBlank()){
            showAlert(Alert.AlertType.ERROR,owner,"Алдаа","ID талбарт утга оруулна уу");
        }
        int i=0;
        String sql="update cinema.movie set ";
        if (!TF_MvName.getText().isBlank()){
            sql=sql+" movieName='"+TF_MvName.getText()+"'";
            i++;
        }
        if (!CB_MoGen.getSelectionModel().isEmpty()){
            if (i!=0){
                sql=sql+",";
            }else {i++;}
            sql=sql+" movieKind='"+CB_MoGen.getValue()+"'";
        }
        if (!TF_MoAuth.getText().isBlank()){
            if (i!=0){
                sql=sql+",";
            }else {i++;}
            sql=sql+" movieAuthor='"+TF_MoAuth.getText()+"'";
        }
        if (!TA_MoDesc.getText().isEmpty()){
            if (i!=0){
                sql=sql+",";
            }else {i++;}
            sql=sql+" MovieDesc='"+TA_MoDesc.getText()+"'";
        }
        sql=sql+" where movieId="+Integer.parseInt(TF_id.getText());
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
        Col_ID.setCellValueFactory(new PropertyValueFactory<>("Int1"));
        Col_Name.setCellValueFactory(new PropertyValueFactory<>("String1"));
        Col_Genre.setCellValueFactory(new PropertyValueFactory<>("String2"));
        Col_Author.setCellValueFactory(new PropertyValueFactory<>("String3"));
        Col_Desc.setCellValueFactory(new PropertyValueFactory<>("String4"));
        ObservableList<Data> dataList=getAllRecords();
        populateTable(dataList);
    }
    private void populateTable(ObservableList<Data> dataList) {
        TV_Manager.setItems(dataList);
    }

    public static ObservableList<Data>getAllRecords() throws ClassNotFoundException, SQLException {
        String sql="select * from cinema.movie";
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
                d.setInt1(rsSet.getInt("movieId"));
                d.setString1(rsSet.getString("movieName"));
                d.setString2(rsSet.getString("movieKind"));
                d.setString3(rsSet.getString("movieAuthor"));
                d.setString4(rsSet.getString("movieDesc"));
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

    public void Table_clicked(MouseEvent mouseEvent) {
        Data data= (Data) TV_Manager.getSelectionModel().getSelectedItem();
        TF_id.setText(Integer.toString(data.getInt1()));
        TF_MvName.setText(data.getString1());
        TF_MoAuth.setText(data.getString3());
        TA_MoDesc.setText(data.getString4());
    }
}
