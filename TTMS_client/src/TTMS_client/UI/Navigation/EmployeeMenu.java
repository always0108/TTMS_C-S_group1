package UI.Navigation;

import UI.Employee.EmployeeAdd;
import UI.Employee.EmployeeList;
import UI.Main;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class EmployeeMenu extends TitledPane{

    public EmployeeMenu() {
        this.setText("用户管理");
        this.getStyleClass().add("firstMenu");
        VBox content = new VBox();
        content.setSpacing(10);
        Button list = new Button("查看用户");
        list.getStyleClass().add("secondMenu");
        Button add = new Button("添加用户");
        add.getStyleClass().add("secondMenu");
        list.setOnAction(e -> {
            new EmployeeList();
        });
        add.setOnAction(e -> {
            Main.borderPane.setCenter(new EmployeeAdd());
        });
        content.getChildren().addAll(list, add);
        this.setContent(content);
    }
}



