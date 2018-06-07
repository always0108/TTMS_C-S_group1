package UI.Schedule;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Schedule;
import node.FunButton;

public class ScheduleDetail extends VBox {

    public ScheduleDetail() {}

    public ScheduleDetail(Schedule sched){
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(80,20,20,20));

        GridPane table = new GridPane();
        table.setHgap(20);
        table.setVgap(10);
        table.setAlignment(Pos.CENTER);
        table.setPadding(new Insets(20,20,20,20));

        Label sched_id = new Label("演出计划ID:");
        Label studio_id = new Label("演出厅ID:");
        Label play_id = new Label("剧目ID:");
            //修改
        Label sched_date = new Label("演出日期:");
        Label sched_time = new Label("演出时间:");
        Label schedule_ticket_price = new Label("票价:");

        Label sched_id_value = new Label(sched.getSched_ticket_price().toString());
        Label studio_id_value = new Label(sched.getStudio_id().toString());
        Label play_id_value = new Label(sched.getPlay_id().toString());
            //修改
        //Label sched_date_value = new Label(sched.getSched_time().toString());
        Label test1 = new Label("test");
        //Label sched_time_value = new Label();
        Label test2 = new Label("test");
        Label sched_ticket_price_value = new Label(sched.getSched_ticket_price().toString());
            //修改
        table.addColumn(0,sched_id,studio_id,play_id,sched_date,sched_time,schedule_ticket_price);
        table.addColumn(1,sched_id_value,studio_id_value,play_id_value,test1,test2,sched_ticket_price_value);

        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(20,20,20,20));
        footer.setSpacing(40);
        FunButton btRet = new FunButton("返回");
        footer.getChildren().addAll(btRet);

            //点击修改按钮
        btRet.setOnAction(e->{
            new ScheduleList();
        });

        this.getChildren().addAll(table,footer);
    }


}

