package TestJava;

import org.junit.Test;
import sample.Database;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class BranchTest {
    String sqlUser = "SELECT * FROM branch WHERE branchId=5";
    CachedRowSet crs,crs1;
    int id = 0;
//    @Test
//    public void getBranchId() throws SQLException, ClassNotFoundException {
//        crs =(CachedRowSet)sample.Database.dbExecute(sqlUser);
//        while(crs.next())
//        {
//             id = crs.getInt("branchId");
//        }
//        assertEquals(5,id);
//    }
//    @Test
//    public void getBranchName() throws SQLException, ClassNotFoundException {
//        sqlUser = "SELECT * FROM branch WHERE branchName='salbar 1'";
//        crs =(CachedRowSet)sample.Database.dbExecute(sqlUser);
//        String name=null;
//        while(crs.next())
//        {
//            name  = crs.getString("branchName");
//        }
//        assertEquals("salbar 1",name);
//    }
//    @Test
//    public void getBranchAddress() throws SQLException, ClassNotFoundException {
//        sqlUser = "SELECT * FROM branch WHERE branchAddress='urguu'";
//        crs =(CachedRowSet)sample.Database.dbExecute(sqlUser);
//        String name=null;
//        while(crs.next())
//        {
//            name  = crs.getString("branchAddress");
//        }
//        assertEquals("urguu",name);
//    }


    @Test
    public void setBranchId() throws SQLException, ClassNotFoundException {
     String sqlSet = "UPDATE branch SET branchId = 5  WHERE branchName = 'salbar 1';";
      sample.Database.dbExecuteQuery(sqlSet);
       String sqlBranch = "SELECT * FROM branch WHERE branchId=5";
        crs1 =(CachedRowSet)sample.Database.dbExecute(sqlBranch);
        while(crs1.next())
        {
            id = crs1.getInt("branchId");
        }
        assertEquals(5,id);
    }
    @Test
    public void setBranchName() throws SQLException, ClassNotFoundException {
        String sqlSet = "UPDATE branch SET branchName = 'salbar 3'  WHERE branchName = 'salbar 1';";
        sample.Database.dbExecuteQuery(sqlSet);
        String sqlBranch = "SELECT * FROM branch WHERE branchName='salbar 3'";
        String name = null;
        crs1 =(CachedRowSet)sample.Database.dbExecute(sqlBranch);
        while(crs1.next())
        {
            name = crs1.getString("branchName");
        }
        assertEquals("salbar 3",name);

    }
    @Test
    public void setBranchAddress() throws SQLException, ClassNotFoundException {
        String sqlSet = "UPDATE branch SET branchAddress = 'horooolol'  WHERE branchAddress = 'urguu';";
        sample.Database.dbExecuteQuery(sqlSet);
        String sqlBranch = "SELECT * FROM branch WHERE branchAddress='horooolol'";
        String name = null;
        crs1 =(CachedRowSet)sample.Database.dbExecute(sqlBranch);
        while(crs1.next())
        {
            name = crs1.getString("branchAddress");
        }
        assertEquals("horooolol",name);

    }

}
