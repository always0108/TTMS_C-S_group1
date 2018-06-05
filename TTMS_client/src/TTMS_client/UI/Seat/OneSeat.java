package UI.Seat;

import javafx.scene.control.Button;
import model.Seat;

public class OneSeat extends Button {
    /*status取值含义:
        0：空位，没有安排座位
        1：完好的座位，可以使用
        -1：座位损坏，不能使用'
    */

    private  Integer row;
    private  Integer col;
    private  Integer status;
    private  Integer seat_id;

    public OneSeat() {super();}

    public OneSeat(Seat seat){
        this.row = seat.getSeat_row();
        this.col = seat.getSeat_column();
        this.status = seat.getSeat_status().intValue();
        this.seat_id = seat.getSeat_id();
        this.setPrefWidth(48);
        this.setPrefHeight(48);
        this.setStyle("-fx-border-style: none;");
        if (seat.getSeat_status() == 1) {          //完好的座位
            this.setStyle("-fx-background-image: url('/image/seat/Optional.png'); -fx-background-color: none");
        } else if (seat.getSeat_status() == -1) {   //损坏
            this.setStyle("-fx-background-image: url('/image/seat/NotOptional.png'); -fx-background-color: none");
        } else if (seat.getSeat_status() == 0) {   //未设置
            this.setStyle("-fx-background-image: none;");
        }

        this.setOnAction(e -> {
            if (getStatus() == 0) {
                this.setStyle("-fx-background-image: url('/image/seat/Optional.png');-fx-background-color: none");
                setStatus(1);                      //好的
            }else if (getStatus() == 1) {
                this.setStyle("-fx-background-image: url('/image/seat/NotOptional.png'); -fx-background-color: none");
                setStatus(-1);                     //坏的
            } else if (getStatus() == -1) {
                this.setStyle("-fx-background-image: none; -fx-background-color: none");
                setStatus(0);                      //空的
            }
            SeatTable.seats.put(seat_id,status);
        });
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(Integer seat_id) {
        this.seat_id = seat_id;
    }
}
