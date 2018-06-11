package model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Schedule {
    private Integer sched_id;

    private Integer studio_id;

    private Integer play_id;

    private Timestamp sched_time;

    private BigDecimal sched_ticket_price;

    public Schedule(){}

    public Schedule(Integer studio_id,Integer play_id,Timestamp sched_time,BigDecimal sched_ticket_price){
        this.studio_id = studio_id;
        this.play_id = play_id;
        this.sched_time = sched_time;
        this.sched_ticket_price = sched_ticket_price;
    }

    public Integer getSched_id() {
        return sched_id;
    }

    public void setSched_id(Integer sched_id) {
        this.sched_id = sched_id;
    }

    public Integer getStudio_id() {
        return studio_id;
    }

    public void setStudio_id(Integer studio_id) {
        this.studio_id = studio_id;
    }

    public Integer getPlay_id() {
        return play_id;
    }

    public void setPlay_id(Integer play_id) {
        this.play_id = play_id;
    }

    public Timestamp getSched_time() {
        return sched_time;
    }

    public void setSched_time(Timestamp sched_time) {
        this.sched_time = sched_time;
    }

    public BigDecimal getSched_ticket_price() {
        return sched_ticket_price;
    }

    public void setSched_ticket_price(BigDecimal sched_ticket_price) {
        this.sched_ticket_price = sched_ticket_price;
    }
}