package UI;


import UI.Seat.SeatTable;
import UI.Studio.StudioAdd;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.scene.layout.Pane;
import model.Seat;
import org.apache.http.impl.client.HttpClients;
import util.Httpclient;

import java.util.List;

public class TestUI {

    public static Pane getTestUI(){
//        List<Seat> seats = null;
//        String url = "/seat/getStudioSeats?id=3";
//        String res = Httpclient.get(url);
//      JSONObject jsonObject = JSON.parseObject(res);
//        if(jsonObject.get("flag").equals(true)) {
//            seats = JSONArray.parseArray(jsonObject.getString("content"), Seat.class);
//        }else{
//            System.out.println("网络连接失败");
//        }
//        return new SeatTable(seats);
        return new StudioAdd();
    }

}
