package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {
    //*******************************
    //SELECT all
    //*******************************
    public static ObservableList<Book> search_all_books() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM book";
        System.out.println("----->");

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Book> bookList = getBookList(rs);

            //Return employee object
            return bookList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from employees operation
    private static ObservableList<Book> getBookList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Book> bookList = FXCollections.observableArrayList();

        while (rs.next()) {
            Book book = new Book();
            book.setBookName(rs.getString("NAME"));
            book.setPublisherName(rs.getString("PUBLISHER"));
            book.setCopies(rs.getInt("COPIES"));
            book.setPrice(rs.getInt("PRICE"));
            book.setPDate(rs.getDate("P_DATE"));
            //Add employee to the ObservableList
            bookList.add(book);
        }
        //return empList (ObservableList of Employees)
        return bookList;
    }

    //*******************************
    //SELECT by book name
    //*******************************
    public static ObservableList<Book> search_by_book(String nam) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM Book WHERE NAME=" + "'" +nam+"'";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            ObservableList<Book> bookList =getBookList(rsEmp);

            //Return employee object
            return bookList;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + nam + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }


    //*******************************
    //SELECT by publisher
    //*******************************
    public static ObservableList<Book> search_by_publisher(String nam) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM Book WHERE PUBLISHER=" + "'" +nam+"'";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            ObservableList<Book> bookList =getBookList(rsEmp);

            //Return employee object
            return bookList;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + nam + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }





    //*************************************
    //INSERT a Book
    //*************************************
    public static void insertBook(String nam, String pub, String price,String copy) throws SQLException, ClassNotFoundException {
        //Declare a INSERT statement
        String insertStmt =
                "BEGIN\n" +
                        "INSERT INTO Book\n" +
                        "(NAME, PUBLISHER,PRICE, COPIES,P_DATE)\n" +
                        "VALUES\n" +
                        "('" + nam + "', '" + pub+ "','" + price + "' ,'" + copy + "',SYSDATE);\n" +
                        "END;";

        //Execute INSERT operation
        try {
            DBUtil.dbExecuteUpdate(insertStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //DELETE a Book
    //*************************************
    public static void deleteWithBook(String nam) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM Book\n" +
                        "         WHERE NAME =" + "'" + nam + "'" +";\n" +
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


}

