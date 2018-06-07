package UI.Schedule;

import UI.Layout.HomeUI;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Schedule;
import node.FunButton;

import java.util.List;

public class ScheduleList{

    private VBox main = new VBox();
    private ScheduleSrv scheduleSrv = new ScheduleSrv();
    private ProgressIndicator progressIndicator = new ProgressIndicator();
    private HBox progress;
    private HBox top;
    private HBox bottom;
    private ScrollPane scrollPane;
    private TextField key;

    public ScheduleList(){
        main.setAlignment(Pos.TOP_CENTER);
        main.setSpacing(20);
        main.setPadding(new Insets(60, 20, 20, 20));

        progress = new HBox();
        progress.setPadding(new Insets(0,50,20,0));
        progress.setSpacing(30);
        progress.setAlignment(Pos.CENTER);
        progress.getChildren().add(progressIndicator);

        top = new HBox();
        top.setSpacing(30);
        top.setAlignment(Pos.CENTER);
        Label note = new Label("演出计划ID:");
        key = new TextField();
        FunButton find = new FunButton("查询");
        find.setDefaultButton(true);
        top.getChildren().addAll(note,key,find);

        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-padding: 0;-fx-opacity: 0.75;-fx-background-color: bisque");

        //功能按钮框
        bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(20,20,20,20));
        bottom.setSpacing(300);
        FunButton btAdd = new FunButton("添加");
        FunButton btret = new FunButton("返回");
        bottom.getChildren().addAll(btAdd,btret);

        btAdd.setOnAction(e->{
            HomeUI.setCenter(new ScheduleAdd());
        });
        btret.setOnAction(e->{
            HomeUI.showNote();
        });
        main.getChildren().addAll(top,scrollPane,bottom);


        Task<List<Schedule>> task = new Task<List<Schedule>>() {
            @Override
            protected List<Schedule> call() throws Exception {
                Thread.sleep(200);
                return scheduleSrv.list();
            }

            @Override
            protected void running() {
                progressIndicator.setMinSize(100,100);
                progressIndicator.progressProperty().bind(this.progressProperty());
                HomeUI.setCenter(progress);
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                scrollPane.setContent(new ScheduleTable(this.getValue()));
                HomeUI.setCenter(main);
                updateMessage("Done!");
            }
        };
        Thread thread = new Thread(task);
        thread.start();

        find.setOnAction(e->{
            find.setDisable(true);

            Task<List<Schedule>> searchtask = new Task<List<Schedule>>() {
                @Override
                protected List<Schedule> call() throws Exception {
                    Thread.sleep(200);
                    return  scheduleSrv.searchByName(key.getText());
                }

                @Override
                protected void running() {
                    main.getChildren().removeAll(scrollPane,bottom);
                    progressIndicator.setMinSize(100,100);
                    progressIndicator.progressProperty().bind(this.progressProperty());
                    main.getChildren().add(progress);
                }

                @Override
                protected void succeeded() {
                    super.succeeded();
                    main.getChildren().remove(progress);
                    scrollPane.setContent(new ScheduleTable(this.getValue()));
                    main.getChildren().addAll(scrollPane,bottom);
                    find.setDisable(false);
                    updateMessage("Done!");
                }
            };
            Thread search = new Thread(searchtask);
            search.start();
        });
    }
}