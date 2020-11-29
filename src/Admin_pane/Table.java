//package Admin_pane;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import sample.Database;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class Table {
//    private void populateTable(ObservableList<Employee> empList) {
//        Table.setItems(empList);
//    }
//
//    public static ObservableList<Employee>getAllRecords() throws ClassNotFoundException, SQLException {
//        String sql="select * from cinema.employee";
//        try {
//            ResultSet rsSet= Database.dbExecute(sql);
//            ObservableList<Employee> empList =getEmployeeObjects(rsSet);
//            return empList;
//        }catch (SQLException e){
//            System.out.println("Error occured while fetching the reacords from DB"+e);
//            e.printStackTrace();
//            throw e;
//        }
//
//    }
//
//    private static ObservableList<Employee> getEmployeeObjects(ResultSet rsSet) throws ClassNotFoundException,SQLException{
//        try {
//            ObservableList<Employee> emplist= FXCollections.observableArrayList();
//
//            while (rsSet.next()){
//                Employee emp=new Employee();
//                emp.setIdProperty(rsSet.getInt("id"));
//                emp.setNameProperty(rsSet.getString("first_name"));
//                emp.setLastNameProperty(rsSet.getString("last_name"));
//                emp.setEmailProperty(rsSet.getString("email"));
//                emplist.add(emp);
//            }
//            return emplist;
//        }catch (SQLException e){
//            System.out.println("Error occured while fetching the reacords from DB"+e);
//            e.printStackTrace();
//            throw e;
//        }
//    }
//}
