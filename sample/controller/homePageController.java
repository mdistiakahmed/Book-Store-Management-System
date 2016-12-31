package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sample.Main;

import java.io.IOException;


public class homePageController {
    public  static Main ob;

    public  static  void setMain(Main b){
        ob=b;
    }
    public  static  EmployeeViewController evc;
    public  static  BookViewController bvc;
    public  static  ReccordViewController rvc;
    public  static  SellViewController svc;
    public  static  CusRecordViewController cvc;

    @FXML
    private AnchorPane appMother;

    @FXML
    public static Button emp_btn;
    @FXML
    void logout_out(ActionEvent event) {
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/LoginPage.fxml"));
            AnchorPane employeeOperationsView = (AnchorPane) loader.load();

            // Set Employee Operations view into the center of root layout.
            ob.rootLayout.setCenter(employeeOperationsView);
            evc.setMain(ob);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void Employee_btn_action(ActionEvent event) {
        if(LoginController.val==1)
            return;
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/EmployeeView.fxml"));
            AnchorPane employeeOperationsView = (AnchorPane) loader.load();

            // Set Employee Operations view into the center of root layout.
            ob.rootLayout.setCenter(employeeOperationsView);
            evc.setMain(ob);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void book_btn_action(ActionEvent event) {
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/BookView.fxml"));
            AnchorPane employeeOperationsView = (AnchorPane) loader.load();

            // Set Employee Operations view into the center of root layout.
            ob.rootLayout.setCenter(employeeOperationsView);
            bvc.setMain(ob);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void sell_btn_action(ActionEvent event) {
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/sellView.fxml"));
            AnchorPane employeeOperationsView = (AnchorPane) loader.load();

            // Set Employee Operations view into the center of root layout.
            ob.rootLayout.setCenter(employeeOperationsView);
            svc.setMain(ob);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void record_btn_action(ActionEvent event) {
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RecordView.fxml"));
            AnchorPane employeeOperationsView = (AnchorPane) loader.load();

            // Set Employee Operations view into the center of root layout.
            ob.rootLayout.setCenter(employeeOperationsView);
            rvc.setMain(ob);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cus_rec_btn_action(ActionEvent event) {
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/CusRecordView.fxml"));
            AnchorPane employeeOperationsView = (AnchorPane) loader.load();

            // Set Employee Operations view into the center of root layout.
            ob.rootLayout.setCenter(employeeOperationsView);
            cvc.setMain(ob);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
