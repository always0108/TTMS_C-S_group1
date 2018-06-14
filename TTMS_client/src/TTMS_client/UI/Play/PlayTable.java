package UI.Play;

import UI.Layout.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Play;
import node.TableButton;

import java.util.List;

public class PlayTable extends GridPane {

    public PlayTable(){}

    public PlayTable(List<Play> plays){
        this.setPadding(new Insets(20,20,20,20));
        this.setHgap(30);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER);

        if(plays == null || plays.size() == 0){
            Label note = new Label("没有符合条件的结果");
            note.setStyle("-fx-font-size: 20px");
            this.add(note,0,0);
        }else {
            Label id = new Label("ID");
            Label name = new Label("影片名称");
            Label length = new Label("影片时长");
            Label ticket_price = new Label("影片票价");
            this.addRow(0,id,name,length,ticket_price);

            for (int i = 0; i < plays.size(); i++) {
                Play play = plays.get(i);
                Label play_id = new Label(play.getPlay_id().toString());
                Label play_name = new Label(play.getPlay_name());
                Label play_length = new Label(play.getPlay_length().toString());
                Label play_ticket_price = new Label(play.getPlay_ticket_price().toString());

                TableButton btDetail = new TableButton("详请");
                TableButton btModify = new TableButton("修改");
                TableButton btDelete = new TableButton("删除");
                btDetail.setOnAction(e->{
                    HomeUI.setCenter(new PlayDetail(play));
                });

                btModify.setOnAction(e->{
                    HomeUI.setCenter(new PlayModify(play));
                });

                btDelete.setOnAction(e->{
                    HomeUI.setCenter(new PlayDelete(play));
                });
                this.addRow(i+1,play_id,play_name,play_length,play_ticket_price,
                        btDetail,btModify,btDelete);
            }
        }
    }
}
