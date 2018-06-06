package Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import util.Httpclient;

import java.util.Map;

public class DataDictSrv {

    public Map<String,Integer> getDataDictByParentName(String name){
        String url = "/dataDict/getDataDictByParentName?name="+name;
        String res = Httpclient.get(url);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.get("flag").equals(true)) {
            return (Map<String, Integer>)jsonObject.get("content");
        }else{
            return null;
        }
    }
}
