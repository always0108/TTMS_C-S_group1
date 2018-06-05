package UI.Studio;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import node.MessageBar;
import util.Httpclient;

import java.util.HashMap;
import java.util.Map;

public class StudioAdd extends VBox {

    private HBox hBox;
    private GridPane gridPane;

    public StudioAdd() {
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20,20,20,20));

        //标题
        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text("添加演出厅");
        text.getStyleClass().add("funText");
        text.setFont(Font.font(20));
        hBox.getChildren().add(text);

        //表单
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label name = new Label("名称:");
        TextField studio_name = new TextField();

        Label row = new Label("行数:");
        TextField studio_row = new TextField();

        Label col = new Label("列数:");
        TextField studio_col = new TextField();

        Label introduction = new Label("简介:");
        TextArea studio_introduction = new TextArea();

        gridPane.addColumn(0, name, row, col, introduction);
        gridPane.addColumn(1, studio_name,studio_row,studio_col,studio_introduction);

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
                    String url = "/studio/add";

                    Map<String, Object> studio = new HashMap<>();

                    studio.put("studio_name",studio_name.getText());
                    studio.put("studio_row_count",Integer.valueOf(studio_row.getText()));
                    studio.put("studio_col_count",Integer.valueOf(studio_col.getText()));
                    studio.put("studio_seat_count",Integer.valueOf(studio_row.getText())*Integer.valueOf(studio_col.getText()));
                    studio.put("studio_introduction",studio_introduction.getText());
                    studio.put("studio_flag",0);

                    String res = Httpclient.post(url, studio);
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
                        MessageBar.showMessageBar("添加成功！");
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

        gridPane.add(btok, 1, 4);
        gridPane.add(btret, 2, 4);

        this.getChildren().addAll(hBox,gridPane);
    }
}
