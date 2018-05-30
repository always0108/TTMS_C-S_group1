package node;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Employee;

public class MessageBox {

    private static Label label = new Label();

    private static Stage messageStage;

    static {
        messageStage = new Stage();
        messageStage.setTitle("消息");
        messageStage.setWidth(350);
        messageStage.setHeight(150);
        messageStage.setResizable(false);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setAlignment(Pos.CENTER);

        HBox center = new HBox();
        center.setSpacing(10);
        center.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: 18px;-fx-background-color: inherit");
        center.getChildren().add(label);

        HBox footer = new HBox();
        footer.setSpacing(10);
        footer.setAlignment(Pos.CENTER_RIGHT);
        Button btok = new Button("确定");
        btok.setStyle("-fx-font-size: 16px");
        footer.getChildren().add(btok);

        vBox.getChildren().addAll(center,footer);

        messageStage.setScene(new Scene(vBox,250,150));
        messageStage.show();

        btok.setOnAction(e->{
            messageStage.hide();
        });
    }

    public MessageBox() {}

    public static void show(String message){
        label.setText(message);
    }
}
