package UI.Play;

import UI.Layout.HomeUI;
import UI.Schedule.ScheduleAdd;
import UI.Schedule.ScheduleList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import model.Play;
import model.Studio;
import node.FunButton;
import util.ImageByte;

public class PlayDetail extends GridPane {

    public PlayDetail(Play play){
        this.setAlignment(Pos.TOP_CENTER);
        this.setHgap(30);
        this.setVgap(10);
        this.setPadding(new Insets(60,20,20,20));

        Label play_id = new Label("剧目编号：");
        Label play_type_id = new Label("剧目类型：");
        Label play_lang_id = new Label("剧目语言：");
        Label play_name = new Label("剧目名称：");
        Label play_length = new Label("放映时间：");
        Label play_ticket_price = new Label("票价：");
        Label play_status = new Label("剧目状态：");
        Label play_introduction = new Label("剧目简介：");
        Label idValue = new Label(play.getPlay_id().toString());
        Label type_idValue = new Label(play.getPlay_type_id().toString());
        Label lang_idValue = new Label(play.getPlay_lang_id().toString());
        Label nameValue = new Label(play.getPlay_name());
        Label lengthValue = new Label(play.getPlay_length().toString());
        Label ticket_priceValue = new Label(play.getPlay_ticket_price().toString());
        Label statusValue= new Label(play.getPlay_status().toString());
        TextArea introductionValue = new TextArea(play.getPlay_introduction());
        introductionValue.setWrapText(true);
        introductionValue.setEditable(false);
        introductionValue.setStyle("-fx-max-width: 400px;-fx-max-height:200px");

        ImageView play_imageview = new ImageView(ImageByte.bytesToImage(play.getPlay_image()));
        play_imageview.setFitHeight(240);
        play_imageview.setFitWidth(180);
        this.add(play_imageview,0,0,1,6);
        this.addColumn(1,play_id,play_type_id,play_lang_id,play_name,play_length,play_ticket_price,play_status,play_introduction);
        this.addColumn(2,idValue,type_idValue,lang_idValue,nameValue,lengthValue,ticket_priceValue,statusValue,introductionValue);

        FunButton btModify = new FunButton("管理演出计划");
        FunButton btRet = new FunButton("返回");
        HBox hBox=new HBox(160);
        hBox.setPadding(new Insets(25,80,25,25));
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().addAll(btModify,btRet);
        this.add(hBox,0,9,3,1);

        btModify.setOnAction(e->{
            new ScheduleList(play.getPlay_id());
        });

        btRet.setOnAction(e->{
            new PlayList();
        });
    }


}