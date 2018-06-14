package UI.Self;

import UI.Layout.HomeUI;
import UI.Layout.Main;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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

import java.util.HashMap;
import java.util.Map;

public class SelfModify extends VBox {
    private HBox hBox;
    private GridPane gridPane;

    public SelfModify(Employee emp) {
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20,20,20,20));

        //标题
        hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text("修改个人信息");
        text.getStyleClass().add("funText");
        text.setFont(Font.font(24));
        hBox.getChildren().add(text);

        //表单
        gridPane = new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        Label name = new Label("姓名:");
        TextField emp_name = new TextField(emp.getEmp_name());

        Label tel = new Label("电话:");
        TextField emp_tel = new TextField(emp.getEmp_tel_num());

        Label addr = new Label("家庭住址:");
        TextField emp_addr = new TextField(emp.getEmp_addr());

        Label email = new Label("Email:");
        TextField emp_email = new TextField(emp.getEmp_email());

        gridPane.addColumn(0, name, tel, addr, email);
        gridPane.addColumn(1, emp_name, emp_tel, emp_addr, emp_email);

        FunButton btok = new FunButton("确定");
        FunButton btret = new FunButton("返回");

        //确认修改
        btok.setOnAction(e -> {
            if(!emp_name.getText().isEmpty() &&
                    !emp_tel.getText().isEmpty() &&
                    !emp_addr.getText().isEmpty() &&
                    !emp_email.getText().isEmpty()){
                btok.setDisable(true);
                //创建后台获取数据的线程
                Task<JSONObject> task = new Task<JSONObject>() {
                    @Override
                    protected JSONObject call() throws Exception {

                        String url = "/employee/update";

                        Map<String, Object> newEmp = new HashMap<>();
                        newEmp.put("emp_id", emp.getEmp_id());
                        newEmp.put("emp_no", emp.getEmp_no());
                        newEmp.put("emp_type",emp.getEmp_type());
                        newEmp.put("emp_name", emp_name.getText());
                        newEmp.put("emp_tel_num", emp_tel.getText());
                        newEmp.put("emp_addr", emp_addr.getText());
                        newEmp.put("emp_email", emp_email.getText());

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
                            emp.setEmp_name(emp_name.getText());
                            emp.setEmp_tel_num(emp_tel.getText());
                            emp.setEmp_addr(emp_addr.getText());
                            emp.setEmp_email(emp_email.getText());
                            Main.borderPane.setTop(new HomeUI().top(emp));
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
