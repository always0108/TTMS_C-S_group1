package UI.Seat;

import Service.SeatSrv;
import UI.Studio.StudioList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Seat;
import node.FunButton;
import node.MessageBar;
import util.Httpclient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatTable extends VBox {

    public static Map<Integer,Integer> seats = new HashMap<>();
    private SeatSrv seatSrv = new SeatSrv();
    private HBox top;
    private Text center;
    private GridPane seatsTable;
    private HBox btGroup;
    public SeatTable() {}

    public SeatTable(Integer studio_id){
        List<Seat> seatsData = seatSrv.getAllSeatByStudioID(studio_id);
        this.setPadding(new Insets(20,20,20,20));
        this.setSpacing(20);
        this.setAlignment(Pos.TOP_CENTER);

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
            btok.setDisable(true);
            //根据演出厅id批量更新座位的状态
            //创建后台获取数据的线程
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/seat/updateByStudioId";
                    Map<String, Object> data = new HashMap<>();

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("studio_id",studio_id);
                    jsonObject.put("seats",seats);
                    String jsonStr = JSON.toJSONString(jsonObject);

                    data.put("json",jsonStr);
                    String res = Httpclient.post(url,data);
                    return JSON.parseObject(res);
                }

                @Override
                protected void running() {

                }

                @Override
                protected void succeeded() {
                    super.succeeded();
                    JSONObject jsonObject = getValue();
                    if (jsonObject.get("flag").equals(true)) {
                        MessageBar.showMessageBar("设置成功！");
                    } else {
                        MessageBar.showMessageBar(jsonObject.get("content").toString());
                    }
                    btok.setDisable(false);
                    seats.clear();
                    updateMessage("Done!");
                }

                @Override
                protected void cancelled() {
                    super.cancelled();
                    updateMessage("Cancelled!");
                }

                @Override
                protected void failed() {
                    super.failed();
                    updateMessage("Failed!");
                }
            };
            Thread thread = new Thread(task);
            thread.start();
        });

        btret.setOnAction(e->{
            new StudioList();
        });

        this.getChildren().addAll(center,seatsTable,btGroup);
    }
}
