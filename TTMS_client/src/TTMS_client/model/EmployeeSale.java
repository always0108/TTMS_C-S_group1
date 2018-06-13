package model;

import java.math.BigDecimal;

public class EmployeeSale {

    String emp_name;
    BigDecimal saleAmount;

    public EmployeeSale() {
    }

    public EmployeeSale(String emp_name, BigDecimal saleAmount) {

        this.emp_name = emp_name;
        this.saleAmount = saleAmount;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }
}
