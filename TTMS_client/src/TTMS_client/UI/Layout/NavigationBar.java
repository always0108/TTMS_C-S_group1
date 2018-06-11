package UI.Layout;

import Service.StudioSrv;
import UI.DataDictionary.DataDictList;
import UI.Employee.EmployeeList;
import UI.Main;
import UI.Play.PlayList;
import UI.Sale.ChoosePlay;
import UI.Studio.StudioList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import node.NavigationButton;

public class NavigationBar{

    public NavigationBar(){}

    public VBox getManagerBar(){
        VBox managerBar = new VBox();
        managerBar.setSpacing(10);
        managerBar.setPadding(new Insets(20,20,20,20));
        managerBar.setAlignment(Pos.TOP_CENTER);
        NavigationButton employeeManagement = new NavigationButton("用户管理");
        employeeManagement.setOnAction(e->{
            new EmployeeList();
        });

        NavigationButton studioManagement = new NavigationButton("演出厅管理");
        studioManagement.setOnAction(e->{
            new StudioList();
        });

        NavigationButton playManagement = new NavigationButton("剧目管理");
        playManagement.setOnAction(e->{
            new PlayList();
        });

        NavigationButton dataDictManagement = new NavigationButton("字典管理");
        dataDictManagement.setOnAction(e->{
            new DataDictList();
        });

        NavigationButton sellTicket = new NavigationButton("卖票");
        sellTicket.setOnAction(e->{
            Main.borderPane.setCenter(new ChoosePlay());
        });

        managerBar.getChildren().addAll(employeeManagement,studioManagement,playManagement,
                dataDictManagement,sellTicket);
        return managerBar;
    }
}
