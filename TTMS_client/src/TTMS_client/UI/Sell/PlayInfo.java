package UI.Sell;

import Service.ScheduleSrv;
import UI.Layout.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Play;
import util.ImageByte;

import java.util.List;

public class PlayInfo extends GridPane {

    private ScheduleSrv scheduleSrv = new ScheduleSrv();

    public PlayInfo() {}

    public PlayInfo(List<Play> plays,Integer flag) {
        this.setHgap(20);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20,20,20,20));

        if(plays == null || plays.size() == 0){
            Label note = new Label("今天打烊啦!");
            note.setStyle("-fx-font-size: 20px");
            this.add(note,0,0);
        }else {
            int j = 0;
            for(int i = 0; i < plays.size(); i++){
                Play play = plays.get(i);
                VBox onePlay = new VBox();
                onePlay.setAlignment(Pos.CENTER);
                onePlay.setSpacing(10);

                ImageView playPicture = new ImageView(ImageByte.bytesToImage(play.getPlay_image()));
                playPicture.setFitWidth(240);
                playPicture.setFitHeight(180);
                Hyperlink link = new Hyperlink(play.getPlay_name(),new ImageView(ImageByte.bytesToImage(play.getPlay_image())));
                link.setStyle("-fx-border-style: hidden;-fx-content-display: top;-fx-font-size: 18px");
                this.add(link,j++,i%4);
                if(j == 3){
                    j = 0;
                }
                link.setOnAction(e->{
                    HomeUI.setCenter(new PlaySchedule(scheduleSrv.getTodayLeastSchedules(play.getPlay_id()),play,flag));
                });
            }
        }
    }
}
