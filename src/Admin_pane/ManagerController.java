

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

public class ManagerController {

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
    private TableColumn<Data, String> Col_userName;

    @FXML
    private TableColumn<Data, String> Col_Position;

    @FXML
    private TableColumn<Data, String> Col_fName;

    @FXML
    private TableColumn<Data, String> Col_lName;

    @FXML
    private TableColumn<Data, String> Col_Password;

    @FXML
    private TableColumn<Data, String> Col_Register;

    @FXML
    private TableColumn<Data, String> Col_pNumber;

    @FXML
    private TableColumn<Data, String> Col_hAddress;

    @FXML
    private TableColumn<Data, String> Col_branchNum;

    @FXML
    private ComboBox<String> CB_pos;

    @FXML
    private ComboBox<String > CB_bracnh;

    @FXML
    private TextField TF_username;

    @FXML
    private TextField TF_fname;

    @FXML
    private TextField TF_lname;

    @FXML
    private TextField TF_Password;

    @FXML
    private TextField TF_register;

    @FXML
    private TextField TF_Pnumber;

    @FXML
    private TextArea TA_hAddres;

    @FXML
    private TextField TF_id;

    Window owner;

    @FXML
    void Btn_Add_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        try{
            String branchIdsql="select branchId from cinema.branch where branchName='"+CB_bracnh.getValue()+"'";
            ResultSet rsSet=Database.dbExecute(branchIdsql);
            Integer id = null;
            while (rsSet.next()){
                id=rsSet.getInt(1);
            }
            String insert;
            if (TF_id.getText().isBlank()){
                insert="Insert into cinema.manager( `branchId`, `username`, `position`, `fName`, `lName`, `password`, `rNUm`, `phoneNumber`, `mHomeAdd`)values('"+
                        id+"','"+TF_username.getText()+"','"+CB_pos.getValue()+"','"+TF_fname.getText()+"','"+TF_lname.getText()+"','"+TF_Password.getText()+
                        "','"+TF_register.getText()+"','"+TF_Pnumber.getText()+"','"+TA_hAddres.getText()+"')";
            }else{
                insert="Insert into cinema.manager(`manageId`, `branchId`, `username`, `position`, `fName`, `lName`, `password`, `rNUm`, `phoneNumber`, `mHomeAdd`)values('"+
                        TF_id.getText()+"','"+id+"','"+TF_username.getText()+"','"+CB_pos.getValue()+"','"+TF_fname.getText()+"','"+TF_lname.getText()+"','"+TF_Password.getText()+
                        "','"+TF_register.getText()+"','"+TF_Pnumber.getText()+"','"+TA_hAddres.getText()+"')";
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
        TF_id.setText("");TF_username.setText("");TF_fname.setText("");
        TF_lname.setText("");TF_Password.setText("");TF_register.setText("");
        TF_Pnumber.setText("");TA_hAddres.setText("");
        CB_pos.getSelectionModel().clearSelection();
        CB_bracnh.getSelectionModel().clearSelection();
    }

    @FXML
    void Btn_delete_clicked(ActionEvent event) {
        int i=0;
        String sql="delete from cinema.manager where ";
        if (!TF_id.getText().isBlank()){
            sql=sql+"manageId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }

        if (!TF_username.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+"username='"+TF_username.getText()+"'";//-------------------------------------------------------------------------------
            i++;
        }
        try {
            System.out.println(sql);
            Database.dbExecuteQuery(sql);
            ObservableList<Data> list=getAllRecords();
            populateTable(list);
            showAlert(Alert.AlertType.INFORMATION,owner,"Мэдэгдэл","Delete нь зөвхөн ID,username талбараар хиййгдэнэ");
            if (list.size()>0){
                populateTable(list);
            }else{
                showAlert(Alert.AlertType.INFORMATION,owner,"Мэдэгдэл","Хэрэглэгч олдсонгүй");
            }
        }catch (SQLException | ClassNotFoundException e){
            showAlert(Alert.AlertType.ERROR,owner,"Алдаа","Алдаа");
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
        String sql="SELECT cinema.manager.*,branch.branchName\n" +
                "FROM cinema.manager\n" +
                "LEFT JOIN cinema.branch\n" +
                "  ON cinema.manager.branchId = cinema.branch.branchId where";
        if (!TF_id.getText().isBlank()){
            sql=sql+" manageId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }

        if (!CB_bracnh.getSelectionModel().isEmpty()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" branchName='"+CB_bracnh.getValue()+"'";
            i++;
        }
        if (!TF_username.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" username='"+TF_username.getText()+"'";
        }
        if (!CB_pos.getSelectionModel().isEmpty()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" position='"+CB_pos.getValue()+"'";
            i++;
        }
        if (!TF_fname.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" fName='"+TF_fname.getText()+"'";
        }
        if (!TF_lname.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" fName='"+TF_lname.getText()+"'";
        }
        if (!TF_Password.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" password='"+TF_Password.getText()+"'";
        }
        if (!TF_register.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" rNUm='"+TF_register.getText()+"'";
        }
        if (!TF_Pnumber.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" phoneNumber='"+TF_Pnumber.getText()+"'";
        }
        if (!TA_hAddres.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" mHomeAdd='"+TA_hAddres.getText()+"'";
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


    ObservableList<String> branches;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        String sql="Select branchName from cinema.branch";

        ResultSet rsSet= Database.dbExecute(sql);
         branches= FXCollections.observableArrayList();
        while (rsSet.next()){
            branches.add(rsSet.getString(1));
        }
        CB_bracnh.setItems(branches);


        Col_ID.setCellValueFactory(new PropertyValueFactory<>("Int1"));
        Col_userName.setCellValueFactory(new PropertyValueFactory<>("String1"));
        Col_Position.setCellValueFactory(new PropertyValueFactory<>("String2"));
        Col_fName.setCellValueFactory(new PropertyValueFactory<>("String3"));
        Col_lName.setCellValueFactory(new PropertyValueFactory<>("String4"));
        Col_Password.setCellValueFactory(new PropertyValueFactory<>("String5"));
        Col_Register.setCellValueFactory(new PropertyValueFactory<>("String6"));
        Col_pNumber.setCellValueFactory(new PropertyValueFactory<>("String7"));
        Col_hAddress.setCellValueFactory(new PropertyValueFactory<>("String8"));
        Col_branchNum.setCellValueFactory(new PropertyValueFactory<>("String9"));
        ObservableList<Data> dataList=getAllRecords();
        populateTable(dataList);
    }

    private void populateTable(ObservableList<Data> dataList) {
        TV_Manager.setItems(dataList);
    }

    public static ObservableList<Data> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql="SELECT cinema.manager.*,branch.branchName\n" +
                "FROM cinema.manager\n" +
                "LEFT JOIN cinema.branch\n" +
                "  ON cinema.manager.branchId = cinema.branch.branchId";
        try {
            ResultSet rsSet= Database.dbExecute(sql);
            ObservableList<Data> dataList =getEmployeeObjects(rsSet);
            return dataList;
        }catch (SQLException e){
            System.out.println(e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Data> getEmployeeObjects(ResultSet rsSet) throws ClassNotFoundException,SQLException{
        try {
            ObservableList<Data> dataList= FXCollections.observableArrayList();

            while (rsSet.next()){
                Data d=new Data();
                d.setInt1(rsSet.getInt("manageId"));
                d.setString1(rsSet.getString("username"));
                d.setString2(rsSet.getString("position"));
                d.setString3(rsSet.getString("fName"));
                d.setString4(rsSet.getString("lName"));
                d.setString5(rsSet.getString("password"));
                d.setString6(rsSet.getString("rNum"));
                d.setString7(rsSet.getString("phoneNumber"));
                d.setString8(rsSet.getString("mHomeAdd"));
                d.setString9(rsSet.getString("branchName"));
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
        TF_username.setText(data.getString2());
        TF_fname.setText(data.getString3());
        TF_lname.setText(data.getString4());
        TF_Password.setText(data.getString5());
        TF_register.setText(data.getString6());
        TF_Pnumber.setText(data.getString7());
        TA_hAddres.setText(data.getString8());
    }
}

