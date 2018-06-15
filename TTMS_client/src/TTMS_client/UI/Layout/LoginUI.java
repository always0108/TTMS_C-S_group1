package UI.Layout;

import Service.DataCollection;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Employee;
import node.FunButton;
import node.MessageBar;
import util.Httpclient;

import java.util.HashMap;
import java.util.Map;

public class LoginUI {

    public static Pane init(){
        VBox pane = new VBox();
        pane.setSpacing(20);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setPadding(new Insets(40,20,20,20));
        //标题
        ImageView logo = new ImageView("/image/Login/logo.png");
        ImageView name = new ImageView("/image/Login/TTMSName.png");

        //表单
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(25);
        gridPane.setPadding(new Insets(20,20,20,20));
        Label note = new Label();
        note.setStyle("-fx-alignment: center");
        gridPane.add(note,1,0);
        Label userName = new Label("用户名:");
        userName.setStyle("-fx-font-size: 20px");
        gridPane.add(userName,0,1);
        TextField userTextField = new TextField();
        gridPane.add(userTextField,1,1);
        Label pw = new Label("密　码:");
        pw.setStyle("-fx-font-size: 20px");
        gridPane.add(pw,0,2);
        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordField,1,2);
        FunButton bt1 = new FunButton("登　录");
        bt1.setDefaultButton(true);
        bt1.setStyle("-fx-min-width: 200px;-fx-min-height: 40px;-fx-background-color: #ca8269;-fx-font-size: 18px");
        gridPane.add(bt1,1,4);

        pane.getChildren().addAll(logo,name,gridPane);

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
                        new DataCollection();
                        Employee emp = jsonObject.getObject("content",Employee.class);
                        if(emp.getEmp_type() == 1){//经理
                            new HomeUI().ManagerUI(emp);
                        }else if(emp.getEmp_type() == 2){//售票员
                            new HomeUI().sellManUI(emp);
                        }else if(emp.getEmp_type() == 3){//管理员
                            new HomeUI().adminUI(emp);
                        }
                        MessageBar.showMessageBar("欢迎登录！");
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
