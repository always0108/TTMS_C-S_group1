package UI.Studio;

import Service.SeatSrv;
import UI.Layout.HomeUI;
import UI.Seat.SeatTable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Studio;
import node.FunButton;
import node.MessageBar;

public class StudioDetail extends VBox {

    private HBox hBox;
    private GridPane gridPane;
    private SeatSrv seatSrv = new SeatSrv();

    public StudioDetail() {}

    public StudioDetail(Studio studio){
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20,20,20,20));

        //标题
        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text("修改演出厅");
        text.getStyleClass().add("funText");
        text.setFont(Font.font(20));
        hBox.getChildren().add(text);

        //表单
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label name = new Label("名称：");
        Label studio_name = new Label(studio.getStudio_name());

        Label row = new Label("行数：");
        Label studio_row = new Label(studio.getStudio_row_count().toString());

        Label col = new Label("列数：");
        Label studio_col = new Label(studio.getStudio_col_count().toString());

        Label introduction = new Label("简介：");
        TextArea studio_introduction = new TextArea(studio.getStudio_introduction());

        Label flag = new Label("状态：");
        Label studio_flag = new Label(studio.getStudio_flag().toString());

        Label seat = new Label("座位管理：");
        Button btinit = new Button("初始化");
        Button btset = new Button("设置");
        FunButton btret = new FunButton("返回");

        btinit.setOnAction(e->{
            if(studio.getStudio_flag() != 0){
                MessageBar.showMessageBar("该演出厅已经被初始化了");
            }else {
                if(seatSrv.initSeatsByStudioID(studio.getStudio_id())){
                    MessageBar.showMessageBar("初始化成功");
                }else{
                    MessageBar.showMessageBar("初始化失败");
                }
                new StudioList();
            }
        });

        btset.setOnAction(e->{
            HomeUI.setCenter(new SeatTable(studio.getStudio_id()));
        });

        btret.setOnAction(e->{
            new StudioList();
        });

        gridPane.addColumn(0, name, row, col, introduction,flag);
        gridPane.addColumn(1, studio_name,studio_row,studio_col,studio_introduction,studio_flag);
        gridPane.addRow(5,seat,btinit,btset);
        gridPane.add(btret,1,6);
        this.getChildren().addAll(hBox,gridPane);
    }
}
