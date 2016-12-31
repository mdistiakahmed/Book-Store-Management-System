package sample.model;


import javafx.beans.property.*;

import java.sql.Date;


public class Employee {
    //Declare Employees Table Columns
    private IntegerProperty employee_id;
    private StringProperty name;
    private SimpleObjectProperty<Date> hire_date;
    private IntegerProperty salary;

    //Constructor
    public Employee() {
        this.employee_id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.hire_date = new SimpleObjectProperty<>();
        this.salary = new SimpleIntegerProperty();
    }

    //employee_id
    public int getEmployeeId() {
        return employee_id.get();
    }

    public void setEmployeeId(int employeeId){
        this.employee_id.set(employeeId);
    }

    public IntegerProperty employeeIdProperty(){
        return employee_id;
    }

    //name
    public String getName () {
        return name.get();
    }

    public void setName(String firstName){
        this.name.set(firstName);
    }

    public StringProperty NameProperty() {
        return name;
    }


    //hire_date
    public Object getHireDate(){
        return hire_date.get();
    }

    public void setHireDate(Date hireDate){
        this.hire_date.set(hireDate);
    }

    public SimpleObjectProperty<Date> hireDateProperty(){
        return hire_date;
    }


    //salary
    public int getSalary() {
        return salary.get();
    }

    public void setSalary(int salary){
        this.salary.set(salary);
    }

    public IntegerProperty salaryProperty(){
        return salary;
    }


}