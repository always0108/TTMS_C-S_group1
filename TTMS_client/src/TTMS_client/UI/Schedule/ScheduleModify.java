package Schedule;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ScheduleModify extends GridPane {
    public ScheduleModify(Schedule schedule){
        this.setHgap(20);
        this.setVgap(20);
        this.setPadding(new Insets(25,25,25,25));

        Text playadd = new Text("修改演出计划");
        //playadd.getStyleClass().add("funText");
        playadd.setFont(Font.font(30));
        this.add(playadd,0,0);
        Label id = new Label("演出计划编号：");
        Label sched_id=new Label(schedule.getSched_id().toString());
        //play_id.getStyleClass().add("textField");
        this.add(id,0,1);
        this.add(sched_id,1,1);


        Label studio_id = new Label("演出厅编号：");
        Label type_id = new Label("类型编号：");
        ComboBox<String> sched_studio_id = new ComboBox<>();
        for (Map.Entry<String,Integer> entry:DataCollection.playTypeComboBox.entrySet()){
            if (schedule.getPlay_type_id() == entry.getValue()) {
                sched_studio_id.setValue(entry.getKey());
            }
            sched_studio_id.getItems().add(entry.getKey());
        }

        this.add(studio_id,0,2);
        this.add(sched_studio_id,1,2);

        Label play_id = new Label("剧目编号：");
        TextField sched_play_id= new TextField(schedule.getPlay_id().toString());
        this.add(play_id,0,3);
        this.add(sched_play_id,1,3);

        Label date = new Label("演出日期：");
        //DatePicker sched_date = new DatePicker();
       // date.getStyleClass().add("textField");
        this.add(date,0,4);
        this.add(sched_date,1,4);
        Label time = new Label("演出时间：");
        TextField sched_time= new TextField(schedule.getSched_time().toString());
        this.add(time,0,5);
        this.add(sched_time,1,5);



        Label ticket_price = new Label("票价：");
        TextField sched_ticket_price = new TextField(schedule.getSched_ticket_price().toString());
        this.add(ticket_price,0,6);
        this.add(sched_ticket_price,1,6);



        Button Confirm = new Button("确认");
        Confirm.setStyle("-fx-background-color: #6C9BBF");
        Button Return = new Button("返回");
        Return.setStyle("-fx-background-color: #6C9BBF");
        HBox bottom_button = new HBox(10);
        bottom_button.setPadding(new Insets(25,25,25,25 ));
        bottom_button.setAlignment(Pos.CENTER_LEFT);
        bottom_button.getChildren().addAll(Confirm,Return);
        this.add(Confirm,0,7);
        this.add(Return,1,7);

        Confirm.setOnAction(e->{
            Confirm.setDisable(true);
            //创建后台获取数据的线程
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/schedule/update";
                    String str;

                    Map<String, Object> schedule = new HashMap<>();

                    schedule.put("studio_id",DataCollection.playTypeComboBox.get(sched_studio_id.getValue()));
                    schedule.put("play_id",play_id.getText());
                    // play.put("sched_date",sched_date.getText());
                    schedule.put("sched_time",Integer.valueOf(sched_time.getText()));
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
                    if (jsonObject.get("flag").equals(true)) {
                        MessageBar.showMessageBar("修改成功！");
                        new PlayList();
                    } else {
                        MessageBar.showMessageBar(jsonObject.get("content").toString());
                    }
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
        });

        Return.setOnAction(e->{
            new ScheduleList();
        });
    }

    }

