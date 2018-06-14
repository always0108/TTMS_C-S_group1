package UI.DataDictionary;

import Service.DataCollection;
import UI.Studio.StudioList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Data_dict;
import model.Studio;
import node.FunButton;
import node.MessageBar;
import util.Httpclient;

import java.util.HashMap;
import java.util.Map;

public class DataDictModify extends VBox {
    private HBox hBox;
    private GridPane gridPane;

    public DataDictModify() {}

    public DataDictModify(Data_dict data_dict){

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20,20,20,20));

        //标题
        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text("修改");
        text.getStyleClass().add("funText");
        text.setFont(Font.font(20));
        hBox.getChildren().add(text);

        //表单
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label name = new Label("名称:");
        TextField dataName = new TextField(data_dict.getDict_name());

        Label value = new Label("含义:");
        TextField dataValue = new TextField(data_dict.getDict_value());

        gridPane.addColumn(0, name,value);
        gridPane.addColumn(1, dataName,dataValue);

        FunButton btok = new FunButton("确定");
        FunButton btret = new FunButton("返回");

        btok.setOnAction(e -> {
            btok.setDisable(true);
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/dataDict/update";
                    Map<String, Object> newdataDict = new HashMap<>();

                    newdataDict.put("dict_id",data_dict.getDict_id());
                    newdataDict.put("dict_parent_id",data_dict.getDict_parent_id());
                    newdataDict.put("dict_index",data_dict.getDict_index());
                    newdataDict.put("dict_name",dataName.getText());
                    newdataDict.put("dict_value",dataValue.getText());

                    String res = Httpclient.post(url, newdataDict);
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
                    btok.setDisable(false);
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

        btret.setOnAction(e -> {
            new DataDictList();
        });

        gridPane.add(btok, 1, 5);
        gridPane.add(btret, 2, 5);

        this.getChildren().addAll(hBox,gridPane);
    }
}
