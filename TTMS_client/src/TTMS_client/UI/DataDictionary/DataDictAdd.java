package UI.DataDictionary;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import node.FunButton;
import node.MessageBar;
import util.Httpclient;

import java.util.HashMap;
import java.util.Map;

public class DataDictAdd extends VBox {

    private HBox hBox;
    private GridPane gridPane;

    public DataDictAdd() {
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20, 20, 20, 20));

        //标题
        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text("添加规则");
        text.getStyleClass().add("funText");
        hBox.getChildren().add(text);

        //表单
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label branch = new Label("分支:");
        ComboBox<String> parentName = new ComboBox<>();
        parentName.getItems().addAll("根","影片类型","语言");
        parentName.setValue("请选择");

        Label name = new Label("名称:");
        TextField dataName = new TextField();

        Label value = new Label("值:");
        TextField dataValue = new TextField();

        gridPane.addColumn(0,branch,name,value);
        gridPane.addColumn(1,parentName,dataName,dataValue);

        FunButton btok = new FunButton("确定");
        FunButton btret = new FunButton("返回");

        gridPane.add(btok, 1, 3);
        gridPane.add(btret, 2, 3);

        this.getChildren().addAll(hBox, gridPane);
        //确认添加
        btok.setOnAction(e -> {
            btok.setDisable(true);
//            //创建后台获取数据的线程
//            Task<JSONObject> task = new Task<JSONObject>() {
//                @Override
//                protected JSONObject call() throws Exception {
//                    String url = "/dataDict/add";
//
//                    Map<String, Object> dataDict = new HashMap<>();
//
//
//                    String res = Httpclient.post(url, dataDict);
//                    return JSON.parseObject(res);
//                }
//
//                @Override
//                protected void running() {
//
//                }
//
//                @Override
//                protected void succeeded() {
//                    super.succeeded();
//                    JSONObject jsonObject = getValue();
//                    if (jsonObject.get("flag").equals(true)) {
//                        MessageBar.showMessageBar("添加成功！");
//                        new DataDictList();
//                    } else {
//                        MessageBar.showMessageBar(jsonObject.get("content").toString());
//                    }
//                    btok.setDisable(false);
//                    updateMessage("Done!");
//                }
//
//                @Override
//                protected void cancelled() {
//                    super.cancelled();
//                    updateMessage("Cancelled!");
//                }
//
//                @Override
//                protected void failed() {
//                    super.failed();
//                    updateMessage("Failed!");
//                }
//            };
//            Thread thread = new Thread(task);
//            thread.start();
        });

        btret.setOnAction(e -> {
            new DataDictList();
        });
    }
}
