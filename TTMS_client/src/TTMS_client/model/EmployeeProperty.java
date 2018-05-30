package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EmployeeProperty {
    private SimpleIntegerProperty emp_id = new SimpleIntegerProperty();

    private SimpleStringProperty emp_no = new SimpleStringProperty();

    private SimpleIntegerProperty emp_type = new SimpleIntegerProperty();

    private SimpleStringProperty emp_name = new SimpleStringProperty();

    private SimpleStringProperty emp_passwd = new SimpleStringProperty();

    private SimpleStringProperty emp_tel_num = new SimpleStringProperty();

    private SimpleStringProperty emp_addr = new SimpleStringProperty();

    private SimpleStringProperty emp_email = new SimpleStringProperty();

    public EmployeeProperty(Integer emp_id,String emp_no, Integer emp_type, String emp_name, String emp_passwd,
                            String emp_tel_num, String emp_addr, String emp_email){
        this.setEmp_id(emp_id);
        this.setEmp_no(emp_no);
        this.setEmp_type(emp_type);
        this.setEmp_name(emp_name);
        this.setEmp_passwd(emp_passwd);
        this.setEmp_tel_num(emp_tel_num);
        this.setEmp_addr(emp_addr);
        this.setEmp_email(emp_email);
    }

    public EmployeeProperty(){}

    public void setEmp_id(int emp_id) {
        this.emp_id.set(emp_id);
    }

    public void setEmp_no(String emp_no) {
        this.emp_no.set(emp_no);
    }

    public void setEmp_type(int emp_type) {
        this.emp_type.set(emp_type);
    }

    public void setEmp_name(String emp_name) {
        this.emp_name.set(emp_name);
    }

    public void setEmp_passwd(String emp_passwd) {
        this.emp_passwd.set(emp_passwd);
    }

    public void setEmp_addr(String emp_addr) {
        this.emp_addr.set(emp_addr);
    }

    public void setEmp_email(String emp_email) {
        this.emp_email.set(emp_email);
    }


    public void setEmp_tel_num(String emp_tel_num) {
        this.emp_tel_num.set(emp_tel_num);
    }

    public int getEmp_id() {
        return emp_id.get();
    }

    public String getEmp_no() {
        return emp_no.get();
    }

    public int getEmp_type() {
        return emp_type.get();
    }

    public String getEmp_name() {
        return emp_name.get();
    }

    public String getEmp_passwd() {
        return emp_passwd.get();
    }

    public String getEmp_tel_num() {
        return emp_tel_num.get();
    }

    public String getEmp_addr() {
        return emp_addr.get();
    }

    public String getEmp_email() {
        return emp_email.get();
    }

    public void print(){
        System.out.println("------------------");
        System.out.println(getEmp_id());
        System.out.println(getEmp_no());
        System.out.println(getEmp_type());
        System.out.println(getEmp_name());
        System.out.println(getEmp_passwd());
        System.out.println(getEmp_tel_num());
        System.out.println(getEmp_addr());
        System.out.println(getEmp_email());
        System.out.println("------------------");

    }
}