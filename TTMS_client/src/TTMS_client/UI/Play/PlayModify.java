package UI.Play;
import Service.DataCollection;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Play;
import node.FunButton;
import util.ImageByte;

import java.io.File;
import java.util.Map;


public class PlayModify extends GridPane {

    public PlayModify(Play play){
        this.setHgap(20);
        this.setVgap(20);
        this.setPadding(new Insets(25,25,25,25));

        Text playadd = new Text("修改剧目");
        playadd.setFont(Font.font(30));
        this.add(playadd,0,0);
        playadd.setTextAlignment(TextAlignment.LEFT);

        Label id = new Label("剧目编号：");
        Label play_id = new Label(play.getPlay_id().toString());
        this.add(id,0,1);
        this.add(play_id,1,1,3,1);

        Label type_id = new Label("类型编号：");
        ComboBox<String> play_type_id = new ComboBox<>();
        for (Map.Entry<String,Integer> entry:DataCollection.playTypeComboBox.entrySet()){
            if (play.getPlay_type_id() == entry.getValue()) {
                play_type_id.setValue(entry.getKey());
            }
            play_type_id.getItems().add(entry.getKey());
        }
        this.add(type_id,0,2);
        this.add(play_type_id,1,2);

        Label lang_id = new Label("语言编号：");
        ComboBox<String> play_lang_id = new ComboBox<>();
        for (Map.Entry<String,Integer> entry:DataCollection.playLangComboBox.entrySet()){
            if(play.getPlay_lang_id() == entry.getValue()){
                play_lang_id.setValue(entry.getKey());
            }
            play_lang_id.getItems().add(entry.getKey());
        }
        this.add(lang_id,2,2);
        this.add(play_lang_id,3,2);

        Label name = new Label("剧目名称：");
        TextField play_name = new TextField(play.getPlay_name());
        this.add(name,0,3);
        this.add(play_name,1,3,3,1);

        Label image = new Label("剧目封面：");
        TextField play_image = new TextField();
        this.add(image,0,4);
        this.add(play_image,1,4);
        Button buttonLoad = new Button("选择文件");

        buttonLoad.setOnAction(e->{
            play_image.setText(ImageByte.getFilePath());
        });
        this.add(buttonLoad,2,4);

        Label introduction = new Label("剧目简介");
        TextArea play_introduction = new TextArea(play.getPlay_introduction());
        play_introduction.setPrefColumnCount(20);
        play_introduction.setPrefRowCount(5);
        play_introduction.setWrapText(true);
        this.add(introduction,0,5);
        this.add(play_introduction,1,5,3,1);

        Label length = new Label("剧目时长：");
        TextField play_length = new TextField(play.getPlay_length().toString());
        this.add(length,0,6);
        this.add(play_length,1,6,3,1);

        Label price = new Label("剧目票价：");
        TextField play_price = new TextField(play.getPlay_ticket_price().toString());
        this.add(price,0,7);
        this.add(play_price,1,7,3,1);


        FunButton Confirm = new FunButton("确认");
        FunButton Return = new FunButton("返回");
        HBox bottom_button = new HBox(100);
        bottom_button.setPadding(new Insets(10,100,25,50));
        bottom_button.setAlignment(Pos.CENTER_LEFT);
        bottom_button.getChildren().addAll(Confirm,Return);
        this.add(bottom_button,1,9,4,1);

        Confirm.setOnAction(e->{

        });

        Return.setOnAction(e->{
            new PlayList();
        });
    }
}