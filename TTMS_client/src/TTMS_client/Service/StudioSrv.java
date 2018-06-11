package Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.Studio;
import util.Httpclient;

import java.sql.ResultSet;
import java.util.List;

public class StudioSrv {

    public StudioSrv(){}

    public List<Studio> getAllStudio() {
        return searchStudioByName("");
    }

    public List<Studio> searchStudioByName(String name){
        String url = "/studio/getStudioByPartName?name="+name;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            List<Studio> studios = JSONArray.parseArray(jsonObject.getString("content"), Studio.class);
            return studios;
        }else{
            System.out.println("网络连接失败");
            return null;
            
        }
    }
}
