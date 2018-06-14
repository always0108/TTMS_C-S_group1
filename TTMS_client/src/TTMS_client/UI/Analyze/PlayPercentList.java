package UI.Analyze;

import Service.SaleSrv;
import UI.Layout.HomeUI;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PlayPercent;

import util.DateFormat;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class PlayPercentList {

    private HBox top;
    private HBox hBox;
    private DatePicker datePicker;
    private SaleSrv saleSrv = new SaleSrv();
    private ProgressIndicator progressIndicator = new ProgressIndicator();
    private HBox progress;
    private VBox vBox = new VBox();

    public PlayPercentList() {
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setPadding(new Insets(40,20,20,20));
        vBox.setSpacing(20);
        Label title = new Label("今日电影排片比");
        title.setStyle("-fx-font-size: 28px");
        vBox.getChildren().add(title);
        top = new HBox();
        top.setSpacing(30);
        top.setAlignment(Pos.CENTER);
        Label note = new Label("选择日期");
        datePicker = new DatePicker();
        top.getChildren().addAll(note, datePicker);

        progress = new HBox();
        progress.setPadding(new Insets(0, 50, 20, 0));
        progress.setSpacing(30);
        progress.setAlignment(Pos.CENTER);
        progress.getChildren().add(progressIndicator);

        Calendar today = Calendar.getInstance();
        String todayStr = DateFormat.dateToStr(today.getTime());
        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(new OneDayPlayPercentAnalysis(saleSrv.getAllPlayPercentByDate(todayStr.substring(0,10))));
        vBox.getChildren().addAll(top, hBox);
        datePicker.setValue(LocalDate.now());
        HomeUI.setCenter(vBox);

        datePicker.setOnAction(e->{
            datePicker.setDisable(true);
            Task<List<PlayPercent>> searchtask = new Task<List<PlayPercent>>() {
                @Override
                protected List<PlayPercent> call() throws Exception {
                    Thread.sleep(200);
                    return  saleSrv.getAllPlayPercentByDate(datePicker.getValue().toString());
                }

                @Override
                protected void running() {
                    vBox.getChildren().removeAll(top,hBox);
                    hBox.getChildren().clear();
                    progressIndicator.setMinSize(100,100);
                    progressIndicator.progressProperty().bind(this.progressProperty());
                    vBox.getChildren().add(progress);
                }

                @Override
                protected void succeeded() {
                    super.succeeded();
                    vBox.getChildren().remove(progress);
                    title.setText(datePicker.getValue().toString()+"排片比");
                    hBox.getChildren().add(new OneDayPlayPercentAnalysis(this.getValue()));
                    vBox.getChildren().addAll(top,hBox);
                    datePicker.setDisable(false);
                    updateMessage("Done!");
                }
            };
            Thread search = new Thread(searchtask);
            search.start();
        });


    }
}
