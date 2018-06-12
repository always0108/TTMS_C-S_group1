package model;

import java.math.BigDecimal;

public class SeatAndTicket {

    private Long ticket_id;
    private BigDecimal ticket_price;
    private Short ticket_status;
    private Integer seat_row;
    private Integer seat_column;

    public SeatAndTicket() {
    }

    public SeatAndTicket(Long ticket_id, BigDecimal ticket_price, Short ticket_status, Integer seat_row, Integer seat_column) {
        this.ticket_id = ticket_id;
        this.ticket_price = ticket_price;
        this.ticket_status = ticket_status;
        this.seat_row = seat_row;
        this.seat_column = seat_column;
    }

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public BigDecimal getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(BigDecimal ticket_price) {
        this.ticket_price = ticket_price;
    }

    public Short getTicket_status() {
        return ticket_status;
    }

    public void setTicket_status(Short ticket_status) {
        this.ticket_status = ticket_status;
    }

    public Integer getSeat_row() {
        return seat_row;
    }

    public void setSeat_row(Integer seat_row) {
        this.seat_row = seat_row;
    }

    public Integer getSeat_column() {
        return seat_column;
    }

    public void setSeat_column(Integer seat_column) {
        this.seat_column = seat_column;
    }
}
