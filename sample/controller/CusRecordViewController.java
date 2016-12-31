package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample.model.CusDOA;
import sample.model.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

public class CusRecordViewController {

    public  static Main ob;
    public  static  void setMain(Main b){
        ob=b;
    }

    @FXML
    private TableView table;
    @FXML
    private TextField name_txt;

    @FXML
    private TextField mobile_txt;

    @FXML
    private TableColumn<Customer, String> nameCol;

    @FXML
    private TableColumn<Customer, String> mobileCol;

    @FXML
    private TableColumn<Customer, Date> dateCol;

    @FXML
    private TableColumn<Customer, String> bookCol;

    @FXML
    private TableColumn<Customer, String> pubCol;

    @FXML
    private TableColumn<Customer, Integer> copiesCol;

    @FXML
    private TableColumn<Customer, Integer> priceCol;


    @FXML
    private TableColumn<Customer, Integer> emp_id_col;


    //*******************************
    //SELECT by publisher name
    //*******************************

    @FXML
    void search_btn_action(ActionEvent event) throws ClassNotFoundException, SQLException{
        try {
            //Get Employee information
            ObservableList<Customer> data  = CusDOA.search_customer(name_txt.getText(),mobile_txt.getText());
            //Populate Employee on TableView and Display on TextArea
            table.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }

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
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        mobileCol.setCellValueFactory(cellData -> cellData.getValue().phnNumberProperty());
        bookCol.setCellValueFactory(cellData -> cellData.getValue().bookProperty());
        pubCol.setCellValueFactory(cellData -> cellData.getValue().PublisherProperty());
        copiesCol.setCellValueFactory(cellData -> cellData.getValue().copiesProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().PDateProperty());
        emp_id_col.setCellValueFactory(cellData -> cellData.getValue().IDProperty().asObject());
    }


    //see all customer

    @FXML
    void see_all_btn_action(ActionEvent event)throws SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            ObservableList<Customer> data = CusDOA.search_all_customer();
            //Populate Employees on TableView
            table.setItems(data);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }

    }

    @FXML
    void delete_all_action(ActionEvent event) throws SQLException, ClassNotFoundException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("All data will be deleted");
        alert.setContentText("Are you sure  want to delete all customer record ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            CusDOA.deleteAll();
        } else {
            // ... user chose CANCEL or closed the dialog
        }


    }

    @FXML
    void home_btn_action(ActionEvent event) throws  Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/homePage.fxml"));
        AnchorPane employeeOperationsView = (AnchorPane) loader.load();

        // Set Employee Operations view into the center of root layout.
        ob.rootLayout.setCenter(employeeOperationsView);

    }

}
