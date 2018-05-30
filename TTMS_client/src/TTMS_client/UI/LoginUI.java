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
import util.Httpclient;

import java.util.HashMap;
import java.util.Map;

public class LoginUI {

    public static Pane init(){
        VBox pane = new VBox();

        //标题
        HBox header = new HBox();
        header.setAlignment(Pos.BOTTOM_CENTER);
        header.setPadding(new Insets(60,20,0,20));
        Label text = new Label("欢迎来到Hackers影院！");
        text.setId("welcome-words");
        header.getChildren().add(text);

        //表单
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(80,25,25,25));
        Label userName = new Label("用户名:");
        userName.getStyleClass().add("label");
        gridPane.add(userName,0,0);
        TextField userTextField = new TextField();
        userTextField.getStyleClass().add("textField");
        gridPane.add(userTextField,1,0);
        Label pw = new Label("密码:");
        pw.getStyleClass().add("label");
        gridPane.add(pw,0,1);
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("textField");
        gridPane.add(passwordField,1,1);

        Label note = new Label();
        note.setStyle("-fx-alignment: center");
        gridPane.add(note,1,2);

        //登录button
        HBox footer = new HBox();
        Button  bt1 = new Button("登录");
        bt1.setDefaultButton(true);
        bt1.getStyleClass().add("funButton");
        footer.setPadding(new Insets(20,300,0,0));
        //FunButton test = new FunButton("确认");
        footer.getChildren().addAll(bt1/*,test**/);
        footer.setSpacing(40);
        footer.setAlignment(Pos.CENTER_RIGHT);

        pane.getChildren().addAll(header,gridPane,footer);


        bt1.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            bt1.setEffect(new DropShadow());
        });
        bt1.addEventFilter(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            bt1.setEffect(null);
        });


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
