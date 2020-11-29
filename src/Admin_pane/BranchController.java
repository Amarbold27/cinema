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

public class BranchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    private TableView TV_Branch;

    @FXML
    private TableColumn<Branch, Integer> Col_ID;

    @FXML
    private TableColumn<Branch, String> Col_branchName;

    @FXML
    private TableColumn<Branch, String> Col_address;



    @FXML
    private TableColumn<Branch, String> Col_phoneNumber;
    @FXML
    private TableColumn<Button, String> Col_update;
    @FXML
    private TableColumn<Button, String> Col_delete;
    @FXML
    private TextField TF_branchAddress;

    @FXML
    private TextField TF_branchPhoneNumber;

    @FXML
    private TextField TF_branchName;

    @FXML
    private TextField TF_id;
    Window owner;

    @FXML
    void Btn_add_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        try{
            String insert;
            if (TF_id.getText().isBlank()){
                insert="Insert into cinema.branch(branchName,branchAddress,branchPhoneNumber)values('"+TF_branchName.getText()+"','"+TF_branchAddress.getText()+"','"+TF_branchPhoneNumber.getText()+"')";
            }else{
                insert="Insert into cinema.branch(branchId,branchName,branchAddress,branchPhoneNumber)values('"+TF_id.getText()+"','"+TF_branchName.getText()+"','"+TF_branchAddress.getText()+"','"+TF_branchPhoneNumber.getText()+"')";
            }

            Database.dbExecuteQuery(insert);
            ObservableList<Branch> branchList=getAllRecords();
            populateTable(branchList);
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
        TF_id.setText("");TF_branchPhoneNumber.setText("");
        TF_branchAddress.setText("");TF_branchName.setText("");
    }

    @FXML
    void Btn_delete_clicked(ActionEvent event) {
        int i=0;
        String sql="delete from cinema.branch where ";
        if (!TF_id.getText().isBlank()){
            sql=sql+"branchId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }

        if (!TF_branchName.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+"branchName='"+TF_branchName.getText()+"'";
            i++;
        }
        try {
            System.out.println(sql);
            Database.dbExecuteQuery(sql);
            ObservableList<Branch> list=getAllRecords();
            populateTable(list);
            showAlert(Alert.AlertType.INFORMATION,owner,"Мэдэгдэл","Delete нь зөвхөн ID,branchName талбараар хиййгдэн");
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
    void Btn_seachAll_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Branch> BranchList=getAllRecords();
        populateTable(BranchList);
    }

    @FXML
    void Btn_search_clicked(ActionEvent event) {
        int i=0;
        String sql="select * from cinema.branch where ";
        if (!TF_id.getText().isBlank()){
            sql=sql+" branchId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }

        if (!TF_branchName.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" branchName='"+TF_branchName.getText()+"'";
            i++;
        }
        if (!TF_branchAddress.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" branchAddress='"+TF_branchAddress.getText()+"'";
        }
        if (!TF_branchPhoneNumber.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" branchPhoneNumber='"+TF_branchPhoneNumber.getText()+"'";
        }
        try {
            System.out.println(sql);
            ResultSet rsSet=Database.dbExecute(sql);
            ObservableList<Branch> list=getEmployeeObjects(rsSet);
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
        String sql="update cinema.branch set ";
        if (!TF_branchName.getText().isBlank()){
            sql=sql+" branchName='"+TF_branchName.getText()+"'";
            i++;
        }
        if (!TF_branchAddress.getText().isBlank()){
            if (i!=0){
                sql=sql+",";
            }else {i++;}
            sql=sql+" branchAddress='"+TF_branchAddress.getText()+"'";
        }
        if (!TF_branchName.getText().isBlank()){
            if (i!=0){
                sql=sql+",";
            }else {i++;}
            sql=sql+" branchPhoneNumber='"+TF_branchPhoneNumber.getText()+"'";
        }
        sql=sql+" where branchId="+Integer.parseInt(TF_id.getText());
        try {
            Database.dbExecuteQuery(sql);
            ObservableList<Branch> branchList=getAllRecords();
            populateTable(branchList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        Col_ID.setCellValueFactory(new PropertyValueFactory<>("idProperty"));
        Col_branchName.setCellValueFactory(new PropertyValueFactory<>("nameProperty"));
        Col_address.setCellValueFactory(new PropertyValueFactory<>("addressProperty"));
        Col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumberProperty"));
        ObservableList<Branch> branchList=getAllRecords();
        populateTable(branchList);
    }
    private void populateTable(ObservableList<Branch> branchList) {
        TV_Branch.setItems(branchList);
    }

    public static ObservableList<Branch>getAllRecords() throws ClassNotFoundException, SQLException {
        String sql="select * from cinema.branch";
        try {
            ResultSet rsSet= Database.dbExecute(sql);
            ObservableList<Branch> branchList =getEmployeeObjects(rsSet);
            return branchList;
        }catch (SQLException e){
            System.out.println("Error occured while fetching the reacords from DB"+e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Branch> getEmployeeObjects(ResultSet rsSet) throws ClassNotFoundException,SQLException{
        try {
            ObservableList<Branch> branchList= FXCollections.observableArrayList();

            while (rsSet.next()){
                Branch emp=new Branch();
                emp.setIdProperty(rsSet.getInt("branchId"));
                emp.setNameProperty(rsSet.getString("branchName"));
                emp.setAddressProperty(rsSet.getString("branchAddress"));
                emp.setPhoneNumberProperty(rsSet.getString("branchPhoneNumber"));
                branchList.add(emp);
            }
            return branchList;
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

    public void OnEditCancel(TableColumn.CellEditEvent cellEditEvent) {
        System.out.println("onEditCancel");
    }

    public void OnEditCommit(TableColumn.CellEditEvent cellEditEvent) {
        System.out.println("OnEditCommit");
    }

    public void OnEditStart(TableColumn.CellEditEvent cellEditEvent) {
        System.out.println("OnEditStart");
    }
}