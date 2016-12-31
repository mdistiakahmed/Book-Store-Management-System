package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample.model.Book;
import sample.model.BookDAO;

import java.sql.SQLException;

public class BookViewController {
    public  static Main ob;
    public  static  void setMain(Main b){
        ob=b;
    }
    private Alert alert;

    @FXML
    private TableView  bookTable;

    @FXML
    private TableColumn<Book, String> nameCol;

    @FXML
    private TableColumn<Book, String> publisherCol;

    @FXML
    private TableColumn<Book,Integer> copiesCol;

    @FXML
    private TextField book_name_txt;

    @FXML
    private TextField publisher_txt;

    @FXML
    private TextField copies_txt;

    @FXML
    private TextField gen_name_txt;

    @FXML
    private TextField price_txt;



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
        nameCol.setCellValueFactory(cellData -> cellData.getValue().BookNameProperty());
        publisherCol.setCellValueFactory(cellData -> cellData.getValue().PublisherNameProperty());
        copiesCol.setCellValueFactory(cellData -> cellData.getValue().copiesProperty().asObject());
    }

    //*******************************
    //ADD button on action
    //*******************************


    @FXML
    void add_btn_action(ActionEvent event) throws SQLException, ClassNotFoundException{
        //System.out.println(book_name_txt.getText().isEmpty());

        if(book_name_txt.getText().isEmpty() || publisher_txt.getText().isEmpty() || price_txt.getText().isEmpty() || copies_txt.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Information");
            alert.setContentText("Please Enter All the information");
            alert.showAndWait();
            return;
        }

        try {
            BookDAO.insertBook(book_name_txt.getText(),publisher_txt.getText(),price_txt.getText(),copies_txt.getText());
            try {
                //Get all Employees information
                ObservableList<Book> data = BookDAO.search_all_books();
                //Populate Employees on TableView
                populate_All_Books(data);
            } catch (SQLException e){
                System.out.println("Error occurred while getting employees information from DB.\n" + e);
                throw e;
            }
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Inserted");
            alert.showAndWait();

        } catch (SQLException e) {
            throw e;
        }

    }

    //*******************************
    //Search by book name
    //*******************************

    @FXML
    void search_book_btn_action(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(gen_name_txt.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Information");
            alert.setContentText("Please Enter Book name");
            alert.showAndWait();
            return;
        }
        try {
            //Get Employee information
            ObservableList<Book> data = BookDAO.search_by_book(gen_name_txt.getText());
            //Populate Employee on TableView and Display on TextArea
            populate_All_Books(data);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    //*******************************
    //SELECT by publisher name
    //*******************************

    @FXML
    void search_publisher_btn_action(ActionEvent event) throws ClassNotFoundException, SQLException{
        if(gen_name_txt.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Information");
            alert.setContentText("Please Enter Publisher name");
            alert.showAndWait();
            return;
        }
        try {
            //Get Employee information
            ObservableList<Book> data  = BookDAO.search_by_publisher(gen_name_txt.getText());
            //Populate Employee on TableView and Display on TextArea
            populate_All_Books(data);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    

    //*******************************
    //SELECT all button on action
    //*******************************

    @FXML
    void see_all_btn_action(ActionEvent event) throws SQLException, ClassNotFoundException{
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
    //Populate Employees for TableView
    @FXML
    private void populate_All_Books (ObservableList<Book> data) throws ClassNotFoundException {
        //Set items to the employeeTable
        bookTable.setItems(data);
    }


    @FXML
    void delete_book_action(ActionEvent event)  throws SQLException, ClassNotFoundException{
        if(gen_name_txt.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Information");
            alert.setContentText("Please Enter Book name");
            alert.showAndWait();
            return;
        }
        try {
            BookDAO.deleteWithBook(gen_name_txt.getText());
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText(null);
            alert.setContentText("Deleted !");

            alert.showAndWait();
        } catch (SQLException e) {
            throw e;
        }

    }

    //*******************************
    //HOME button on action
    //*******************************

    @FXML
    void home_btn_action(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/homePage.fxml"));
        AnchorPane employeeOperationsView = (AnchorPane) loader.load();

        // Set Employee Operations view into the center of root layout.
        ob.rootLayout.setCenter(employeeOperationsView);

    }

}
