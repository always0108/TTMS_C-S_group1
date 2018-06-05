package node;

import Service.StudioSrv;
import UI.Employee.EmployeeList;
import UI.HomeUI;
import UI.Main;
import UI.Studio.StudioList;
import UI.Studio.StudioTable;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Studio;

import java.util.List;

public class NavigationBar{

    private ProgressIndicator progressIndicator = new ProgressIndicator();
    private HBox progress;
    private StudioSrv studioSrv = new StudioSrv();

    public NavigationBar(){
        progress = new HBox();
        progress.setPadding(new Insets(0,50,20,0));
        progress.setSpacing(30);
        progress.setAlignment(Pos.CENTER);
        progress.getChildren().add(progressIndicator);
    }

    public VBox getManagerBar(){
        VBox managerBar = new VBox();
        managerBar.setSpacing(10);
        managerBar.setPadding(new Insets(20,20,20,50));
        managerBar.setAlignment(Pos.TOP_CENTER);
        NavigationButton employeeManagement = new NavigationButton("用户管理");
        employeeManagement.setOnAction(e->{
            new EmployeeList();
        });

        NavigationButton studioManagement = new NavigationButton("演出厅管理");
        studioManagement.setOnAction(e->{
            new StudioList();
        });

        managerBar.getChildren().addAll(employeeManagement,studioManagement);

        return managerBar;
    }
}
