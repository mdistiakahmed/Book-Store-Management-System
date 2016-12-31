package sample.controller;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample.model.Employee;
import sample.model.EmployeeDAO;

import java.sql.Date;
import java.sql.SQLException;

public class EmployeeViewController {

    public  static Main ob;
    public  static  void setMain(Main b){
        ob=b;
    }

    @FXML
    private TextField empIdText;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField newSalaryText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField idText;
    @FXML
    private TextField salaryText;
    @FXML
    private TableView employeeTable;
    @FXML
    private TableColumn<Employee, Integer>  empIdColumn;
    @FXML
    private TableColumn<Employee, String>  empNameColumn;
    @FXML
    private TableColumn<Employee, Integer> empSalaryColumn;
    @FXML
    private TableColumn<Employee, Date> empHireDateColumn;

    //Search an employee
    @FXML
    private void searchEmployee (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Employee information
            Employee emp = EmployeeDAO.searchEmployee(empIdText.getText());
            //Populate Employee on TableView and Display on TextArea
            populateAndShowEmployee(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting employee information from DB.\n" + e);
            throw e;
        }
    }

    //Search all employees
    @FXML
    private void searchEmployees(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            ObservableList<Employee> empData = EmployeeDAO.searchEmployees();
            //Populate Employees on TableView
            populateEmployees(empData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
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
        empIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
        empNameColumn.setCellValueFactory(cellData -> cellData.getValue().NameProperty());
        empSalaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());
        empHireDateColumn.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty());
    }

    //Populate Employee
    @FXML
    private void populateEmployee (Employee emp) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Employee> empData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        empData.add(emp);
        //Set items to the employeeTable
        employeeTable.setItems(empData);
    }

    //Set Employee information to Text Area
    @FXML
    private void setEmpInfoToTextArea ( Employee emp) {
        resultArea.setText("First Name: " + emp.getName() + "\n");
    }

    //Populate Employee for TableView and Display Employee on TextArea
    @FXML
    private void populateAndShowEmployee(Employee emp) throws ClassNotFoundException {
        if (emp != null) {
            populateEmployee(emp);
            setEmpInfoToTextArea(emp);
        } else {
            resultArea.setText("This employee does not exist!\n");
        }
    }

    //Populate Employees for TableView
    @FXML
    private void populateEmployees (ObservableList<Employee> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
        employeeTable.setItems(empData);
    }

    //Update employee's email with the email which is written on newEmailText field
    @FXML
    private void updateEmployeeEmail (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.updateSalary(empIdText.getText(),newSalaryText.getText());
            resultArea.setText("salary has been updated for, employee id: " + empIdText.getText() + "\n");
            ObservableList<Employee> empData = EmployeeDAO.searchEmployees();
            //Populate Employees on TableView
            populateEmployees(empData);
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating email: " + e);
        }
    }

    //Insert an employee to the DB
    @FXML
    private void insertEmployee (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.insertEmp(nameText.getText(),idText.getText(),salaryText.getText());
            resultArea.setText("Employee inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting employee " + e);
            throw e;
        }
    }

    //Delete an employee with a given employee Id from DB
    @FXML
    private void deleteEmployee (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.deleteEmpWithId(empIdText.getText());
            resultArea.setText("Employee deleted! Employee id: " + empIdText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while deleting employee " + e);
            throw e;
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