package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CusDOA {
    //*******************************
    //SELECT all
    //*******************************
    public static ObservableList<Customer> search_all_customer() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM customer";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Customer> cusList = getCusList(rs);

            //Return employee object
            return cusList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from employees operation
    private static ObservableList<Customer> getCusList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Customer> cusList = FXCollections.observableArrayList();

        while (rs.next()) {
            Customer cus = new Customer();
            cus.setName(rs.getString("NAME"));
            cus.setPhnnumber(rs.getString("PHONE_NUMBER"));
            cus.setBook(rs.getString("BOOK"));
            cus.setPublisher(rs.getString("PUBLISHER"));
            cus.setPDate(rs.getDate("P_DATE"));
            cus.setCopies(rs.getInt("COPIES"));
            cus.setPrice(rs.getInt("PRICE"));
            cus.setID(rs.getInt("EMP_ID"));
            //Add employee to the ObservableList
            cusList.add(cus);
        }
        //return empList (ObservableList of Employees)
        return cusList;
    }


    //*************************************
    //DELETE an employee
    //*************************************
    public static void deleteAll() throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM customer\n" +
                        "   COMMIT;\n" +
                        "END;";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    //*******************************
    //SELECT a customer
    //*******************************
    public static ObservableList<Customer> search_customer(String name,String phn) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM customer where NAME = " + "'" + name+ "'"  +
                " and " + " PHONE_NUMBER = " + "'" + phn + "'";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            if(!rs.next()){
                System.out.println("not h");
            }
            rs.beforeFirst();

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Customer> cusList = getCusList(rs);

            //Return employee object
            return cusList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
}
