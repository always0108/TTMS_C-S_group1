package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


public class PlayTable extends GridPane {

    public PlayTable(List<Play> plays){
        this.setPadding(new Insets(20,20,20,20));
        this.setHgap(30);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER);

        if(plays.size() == 0 || plays == null){
            Label note = new Label("没有符合条件的结果");
            note.setStyle("-fx-font-size: 20px");
            this.add(note,0,0);
        }else {
            Label id = new Label("ID");
            Label type_id = new Label("影片类型");
            Label lang_id= new Label("语言类型");
            Label name = new Label("影片名称");
            Label length = new Label("影片时长");
            Label ticket_price = new Label("影片票价");
            this.addRow(0,id,name,length,type_id,lang_id,ticket_price);

            for (int i = 0; i < plays.size(); i++) {
                Play play = plays.get(i);
                Label play_id = new Label(play.getPlay_id().toString());
                Label play_type_id = new Label(play.getPlay_type_id().toString());
                Label play_lang_id = new Label(play.getPlay_lang_id().toString());
                Label play_name = new Label(play.getPlay_name());
                Label play_length = new Label(play.getPlay_length());
                Label play_ticket_price = new Label(play.getPlay_ticket_price());

                Button btDetail = new Button("详请");
                Button btModify = new Button("修改");
                Button btDelete = new Button("删除");
                btDetail.setOnAction(e->{
                    HomeUI.setCenter(new PlayDetail(play));
                });

                btModify.setOnAction(e->{
                    HomeUI.setCenter(new PlayModify(play));
                });

                btDelete.setOnAction(e->{
                    HomeUI.setCenter(new PlayDelete(play));
                });
                this.addRow(i+1,play_id,play_name,play_length,play_type_id,play_lang_id,play_ticket_price,
                        btDetail,btModify,btDelete);
            }
        }
    }

    public PlayTable(){}
}
