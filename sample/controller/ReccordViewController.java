package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import oracle.sql.DATE;
import sample.Main;
import sample.model.Book;
import sample.model.BookDAO;
import sample.model.RecordDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class ReccordViewController {

    public  static Main ob;
    public  static  void setMain(Main b){
        ob=b;
    }

    @FXML
    private TableView  table;

    @FXML
    private TableColumn<Book, String> pubCol;

    @FXML
    private TableColumn<Book, String> bookCol;

    @FXML
    private TableColumn<Book, Integer> copiesCol;

    @FXML
    private TableColumn<Book,Date> dateCol;

    @FXML
    private TableColumn<Book, Integer> priceCol;

    @FXML
    private TextField pname_txt;


    @FXML
    private TextField bname_txt;

    @FXML
    private DatePicker by_date;

    @FXML
    void by_date_action(ActionEvent event) throws Exception{

        //System.out.println(by_date.getEditor().getText());
        ObservableList<Book> data = RecordDAO.search_by_date( getDate());
        //Populate Employee on TableView and Display on TextArea
        populate_All_Books(data);
    }
    String getDate(){
        StringTokenizer st = new StringTokenizer(by_date.getEditor().getText(),"/");
        int mon=parseInt(st.nextToken());
        String day=st.nextToken();
        String yy=st.nextToken();
        String all[] = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        String ans=day+"-"+all[mon-1]+"-"+yy;

        System.out.println(ans);

        return  ans;
    }
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ChoiceBox<String> choice_by_book;

    //*******************************
    //INTITIALIZE
    //*******************************

    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Employee objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe

        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */
        bookCol.setCellValueFactory(cellData -> cellData.getValue().BookNameProperty());
        pubCol.setCellValueFactory(cellData -> cellData.getValue().PublisherNameProperty());
        copiesCol.setCellValueFactory(cellData -> cellData.getValue().copiesProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().PDateProperty());
        System.out.println("here");
        try {
            ObservableList<Book> d = BookDAO.search_all_books();
            ObservableList<String>ddd=FXCollections.observableArrayList();;
            for(Book ob:d){
                ddd.add(ob.getPublisherName());
            }
            choiceBox.setItems(ddd);
            choiceBox.show();


            ObservableList<String>d1=FXCollections.observableArrayList();;
            for(Book ob:d){
                d1.add(ob.getBookName());
            }
            choice_by_book.setItems(d1);
            choice_by_book.show();

        }catch (Exception e) {
            System.out.println("Exception");
        }

    }

    @FXML
    void by_book_action(ActionEvent event) throws ClassNotFoundException, SQLException{
        try {
            String str=choice_by_book.getValue();
            ObservableList<Book> data = RecordDAO.search_by_book(str);
            //Populate Employee on TableView and Display on TextArea
            populate_All_Books(data);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }


    @FXML
    void by_publisher_action(ActionEvent event)  throws ClassNotFoundException, SQLException{
        try {
            //Get Employee information
            String str=choiceBox.getValue();
            ObservableList<Book> data  = RecordDAO.search_by_publisher(str);
            //Populate Employee on TableView and Display on TextArea
            populate_All_Books(data);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }

    @FXML
    private void populate_All_Books (ObservableList<Book> data) throws ClassNotFoundException {
        //Set items to the employeeTable
        table.setItems(data);
    }

    @FXML
    void see_all_action(ActionEvent event)  throws SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            ObservableList<Book> data = BookDAO.search_all_books();
            //Populate Employees on TableView
            populate_All_Books(data);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }

    }

    @FXML
    void home_btn_action(ActionEvent event) throws  Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/homePage.fxml"));
        AnchorPane employeeOperationsView = (AnchorPane) loader.load();

        // Set Employee Operations view into the center of root layout.
        ob.rootLayout.setCenter(employeeOperationsView);
    }

}
