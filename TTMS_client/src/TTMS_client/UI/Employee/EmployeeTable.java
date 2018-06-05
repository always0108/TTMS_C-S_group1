package UI.Employee;

import UI.HomeUI;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.EmployeeProperty;

public class EmployeeTable extends GridPane {

    public EmployeeTable(ObservableList<EmployeeProperty> employeePropertyObservableList){

        this.setPadding(new Insets(20,20,20,20));
        this.setStyle("-fx-background-color: transparent;-fx-control-inner-background: transparent;");
        this.setAlignment(Pos.CENTER);
        this.setVgap(20);
        this.setHgap(40);

        if(employeePropertyObservableList.size() == 0 || employeePropertyObservableList == null){
            Label note = new Label("没有符合条件的结果");
            note.setStyle("-fx-font-size: 20px");
            this.add(note,0,0);
        }else {
            Label id = new Label("ID");
            Label type = new Label("账户类型");
            Label no = new Label("工号");
            Label name = new Label("用户名");
            this.addRow(0, id, type, no, name);

            for (int i = 0; i < employeePropertyObservableList.size(); i++) {

                HBox oneUser = new HBox();
                oneUser.setPadding(new Insets(20, 20, 20, 20));
                oneUser.setSpacing(30);
                oneUser.setAlignment(Pos.CENTER_LEFT);
                EmployeeProperty emp = employeePropertyObservableList.get(i);
                Label emp_id = new Label(String.valueOf(emp.getEmp_id()));
                Label emp_type = new Label(String.valueOf(emp.getEmp_type()));
                Label emp_no = new Label(emp.getEmp_no());
                Label emp_name = new Label(emp.getEmp_name());
                Button btMore = new Button("更多");
                btMore.setOnAction(e -> {
                    HomeUI.setCenter(new EmployeeDetail(emp));
                });
                this.addRow(i + 1, emp_id, emp_type, emp_no, emp_name, btMore);
            }
        }
    }

    public EmployeeTable(){}
}
