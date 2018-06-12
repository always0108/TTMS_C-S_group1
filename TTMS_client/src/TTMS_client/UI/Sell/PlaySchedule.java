package UI.Sell;

import UI.Layout.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Play;
import model.Schedule;
import node.FunButton;

import java.util.List;

public class PlaySchedule extends VBox {

    ScrollPane scrollPane = new ScrollPane();

    public PlaySchedule(List<Schedule> schedules, Play play){
        this.setPadding(new Insets(30,20,20,20));
        this.setSpacing(30);
        this.setAlignment(Pos.TOP_LEFT);

        if(schedules == null || schedules.size() == 0){
            Label note = new Label("今日场次已经放映完");
            note.setStyle("-fx-font-size: 28px");
            this.getChildren().addAll(note);
        }else {
            Text title = new Text("请选择场次：");
            title.setFont(Font.font(28));
            VBox scheduleArea = new VBox();
            for (Schedule schedule:schedules) {
                scheduleArea.getChildren().add(new OnePlaySchedule(schedule,play));
            }
            scrollPane.setContent(scheduleArea);
            this.getChildren().addAll(title,scrollPane);
        }

        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER_RIGHT);
        bottom.setPadding(new Insets(10,80,10,10));
        FunButton btret = new FunButton("返回");
        bottom.getChildren().add(btret);

        btret.setOnAction(e->{
            HomeUI.setCenter(new ChoosePlay());
        });
        this.getChildren().addAll(bottom);
    }
}
