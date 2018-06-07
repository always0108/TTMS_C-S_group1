package UI.Schedule;

import UI.Layout.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import model.Schedule;

import java.util.List;

public class ScheduleTable extends GridPane {

    public ScheduleTable(List<Schedule> schedules){
        this.setPadding(new Insets(20,20,20,20));
        this.setHgap(30);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER);

        if(schedules.size() == 0 || schedules == null){
            Label note = new Label("没有符合条件的结果");
            note.setStyle("-fx-font-size: 20px");
            this.add(note,0,0);
        }else {
            Label sched_id = new Label("ID");
            Label studio_id = new Label("演播厅ID");
            Label play_id = new Label("剧目ID");

            //新加一个sched_date 演出日期 sche_time 演出时间

            Label sched_date = new Label("演出日期");
            Label sched_time = new Label("演出时间");
            Label sched_ticket_price = new Label("票价");
            this.addRow(0, sched_id,studio_id,play_id,sched_date,sched_time,sched_ticket_price);

            for (int i = 0; i < schedules.size(); i++) {
                Schedule schedule = schedules.get(i);
                Label schedule_sched_id = new Label(schedule.getSched_id().toString());
                Label schedule_studio_id = new Label(schedule.getStudio_id().toString());
                Label schedule_play_id = new Label(schedule.getPlay_id().toString());

                //更改
                // Label schedule_sched_date = new Label(schedule.getSched_time().toString());
                //Label schedule_sched_time = new Label();
                Label schedule_ticket_price = new Label(schedule.getSched_ticket_price().toString());

                Button btDetail = new Button("详请");
                Button btModify = new Button("修改");
                Button btDelete = new Button("删除");
                btDetail.setOnAction(e->{
                    HomeUI.setCenter(new ScheduleDetail(schedule));
                });

                btModify.setOnAction(e->{
                    HomeUI.setCenter(new ScheduleModify(schedule));
                });

                btDelete.setOnAction(e->{
                    HomeUI.setCenter(new ScheduleDelete(schedule));
                });
                //需要添加schedule_sched_date & schedule_sched_time
                this.addRow(i+1,schedule_sched_id,schedule_studio_id,schedule_play_id,schedule_ticket_price,
                        btDetail,btModify,btDelete);
            }
        }
    }
}
