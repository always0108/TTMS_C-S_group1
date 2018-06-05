package UI;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import node.FunButton;
import util.Httpclient;

import java.util.HashMap;
import java.util.Map;

public class LoginUI {

    public static Pane init(){
        VBox pane = new VBox();

        //标题
        HBox header = new HBox();
        header.setAlignment(Pos.BOTTOM_CENTER);
        header.setPadding(new Insets(60,20,0,40));
        Label text = new Label("海客票务管理系统");
        text.setId("welcome-words");
        header.getChildren().add(text);

        //表单
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(25);
        gridPane.setPadding(new Insets(80,20,20,20));
        Label note = new Label();
        note.setStyle("-fx-alignment: center");
        gridPane.add(note,1,0);
        Label userName = new Label("用户名:");
        userName.getStyleClass().add("label");
        gridPane.add(userName,0,1);
        TextField userTextField = new TextField();
        userTextField.getStyleClass().add("textField");
        gridPane.add(userTextField,1,1);
        Label pw = new Label("密码:");
        pw.getStyleClass().add("label");
        gridPane.add(pw,0,2);
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("textField");
        gridPane.add(passwordField,1,2);
        FunButton bt1 = new FunButton("登录");
        bt1.setDefaultButton(true);
        bt1.setStyle("-fx-min-width: 200px;-fx-min-height: 40px;");
        gridPane.add(bt1,1,3);

        pane.getChildren().addAll(header,gridPane);

        bt1.setOnAction(e->{
            bt1.setDisable(true);
            //创建后台获取数据的线程
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/login";
                    Map<String,Object> user = new HashMap<>();
                    user.put("username",userTextField.getText());
                    user.put("password",passwordField.getText());
                    String res = Httpclient.post(url,user);
                    return JSON.parseObject(res);
                }

                @Override
                protected void running(){
                    note.setTextFill(Color.GREEN);
                    note.setText("正在登录....");
                }

                @Override protected void succeeded() {
                    super.succeeded();
                    JSONObject jsonObject = getValue();
                    if(jsonObject.get("flag").equals(true)){
                        note.setText("登录成功");
                        JSONObject emp = JSON.parseObject(jsonObject.get("content").toString());
                        new HomeUI().ManagerUI(emp.get("emp_name").toString());
                    }else {
                        note.setTextFill(Color.RED);
                        note.setText(jsonObject.get("content").toString());
                    }
                    bt1.setDisable(false);
                    updateMessage("Done!");
                }

                @Override protected void cancelled() {
                    super.cancelled();
                    note.setTextFill(Color.RED);
                    note.setText("已取消");
                    bt1.setDisable(false);
                    updateMessage("Cancelled!");
                }

                @Override protected void failed() {
                    super.failed();
                    note.setTextFill(Color.RED);
                    note.setText("登录失败");
                    bt1.setDisable(false);
                    updateMessage("Failed!");
                }
            };
            Thread thread = new Thread(task);
            thread.start();
        });
        return pane;
    }
}
