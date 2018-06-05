package UI.Layout;

import UI.LoginUI;
import UI.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import node.MessageBar;
import util.Httpclient;

public class HomeUI{

    public HBox top(String name){
        HBox toppane = new HBox();
        toppane.setStyle("-fx-background-color: darkgray;-fx-opacity: 0.75");
        toppane.setAlignment(Pos.CENTER_RIGHT);
        toppane.setSpacing(20);
        toppane.setPadding(new Insets(8,20,8,20));
        Label username = new Label(name);
        username.getStyleClass().add("label");
        Button logout = new Button("注销");
        logout.getStyleClass().add("funButton");
        logout.setStyle("-fx-font-size: 16px");
        logout.setOnAction(e->{
            String url = "/logout";
            Httpclient.get(url);
            Main.borderPane.setTop(null);
            Main.borderPane.setLeft(null);
            Main.borderPane.setBottom(null);
            Main.borderPane.setCenter(LoginUI.init());
        });
        toppane.getChildren().addAll(username,logout);
        return  toppane;
    }

    public Pane center(){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setSpacing(40);
        vBox.setPadding(new Insets(50,20,20,20));
        Label welcome = new Label("欢迎使用海客票务管理系统！");
        welcome.setId("welcome");
        Label note = new Label("<使用中遇到问题请及时与我们联系>");
        note.setId("note");
        Label email = new Label("Email:  Hackers@hacker.org");
        email.getStyleClass().add("conection");
        Label tel = new Label("Phone:  88888888");
        tel.getStyleClass().add("conection");
        vBox.getChildren().addAll(welcome,note,email,tel);
        return vBox;
    }

    public void cherkUI(String name){
        Main.borderPane.setTop(top(name));
        //Main.borderPane.setCenter(salemenu());
    }

    public void ManagerUI(String name){
        Main.borderPane.setTop(top(name));
        Main.borderPane.setCenter(center());
        Main.borderPane.setLeft(new NavigationBar().getManagerBar());
        MessageBar.showMessageBar("欢迎登录！");
    }

    public static void setCenter(Pane pane){
        Main.borderPane.setCenter(pane);
    }

    public static void showNote(){
        Main.borderPane.setCenter(new HomeUI().center());
    }
}
