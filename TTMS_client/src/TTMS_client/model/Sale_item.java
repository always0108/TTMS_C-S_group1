package model;

import java.math.BigDecimal;

public class Sale_item {
    private Long sale_item_id;

    private Long ticket_id;

    private Long sale_ID;

    private BigDecimal sale_item_price;

    public Sale_item(){}

    public Sale_item(Long ticket_id,Long sale_ID,BigDecimal sale_item_price){
        this.ticket_id = ticket_id;
        this.sale_ID = sale_ID;
        this.sale_item_price =sale_item_price;
    }

    public Long getSale_item_id() {
        return sale_item_id;
    }

    public void setSale_item_id(Long sale_item_id) {
        this.sale_item_id = sale_item_id;
    }

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Long getSale_ID() {
        return sale_ID;
    }

    public void setSale_ID(Long sale_ID) {
        this.sale_ID = sale_ID;
    }

    public BigDecimal getSale_item_price() {
        return sale_item_price;
    }

    public void setSale_item_price(BigDecimal sale_item_price) {
        this.sale_item_price = sale_item_price;
    }
}