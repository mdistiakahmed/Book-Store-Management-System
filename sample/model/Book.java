package sample.model;

import javafx.beans.property.*;

import java.sql.Date;

public class Book {
    //Declare Book Table Columns
    public  static  int flag=99;
    public  static  int val=0;
    private IntegerProperty copies;
    private StringProperty name;
    private StringProperty publisher;
    private SimpleObjectProperty<Date> p_date;
    private IntegerProperty price;

    //Constructor
    public Book() {
        this.copies = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.publisher = new SimpleStringProperty();
        this.p_date = new SimpleObjectProperty<>();
        this.price = new SimpleIntegerProperty();
    }

    public  String toString(){
        return  publisher.toString();
    }
    //copies
    public int getCopies() {
        return copies.get();
    }

    public void setCopies(int cop){
        this.copies.set(cop);
    }

    public IntegerProperty copiesProperty(){
        return copies;
    }

    //Bookname
    public String getBookName () {
        return name.get();
    }

    public void setBookName(String Name){
        this.name.set(Name);
    }

    public StringProperty BookNameProperty() {
        return name;
    }

    //publisherName
    public String getPublisherName () {
        return publisher.get();
    }

    public void setPublisherName(String Name){
        this.publisher.set(Name);
    }

    public StringProperty PublisherNameProperty() {
        return publisher;
    }

    //purchase date
    public Object getPDate(){
        return p_date.get();
    }

    public void setPDate(Date hireDate){
        this.p_date.set(hireDate);
    }

    public SimpleObjectProperty<Date> PDateProperty(){
        return p_date;
    }

    //price
    public int getPrice() {
        return price.get();
    }

    public void setPrice(int cop){
        this.price.set(cop);
    }

    public IntegerProperty priceProperty(){
        return price;
    }
}
