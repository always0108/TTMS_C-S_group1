package UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class test extends Application{

    public void start(Stage primaryStage){
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        GridPane gridPane = new GridPane();

        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20,20,20,20));

        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text("退票");
        text.getStyleClass().add("funText");
        text.setFont(Font.font(20));
        hBox.getChildren().add(text);

        //表单
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25,25,25,25));

        Label name = new Label("订单号：");
        TextField ticket_id = new TextField();

        gridPane.add(name,0,0);
        gridPane.add(ticket_id,1,0);

        Button btok = new Button("确定");
        btok.getStyleClass().add("funButton");
        Button btcan = new Button("取消");
        btcan.getStyleClass().add("funButton");


        gridPane.add(btok,0,1);
        gridPane.add(btcan,1,1);

        vBox.getChildren().addAll(hBox,gridPane);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
