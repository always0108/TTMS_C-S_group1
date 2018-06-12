package UI.Ticket;

import javafx.scene.control.Button;
import model.SeatAndTicket;

public class OneTicket extends Button {

    private SeatAndTicket seatAndTicket;

    public OneTicket() {super();}

    public OneTicket(SeatAndTicket seatAndTicket){
        this.seatAndTicket = seatAndTicket;
        this.setPrefWidth(48);
        this.setPrefHeight(48);
        this.setStyle("-fx-border-style: none;");
        if (this.seatAndTicket.getTicket_status() == 0) {          //可售卖
            this.setStyle("-fx-background-image: url('/image/seat/Optional.png'); -fx-background-color: none");
        } else if (this.seatAndTicket.getTicket_status() == -1) {   //已卖出
            this.setStyle("-fx-background-image: url('/image/seat/Selected.png'); -fx-background-color: none");
        } else if (this.seatAndTicket.getTicket_status() == 1) {   //锁定
            this.setStyle("-fx-background-image: url('/image/seat/checked.png'); -fx-background-color: none");
        }

        this.setOnAction(e -> {
            if (this.seatAndTicket.getTicket_status() == 0) {
                this.setStyle("-fx-background-image: url('/image/seat/checked.png');-fx-background-color: none");
                this.seatAndTicket.setTicket_status(Short.parseShort("1"));//锁定
                TicketTable.tickets.add(this.seatAndTicket);
            }else if (this.seatAndTicket.getTicket_status() == 1) {
                this.setStyle("-fx-background-image: url('/image/seat/Optional.png'); -fx-background-color: none");
                this.seatAndTicket.setTicket_status(Short.parseShort("0"));//取消锁定
                TicketTable.tickets.remove(this.seatAndTicket);
            } else if (this.seatAndTicket.getTicket_status() == -1) {
                                                   //售出的票不能点击
            }
        });
    }

    public SeatAndTicket getSeatAndTicket() {
        return seatAndTicket;
    }
}
