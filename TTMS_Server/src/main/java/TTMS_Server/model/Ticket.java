package TTMS_Server.model;

import java.math.BigDecimal;
import java.util.Date;

public class Ticket {
    private Long ticket_id;

    private Integer seat_id;

    private Integer sched_id;

    private BigDecimal ticket_price;

    private Short ticket_status;

    private Date ticket_locked_time;

    public Ticket(){}

    public Ticket(Long ticket_id,Integer seat_id,Integer sched_id,BigDecimal ticket_price,Short ticket_status,Date ticket_locked_time){
        this.ticket_id = ticket_id;
        this.seat_id = seat_id;
        this.sched_id = sched_id;
        this.ticket_price = ticket_price;
        this.ticket_status = ticket_status;
        this.ticket_locked_time = ticket_locked_time;
    }

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(Integer seat_id) {
        this.seat_id = seat_id;
    }

    public Integer getSched_id() {
        return sched_id;
    }

    public void setSched_id(Integer sched_id) {
        this.sched_id = sched_id;
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

    public Date getTicket_locked_time() {
        return ticket_locked_time;
    }

    public void setTicket_locked_time(Date ticket_locked_time) {
        this.ticket_locked_time = ticket_locked_time;
    }
}