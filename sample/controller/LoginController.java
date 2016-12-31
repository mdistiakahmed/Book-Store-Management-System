package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample.model.passDAO;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class LoginController {
    String nam;
    String pass;
    public  static  int val;
    public  static Main ob;
    public  static  void setMain(Main b){
        ob=b;
    }

    @FXML

    private TextField user_id;

    @FXML
    private TextField password_id;

    @FXML
    void forget_action(ActionEvent event) throws  Exception{
        String str = "imran";
        File newTextFile = new File("F:\\project_JAVA\\finish1\\src\\sample\\controller\\output.txt");

        FileWriter fw = new FileWriter(newTextFile);
        fw.write("User Name : Euser\n");
        fw.write("Password : 123");
        fw.close();
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Attention");
        //alert.setHeaderText("This is a Book Store Management System");
        alert.setContentText("Username and Password is sent to your Secret File.");
        alert.show();
       // PrintWriter out = new PrintWriter("F:\\project_JAVA\\start_3\\src\\sample\\controller\\output.txt");
       // out.println("imran");
        //out.println("123");
    }

    @FXML
    void login_action(ActionEvent event) throws Exception{

        String name=user_id.getText();
        String  pass=password_id.getText();
        int t=passDAO.pass_match(name,pass);
        val=t;
        if(t==2 || t==1) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/homePage.fxml"));
            AnchorPane employeeOperationsView = (AnchorPane) loader.load();

            // Set Employee Operations view into the center of root layout.
            ob.rootLayout.setCenter(employeeOperationsView);
        }


    }



}
