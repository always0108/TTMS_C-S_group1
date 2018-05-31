package node;

import UI.Employee.EmployeeList;
import UI.Employee.EmployeeMenu;
import UI.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class NavigationBar{


    public VBox getManagerBar(){
        VBox managerBar = new VBox();
        managerBar.setSpacing(10);
        managerBar.setPadding(new Insets(20,20,20,50));
        managerBar.setAlignment(Pos.TOP_CENTER);
        NavigationButton employeeManagement = new NavigationButton("用户管理");
        employeeManagement.setOnAction(e->{
            Main.borderPane.setCenter(new EmployeeList());
        });

        NavigationButton studioManagement = new NavigationButton("演出厅管理");
        studioManagement.setOnAction(e->{

        });

        managerBar.getChildren().addAll(employeeManagement,studioManagement);

        return managerBar;
    }
}
