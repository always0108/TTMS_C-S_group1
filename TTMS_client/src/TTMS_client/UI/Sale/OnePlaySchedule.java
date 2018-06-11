package UI.Sale;

import Service.DataCollection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Play;
import model.Schedule;
import node.FunButton;
import util.DateFormat;

import java.sql.Timestamp;
import java.util.Calendar;

public class OnePlaySchedule extends HBox {

    public OnePlaySchedule(Schedule schedule, Play play){
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(10,10,10,10));
        this.setSpacing(30);

        VBox time = new VBox();
        time.setPadding(new Insets(5, 20, 5, 20));
        time.setAlignment(Pos.CENTER);
        Timestamp start = schedule.getSched_time();
        Calendar date = Calendar.getInstance();
        date.setTime(start);
        Label starttime = new Label(DateFormat.dateToStr(date.getTime()).substring(11,16));
        starttime.setStyle("-fx-font-size: 24;-fx-font-style: italic");
        date.add(Calendar.MINUTE,play.getPlay_length());
        Label endtime = new Label(DateFormat.dateToStr(date.getTime()).substring(11,16)+"散场");
        time.getChildren().addAll(starttime, endtime);

        VBox type = new VBox();
        type.setAlignment(Pos.CENTER);
        type.setPadding(new Insets(5, 20, 5, 20));
        Label viewtype = new Label(DataCollection.playTypeTable.get(play.getPlay_type_id()));
        Label languagtype = new Label(DataCollection.playlangTable.get(play.getPlay_lang_id()));
        type.getChildren().addAll(viewtype, languagtype);

        VBox price = new VBox();
        price.setAlignment(Pos.CENTER);
        price.setPadding(new Insets(5, 30, 5, 30));
        Label ticketprice = new Label(String.valueOf(schedule.getSched_ticket_price()));
        ticketprice.setStyle("-fx-font-size: 30");
        price.getChildren().add(ticketprice);

        VBox choose = new VBox();
        choose.setAlignment(Pos.CENTER);
        choose.setPadding(new Insets(5, 20, 5, 20));
        FunButton check = new FunButton("选座");
        choose.getChildren().add(check);
        this.getChildren().addAll(time,type,price,choose);
    }
}
