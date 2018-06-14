package UI.Schedule;

import UI.Layout.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import model.Schedule;
import node.TableButton;

import java.util.List;

public class ScheduleTable extends GridPane {

    public ScheduleTable(List<Schedule> schedules){
        this.setPadding(new Insets(20,20,20,20));
        this.setHgap(30);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER);

        if(schedules == null || schedules.size() == 0){
            Label note = new Label("没有安排演出计划");
            note.setStyle("-fx-font-size: 20px");
            this.add(note,0,0);
        }else {
            Label sched_id = new Label("ID");
            Label studio_id = new Label("演播厅ID");
            Label play_id = new Label("剧目ID");
            Label sched_time = new Label("演出时间");
            Label sched_ticket_price = new Label("票价");
            this.addRow(0, sched_id,studio_id,play_id,sched_time,sched_ticket_price);

            for (int i = 0; i < schedules.size(); i++) {
                Schedule schedule = schedules.get(i);
                Label schedule_sched_id = new Label(schedule.getSched_id().toString());
                Label schedule_studio_id = new Label(schedule.getStudio_id().toString());
                Label schedule_play_id = new Label(schedule.getPlay_id().toString());
                Label schedule_time = new Label(schedule.getSched_time().toString());
                Label schedule_ticket_price = new Label(schedule.getSched_ticket_price().toString());

                TableButton btModify = new TableButton("修改");
                TableButton btDelete = new TableButton("删除");

                btModify.setOnAction(e->{
                    HomeUI.setCenter(new ScheduleModify(schedule));
                });

                btDelete.setOnAction(e->{
                    HomeUI.setCenter(new ScheduleDelete(schedule));
                });

                this.addRow(i+1,schedule_sched_id,schedule_studio_id,schedule_play_id,
                        schedule_time,schedule_ticket_price, btModify,btDelete);
            }
        }
    }
}
