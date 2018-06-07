package UI.DataDictionary;

import Service.DataDictSrv;
import UI.Layout.HomeUI;
import UI.Studio.StudioAdd;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Data_dict;
import node.FunButton;

import java.util.List;

public class DataDictList {
    private DataDictSrv dataDictSrv = new DataDictSrv();
    private VBox main;
    private HBox top;
    private ScrollPane scrollPane;
    private HBox bottom;
    TextField key;
    private ProgressIndicator progressIndicator = new ProgressIndicator();
    private HBox progress;

    public DataDictList(){
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
        Label note = new Label("名称：");
        key = new TextField();
        FunButton find = new FunButton("查询");
        find.setDefaultButton(true);
        top.getChildren().addAll(note,key,find);

        Task<List<Data_dict>> task = new Task<List<Data_dict>>() {
            @Override
            protected List<Data_dict> call() throws Exception {
                Thread.sleep(200);
                return dataDictSrv.getAllDataDict();
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
                scrollPane.setContent(new DataDictTable(this.getValue()));
                HomeUI.setCenter(main);
                updateMessage("Done!");
            }
        };

        Thread thread = new Thread(task);
        thread.start();

        find.setOnAction(e->{
            find.setDisable(true);

            Task<List<Data_dict>> searchtask = new Task<List<Data_dict>>() {
                @Override
                protected List<Data_dict> call() throws Exception {
                    Thread.sleep(200);
                    return dataDictSrv.searchDataDictByName(key.getText());
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
                    scrollPane.setContent(new DataDictTable(this.getValue()));
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

        //功能按钮框
        bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(20,20,20,20));
        bottom.setSpacing(300);
        FunButton btAdd = new FunButton("添加");
        FunButton btret = new FunButton("返回");
        bottom.getChildren().addAll(btAdd,btret);
        btAdd.setOnAction(e->{
            HomeUI.setCenter(new DataDictAdd());
        });

        btret.setOnAction(e->{
            HomeUI.showNote();
        });

        main.getChildren().addAll(top,scrollPane,bottom);
    }
}
