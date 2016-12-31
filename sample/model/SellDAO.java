package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellDAO {
    public  static int cus_insert(String name,String phn,String age,String  book,String pub,String cop,int p,String emp_id)throws SQLException, ClassNotFoundException{
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO customer\n" +
                        "( NAME, PHONE_NUMBER, BOOK,PUBLISHER,P_DATE,COPIES,PRICE,AGE,EMP_ID)\n" +
                        "VALUES\n" +
                        "('" + name + "', '" + phn + "' , '" + book + "' , '" + pub + "',SYSDATE , '" + cop+ "' , '" + p + "', '" + age + "' , '"+emp_id + "');\n" +
                        "END;";
        //Execute INSERT operation
        try {
            System.out.println("--here");
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }

        return  1;
    }

    public  static  void book_update(String book,String  pub,int cop)throws SQLException, ClassNotFoundException{
        //Declare a UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE book\n" +
                        "      SET COPIES = " + cop + "\n" +
                        "    WHERE NAME = '" + book + "'\n" +
                        " AND PUBLISHER = '"+pub+"';\n"+
                        "   COMMIT;\n" +
                        "END;";
        if(cop==0){
            updateStmt =
                    "BEGIN\n" +
                            "   DELETE FROM book\n" +
                            "         WHERE NAME = '" + book + "'\n" +
                            "         and PUBLISHER = '"+pub+"';\n"+
                            "   COMMIT;\n" +
                            "END;";
        }

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }

    }
    public static Book go(String book,String pub,String cop)throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM book where name= "+ "'"+ book + "'" + " and publisher = " +
                "'" +pub+ "'";

        Book ob=null;
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            if(!rs.next()) {
                ob=new Book();
                ob.flag=1;
                return  ob;
            }
            rs.beforeFirst();

            ob=getBook(rs);
            int x =Integer.parseInt(cop);
            int y=ob.getCopies();
            if(y<x) {
                ob.flag=2;
                return  ob;
            }


        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }

        return  ob;
    }

    private static Book getBook(ResultSet rs) throws SQLException {
        Book book = null;
        if (rs.next()) {
            book = new Book();
            book.setBookName(rs.getString("NAME"));
            book.setPublisherName(rs.getString("PUBLISHER"));
            book.setCopies(rs.getInt("COPIES"));
            book.setPrice(rs.getInt("PRICE"));
        }
        return book;
    }
}
