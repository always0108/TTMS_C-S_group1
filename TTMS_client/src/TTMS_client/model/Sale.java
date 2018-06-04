package model;

import java.math.BigDecimal;
import java.util.Date;

public class Sale {
    private Long sale_ID;

    private Integer emp_id;

    private Date sale_time;

    private BigDecimal sale_payment;

    private BigDecimal sale_change;

    private Short sale_type;

    private Short sale_status;

    public Sale(){}

    public Sale(Integer emp_id,Date sale_time,BigDecimal sale_payment,BigDecimal sale_change,Short sale_type,Short sale_status){
        this.emp_id = emp_id;
        this.sale_time = sale_time;
        this.sale_payment = sale_payment;
        this.sale_change = sale_change;
        this.sale_type = sale_type;
        this.sale_status =sale_status;
    }
    public Long getSale_ID() {
        return sale_ID;
    }

    public void setSale_ID(Long sale_ID) {
        this.sale_ID = sale_ID;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public Date getSale_time() {
        return sale_time;
    }

    public void setSale_time(Date sale_time) {
        this.sale_time = sale_time;
    }

    public BigDecimal getSale_payment() {
        return sale_payment;
    }

    public void setSale_payment(BigDecimal sale_payment) {
        this.sale_payment = sale_payment;
    }

    public BigDecimal getSale_change() {
        return sale_change;
    }

    public void setSale_change(BigDecimal sale_change) {
        this.sale_change = sale_change;
    }

    public Short getSale_type() {
        return sale_type;
    }

    public void setSale_type(Short sale_type) {
        this.sale_type = sale_type;
    }

    public Short getSale_status() {
        return sale_status;
    }

    public void setSale_status(Short sale_status) {
        this.sale_status = sale_status;
    }
}