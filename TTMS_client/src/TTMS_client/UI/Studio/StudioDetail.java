package UI.Studio;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Studio;

public class StudioDetail extends VBox {

    private HBox hBox;
    private GridPane gridPane;

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

        Label name = new Label("名称:");
        TextField studio_name = new TextField(studio.getStudio_name());

        Label row = new Label("行数:");
        TextField studio_row = new TextField(studio.getStudio_row_count().toString());

        Label col = new Label("列数:");
        TextField studio_col = new TextField(studio.getStudio_col_count().toString());

        Label introduction = new Label("简介:");
        TextArea studio_introduction = new TextArea(studio.getStudio_seat_count().toString());

        Label flag = new Label("状态");
        Label studio_flag = new Label(studio.getStudio_flag().toString());

        gridPane.addColumn(0, name, row, col, introduction,flag);
        gridPane.addColumn(1, studio_name,studio_row,studio_col,studio_introduction,studio_flag);

        Button btok = new Button("确定");
        btok.getStyleClass().add("funButton");
        Button btret = new Button("返回");
        btret.getStyleClass().add("funButton");
    }
}
