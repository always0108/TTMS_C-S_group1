package UI.Seat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Seat;
import node.FunButton;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SeatTable extends VBox {

    public static Map<Integer,Integer> seats = new HashMap<>();
    private HBox top;
    private Text center;
    private GridPane seatsTable;
    private HBox btGroup;
    public SeatTable() {}

    public SeatTable(List<Seat> seatsData){
        this.setPadding(new Insets(20,20,20,20));
        this.setSpacing(20);
        this.setAlignment(Pos.TOP_CENTER);

        //标题
        top = new HBox();
        top.setAlignment(Pos.CENTER_LEFT);
        Text text = new Text("设置座位");
        text.setStyle("-fx-font-size: 40px");
        top.getChildren().add(text);

        center = new Text("屏幕中央");
        center.setFont(Font.font(20));

        //座位图
        seatsTable = new GridPane();
        seatsTable.setAlignment(Pos.CENTER);
        seatsTable.setVgap(10);
        seatsTable.setHgap(10);

        seats.clear();
        for (int i = 0; i < seatsData.size();i++){
            OneSeat temp = new OneSeat(seatsData.get(i));
            seatsTable.add(temp,temp.getCol(),temp.getRow());
        }

        btGroup = new HBox();
        btGroup.setAlignment(Pos.CENTER);
        btGroup.setSpacing(50);
        FunButton btok = new FunButton("确认");
        FunButton btret = new FunButton("返回");

        btGroup.getChildren().addAll(btok,btret);

        btok.setOnAction(e->{
            for(Map.Entry<Integer,Integer> entry:seats.entrySet()){
                System.out.println(entry.getKey()+" : "+entry.getValue());
            }
        });

        btret.setOnAction(e->{

        });

        this.getChildren().addAll(top,center,seatsTable,btGroup);



    }
}
