package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample.model.Book;
import sample.model.Sell;
import sample.model.SellDAO;

import java.sql.SQLException;
import java.util.Optional;

public class SellViewController {

    public  static Main ob;
    public  static  void setMain(Main b){
        ob=b;
    }

    @FXML
    private TextField book_txt;

    @FXML
    private TextField pub_txt;

    @FXML
    private TextField copies_txt;

    @FXML
    private TextField age_txt;

    @FXML
    private TextField cus_name_txt;

    @FXML
    private TextField mobile_txt;

    @FXML
    private TextField emp_id_txt;



    @FXML
    void sell_btn_action(ActionEvent event) throws SQLException, ClassNotFoundException{
        Alert alert;
        if(book_txt.getText().isEmpty() || pub_txt.getText().isEmpty() || copies_txt.getText().isEmpty()){
            alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("INSUFFICIENT INFORMATION");
            alert.setContentText("Please enter book name,publisher and number of copies correctly");
            alert.showAndWait();

            return;

        }
        Book data  = SellDAO.go(book_txt.getText(), pub_txt.getText(),copies_txt.getText());

        if(data.flag==1){
            alert = new Alert (Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Book Not Found");
            alert.setContentText("Check Spelling or We don't Have This Book");
            alert.showAndWait();
           // System.out.println(age_txt.getText().isEmpty());
        }
        else if(data.flag==2){
            alert = new Alert (Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Found only "+data.getCopies()+" Books");
            alert.setContentText("You wanted "+copies_txt.getText()+" books But we have only "+data.getCopies()+" books");
            alert.showAndWait();
        }
        else{
            int temp=data.getPrice() * Integer.parseInt(copies_txt.getText());
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Total Cost will be : "+temp +" TAKA");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                if(cus_name_txt.getText().isEmpty() || age_txt.getText().isEmpty() || mobile_txt.getText().isEmpty()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Insufficient Information");
                    alert.setContentText("Please Enter customer information correctly");
                    alert.show();
                    return;

                }
                int x=SellDAO.cus_insert(cus_name_txt.getText(),mobile_txt.getText(),age_txt.getText(),
                        book_txt.getText(),pub_txt.getText(),copies_txt.getText(),temp,emp_id_txt.getText());
                SellDAO.book_update(book_txt.getText(),pub_txt.getText(),data.getCopies()-Integer.parseInt(copies_txt.getText()));
                Alert a = new Alert (Alert.AlertType.INFORMATION);
                a.setTitle("INFORMATION");
                a.setHeaderText("Sold");
                a.setContentText(null);
                a.showAndWait();
            } else {
                // ... user chose CANCEL or closed the dialog
            }

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
