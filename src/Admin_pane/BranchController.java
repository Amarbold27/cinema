package Admin_pane;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import sample.Database;



/**
 * author Baldorj
 *
 * Enehvv class ni branch fxml iin controller class ba branch tableiin ogogdoltoi haritsahad zoriulagdsan
 * */
public class BranchController {

    @FXML
    private TableView TV_Branch;

    @FXML
    private TableColumn<Data, Integer> Col_ID;

    @FXML
    private TableColumn<Data, String> Col_branchName;

    @FXML
    private TableColumn<Data, String> Col_address;

    @FXML
    private TableColumn<Data, String> Col_phoneNumber;

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
    void Btn_add_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {       //button deerh actionlistener
        try{
            String insert;
            //enehvv if nohtsol ni herew id oruulj ogson baiwal insert queryd id baganiig nemj ogno
            //id ogoogvi bol ogogdliin san deer auto id onoogdono
            if (TF_id.getText().isBlank()){
                insert="Insert into cinema.branch(branchName,branchAddress,branchPhoneNumber)values('"+TF_branchName.getText()+"','"+TF_branchAddress.getText()+"','"+TF_branchPhoneNumber.getText()+"')";
            }else{
                insert="Insert into cinema.branch(branchId,branchName,branchAddress,branchPhoneNumber)values('"+TF_id.getText()+"','"+TF_branchName.getText()+"','"+TF_branchAddress.getText()+"','"+TF_branchPhoneNumber.getText()+"')";
            }

            System.out.println(insert);
            Database.dbExecuteQuery(insert);               //bichigdsen query geer databased handah
            ObservableList<Data> branchList=getAllRecords(); // enehvv func ni ogodolnemedsenii daraah ogogdliig awah
            populateTable(branchList);                      // tuhain ogogdloo table deer haruulah
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Insert Successful!",
                    "Бүртгэл амжилттай." );
        }catch (SQLException | ClassNotFoundException e){             // aldaa garsan tohioldold
            System.out.println(e);
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    void Btn_clear(ActionEvent event) {                          //oruuld ogson utguudiig arilgah func
        TF_id.setText("");TF_branchPhoneNumber.setText("");
        TF_branchAddress.setText("");TF_branchName.setText("");
    }

    @FXML
    void Btn_delete_clicked(ActionEvent event) {                //enehvv func ni tuhain table ees ogodliig ustgah
        int i=0;
        String sql="delete from cinema.branch where ";          //herew tuhain branchiig ustgasan tohioldold terhvv branchiin
        String sql2="delete from cinema.hall where ";            //oroonvvdiig bas ustgah estoi
        if (!TF_id.getText().isBlank()){                           //herew id talber ogodol bichsen bol nohtsol bolj tuhain branch ustgagdana
            sql=sql+"branchId="+Integer.parseInt(TF_id.getText())+" ";
            sql2=sql2+"branchId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }
        try {
            System.out.println(sql);
            System.out.println(sql2);
            Database.dbExecuteQuery(sql);
            Database.dbExecuteQuery(sql2);
            ObservableList<Data> list=getAllRecords();     // enehvv func ni ogodol ustgasnii daraah ogogdliig awah
            populateTable(list);                            //terhvv ogogdloo hvsnegtendee haruulah
            showAlert(Alert.AlertType.INFORMATION,owner,"Мэдэгдэл","Delete нь зөвхөн ID,branchName талбараар хиййгдэн");
            if (list.size()>0){                          //herew branch vldsen bol hvsnegtend utga haruulna
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
        ObservableList<Data> BranchList=getAllRecords();      //ene ogogdloo dahin refresh hiij baigaa func
        populateTable(BranchList);
    }


    @FXML
    void Btn_search_clicked(ActionEvent event) {     //enehvv func ni hereglegchiin oruulsan ogodlvvdeer hailt hiine
        int i=0;
        String sql="select * from cinema.branch where ";
        if (!TF_id.getText().isBlank()){             //id ogodson baiwal nohtsol deer nemeh
            sql=sql+" branchId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }

        if (!TF_branchName.getText().isBlank()){             //branchName ogodson baiwal nohtsol deer nemeh
            if (i!=0){                                      // if nohtsol ni herewee enehvv tablar ni ehnii nohtsol bish bol and awahiig zaana
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" branchName='"+TF_branchName.getText()+"'";
            i++;
        }
        if (!TF_branchAddress.getText().isBlank()){             //bracnhAddress ogodson baiwal nohtsol deer nemeh
            if (i!=0){                                     // if nohtsol ni herewee enehvv tablar ni ehnii nohtsol bish bol and awahiig zaana
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" branchAddress='"+TF_branchAddress.getText()+"'";
        }
        if (!TF_branchPhoneNumber.getText().isBlank()){             //\branchPhoneNumber ogodson baiwal nohtsol deer nemeh
            if (i!=0){                                     // if nohtsol ni herewee enehvv tablar ni ehnii nohtsol bish bol and awahiig zaana
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" branchPhoneNumber='"+TF_branchPhoneNumber.getText()+"'";
        }
        try {
            System.out.println(sql);
            ResultSet rsSet=Database.dbExecute(sql);
            ObservableList<Data> list=getEmployeeObjects(rsSet); // enehvv func ni ogodol nemegsen nemedsenii daraah ogogdliig awah
            if (list.size()>0){
                populateTable(list);                                //awsan ogogdloo table deeree shinechlen haruulah
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
        if (TF_id.getText().isBlank()){                             //enehvv table iin huwid pk bolon unique utga ni id uchir hooson esehiig shalgana
            showAlert(Alert.AlertType.ERROR,owner,"Алдаа","ID талбарт утга оруулна уу");
        }
        int i=0;
        String sql="update cinema.branch set ";                     //update query
        /**
         * //tuhain talbar deer ogodol oruulsan bol nohtsol bolgoj query deeree nemne.
         * enehvv i huwisagch ni ehnii ogogdol esehiig shalgah
         * herew ehnii ogodol bish bwl , iig awah bolno
         * */

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
            /*
             *bichigdsen sql ajiluulah ba vvnii daraagaar data gaa dahin awj, table deeree oruulna
             *  */
            Database.dbExecuteQuery(sql);
            ObservableList<Data> branchList=getAllRecords();
            populateTable(branchList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        /*
         * enehvv func ni ene classig duudahad hanmgiin tvrvvnd ajillah func ba ogodliig o.sangaas awch table
         * deer haruulah vildliig hiiw
         * */
        Col_ID.setCellValueFactory(new PropertyValueFactory<>("Int1"));
        Col_branchName.setCellValueFactory(new PropertyValueFactory<>("String1"));
        Col_address.setCellValueFactory(new PropertyValueFactory<>("String2"));
        Col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("String3"));
        ObservableList<Data> branchList=getAllRecords();
        populateTable(branchList);
    }
    private void populateTable(ObservableList<Data> branchList) {
        /*
         * ene function ni parametreer orj irsen observableList
         * torliin utguudiig tablview deeree onoono
         * */
        TV_Branch.setItems(branchList);
    }

    public static ObservableList<Data>getAllRecords() throws ClassNotFoundException, SQLException {
        /**
         * enehvv func ni tuhain table ees bvh ogodliig awch getEmployeeObject
         * function oor damjuulan Observablelist torliin Data d onooj butsaana
         * */
        String sql="select * from cinema.branch";
        try {
            ResultSet rsSet= Database.dbExecute(sql);
            ObservableList<Data> branchList =getEmployeeObjects(rsSet);
            return branchList;
        }catch (SQLException e){
            System.out.println("Error occured while fetching the reacords from DB"+e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Data> getEmployeeObjects(ResultSet rsSet) throws SQLException{
        /**
         * tuhain orj irsen result set ogodliig Data torliin observablelisted onooj butsaana
         * */
        try {
            ObservableList<Data> branchList= FXCollections.observableArrayList();

            while (rsSet.next()){
                Data emp=new Data();
                emp.setInt1(rsSet.getInt("branchId"));
                emp.setString1(rsSet.getString("branchName"));
                emp.setString2(rsSet.getString("branchAddress"));
                emp.setString3(rsSet.getString("branchPhoneNumber"));
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
        /**
         * enehvv function ni Alert iig haruulna
         * */
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void table_clicked(MouseEvent mouseEvent) {
        /**
         * tebleView iin tuhain tor deer darahad tuhain mornii utgiig Data torliin ogodold awch
         * hargalzah utguudad onooj ogno
         * */
        Data data= (Data) TV_Branch.getSelectionModel().getSelectedItem();
        TF_id.setText(Integer.toString(data.getInt1()));
        TF_branchName.setText(data.getString1());
        TF_branchAddress.setText(data.getString2());
        TF_branchPhoneNumber.setText(data.getString3());
    }
}