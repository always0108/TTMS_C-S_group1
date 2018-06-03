package UI.Employee;

import UI.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.EmployeeProperty;
import node.FunButton;

public class EmployeeDetail extends VBox {

    public EmployeeDetail() {}

    public EmployeeDetail(EmployeeProperty emp){
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(80,20,20,20));

        GridPane table = new GridPane();
        table.setHgap(20);
        table.setVgap(10);
        table.setAlignment(Pos.CENTER);
        table.setPadding(new Insets(20,20,20,20));

        Label emp_no = new Label("工号：");
        Label emp_type = new Label("类型：");
        Label emp_name = new Label("姓名：");
        Label emp_tel_num = new Label("电话：");
        Label emp_addr = new Label("家庭住址：");
        Label emp_email = new Label("Email：");

        Label noValue = new Label(emp.getEmp_no());
        Label typeValue = new Label(String.valueOf(emp.getEmp_type()));
        Label nameValue = new Label(emp.getEmp_name());
        Label tel_numValue = new Label(emp.getEmp_tel_num());
        Label addrValue = new Label(emp.getEmp_addr());
        Label emailValue = new Label(emp.getEmp_email());

        table.addColumn(0,emp_no,emp_type,emp_name,emp_tel_num,emp_addr,emp_email);
        table.addColumn(1,noValue,typeValue,nameValue,tel_numValue,addrValue,emailValue);

        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(20,20,20,20));
        footer.setSpacing(40);
        FunButton btModify = new FunButton("修改");
        FunButton btDelete = new FunButton("删除");
        FunButton btRet = new FunButton("返回");
        footer.getChildren().addAll(btModify,btDelete,btRet);

        //点击修改按钮
        btModify.setOnAction(e->{
            HomeUI.setCenter(new EmployeeModify(emp));
        });

        //点击删除按钮
        btDelete.setOnAction(e->{
            HomeUI.setCenter(new EmployeeDelete(emp));
        });

        //点击修改按钮
        btRet.setOnAction(e->{
            new EmployeeList();
        });

        this.getChildren().addAll(table,footer);


    }


}
