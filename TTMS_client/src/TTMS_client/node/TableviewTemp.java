package node;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import model.EmployeeProperty;
import node.ButtonCell;

public class TableviewTemp extends HBox {

    public TableviewTemp(ObservableList<EmployeeProperty> employeePropertyObservableList){
        this.setAlignment(Pos.CENTER);
        //创建表单
        TableView<EmployeeProperty> table = new TableView<>();
        table.setItems(employeePropertyObservableList);
        table.setEditable(false);

        //创建各个列并设置是否显示
        TableColumn<EmployeeProperty, String> emp_no_id = new TableColumn<>("主键");
        emp_no_id.setCellValueFactory(new PropertyValueFactory("emp_id"));
        emp_no_id.setVisible(false);

        TableColumn<EmployeeProperty, String> emp_no_col = new TableColumn<>("工号");
        emp_no_col.setCellValueFactory(new PropertyValueFactory("emp_no"));

        TableColumn<EmployeeProperty, Integer> emp_type_col = new TableColumn<>("类型");
        emp_type_col.setCellValueFactory(new PropertyValueFactory("emp_type"));

        TableColumn<EmployeeProperty, String> emp_name_col = new TableColumn<>("名称");
        emp_name_col.setCellValueFactory(new PropertyValueFactory("emp_name"));

        TableColumn<EmployeeProperty, String> emp_passwd_col = new TableColumn<>("密码");
        emp_passwd_col.setCellValueFactory(new PropertyValueFactory("emp_passwd"));
        emp_passwd_col.setVisible(false);

        TableColumn<EmployeeProperty, String> emp_tel_num_col = new TableColumn<>("电话");
        emp_tel_num_col.setCellValueFactory(new PropertyValueFactory("emp_tel_num"));

        TableColumn<EmployeeProperty, String> emp_addr_col = new TableColumn<>("地址");
        emp_addr_col.setCellValueFactory(new PropertyValueFactory("emp_addr"));
        emp_addr_col.setVisible(false);

        TableColumn<EmployeeProperty, String> emp_email_col = new TableColumn<>("Email");
        emp_email_col.setCellValueFactory(new PropertyValueFactory("emp_email"));
        emp_email_col.setVisible(false);

        TableColumn fun_col = new TableColumn<>("功能");
        fun_col.setCellValueFactory( new Callback<TableColumn.CellDataFeatures<EmployeeProperty, Boolean>,
                ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<EmployeeProperty, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        fun_col.setCellFactory(new Callback<TableColumn<EmployeeProperty, Boolean>, TableCell<EmployeeProperty, Boolean>>() {
            @Override
            public TableCell<EmployeeProperty, Boolean> call(TableColumn<EmployeeProperty, Boolean> param) {
                return new ButtonCell();
            }
        });

        //加载列
        table.getColumns().setAll(emp_no_id,emp_no_col, emp_type_col, emp_name_col,emp_passwd_col ,emp_tel_num_col, emp_addr_col, emp_email_col,fun_col);
        this.setPadding(new Insets(20,20,20,20));
        this.getChildren().add(table);
    }
}
