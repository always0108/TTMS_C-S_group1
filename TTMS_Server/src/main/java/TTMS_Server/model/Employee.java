package TTMS_Server.model;

import TTMS_Server.utils.MD5;

public class Employee {
    private Integer emp_id;

    private String emp_no;

    private Integer emp_type;

    private String emp_name;

    private String emp_passwd;

    private String emp_tel_num;

    private String emp_addr;

    private String emp_email;

    public Employee(String emp_no,Integer emp_type,String emp_name,String emp_passwd,
                    String emp_tel_num,String emp_addr,String emp_email){
        this.emp_no = emp_no;
        this.emp_type = emp_type;
        this.emp_name = emp_name;
        this.emp_passwd = MD5.codeByMD5(emp_passwd);
        this.emp_tel_num = emp_tel_num;
        this.emp_addr = emp_addr;
        this.emp_email = emp_email;
    }

    public Employee(){}

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no == null ? null : emp_no.trim();
    }

    public Integer getEmp_type() {
        return emp_type;
    }

    public void setEmp_type(Integer emp_type) {
        this.emp_type = emp_type;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name == null ? null : emp_name.trim();
    }

    public String getEmp_passwd() {
        return emp_passwd;
    }

    public void setEmp_passwd(String emp_passwd) {
        this.emp_passwd = emp_passwd == null ? null : MD5.codeByMD5(emp_passwd);
    }

    public String getEmp_tel_num() {
        return emp_tel_num;
    }

    public void setEmp_tel_num(String emp_tel_num) {
        this.emp_tel_num = emp_tel_num == null ? null : emp_tel_num.trim();
    }

    public String getEmp_addr() {
        return emp_addr;
    }

    public void setEmp_addr(String emp_addr) {
        this.emp_addr = emp_addr == null ? null : emp_addr.trim();
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email == null ? null : emp_email.trim();
    }
}