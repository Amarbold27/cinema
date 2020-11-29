package Admin_pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class CommentController {

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
    private TableColumn<Data,String> Col_movName;

    @FXML
    private TableColumn<Data,String> Col_userName;

    @FXML
    private TableColumn<Data,String> Col_rating;

    @FXML
    private TableColumn<Data,String> Col_Desc;

    @FXML
    private TextArea TA_commDecs;

    @FXML
    private ComboBox<String> CB_userN;

    @FXML
    private ComboBox<String> CB_rating;

    @FXML
    private ComboBox<String> CB_movieN;

    @FXML
    private TextField TF_id;

    Window owner;

    public void avgRating_clicked(ActionEvent actionEvent) {
        int i=0;
        String sql="SELECT cinema.movie.movieName,\n" +
                "FORMAT(AVG(DISTINCT cinema.comment.Rating), 1)  as Rating\n" +
                "FROM cinema.movie,cinema.comment\n" +
                "WHERE cinema.movie.movieId=cinema.comment.movieId\n" +
                "GROUP BY comment.movieId\n" +
                "Order by rating desc;";
        try {
            System.out.println(sql);
            ResultSet rsSet= Database.dbExecute(sql);
            ObservableList<Data> Data= FXCollections.observableArrayList();
            while (rsSet.next()){
                Data d=new Data();
                d.setString1(rsSet.getString("movieName"));
                d.setString3(rsSet.getString("Rating"));
                Data.add(d);
            }
            if (Data.size()>0){
                System.out.println("------------------");
                populateTable(Data);
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
    void Btn_clear(ActionEvent event) {
        TF_id.setText("");
        CB_userN.getSelectionModel().clearSelection();
        CB_movieN.getSelectionModel().clearSelection();
        CB_rating.getSelectionModel().clearSelection();
        TA_commDecs.setText("");
    }

    @FXML
    void Btn_delete_clicked(ActionEvent event) {
        int i=0;
        String sql="delete from cinema.comment where ";
        if (!TF_id.getText().isBlank()){
            sql=sql+"CommentId="+Integer.parseInt(TF_id.getText())+" ";
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
    void Btn_search_clicked(ActionEvent event) {
        int i=0;
        String sql="SELECT\n" +
                "  CommentId,userName,movieName,Rating,commentDesc\n" +
                "FROM cinema.comment\n" +
                "LEFT JOIN cinema.user\n" +
                "  ON cinema.comment.userId = cinema.user.userId\n" +
                "LEFT JOIN cinema.movie\n" +
                "  ON cinema.comment.movieId = cinema.movie.movieId where";
        if (!TF_id.getText().isBlank()){
            sql=sql+" CommentId="+Integer.parseInt(TF_id.getText())+" ";
            i++;
        }

        if (!CB_userN.getSelectionModel().isEmpty()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" userName='"+CB_userN.getValue()+"'";
            i++;
        }
        if (!CB_movieN.getSelectionModel().isEmpty()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" movieName='"+CB_movieN.getValue()+"'";
        }
        if (!CB_rating.getSelectionModel().isEmpty()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" Rating='"+CB_rating.getValue()+"'";
        }
        if (!TA_commDecs.getText().isBlank()){
            if (i!=0){
                sql=sql+" and ";
            }else {i++;}
            sql=sql+" commentDesc='"+TA_commDecs.getText()+"'";
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
        String sql="update cinema.comment set ";
        if (!CB_userN.getValue().isEmpty()){
            String userSql="select userId from cinema.user where userName='"+CB_userN.getValue()+"'";
            ResultSet rsSet=Database.dbExecute(userSql);
            Integer id = null;
            while (rsSet.next()){
                id=rsSet.getInt(1);
            }

            if (i!=0){
                sql=sql+",";
            }else {i++;}
            sql=sql+" userId="+id;
        }
        if (!CB_movieN.getValue().isEmpty()){
            String userSql="select movieId from cinema.movie where movieName='"+CB_movieN.getValue()+"'";
            ResultSet rsSet=Database.dbExecute(userSql);
            Integer id = null;
            while (rsSet.next()){
                id=rsSet.getInt(1);
            }

            if (i!=0){
                sql=sql+",";
            }else {i++;}
            sql=sql+" movieId="+id;
        }
        if (!CB_rating.getValue().isEmpty()){
            if (i!=0){
                sql=sql+",";
            }else {i++;}
            sql=sql+" Rating="+CB_rating.getValue();
        }

        if (!TA_commDecs.getText().isBlank()){
            if (i!=0){
                sql=sql+",";
            }else {i++;}
            sql=sql+" commentDesc='"+TA_commDecs.getText()+"'";
        }
        sql=sql+" where CommentId="+Integer.parseInt(TF_id.getText());
        try {
            System.out.println(sql);
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
        String sql="Select movieName from cinema.movie";

        ResultSet rsSet= Database.dbExecute(sql);
        ObservableList<String> Movies= FXCollections.observableArrayList();
        while (rsSet.next()){
            Movies.add(rsSet.getString(1));
        }
        CB_movieN.setItems(Movies);

        sql="Select userName from cinema.user";

        rsSet= Database.dbExecute(sql);
        ObservableList<String> Users= FXCollections.observableArrayList();
        while (rsSet.next()){
            Users.add(rsSet.getString(1));
        }
        CB_userN.setItems(Users);


        Col_ID.setCellValueFactory(new PropertyValueFactory<>("Int1"));
        Col_movName.setCellValueFactory(new PropertyValueFactory<>("String1"));
        Col_userName.setCellValueFactory(new PropertyValueFactory<>("String2"));
        Col_rating.setCellValueFactory(new PropertyValueFactory<>("String3"));
        Col_Desc.setCellValueFactory(new PropertyValueFactory<>("String4"));
        ObservableList<Data> dataList=getAllRecords();
        populateTable(dataList);
    }
    private void populateTable(ObservableList<Data> dataList) {
        TV_Manager.setItems(dataList);
    }

    public static ObservableList<Data>getAllRecords() throws ClassNotFoundException, SQLException {
        String sql="SELECT\n" +
                "  CommentId,userName,movieName,Rating,commentDesc\n" +
                "FROM cinema.comment\n" +
                "LEFT JOIN cinema.user\n" +
                "  ON cinema.comment.userId = cinema.user.userId\n" +
                "LEFT JOIN cinema.movie\n" +
                "  ON cinema.comment.movieId = cinema.movie.movieId;";
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
                d.setInt1(rsSet.getInt("CommentId"));
                d.setString1(rsSet.getString("movieName"));
                d.setString2(rsSet.getString("userName"));
                d.setString3(rsSet.getString("Rating"));
                d.setString4(rsSet.getString("commentDesc"));
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
