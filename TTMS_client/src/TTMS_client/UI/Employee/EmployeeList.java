package UI.Employee;

import Service.EmployeeSrv;
import UI.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import node.FunButton;

public class EmployeeList extends VBox {

    private EmployeeSrv employeeSrv = new EmployeeSrv();
    private HBox hBox;
    private Pane pane;
    private HBox footer;
    TextField key;

    public EmployeeList(){
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20, 20, 20, 20));

        hBox = new HBox();
        hBox.setSpacing(30);
        hBox.setAlignment(Pos.CENTER);
        Label note = new Label("关键字:");
        key = new TextField();
        Button find = new Button("查询");
        hBox.getChildren().addAll(note,key,find);
        pane = new EmployeeTable(employeeSrv.list());

        footer = new HBox();
        footer.setAlignment(Pos.CENTER_RIGHT);
        footer.setPadding(new Insets(20,150,20,20));
        FunButton add = new FunButton("添加用户");
        add.setOnAction(e->{
            Main.borderPane.setCenter(new EmployeeAdd());
        });
        footer.getChildren().add(add);

        find.setOnAction(e->{
            this.getChildren().removeAll(pane,footer);
            pane = new EmployeeTable(employeeSrv.searchByName(key.getText()));
            this.getChildren().addAll(pane,footer);
        });

        this.getChildren().addAll(hBox,pane,footer);

    }
}
