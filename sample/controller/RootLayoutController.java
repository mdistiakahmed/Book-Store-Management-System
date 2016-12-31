package sample.controller;


import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class RootLayoutController {

    //Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("This is a Book Store Management System");
        alert.setContentText("All right reserved by Istiak & Imran.");
        alert.show();
    }
}