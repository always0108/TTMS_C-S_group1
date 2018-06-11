package UI.Employee;

import UI.Layout.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Employee;

import java.util.List;

public class EmployeeTable extends GridPane {

    public EmployeeTable(List<Employee> employees){
        this.setPadding(new Insets(20,20,20,20));
        this.setHgap(30);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER);

        if(employees == null || employees.size() == 0 ){
            Label note = new Label("没有符合条件的职员");
            note.setStyle("-fx-font-size: 20px");
            this.add(note,0,0);
        }else {
            Label id = new Label("ID");
            Label type = new Label("账户类型");
            Label no = new Label("工号");
            Label name = new Label("用户名");
            this.addRow(0, id, type, no, name);

            for (int i = 0; i < employees.size(); i++) {
                Employee emp = employees.get(i);
                Label emp_id = new Label(emp.getEmp_id().toString());
                Label emp_type = new Label(emp.getEmp_type().toString());
                Label emp_no = new Label(emp.getEmp_no());
                Label emp_name = new Label(emp.getEmp_name());

                Button btDetail = new Button("详请");
                Button btModify = new Button("修改");
                Button btDelete = new Button("删除");
                btDetail.setOnAction(e->{
                    HomeUI.setCenter(new EmployeeDetail(emp));
                });

                btModify.setOnAction(e->{
                    HomeUI.setCenter(new EmployeeModify(emp));
                });

                btDelete.setOnAction(e->{
                    HomeUI.setCenter(new EmployeeDelete(emp));
                });
                this.addRow(i+1,emp_id,emp_type,emp_no,emp_name,
                        btDetail,btModify,btDelete);
            }
        }
    }

    public EmployeeTable(){}
}
