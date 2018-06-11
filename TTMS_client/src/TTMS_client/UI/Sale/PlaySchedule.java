package UI.Sale;

import UI.Layout.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Play;
import model.Schedule;
import node.FunButton;

import java.util.List;

public class PlaySchedule extends VBox {

    ScrollPane scrollPane = new ScrollPane();

    public PlaySchedule(List<Schedule> schedules, Play play){
        this.setPadding(new Insets(20,20,20,20));
        this.setSpacing(20);
        this.setAlignment(Pos.TOP_CENTER);

        if(schedules == null || schedules.size() == 0){
            Label note = new Label("今日场次已经放映完");
            note.setStyle("-fx-font-size: 20px");
            scrollPane.setContent(note);
        }else {
            VBox scheduleArea = new VBox();
            for (Schedule schedule:schedules) {
                scheduleArea.getChildren().add(new OnePlaySchedule(schedule,play));
            }
            scrollPane.setContent(scheduleArea);
        }

        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER_RIGHT);
        FunButton btret = new FunButton("返回");

        btret.setOnAction(e->{
            HomeUI.setCenter(new ChoosePlay());
        });
        this.getChildren().addAll(scrollPane,bottom);
    }
}
