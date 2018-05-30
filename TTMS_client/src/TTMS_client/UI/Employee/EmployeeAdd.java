package UI.Employee;

import UI.HomeUI;
import UI.Main;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import node.MessageBar;
import util.Httpclient;

import java.util.HashMap;
import java.util.Map;

public class EmployeeAdd extends VBox{

    private HBox hBox;
    private GridPane gridPane;

    public EmployeeAdd() {
        this.setSpacing(20);
        this.setPadding(new Insets(20,20,20,20));

        //标题
        hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text("添加用户");
        text.getStyleClass().add("funText");
        text.setFont(Font.font(24));
        hBox.getChildren().add(text);

        //表单
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label no = new Label("工号:");
        TextField emp_no = new TextField();
        emp_no.getStyleClass().add("textField");

        Label type = new Label("用户类型:");
        RadioButton rbt1 = new RadioButton("经理");
        RadioButton rbt2 = new RadioButton("售票员");
        RadioButton rbt3 = new RadioButton("管理员");
        FlowPane flow = new FlowPane();
        flow.setHgap(10);
        ToggleGroup group = new ToggleGroup();
        rbt1.setToggleGroup(group);
        rbt2.setToggleGroup(group);
        rbt3.setToggleGroup(group);
        flow.getChildren().addAll(rbt1, rbt2, rbt3);

        Label name = new Label("姓名:");
        TextField emp_name = new TextField();
        emp_name.getStyleClass().add("textField");

        Label password = new Label("密码:");
        PasswordField emp_password = new PasswordField();
        emp_password.getStyleClass().add("textField");

        Label tel = new Label("电话:");
        TextField emp_tel = new TextField();
        emp_tel.getStyleClass().add("textField");

        Label addr = new Label("家庭住址:");
        TextField emp_addr = new TextField();
        emp_addr.getStyleClass().add("textField");

        Label email = new Label("Email:");
        TextField emp_email = new TextField();
        emp_email.getStyleClass().add("textField");

        gridPane.addColumn(0, no, type, name, password, tel, addr, email);
        gridPane.addColumn(1, emp_no, flow, emp_name, emp_password, emp_tel, emp_addr, emp_email);

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
                    int emp_type;
                    if (rbt1.isSelected()) {
                        emp_type = 1;
                    } else if (rbt2.isSelected()) {
                        emp_type = 2;
                    } else if (rbt3.isSelected()) {
                        emp_type = 3;
                    } else {
                        emp_type = 9;
                    }
                    String url = "/employee/add";

                    Map<String, Object> emp = new HashMap<>();
                    emp.put("emp_no", emp_no.getText());
                    emp.put("emp_type", emp_type);
                    emp.put("emp_name", emp_name.getText());
                    emp.put("emp_passwd", emp_password.getText());
                    emp.put("emp_tel_num", emp_tel.getText());
                    emp.put("emp_addr", emp_addr.getText());
                    emp.put("emp_email", emp_email.getText());

                    String res = Httpclient.post(url, emp);
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
                    } else {
                        MessageBar.showMessageBar("添加失败！");
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
            MessageBar.showMessageBar("This is a test !");
        });

        gridPane.add(btok, 1, 7);
        gridPane.add(btret, 2, 7);

        this.getChildren().addAll(hBox,gridPane);
    }
}
