package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Admin_pane.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private Pane Pane_film;
    @FXML
    private ImageView ImgV_film;
    @FXML
    private Label Lbl_notImg;
    @FXML
    private Button Btn_home;
    @FXML
    private GridPane GPan_event;
    @FXML
    private GridPane GPane_sit;
    Window owner;
    Image Img_iconHome=null;

    private Image Img_film=null;
    @FXML
    void Watched_Clicked(ActionEvent event) {
        try {
            String sql="SELECT Concat(Count(cinema.movie.movieId),' - ',movie.movieName) as movieName,movie.movieKind,movieAuthor,movie.MovieDesc \n" +
                    "FROM cinema.user_history\n" +
                    "LEFT JOIN cinema.schedule\n" +
                    "  ON cinema.user_history.uschedule_id = cinema.schedule.scheduleId\n" +
                    "LEFT JOIN cinema.movie\n" +
                    "  ON cinema.movie.movieId = cinema.schedule.movieId\n" +
                    "GROUP BY movie.movieId\n" +
                    "order by Count(cinema.movie.movieId) desc";
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
            String sql="SELECT Concat(Count(cinema.movie.movieId),' - ',movie.movieName) as movieName,movie.movieKind,movieAuthor,movie.MovieDesc\n" +
                    "FROM cinema.schedule\n" +
                    "LEFT JOIN cinema.movie\n" +
                    "  ON cinema.schedule.movieId = cinema.movie.movieId\n" +
                    "where cinema.schedule.Date<CURDATE()\n" +
                    "GROUP BY schedule.movieId\n" +
                    "order by Count(cinema.movie.movieId) desc";
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
    private Integer userId;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        if (Main.person.getUsername()!=null){
            String sql="select userId from cinema.user where userName='"+Main.person.getUsername()+"'";
            ResultSet rset=Database.dbExecute(sql);
            while (rset.next()){
                userId=rset.getInt("userId");
            }
            System.out.println("newtersen hereglegch "+ Main.person.getUsername());
        }
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


        try{
            Img_film=new Image(getClass().getResourceAsStream("./image/movie.png"));
            Img_iconHome=new Image(getClass().getResourceAsStream("./icon/home.png"));
        }catch (Exception e){
            System.out.println(e);
        }
        if (Img_film!=null){
            ImgV_film.setImage(Img_film);
        }else {
            Lbl_notImg.setVisible(true);
            Pane_film.setStyle("-fx-background-color: #8AFF66");
        }
        if (Img_iconHome!=null){
            ImageView btn=new ImageView(Img_iconHome);
            btn.setPickOnBounds(true);
            btn.setFitHeight(50);
            btn.setFitWidth(50);
            Btn_home.setGraphic(btn);
        }else {
            Btn_home.setText("Home");
        }

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

    public void table_clicked(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Movie_data rowList = TV_ticket.getSelectionModel().getSelectedItem();
        Lbl_MovieName.setText(rowList.getString1());
        Lbl_MovieName2.setText(rowList.getString1());
        Lbl_Author.setText(rowList.getString3());
        TA_Desc.setText(rowList.getString4());
        Event_display(rowList.getString1());
        GPane_sit.getChildren().clear();

    }

    public void Event_display(String movieName) throws SQLException, ClassNotFoundException {
        GPan_event.getChildren().clear();
        GPane_sit.getChildren().clear();
        //tuhain songogdson kinonii nereeer huwaaari talbaraas tuhain kinon deerh huwaariudiig awaw
        String sql="select schedule.scheduleId,CONCAT(MONTH(cinema.schedule.Date), '-',DAY(Date))as Date,cinema.hall.hallAllSit\n" +
                "From cinema.schedule\n" +
                "Left Join cinema.movie\n" +
                "On cinema.schedule.movieId=cinema.movie.movieId\n" +
                "Left Join cinema.hall\n" +
                "On cinema.schedule.hallId=cinema.hall.hallId\n" +
                "Where Date>=CURDATE() and movieName='"+movieName+"'";
        ResultSet rSet=Database.dbExecute(sql);
        ArrayList<String> event=new ArrayList<>();
        ArrayList<Integer> eventId=new ArrayList<>();
        ArrayList<Integer> hallAllSit=new ArrayList<>();
        while (rSet.next()){
            event.add(rSet.getString("Date"));
            eventId.add(rSet.getInt("scheduleId"));
            hallAllSit.add(rSet.getInt("hallAllSit"));
//            System.out.println(rSet.getString("Date")+"  "+rSet.getInt("scheduleId"));
        }
        Button button;
        for (int i=0;i<event.size()&&i<15;i++){
            button=new Button(event.get(i));
            int finalI = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("Clicked:"+eventId.get(finalI)+","+event.get(finalI)+"\n");
                    try {
                        sit_dispay(eventId.get(finalI),event.get(finalI),hallAllSit.get(finalI));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            GPan_event.add(button,i%3,i/3,1,1);
//            GridPane.setMargin(button, new Insets(20, 0, 0, 20));
            GPane_sit.setHalignment(button, HPos.CENTER);
        }
    }

    public void sit_dispay(Integer eventId,String Date,Integer hallAllsit) throws SQLException, ClassNotFoundException {
        GPane_sit.getChildren().clear();
        //songogdson sandaliig idewhgvi bolgoh
        String sql="select sitNum from cinema.user_history where uschedule_id='"+eventId
                +"' order by sitNum asc";
        ResultSet rSet=Database.dbExecute(sql);
        ArrayList<Integer> ordered=new ArrayList<>();
        while (rSet.next()){
            ordered.add(rSet.getInt("sitNum"));
        }
//        GPane_sit.setHalignment(, HPos.CENTER);
//        GridPane.setMargin(chip5, new Insets(5, 0, 0, 0));
        Button button;
        Integer j=0;
        for (int i=1;i<=hallAllsit;i++){
            button=new Button(Integer.toString(i));
            int finalI = i;
            Integer finalEventId = eventId;
            if (ordered.size()>j&&ordered.get(j)==i){      //zahialagdsan sandal
                System.out.println(ordered.get(j)+"  ");
                button.setStyle("-fx-background-color: #00ff00");
                j++;
            }else {
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            ticket_buy(eventId,finalI,hallAllsit);
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            GPane_sit.add(button,(i-1)%10,(i-1)/10);
        }
    }

    public void ticket_buy(Integer eventId, Integer sitNum,Integer allSit) throws SQLException, ClassNotFoundException {
        Alert alert;
        if (Main.person.getPosition()=="user"&& Main.person.getUsername()!=null){
            alert=new Alert(Alert.AlertType.CONFIRMATION, "Та захиалахдаа итгэлтэй байна уу",ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (alert.getResult() == ButtonType.YES)
            {
                String sql="INSERT INTO cinema.user_history (userId, uschedule_id, sitNum) values('"+
                        userId+"','"+eventId+"','"+sitNum+"')";
                Database.dbExecuteQuery(sql);
                showAlert(Alert.AlertType.INFORMATION, owner,"Мэдээлэл","Амжилттай бүртэгдлээ" );
                sit_dispay(eventId,null,allSit);
            }else if (alert.getResult() == ButtonType.NO){
                System.out.println("no");
            }
        }else {
            showAlert(Alert.AlertType.ERROR,owner,"Амжилтгүй","Та бүртгүүлнэ үү");
        }
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

    public void Home_clicked(ActionEvent actionEvent) throws IOException {
        StackPane stkP= FXMLLoader.load(getClass().getResource("Home.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    public void Logout_Clicked(ActionEvent actionEvent) throws IOException {
        Main.person.setUsername(null);
        Main.person.setPosition(null);
        StackPane stkP= FXMLLoader.load(getClass().getResource("Home.fxml"));
        SPane.getChildren().setAll(stkP);
    }

    public void MyTicket_clicked(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (Main.person.getUsername()!=null&&Main.person.getPosition()=="user"){
            String sql="SELECT movie.movieName,movie.movieKind,Concat('date:',schedule.Date) as movieAuthor,movie.MovieDesc\n" +
                    "from cinema.user\n" +
                    "left join cinema.user_history\n" +
                    "on user.userId=user_history.userId\n" +
                    "left join cinema.schedule\n" +
                    "on user_history.uschedule_id=schedule.scheduleId\n" +
                    "left join cinema.movie\n" +
                    "on schedule.movieId=movie.movieId\n" +
                    "where userName='" +Main.person.getUsername()+"' "+
                    "order by movieAuthor asc\n";
            System.out.println(sql);
            ResultSet rsSet= Database.dbExecute(sql);
            ObservableList<Movie_data> dataList =getEmployeeObjects(rsSet);
            populateTable(dataList);
        }else {
            showAlert(Alert.AlertType.ERROR,owner,"Алдаа","Та нэвтрэнэ үү");
        }
    }
}
