package UI.Layout;

import UI.Layout.LoginUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static BorderPane borderPane;
    public static Stage messageStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("TTMS");
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);
        primaryStage.setResizable(false);

        borderPane = new BorderPane();
        borderPane.setCenter(LoginUI.init());

        borderPane.setStyle("-fx-background-image: url('/image/background.jpg')");
        Scene scene = new Scene(borderPane, 1024, 768);
        scene.getStylesheets().add(getClass().getResource("/css/MainStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
