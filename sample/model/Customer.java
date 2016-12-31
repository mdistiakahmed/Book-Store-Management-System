package sample.model;

import javafx.beans.property.*;

import java.sql.Date;

public class Customer {
    //Declare Customer Table Columns
    private StringProperty name;
    private StringProperty phn_number;
    private StringProperty book;
    private StringProperty publisher;
    private SimpleObjectProperty<Date> p_date;
    private IntegerProperty price;
    private IntegerProperty copies;
    private IntegerProperty emp_id;

    //Constructor
    public Customer() {
        this.name = new SimpleStringProperty();
        this.phn_number = new SimpleStringProperty();
        this.book= new SimpleStringProperty();
        this.publisher = new SimpleStringProperty();
        this.p_date = new SimpleObjectProperty<>();
        this.price = new SimpleIntegerProperty();
        this.copies = new SimpleIntegerProperty();
        this.emp_id = new SimpleIntegerProperty();

    }

    //id
    public int getID() {
        return emp_id.get();
    }

    public void setID(int cop){
        this.emp_id.set(cop);
    }

    public IntegerProperty IDProperty(){
        return emp_id;
    }
    //Name
    public String getName() {
        return name.get();
    }

    public void setName(String Name){
        this.name.set(Name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    //phn number
    public String getPhnNumber() {
        return phn_number.get();
    }

    public void setPhnnumber(String Name){
        this.phn_number.set(Name);
    }

    public StringProperty phnNumberProperty() {
        return phn_number;
    }

    //book
    public String getBook() {
        return book.get();
    }

    public void setBook(String Name){
        this.book.set(Name);
    }

    public StringProperty bookProperty() {
        return book;
    }

    //publisherName
    public String getPublisher() {
        return publisher.get();
    }

    public void setPublisher(String Name){
        this.publisher.set(Name);
    }

    public StringProperty PublisherProperty() {
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
