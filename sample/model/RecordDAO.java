package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RecordDAO {

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

    private static ObservableList<Book> getBookList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Book> bookList = FXCollections.observableArrayList();

        while (rs.next()) {
            Book book = new Book();
            book.setBookName(rs.getString("NAME"));
            book.setPublisherName(rs.getString("PUBLISHER"));
            book.setCopies(rs.getInt("COPIES"));
            book.setPDate(rs.getDate("P_DATE"));
            book.setPrice(rs.getInt("PRICE"));
            //Add employee to the ObservableList
            bookList.add(book);
        }
        //return empList (ObservableList of Employees)
        return bookList;
    }

    //*******************************
    //SELECT by date
    //*******************************
    public static ObservableList<Book> search_by_date(String nam) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        //select * from book where p_date-1<'6-DEC-16' and p_date+1>'6-DEC-16';
        String selectStmt = "SELECT * FROM Book WHERE P_DATE-1 < '" +nam+"' and p_date+1 > '"+nam+"'";
        //String selectStmt = "SELECT * FROM Book";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet ans = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            ObservableList<Book> bList =getBookList(ans);

            //Return employee object
            return bList;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + nam + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
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

}
