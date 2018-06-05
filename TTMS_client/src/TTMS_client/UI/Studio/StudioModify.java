package UI.Studio;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Studio;
import node.MessageBar;
import util.Httpclient;

import java.util.HashMap;
import java.util.Map;

public class StudioModify extends VBox {

    private HBox hBox;
    private GridPane gridPane;

    public StudioModify() {}

    public StudioModify(Studio studio){

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20,20,20,20));

        //标题
        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text("修改演出厅");
        text.getStyleClass().add("funText");
        text.setFont(Font.font(20));
        hBox.getChildren().add(text);

        //表单
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label name = new Label("名称:");
        TextField studio_name = new TextField(studio.getStudio_name());

        Label row = new Label("行数:");
        TextField studio_row = new TextField(studio.getStudio_row_count().toString());

        Label col = new Label("列数:");
        TextField studio_col = new TextField(studio.getStudio_col_count().toString());

        Label introduction = new Label("简介:");
        TextArea studio_introduction = new TextArea(studio.getStudio_seat_count().toString());

        Label flag = new Label("状态");
        Label studio_flag = new Label(studio.getStudio_flag().toString());

        gridPane.addColumn(0, name, row, col, introduction,flag);
        gridPane.addColumn(1, studio_name,studio_row,studio_col,studio_introduction,studio_flag);

        Button btok = new Button("确定");
        btok.getStyleClass().add("funButton");
        Button btret = new Button("返回");
        btret.getStyleClass().add("funButton");

        //确认添加
        btok.setOnAction(e -> {
            btok.setDisable(true);
            //创建后台获取数据的线程
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/studio/update";
                    Map<String, Object> newStudio = new HashMap<>();
                    newStudio.put("studio_id",studio.getStudio_id());
                    newStudio.put("studio_name",studio_name.getText());
                    newStudio.put("studio_row_count",Integer.valueOf(studio_row.getText()));
                    newStudio.put("studio_col_count",Integer.valueOf(studio_col.getText()));
                    newStudio.put("studio_seat_count",Integer.valueOf(studio_row.getText())*Integer.valueOf(studio_col.getText()));
                    newStudio.put("studio_introduction",studio_introduction.getText());
                    newStudio.put("studio_flag",studio.getStudio_flag());
                    String res = Httpclient.post(url, newStudio);
                    return JSON.parseObject(res);
                }

                @Override
                protected void running() {

                }

                @Override
                protected void succeeded() {
                    super.succeeded();
                    JSONObject jsonObject = getValue();
                    if (jsonObject.get("flag").equals(true)) {
                        MessageBar.showMessageBar("修改成功！");
                        new StudioList();
                    } else {
                        MessageBar.showMessageBar(jsonObject.get("content").toString());
                    }
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
            new StudioList();
        });

        gridPane.add(btok, 1, 5);
        gridPane.add(btret, 2, 5);

        this.getChildren().addAll(hBox,gridPane);
    }
}
