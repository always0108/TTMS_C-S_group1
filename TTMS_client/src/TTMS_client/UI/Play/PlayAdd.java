package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PlayAdd extends GridPane {

    public PlayAdd (){
        this.setHgap(20);
        this.setVgap(20);
        this.setPadding(new Insets(25,25,25,25));

        Text playadd = new Text("添加剧目");
        playadd.setFont(Font.font(30));
        this.add(playadd,0,0,3,1);
        playadd.setTextAlignment(TextAlignment.LEFT);

        Label id = new Label("剧目编号：");
        TextField play_id = new TextField();
        this.add(id,0,1);
        this.add(play_id,1,1,3,1);

        Label type_id = new Label("类型编号：");
        ComboBox<String> play_type_id = new ComboBox<>();
        play_type_id.getItems().addAll("无","惊悚片","爱情片","科幻片");
        play_type_id.setStyle("-fx-border-color: lightgray");
        play_type_id.setValue("无");
        this.add(type_id,0,2);
        this.add(play_type_id,1,2);

        Label lang_id = new Label("语言编号：");
        ComboBox<String> play_lang_id = new ComboBox<>();
        play_lang_id.getItems().addAll("原音","中文","英文");
        play_lang_id.setStyle("-fx-border-color: lightgray");
        play_lang_id.setValue("原音");
        this.add(lang_id,2,2);
        this.add(play_lang_id,3,2);

        Label name = new Label("剧目名称：");
        TextField play_name = new TextField();
        play_name.getStyleClass().add("textField");
        this.add(name,0,3);
        this.add(play_name,1,3,3,1);

        Label image = new Label("剧目封面：");
        this.add(image,0,4);
        TextField play_image_textfield = new TextField();
        this.add(play_image_textfield,1,4);
        Stage play_image_stage = null;
        Button select_button = new Button("选择文件");
        select_button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                FileChooser play_iamge = new FileChooser();
                play_iamge.setTitle("选择文件");
                play_iamge.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.*"),
                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                        new FileChooser.ExtensionFilter("PNG", "*.png")
                );

                File file = play_iamge.showOpenDialog(play_image_stage);
                System.out.println(file);
                play_image_textfield.setText(file.getPath());
            }
        });
        this.add(select_button,2,4);

        Label introduction = new Label("剧目简介:");
        TextArea play_introduction = new TextArea("这是一条剧目简介......");
        play_introduction.setPrefColumnCount(20);
        play_introduction.setPrefRowCount(5);
        play_introduction.setWrapText(true);
        play_introduction.setStyle("-fx-text-fill: lightgray;-fx-font-style: Times;-fx-font-size: 16");
        this.add(introduction,0,5);
        this.add(play_introduction,1,5,3,1);


        Label length = new Label("剧目时长：");
        TextField play_length = new TextField();
        this.add(length,0,6);
        this.add(play_length,1,6,3,1);

        Label price = new Label("剧目票价：");
        TextField play_price = new TextField();
        this.add(price,0,7);
        this.add(play_price,1,7,3,1);

        Label status = new Label("剧目状态:");
        RadioButton on = new RadioButton("正在热映");
        RadioButton off= new RadioButton("已下映");
        RadioButton will = new RadioButton("即将上映");
        ToggleGroup group = new ToggleGroup();
        on.setToggleGroup(group);
        off.setToggleGroup(group);
        will.setToggleGroup(group);
        HBox status_butoon = new HBox(25);
        status_butoon.setPadding(new Insets(25,25,25,25));
        status_butoon.setAlignment(Pos.CENTER);
        status_butoon.getChildren().addAll(on,off,will);
        this.add(status_butoon,1,8,3, 1);
        this.add(status,0,8);


        Button Confirm = new Button("确认");
        Confirm.setStyle("-fx-background-color: #6C9BBF");
        Button Return = new Button("返回");
        Return.setStyle("-fx-background-color: #6C9BBF");
        HBox bottom_button = new HBox(100);
        bottom_button.setPadding(new Insets(10,100,25,50));
        bottom_button.setAlignment(Pos.CENTER_LEFT);
        bottom_button.getChildren().addAll(Confirm,Return);
        this.add(bottom_button,1,9,4,1);
    }
}
