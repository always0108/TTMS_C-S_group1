package UI.Layout;

import UI.Analysis.AnalysisMenu;
import UI.DataDictionary.DataDictList;
import UI.Employee.EmployeeList;
import UI.Play.PlayList;
import UI.Sell.ChoosePlay;
import UI.Studio.StudioList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import node.NavigationButton;

public class NavigationBar{

    public static VBox getManagerBar(){
        VBox managerBar = new VBox();
        managerBar.setSpacing(10);
        managerBar.setPadding(new Insets(20,20,20,20));
        managerBar.setAlignment(Pos.TOP_CENTER);

        NavigationButton playManagement = new NavigationButton("剧目管理");
        playManagement.setOnAction(e->{
            new PlayList();
        });

        NavigationButton dataDictManagement = new NavigationButton("字典管理");
        dataDictManagement.setOnAction(e->{
            new DataDictList();
        });

        NavigationButton saleAnalysis = new NavigationButton("销售分析");
        saleAnalysis.setOnAction(e->{
            HomeUI.setCenter(new AnalysisMenu());
        });

        managerBar.getChildren().addAll(playManagement,dataDictManagement,saleAnalysis);
        return managerBar;
    }

    public static VBox getSellManBar(){
        VBox sellMan = new VBox();
        sellMan.setSpacing(10);
        sellMan.setPadding(new Insets(20,20,20,20));
        sellMan.setAlignment(Pos.TOP_CENTER);

        NavigationButton sellTicket = new NavigationButton("售票");
        sellTicket.setOnAction(e->{
            Main.borderPane.setCenter(new ChoosePlay(1));
        });

        NavigationButton returnTicket = new NavigationButton("退票");
        returnTicket.setOnAction(e->{
            Main.borderPane.setCenter(new ChoosePlay(-1));
        });

        sellMan.getChildren().addAll(sellTicket,returnTicket);
        return sellMan;
    }

    public static VBox getAdminBar(){
        VBox admin = new VBox();
        admin.setSpacing(10);
        admin.setPadding(new Insets(20,20,20,20));
        admin.setAlignment(Pos.TOP_CENTER);

        NavigationButton employeeManagement = new NavigationButton("用户管理");
        employeeManagement.setOnAction(e->{
            new EmployeeList();
        });

        NavigationButton studioManagement = new NavigationButton("演出厅管理");
        studioManagement.setOnAction(e->{
            new StudioList();
        });
        admin.getChildren().addAll(employeeManagement,studioManagement);

        return admin;
    }
}
