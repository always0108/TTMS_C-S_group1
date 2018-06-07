package UI.DataDictionary;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Data_dict;
import node.FunButton;
import node.MessageBar;
import util.Httpclient;

public class DataDictDelete extends VBox {

    public DataDictDelete(){}

    public DataDictDelete(Data_dict data_dict){
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20,20,20,20));

        HBox mes = new HBox();
        mes.setAlignment(Pos.CENTER);
        Label note = new Label("确定要删除"+ data_dict.getDict_name() +"?");
        mes.getChildren().add(note);

        HBox btGroup = new HBox();
        btGroup.setAlignment(Pos.CENTER);
        btGroup.setSpacing(50);
        btGroup.setPadding(new Insets(40,20,20,20));

        FunButton btOK = new FunButton("确认");
        FunButton btCancel = new FunButton("取消");

        btGroup.getChildren().addAll(btOK,btCancel);

        this.getChildren().addAll(mes,btGroup);

        btOK.setOnAction(e-> {
            btOK.setVisible(true);
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/dataDict/delete?id=" + data_dict.getDict_id();
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
                    new DataDictList();
                    btOK.setDisable(false);
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
            new DataDictList();
        });
    }
}
