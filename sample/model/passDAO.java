package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class passDAO {
    public static int pass_match(String userid,String pass) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM login";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);
            while (rsEmp.next()) {
                System.out.println(rsEmp.getString("USERID"));
                System.out.println(rsEmp.getString("pass"));
                if(rsEmp.getString("USERID").equals(userid) && rsEmp.getString("PASS").equals(pass)){
                    if(userid.charAt(0)=='E'){
                        return  1;
                    }
                    else
                        return  2;

                }
            }
            return  0;

        } catch (SQLException e) {
            //Return exception
            throw e;
        }
    }

}
