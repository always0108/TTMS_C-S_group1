package TTMS_Server.model;

import java.math.BigDecimal;

public class EmployeeSale {

    String emp_name;
    Integer emp_id;
    BigDecimal saleAmount;

    public EmployeeSale() {
    }

    public EmployeeSale(String emp_name,Integer emp_id, BigDecimal saleAmount) {

        this.emp_name = emp_name;
        this.emp_id = emp_id;
        this.saleAmount = saleAmount;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Integer getEmp_id() { return  emp_id; }

    public void setEmp_id(Integer  emp_id) { this.emp_id = emp_id; }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }
}
