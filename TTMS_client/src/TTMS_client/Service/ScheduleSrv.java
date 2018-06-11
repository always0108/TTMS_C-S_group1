package Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.Schedule;
import util.Httpclient;

import java.util.List;

public class ScheduleSrv {

    public ScheduleSrv() {
    }

    public List<Schedule> getScheduleByPlayId(Integer play_id) {
        String url = "/schedule/getScheduleByPlayId?play_id="+play_id;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        return JSONToList(jsonObject);
    }

    public List<Schedule> searchScheduleByDate(Integer play_id,String date) {
        String url = "/schedule/getScheduleByPlayIdDate?play_id="+play_id+"&date="+date;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        return JSONToList(jsonObject);
    }

    public List<Schedule> JSONToList(JSONObject jsonObject){
        if (jsonObject.get("flag").equals(true)) {
            List<Schedule> schedules = JSONArray.parseArray(jsonObject.getString("content"), Schedule.class);
            return schedules;
        } else {
            return null;
        }
    }
}
