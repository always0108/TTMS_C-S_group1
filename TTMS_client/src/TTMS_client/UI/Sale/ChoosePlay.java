package UI.Sale;

import Service.PlaySrv;
import UI.Layout.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import node.FunButton;

import java.util.Calendar;


public class ChoosePlay extends VBox {

    private PlaySrv playSrv = new PlaySrv();
    private ScrollPane playsArea;

    public ChoosePlay(){
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(20,20,20,20));

        HBox dateBox = new HBox();
        Calendar todayDate = Calendar.getInstance();
        Calendar tomorrowDate = Calendar.getInstance();
        tomorrowDate.add(Calendar.DATE,1);
        dateBox.setAlignment(Pos.CENTER_LEFT);
        dateBox.setSpacing(20);
        dateBox.setPadding(new Insets(10,10,10,10));
        Button today = new Button((todayDate.get(Calendar.MONTH)+1) +"月"+todayDate.get(Calendar.DAY_OF_MONTH)+"日");
        Button tomorrow = new Button((tomorrowDate.get(Calendar.MONTH)+1) +"月"+tomorrowDate.get(Calendar.DAY_OF_MONTH)+"日");
        dateBox.getChildren().addAll(today,tomorrow);
        today.setDisable(true);

        String todayDateString = todayDate.get(Calendar.YEAR)+"-"+(todayDate.get(Calendar.MONTH)+1)
                +"-"+ todayDate.get(Calendar.DAY_OF_MONTH);

        String tomorrowDateString = tomorrowDate.get(Calendar.YEAR)+"-"+(tomorrowDate.get(Calendar.MONTH)+1)
                +"-"+ tomorrowDate.get(Calendar.DAY_OF_MONTH);

        playsArea = new ScrollPane();
        playsArea.setStyle("-fx-padding: 0;-fx-opacity: 0.75;-fx-background-color: bisque");
        playsArea.setContent(new PlayInfo(playSrv.selectPlayByDate(todayDateString)));


        today.setOnAction(e->{
            today.setDisable(true);
            tomorrow.setDisable(false);
            playsArea.setContent(new PlayInfo(playSrv.selectPlayByDate(todayDateString)));
        });

        tomorrow.setOnAction(e->{
            tomorrow.setDisable(true);
            today.setDisable(false);
            playsArea.setContent(new PlayInfo(playSrv.selectPlayByDate(tomorrowDateString)));
        });

        //功能按钮框
        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER_RIGHT);
        bottom.setPadding(new Insets(20,80,20,20));
        bottom.setSpacing(300);
        FunButton btret = new FunButton("返回");
        bottom.getChildren().add(btret);
        btret.setOnAction(e->{
            HomeUI.showNote();
        });

        this.getChildren().addAll(dateBox,playsArea,bottom);
    }

}
