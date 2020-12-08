package sample;

import javax.sql.rowset.*;
import java.sql.*;

/*
* author Baldorj
*
* Enehvv class ni ogodliin santai haritsah class
*
* */


public class Database {
    private static final String JDB_Driver="com.mysql.cj.jdbc.Driver";
    private static Connection connection=null;
    private static final String User="root";
    private static final String Password="root";
    private static final String DatabaseName="cinema";
    private static final String ConnStr="jdbc:mysql://localhost:3306/"+DatabaseName;



    // enehvv function ni ogogdliin santai holboh func
    public static void dbConnect() throws SQLException,ClassNotFoundException{
        try{
            Class.forName(JDB_Driver);
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

        try{
            connection=DriverManager.getConnection(ConnStr,User,Password);
            if (connection!=null){
                System.out.println("database connection");
            }
        }catch (Exception e){
            System.out.println("Connection failed!"+e);
            throw e;
        }
    }


    //enehvv function ni ogogdllin sangiin holboltiig salgana
    public static  void dbDicsonnect() throws SQLException{
        try{
            if (connection !=null&& !connection.isClosed()){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //insert/delete/update vildlvvdiig enehuu func eer duudna
    public static void dbExecuteQuery(String sqlStmt)throws SQLException,ClassNotFoundException{
        Statement stmt=null;
        try{
            dbConnect();
            stmt=connection.createStatement();
            stmt.executeUpdate(sqlStmt);
            System.out.println("Successful");
        }catch (Exception e){
            System.out.println("!!!!!!!! Error dbExecuteQuery "+e);
            throw  e;
        }
        finally {                    //holbolt hiisen ch bsn hiigeegvi ch bsn holboltoo butsaaj salgah
            if (stmt!=null){
                stmt.close();
            }
            dbDicsonnect();
        }
    }


    //enehvv function select vildliig hiine, resultset eer ogogdloo butsaana
    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException,SQLException{
        Statement stmt=null;
        ResultSet rs=null;
        CachedRowSet crs;
        try{
            dbConnect();                           //database holboh func
            stmt=connection.createStatement();
            rs=stmt.executeQuery(sqlQuery);         //parametreer irsen query geer handalt hiih
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);                         //awsan resultsetee ee haahiin tuld cachedRowseted hadgalj baij
                                                        //omnoh awsan rowsetee haana
        }catch (SQLException e){
            System.out.println("!!Error in dbExecute"+e);
            throw e;
        }
        finally {                                   //vildliig gvitsetgesnii daraa haaj ogoh
            if (rs!=null){
                rs.close();
            }
            if (stmt!=null){
                stmt.close();
            }
            dbDicsonnect();
        }
        return crs;
    }

}
