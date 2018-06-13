package UI.Play;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Play;
import node.FunButton;
import node.MessageBar;
import util.Httpclient;

public class PlayDelete extends VBox {

    public PlayDelete(){}

    public PlayDelete(Play play){
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20,20,20,20));

        HBox mes = new HBox();
        mes.setAlignment(Pos.CENTER);
        Label note = new Label("确定要删除"+play.getPlay_name()+"剧目?");
        mes.getChildren().add(note);

        HBox btGroup = new HBox();
        btGroup.setAlignment(Pos.CENTER);
        btGroup.setSpacing(50);
        btGroup.setPadding(new Insets(40,20,20,20));

        FunButton btOK = new FunButton("确认");
        FunButton btCancel = new FunButton("取消");

        btGroup.getChildren().addAll(btOK,btCancel);

        this.getChildren().addAll(mes,btGroup);
        //确认
        btOK.setOnAction(e-> {
            btOK.setVisible(true);
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/play/delete?id=" + play.getPlay_id();
                    String res = Httpclient.get(url);
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

        btCancel.setOnAction(e->{
            new PlayList();
        });

    }

}
