package UI.Schedule;

import Service.StudioSrv;
import UI.Play.PlayDetail;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Play;
import model.Studio;
import node.FunButton;
import node.MessageBar;
import util.DateFormat;
import util.Httpclient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ScheduleAdd extends GridPane{
    private List<Studio> studios = new StudioSrv().getAllStudio();
    private Map<String,Integer> studioComboBox = new HashMap<>();

    public ScheduleAdd() {}

    public ScheduleAdd(Integer play_id) {
            this.setHgap(20);
            this.setVgap(20);
            this.setPadding(new Insets(25,25,25,25));

            Text playadd = new Text("添加演出计划");
            playadd.setFont(Font.font(30));
            this.add(playadd,0,0,2,1);

            Label studio_id = new Label("演出厅：");
            ComboBox<String> sched_studio_id = new ComboBox<>();
            sched_studio_id.setValue("请选择");
            for (Studio studio:studios) {
                studioComboBox.put(studio.getStudio_name(),studio.getStudio_id());
                sched_studio_id.getItems().add(studio.getStudio_name());
            }

//            Label date = new Label("演出日期：");
//            DatePicker sched_date = new DatePicker();
//
//            Label time = new Label("演出时间：");
//            TextField sched_time= new TextField();

            Label time = new Label("演出时间：");
            TextField sched_time= new TextField();
            Label note = new Label("(yyyy-MM-dd HH:mm:ss)");

            Label ticket_price = new Label("票价：");
            TextField sched_ticket_price = new TextField();

            FunButton Confirm = new FunButton("确认");
            FunButton Return = new FunButton("返回");

            this.addColumn(0,studio_id,time,ticket_price,Confirm);
            this.addColumn(1,sched_studio_id,sched_time,sched_ticket_price,Return);
            this.add(note,2,2);
            Confirm.setOnAction(e->{
                if(!sched_studio_id.getValue().equals("请选择") &&
                        !sched_time.getText().isEmpty() && !sched_ticket_price.getText().isEmpty()){
                    Confirm.setDisable(true);
                    //创建后台获取数据的线程
                    Task<JSONObject> task = new Task<JSONObject>() {
                        @Override
                        protected JSONObject call() throws Exception {
                            String url = "/schedule/add";

                            Map<String, Object> schedule = new HashMap<>();

                            schedule.put("studio_id",studioComboBox.get(sched_studio_id.getValue()));
                            schedule.put("play_id",play_id);
                            schedule.put("sched_time",DateFormat.stringToTimestamp(sched_time.getText()));
                            schedule.put("sched_ticket_price",new BigDecimal(sched_ticket_price.getText()));

                            String res = Httpclient.post(url, schedule);
                            return JSON.parseObject(res);
                        }

                        @Override
                        protected void running() {

                        }

                        @Override
                        protected void succeeded() {
                            super.succeeded();
                            JSONObject jsonObject = getValue();
                            MessageBar.showMessageBar(jsonObject.get("content").toString());
                            new ScheduleList(play_id);
                            Confirm.setDisable(false);
                            updateMessage("Done!");
                        }

                        @Override
                        protected void cancelled() {
                            super.cancelled();
                            updateMessage("Cancelled!");
                        }

                        @Override
                        protected void failed() {
                            super.failed();
                            updateMessage("Failed!");
                        }
                    };
                    Thread thread = new Thread(task);
                    thread.start();
                }
                MessageBar.showMessageBar("请将信息填写完整");
            });

            Return.setOnAction(e->{
                new ScheduleList(play_id);
            });
        }
}

