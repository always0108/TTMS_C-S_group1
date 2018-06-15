package UI.Self;

import UI.Layout.HomeUI;
import UI.Layout.Main;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Employee;
import node.FunButton;
import node.MessageBar;
import util.Httpclient;
import util.MD5;

import java.util.HashMap;
import java.util.Map;

public class SelfUpdatePassword extends VBox {
    private HBox hBox;
    private GridPane gridPane;

    public SelfUpdatePassword(Employee emp) {
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20,20,20,20));

        //标题
        hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text("修改密码");
        text.getStyleClass().add("funText");
        text.setFont(Font.font(24));
        hBox.getChildren().add(text);

        //表单
        gridPane = new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(20, 20, 20, 20));


        Label oldPassword = new Label("原密码:");
        PasswordField oldPasswordField = new PasswordField();

        Label newPassword = new Label("新密码:");
        PasswordField newPasswordField = new PasswordField();

        gridPane.addColumn(0,oldPassword,newPassword);
        gridPane.addColumn(1,oldPasswordField,newPasswordField);

        FunButton btok = new FunButton("确定");
        FunButton btret = new FunButton("返回");

        //确认修改
        btok.setOnAction(e -> {
            if(!oldPasswordField.getText().isEmpty() &&
                    !newPasswordField.getText().isEmpty()){
                btok.setDisable(true);
                //创建后台获取数据的线程
                Task<JSONObject> task = new Task<JSONObject>() {
                    @Override
                    protected JSONObject call() throws Exception {

                        String url = "/employee/updatePassword";

                        Map<String, Object> newEmp = new HashMap<>();
                        newEmp.put("emp_id", emp.getEmp_id());
                        newEmp.put("oldPassword", oldPasswordField.getText());
                        newEmp.put("newPassword", newPasswordField.getText());

                        String res = Httpclient.post(url, newEmp);
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
                            emp.setEmp_passwd(MD5.codeByMD5(newPasswordField.getText()));
                            HomeUI.employee = emp;
                            Main.borderPane.setTop(HomeUI.top());
                            HomeUI.setCenter(new SelfMenu(emp));
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
                MessageBar.showMessageBar("请将信息填写完整");

            }
        });

        btret.setOnAction(e -> {
            HomeUI.setCenter(new SelfMenu(emp));
        });

        gridPane.add(btok, 1, 6);
        gridPane.add(btret, 2, 6);
        this.getChildren().addAll(hBox,gridPane);
    }
}
