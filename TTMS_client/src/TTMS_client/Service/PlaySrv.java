package Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.Play;
import util.Httpclient;

import java.util.List;

public class PlaySrv{

    public PlaySrv(){}

    public List<Play> getAllPlay() {
        return searchPlayByName("");
    }

    public List<Play> searchPlayByName(String name){
        String url = "/play/getPlayByPartName?name="+name;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            List<Play> plays = JSONArray.parseArray(jsonObject.getString("content"), Play.class);
            for(Play play:plays){
                play.strToByte();
            }
            return plays;
        }else{
            System.out.println("网络连接失败");
            return null;
        }
    }
}
