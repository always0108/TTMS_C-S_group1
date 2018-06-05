package UI.Studio;

import Service.StudioSrv;
import UI.HomeUI;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Studio;
import node.FunButton;

import java.util.List;

public class StudioList{

    private StudioSrv studioSrv = new StudioSrv();
    private List<Studio> studios;
    private VBox main;
    private HBox top;
    private ScrollPane scrollPane;
    private HBox bottom;
    private ProgressIndicator progressIndicator = new ProgressIndicator();
    private HBox progress;

    public StudioList(){
        main = new VBox();
        main.setAlignment(Pos.TOP_CENTER);
        main.setPadding(new Insets(60,20,20,20));
        main.setSpacing(20);

        //进度条
        progress = new HBox();
        progress.setPadding(new Insets(0,50,20,0));
        progress.setSpacing(30);
        progress.setAlignment(Pos.CENTER);

        //搜索框
        top = new HBox();
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(20,20,20,20));
        top.setSpacing(30);
        Label note = new Label("演出厅名称：");
        TextField key = new TextField();
        FunButton find = new FunButton("查询");
        find.setDefaultButton(true);
        top.getChildren().addAll(note,key,find);

        Task<JSONObject> task = new Task<JSONObject>() {
            @Override
            protected JSONObject call() throws Exception {
                studios = studioSrv.getAllStudio();
                Thread.sleep(300);
                return null;
            }

            @Override
            protected void running() {
                progressIndicator.setMinSize(100,100);
                progressIndicator.progressProperty().bind(this.progressProperty());
                progress.getChildren().add(progressIndicator);
                HomeUI.setCenter(progress);
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                scrollPane.setContent(new StudioTable(studios));
                HomeUI.setCenter(main);
                updateMessage("Done!");
            }
        };

        Thread thread = new Thread(task);
        thread.start();

        find.setOnAction(e->{
            find.setDisable(true);

            Task<JSONObject> searchtask = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    studios = studioSrv.searchStudioByName(key.getText());
                    Thread.sleep(300);
                    return null;
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
                    scrollPane.setContent(new StudioTable(studios));
                    main.getChildren().addAll(scrollPane,bottom);
                    find.setDisable(false);
                    updateMessage("Done!");
                }
            };
            Thread search = new Thread(searchtask);
            search.start();
        });

        //搜索结果
        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-opacity: 0.75;-fx-border-style: none;-fx-background-color: bisque");
        scrollPane.setContent(new StudioTable(studioSrv.getAllStudio()));

        //功能按钮框
        bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(20,20,20,20));
        bottom.setSpacing(300);
        FunButton btAdd = new FunButton("添加");
        FunButton btret = new FunButton("返回");
        bottom.getChildren().addAll(btAdd,btret);
        btAdd.setOnAction(e->{
            HomeUI.setCenter(new StudioAdd());
        });

        btret.setOnAction(e->{
            HomeUI.showNote();
        });

        main.getChildren().addAll(top,scrollPane,bottom);
    }
}
