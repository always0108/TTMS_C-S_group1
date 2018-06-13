package UI.Ticket;

import javafx.scene.control.Button;
import model.SeatAndTicket;

public class OneTicket extends Button {

    private SeatAndTicket seatAndTicket;

    public OneTicket() {super();}

    public OneTicket(SeatAndTicket seatAndTicket,Integer flag){
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
            //flag为1表示购票
            if(flag == 1){
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
            }else {
                //选择要退的座位
                if (this.seatAndTicket.getTicket_status() == -1) {
                    this.setStyle("-fx-background-image: url('/image/seat/Optional.png'); -fx-background-color: none");
                    this.seatAndTicket.setTicket_status(Short.parseShort("0"));
                    TicketTable.tickets.add(this.seatAndTicket);
                }else if (this.seatAndTicket.getTicket_status() == 1) {
                    this.setStyle("-fx-background-image: url('/image/seat/Selected.png'); -fx-background-color: none");
                    this.seatAndTicket.setTicket_status(Short.parseShort("-1"));
                    TicketTable.tickets.remove(this.seatAndTicket);
                }
            }

        });
    }

    public SeatAndTicket getSeatAndTicket() {
        return seatAndTicket;
    }
}
