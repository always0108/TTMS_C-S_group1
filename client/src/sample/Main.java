package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        HBox hBox = new HBox();
        Label label = new Label("你好啊！");
        hBox.getChildren().add(label);
        primaryStage.setTitle("测试");
        primaryStage.setScene(new Scene(hBox, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
