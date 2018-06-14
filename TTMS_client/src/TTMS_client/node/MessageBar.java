package node;

import UI.Layout.Main;
import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class MessageBar {

    private static Label label = new Label();
    private static HBox pane = new HBox();

    static {
        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: #2b2b2b");
        label.setStyle("-fx-text-fill: #eaf4fc;-fx-font-size: 18px");
        pane.getChildren().addAll(label);
        pane.setPrefHeight(30);
        Main.borderPane.setBottom(pane);
    }

    public static void showMessageBar(String mes){
        label.setText(mes);
        FadeTransition appear = new FadeTransition();
        appear.setNode(pane);
        appear.setDuration(new Duration(1000));
        appear.setFromValue(0);
        appear.setToValue(1.0);
        appear.play();

        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                Thread.sleep(3000);
                return  null;
            }

            @Override protected void succeeded() {
                super.succeeded();
                FadeTransition disappear = new FadeTransition();
                disappear.setNode(pane);
                disappear.setDuration(new Duration(1000));
                disappear.setFromValue(1.0);
                disappear.setToValue(0);
                disappear.play();
                updateMessage("Done!");
            }

            @Override protected void cancelled() {
                updateMessage("Cancelled!");
            }

            @Override protected void failed() {
                updateMessage("Failed!");
            }
        };
        new Thread(task).start();
    }
}
