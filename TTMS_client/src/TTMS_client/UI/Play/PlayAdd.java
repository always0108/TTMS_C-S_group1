package UI.Play;

import Service.DataCollection;
import UI.Studio.StudioList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Play;
import node.FunButton;
import node.MessageBar;
import org.apache.commons.codec.binary.Base64;
import util.Httpclient;
import util.ImageByte;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PlayAdd extends GridPane {

    public PlayAdd (){
        this.setHgap(20);
        this.setVgap(20);
        this.setPadding(new Insets(25,25,25,25));

        Text playadd = new Text("添加剧目");
        playadd.setFont(Font.font(30));
        this.add(playadd,0,0,3,1);
        playadd.setTextAlignment(TextAlignment.LEFT);

        Label type_id = new Label("影片类型：");
        ComboBox<String> play_type_id = new ComboBox<>();
        play_type_id.setValue("请选择");
        for (Map.Entry<String,Integer> entry:DataCollection.playTypeComboBox.entrySet()){
            play_type_id.getItems().add(entry.getKey());
        }
        this.add(type_id,0,1);
        this.add(play_type_id,1,1);

        Label lang_id = new Label("语言：");
        ComboBox<String> play_lang_id = new ComboBox<>();
        play_lang_id.setValue("请选择");
        for (Map.Entry<String,Integer> entry:DataCollection.playLangComboBox.entrySet()){
            play_lang_id.getItems().add(entry.getKey());
        }
        this.add(lang_id,2,1);
        this.add(play_lang_id,3,1);

        Label name = new Label("剧目名称：");
        TextField play_name = new TextField();
        this.add(name,0,2);
        this.add(play_name,1,2,3,1);

        Label image = new Label("剧目封面：");
        this.add(image,0,3);
        TextField play_image_textfield = new TextField();
        this.add(play_image_textfield,1,3);
        Button select_button = new Button("选择文件");
        select_button.setOnAction(e->{
            play_image_textfield.setText(ImageByte.getFilePath());
        });
        this.add(select_button,2,3);

        Label introduction = new Label("剧目简介:");
        TextArea play_introduction = new TextArea("这是一条剧目简介......");
        play_introduction.setPrefColumnCount(16);
        play_introduction.setPrefRowCount(4);
        play_introduction.setWrapText(true);
        this.add(introduction,0,4);
        this.add(play_introduction,1,4,3,1);

        Label length = new Label("剧目时长：");
        TextField play_length = new TextField();
        this.add(length,0,5);
        this.add(play_length,1,5,3,1);

        Label price = new Label("剧目票价：");
        TextField play_price = new TextField();
        this.add(price,0,6);
        this.add(play_price,1,6,3,1);

        FunButton Confirm = new FunButton("确认");
        FunButton Return = new FunButton("返回");
        HBox bottom_button = new HBox(200);
        bottom_button.setPadding(new Insets(10,100,25,50));
        bottom_button.setAlignment(Pos.CENTER_LEFT);
        bottom_button.getChildren().addAll(Confirm,Return);
        this.add(bottom_button,1,7,4,1);

        Confirm.setOnAction(e->{
            Confirm.setDisable(true);
            //创建后台获取数据的线程
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/play/add";
                    short status = 0;
                    byte[] data = ImageByte.imageToBytes(play_image_textfield.getText());
                    String str = Base64.encodeBase64String(data);

                    Map<String, Object> play = new HashMap<>();
                    play.put("play_type_id",DataCollection.playTypeComboBox.get(play_type_id.getValue()));
                    play.put("play_lang_id",DataCollection.playLangComboBox.get(play_lang_id.getValue()));
                    play.put("play_name",play_name.getText());
                    play.put("play_introduction",play_introduction.getText());
                    play.put("play_length",Integer.valueOf(play_length.getText()));
                    play.put("play_ticket_price",new BigDecimal(play_price.getText()));
                    play.put("Base64play_image",str);
                    play.put("play_status",status);

                    String res = Httpclient.post(url, play);
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
                    new PlayList();
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
            new PlayList();
        });
    }
}
