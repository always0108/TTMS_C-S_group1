package UI.Employee;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Employee;
import node.FunButton;
import node.MessageBar;
import util.Httpclient;

public class EmployeeDelete extends VBox {

    public EmployeeDelete(){}

    public EmployeeDelete(Employee emp){
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20,20,20,20));

        HBox mes = new HBox();
        mes.setAlignment(Pos.CENTER);
        Label note = new Label("确定要删除"+emp.getEmp_name()+"用户?");
        mes.getChildren().add(note);

        HBox btGroup = new HBox();
        btGroup.setAlignment(Pos.CENTER);
        btGroup.setSpacing(50);
        btGroup.setPadding(new Insets(40,20,20,20));

        FunButton btOK = new FunButton("确认");
        FunButton btCancel = new FunButton("取消");

        btGroup.getChildren().addAll(btOK,btCancel);

        this.getChildren().addAll(mes,btGroup);

        btOK.setOnAction(e-> {
            btOK.setVisible(true);
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/employee/delete?id=" + emp.getEmp_id();
                    String res = Httpclient.get(url);
                    return JSON.parseObject(res);
                }

                @Override
                protected void running() {
                }

                @Override
                protected void succeeded() {
                    super.succeeded();
                    JSONObject jsonObject = getValue();
                    MessageBar.showMessageBar(jsonObject.get("content").toString());
                    new EmployeeList();
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

        btCancel.setOnAction(e->{
            new EmployeeList();
        });

    }

}
