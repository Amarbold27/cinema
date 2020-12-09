package Test;

import sample.Database;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class BranchTest {
    String sqlUser = "SELECT * FROM branch WHERE branchId=1";
    CachedRowSet crs;
    @Test
    public void getBranchId(){

    }
    crs =(CachedRowSet)sample.Database.dbExecute(sqlUser);
        while(crs.next())
    {   //data gaar guine
        int id = crs.getInt("branchId");

    }
}
