package UI.Ticket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class QuitTicket extends VBox{
    private HBox hBox;
    private GridPane gridPane;

    public QuitTicket(){
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20,20,20,20));

        //标题
        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text("退票");
        text.getStyleClass().add("funText");
        text.setFont(Font.font(20));
        hBox.getChildren().add(text);

        //表单
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25,25,25,25));

        Label name = new Label("订单号");
        TextField ticket_id = new TextField();

        gridPane.add(name,0,0);
        gridPane.add(ticket_id,1,0);

        Button btok = new Button("确定");
        btok.getStyleClass().add("funButton");
        Button btcan = new Button("取消");
        btcan.getStyleClass().add("funButton");


        gridPane.add(btok,0,1);
        gridPane.add(btcan,1,1);

        this.getChildren().addAll(hBox,gridPane);
    }
}
