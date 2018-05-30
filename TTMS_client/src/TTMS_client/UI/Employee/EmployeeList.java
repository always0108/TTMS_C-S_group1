package UI.Employee;

import Service.EmployeeSrv;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class EmployeeList extends VBox {

    private EmployeeSrv employeeSrv = new EmployeeSrv();
    private HBox hBox;
    private Pane pane;

    public EmployeeList(){
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20, 20, 20, 20));

        hBox = new HBox();
        hBox.setSpacing(30);
        hBox.setAlignment(Pos.CENTER);
        Label note = new Label("关键字:");
        TextField key = new TextField();
        Button find = new Button("查询");
        find.setOnAction(e->{
            this.getChildren().remove(pane);
            pane = new EmployeeTable(employeeSrv.searchByName(key.getText()));
            this.getChildren().add(pane);
        });

        hBox.getChildren().addAll(note,key,find);
        pane = new EmployeeTable(employeeSrv.list());
        this.getChildren().addAll(hBox,pane);

    }
}
