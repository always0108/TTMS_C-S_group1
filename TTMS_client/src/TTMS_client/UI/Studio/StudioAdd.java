package UI.Studio;

import Service.SeatSrv;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private SeatSrv seatSrv = new SeatSrv();

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

        //最大支持16*16个座位
        ObservableList<String> num = FXCollections.observableArrayList();
        num.addAll("1","2","3","4","5","6","7","8","9","10");

        Label row = new Label("行数:");
        ComboBox<String> studio_row = new ComboBox<>();
        studio_row.setValue("请选择");
        studio_row.setItems(num);

        Label col = new Label("列数:");
        ComboBox<String> studio_col = new ComboBox<>();
        studio_col.setValue("请选择");
        studio_col.setItems(num);

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
            if(!studio_name.getText().isEmpty() &&
                    !studio_row.getValue().equals("请选择") &&
                    !studio_col.getValue().equals("请选择") &&
                    !studio_introduction.getText().isEmpty()){
                btok.setDisable(true);
                //创建后台获取数据的线程
                Task<JSONObject> task = new Task<JSONObject>() {
                    @Override
                    protected JSONObject call() throws Exception {
                        String url = "/studio/add";

                        Map<String, Object> studio = new HashMap<>();
                        studio.put("studio_name",studio_name.getText());
                        studio.put("studio_row_count",Integer.valueOf(studio_row.getValue()));
                        studio.put("studio_col_count",Integer.valueOf(studio_col.getValue()));
                        studio.put("studio_seat_count",Integer.valueOf(studio_row.getValue())*Integer.valueOf(studio_col.getValue()));
                        studio.put("studio_introduction",studio_introduction.getText());
                        studio.put("studio_flag",0);

                        System.out.println(url);
                        String res = Httpclient.post(url, studio);
                        System.out.println(res);
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
            }else {
                MessageBar.showMessageBar("请将信息补充完整");
            }
        });

        btret.setOnAction(e -> {
            new StudioList();
        });

        HBox bottom_button = new HBox(150);
        bottom_button.setAlignment(Pos.CENTER);
        bottom_button.getChildren().addAll(btok,btret);
        gridPane.add(bottom_button,1,4);

        this.getChildren().addAll(hBox,gridPane);
    }
}
