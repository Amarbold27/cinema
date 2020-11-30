package sample;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Admin_pane.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;

public class TicketController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane SPane;

    @FXML
    private TableView<Movie_data> TV_ticket;

    @FXML
    private TableColumn<Movie_data, String> Col_1;

    @FXML
    private TableColumn<Movie_data, String> Col_2;

    @FXML
    private TableColumn<Movie_data, String> Col3;
    @FXML
    private TableColumn<Movie_data, String> Col_Watch;

    @FXML
    private Label Lbl_MovieName;

    @FXML
    private Label Lbl_MovieName2;

    @FXML
    private Label Lbl_Author;

    @FXML
    private TextArea TA_Desc;
    @FXML
    private CheckBox Cbx_Fantasy;
    @FXML
    private CheckBox Cbx_Historical;
    @FXML
    private CheckBox Cbx_Action;
    @FXML
    private CheckBox Cbx_Adventure;
    @FXML
    private CheckBox Cbx_Comedy;
    @FXML
    private CheckBox Cbx_Crime;
    @FXML
    private CheckBox Cbx_Drama;
    @FXML
    void Watched_Clicked(ActionEvent event) {
        try {
            String sql="SELECT Concat(SUM(cinema.movie.movieId),' - ',movie.movieName) as movieName,movie.movieKind,movieAuthor,movie.MovieDesc \n" +
                    "FROM cinema.user_history\n" +
                    "LEFT JOIN cinema.schedule\n" +
                    "  ON cinema.user_history.uschedule_id = cinema.schedule.scheduleId\n" +
                    "LEFT JOIN cinema.movie\n" +
                    "  ON cinema.movie.movieId = cinema.schedule.movieId\n" +
                    "GROUP BY movie.movieId\n" +
                    "order by SUM(cinema.movie.movieId) desc";
            System.out.println(sql);
            ResultSet rsSet= Database.dbExecute(sql);
            ObservableList<Movie_data> dataList =getEmployeeObjects(rsSet);
            populateTable(dataList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @FXML
    void Will_Clicked(ActionEvent event) {
        try {
            //Hamgiin ih garan kino
            String sql="SELECT Concat(SUM(cinema.movie.movieId),' - ',movie.movieName) as movieName,movie.movieKind,movieAuthor,movie.MovieDesc\n" +
                    "FROM cinema.schedule\n" +
                    "LEFT JOIN cinema.movie\n" +
                    "  ON cinema.schedule.movieId = cinema.movie.movieId\n" +
                    "where cinema.schedule.Date<CURDATE()\n" +
                    "GROUP BY schedule.movieId\n" +
                    "order by SUM(cinema.movie.movieId) desc";
            System.out.println(sql);
            ResultSet rsSet= Database.dbExecute(sql);
            ObservableList<Movie_data> dataList =getEmployeeObjects(rsSet);
            populateTable(dataList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
    @FXML
    public void Back_Clicked(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        dataList=getAllRecords();
        populateTable(dataList);
    }

    private ObservableList<Movie_data> dataList;
    private ArrayList<CheckBox> CheckBoxes;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        Col_1.setCellValueFactory(new PropertyValueFactory<>("String1"));
        Col_2.setCellValueFactory(new PropertyValueFactory<>("String2"));
        Col3.setCellValueFactory(new PropertyValueFactory<>("String3"));
        dataList=getAllRecords();
        populateTable(dataList);
        CheckBoxes=new ArrayList<>();
        CheckBoxes.add(Cbx_Fantasy);
        CheckBoxes.add(Cbx_Historical);
        CheckBoxes.add(Cbx_Action);
        CheckBoxes.add(Cbx_Adventure);
        CheckBoxes.add(Cbx_Comedy);
        CheckBoxes.add(Cbx_Crime);
        CheckBoxes.add(Cbx_Drama);
    }
    private void populateTable(ObservableList<Movie_data> dataList1) {
        TV_ticket.setItems(dataList1);
    }

    public static ObservableList<Movie_data>getAllRecords() throws ClassNotFoundException, SQLException {
        String sql="select * from cinema.movie";
        try {
            ResultSet rsSet= Database.dbExecute(sql);
            ObservableList<Movie_data> dataList =getEmployeeObjects(rsSet);
            return dataList;
        }catch (SQLException e){
            System.out.println("Error occured while fetching the reacords from DB"+e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Movie_data> getEmployeeObjects(ResultSet rsSet) throws ClassNotFoundException,SQLException{
        try {
            ObservableList<Movie_data> dataList= FXCollections.observableArrayList();

            while (rsSet.next()){
                Movie_data d=new Movie_data();
                d.setString1(rsSet.getString("movieName"));
                d.setString2(rsSet.getString("movieKind"));
                d.setString3(rsSet.getString("movieAuthor"));
                d.setString4(rsSet.getString("MovieDesc"));
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

    public void table_clicked(MouseEvent mouseEvent) {
        Movie_data rowList = TV_ticket.getSelectionModel().getSelectedItem();
        Lbl_MovieName.setText(rowList.getString1());
        Lbl_MovieName2.setText(rowList.getString1());
        Lbl_Author.setText(rowList.getString3());
        TA_Desc.setText(rowList.getString4());
    }


    public void Fantasy_Checked(ActionEvent actionEvent) {
        CheckBoxSql();
    }
    public void Historical_Checked(ActionEvent actionEvent) {
        CheckBoxSql();
    }
    public void Comedy_Checked(ActionEvent actionEvent) {
        CheckBoxSql();
    }
    public void Action_Checked(ActionEvent actionEvent) {
        CheckBoxSql();
    }
    public void Adventure_Checked(ActionEvent actionEvent) {
        CheckBoxSql();
    }
    public void Crime_Checked(ActionEvent actionEvent) {
        CheckBoxSql();
    }
    public void Drama_Checked(ActionEvent actionEvent) {
        CheckBoxSql();
    }
    public void CheckBoxSql(){
        try {
            //Hamgiin ih garan kino
//            String sql="select * from cinema.movie where movieKind='romantik' or movieKind='Action'";
            String sql="select * from cinema.movie where ";
            Integer first=0;
            for (int i=0;i<CheckBoxes.size();i++){
                if (CheckBoxes.get(i).isSelected()){
                    if (first!=0){
                        sql=sql+"or movieKind='"+CheckBoxes.get(i).getText() +"' ";
                    }else{
                        sql=sql+" movieKind='"+CheckBoxes.get(i).getText() +"' ";
                        first++;
                    }
                }
            }
            if (first==0){
                sql="select * from cinema.movie";
            }

            System.out.println(sql);
            ResultSet rsSet= Database.dbExecute(sql);
            ObservableList<Movie_data> dataList =getEmployeeObjects(rsSet);
            populateTable(dataList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
