package Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.Seat;
import util.Httpclient;

import java.util.List;

public class SeatSrv {

    public SeatSrv() {}

    public List<Seat> getAllSeatByStudioID(Integer studio_id) {
        String url = "/seat/getStudioSeats?id="+studio_id;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            List<Seat> seats = JSONArray.parseArray(jsonObject.getString("content"), Seat.class);
            return seats;
        }else{
            return null;
        }
    }

    public boolean initSeatsByStudioID(Integer studio_id){
        String url = "/seat/init?id="+studio_id;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            return true;
        }else{
            return false;
        }
    }
}
