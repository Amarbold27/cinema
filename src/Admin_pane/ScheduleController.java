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
import sample.Main;
import sample.Main.Person;

public class ScheduleController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    private TableView TV_Schedule;

    @FXML
    private TableColumn<Data, Integer> Col_ID;

    @FXML
    private TableColumn<Data, String> Col_Movie;

    @FXML
    private TableColumn<Data, String> Col_Author;

    @FXML
    private TableColumn<Data, String> Col_Branch_hall;

    @FXML
    private TableColumn<Data, String> Col_Manager;

    @FXML
    private TableColumn<Data, String> Col_Date;

    @FXML
    private ComboBox<String> CB_Movie;

    @FXML
    private ComboBox<String> CB_bracnh;

    @FXML
    private DatePicker DP_date;

    @FXML
    private TextField TF_id;

    static  Window owner;
    @FXML
    void Btn_Add_clicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        try{
            Integer movieId = null,hallId = null,managerId = null;
            String branchIdsql,managerSql,movieSql;
            ResultSet rsSet;
            //-----------hall id g oloh heseg
            branchIdsql="SELECT Concat(branch.branchName,'-',hall.roomNum) as Branch_room,hallId\n" +
                    "FROM cinema.hall\n" +
                    "LEFT JOIN cinema.branch\n" +
                    "  ON cinema.hall.branchId = cinema.branch.branchId;";
            rsSet=Database.dbExecute(branchIdsql);
            while (rsSet.next()){
                if (rsSet.getString(1).equals(CB_bracnh.getValue())){
                    hallId=rsSet.getInt(2);
                }
            }

//            System.out.println("Username-"+Main.person.getUsername());
            //----------manager id g oloh heseg
            managerSql="select manageId from cinema.manager where username='"+Main.person.getUsername()+"'";
            /**
             *
             *
             * sample.main clasiin person ruu handaj bolohgvi baina
             *
             *
             * **/
            rsSet=Database.dbExecute(managerSql);
            while (rsSet.next()){
                managerId=rsSet.getInt(1);
            }

            //----------movie id g oloh heseg
            movieSql="select movieId from cinema.movie where movieName='"+CB_Movie.getValue()+"'";
            rsSet=Database.dbExecute(movieSql);
            while (rsSet.next()){
                movieId=rsSet.getInt(1);
            }
            System.out.println(DP_date.getValue());
            String insert;
            if (TF_id.getText().isBlank()){
                insert="Insert into cinema.schedule( movieId, hallId, managerId, Date)values('"+
                        movieId+"','"+hallId+"','"+managerId+"', Date'"+DP_date.getValue()+"')";
            }else{
                insert="Insert into cinema.schedule(scheduleId, movieId, hallId, managerId, Date)values('"+
                        TF_id.getText()+"','"+movieId+"','"+hallId+"','"+managerId+"', Date'"+DP_date.getValue()+"')";
            }

            System.out.println(insert);
            Database.dbExecuteQuery(insert);
            ObservableList<Data> Data=getAllRecords();
            populateTable(Data);
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Insert Successful!",
                    "Бүртгэл амжилттай." );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Btn_clear(ActionEvent event) {
        TF_id.setText("");
        CB_Movie.getSelectionModel().clearSelection();
        CB_bracnh.getSelectionModel().clearSelection();
        DP_date.setValue(null);
    }

    @FXML
    void Btn_delete_clicked(ActionEvent event) throws Exception {
        int i=0;
        String sql="delete from cinema.schedule where ";
        if (!TF_id.getText().isBlank()){
            sql=sql+"scheduleId="+Integer.parseInt(TF_id.getText())+" ";
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
    void Btn_seachAll_clicked(ActionEvent event) throws Exception {
        ObservableList<Data> Data=getAllRecords();
        populateTable(Data);
    }

    @FXML
    void Btn_search_clicked(ActionEvent event) throws Exception {
        int i=0;
        String sql="SELECT schedule.scheduleId,movie.movieName,movie.movieAuthor,Concat(branch.branchName,'-',hall.roomNum) as Branch_room,manager.username,schedule.Date\n" +
                "FROM cinema.schedule\n" +
                "LEFT JOIN cinema.movie\n" +
                "  ON cinema.schedule.movieId = cinema.movie.movieId\n" +
                "LEFT JOIN cinema.manager\n" +
                "  ON cinema.schedule.managerId = cinema.manager.manageId\n" +
                "LEFT JOIN cinema.hall\n" +
                "  ON cinema.schedule.hallId = cinema.hall.hallId\n" +
                "LEFT JOIN cinema.branch\n" +
                "  ON cinema.hall.branchId = cinema.branch.branchId where";
        if (!TF_id.getText().isBlank()){
            sql=sql+" scheduleId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }

        if (!CB_Movie.getSelectionModel().isEmpty()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" movieName='"+CB_Movie.getValue()+"'";
        }
        if (!CB_bracnh.getSelectionModel().isEmpty()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" Concat(branch.branchName,'-',hall.roomNum)='"+CB_bracnh.getValue()+"'";
        }
        if (DP_date.getValue()!=null){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" Date='"+DP_date.getValue()+"'";
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
    void initialize() throws Exception {
        String sql="SELECT Concat(branch.branchName,'-',hall.roomNum) as Branch_room\n" +
                "FROM cinema.hall\n" +
                "LEFT JOIN cinema.branch\n" +
                "  ON cinema.hall.branchId = cinema.branch.branchId;";

        ResultSet branchSet= Database.dbExecute(sql);
        ObservableList<String> Branches= FXCollections.observableArrayList();
        while (branchSet.next()){
            Branches.add(branchSet.getString(1));
        }


        sql="SELECT movie.movieName FROM cinema.movie";

        ResultSet movieSet= Database.dbExecute(sql);
        ObservableList<String> Movies= FXCollections.observableArrayList();
        while (movieSet.next()){
            Movies.add(movieSet.getString(1));
        }

        CB_bracnh.setItems(Branches);
        CB_Movie.setItems(Movies);

        Col_ID.setCellValueFactory(new PropertyValueFactory<>("Int1"));
        Col_Movie.setCellValueFactory(new PropertyValueFactory<>("String1"));
        Col_Author.setCellValueFactory(new PropertyValueFactory<>("String2"));
        Col_Branch_hall.setCellValueFactory(new PropertyValueFactory<>("String3"));
        Col_Manager.setCellValueFactory(new PropertyValueFactory<>("String4"));
        Col_Date.setCellValueFactory(new PropertyValueFactory<>("String5"));
        ObservableList<Data> dataList=getAllRecords();
        populateTable(dataList);
    }
    private void populateTable(ObservableList<Data> dataList) throws  Exception {
        TV_Schedule.setItems(dataList);
    }

    public  ObservableList<Data>getAllRecords() throws ClassNotFoundException, SQLException {
        String sql="SELECT schedule.scheduleId,movie.movieName,movie.movieAuthor,Concat(branch.branchName,'-',hall.roomNum) as Branch_room,manager.username,schedule.Date\n" +
                "FROM cinema.schedule\n" +
                "LEFT JOIN cinema.movie\n" +
                "  ON cinema.schedule.movieId = cinema.movie.movieId\n" +
                "LEFT JOIN cinema.manager\n" +
                "  ON cinema.schedule.managerId = cinema.manager.manageId\n" +
                "LEFT JOIN cinema.hall\n" +
                "  ON cinema.schedule.hallId = cinema.hall.hallId\n" +
                "LEFT JOIN cinema.branch\n" +
                "  ON cinema.hall.branchId = cinema.branch.branchId;";

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
                d.setInt1(rsSet.getInt("scheduleId"));
                d.setString1(rsSet.getString("movieName"));
                d.setString2(rsSet.getString("movieAuthor"));
                d.setString3(rsSet.getString("Branch_room"));
                d.setString4(rsSet.getString("username"));
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
